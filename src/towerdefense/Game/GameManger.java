/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game;

import java.util.Arrays;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import towerdefense.Game.Enemy.Enemy;
import towerdefense.Game.Enemy.EnemyManager;
import towerdefense.Game.Path.Path;
import towerdefense.Game.Path.Vector;
import towerdefense.Game.Tower.Tower;
import towerdefense.Game.Tower.TowerManager;

/**
 *
 * @author Habedi
 */
public class GameManger
{

	private EnemyManager enemyMang;
	private TowerManager towMang;
	private LinkedList<Enemy> enemies;
	private LinkedList<Tower> towers;
	private LinkedList<GameEvent> events;
	private Path path;
	private double staticT;

	public GameManger(double dt)
	{
		this();
		staticT = dt;
	}
	
	public GameManger()
	{
		//TEMP 
		//TODO read from file

		this.staticT = 1;

		enemies = new LinkedList<>();
		towers = new LinkedList<>();
		events = new LinkedList<>();

	}
	@Deprecated
	public void initDEBUG()
	{
		Vector[] w;
		w = new Vector[5];
		w[0] = new Vector(50, 50);
		w[1] = new Vector(200, 50);
		w[2] = new Vector(50, 100);
		w[3] = new Vector(100, 100);
		w[4] = new Vector(300, 400);
		path = new Path(w);
		
		
		enemyMang = new EnemyManager(enemies, path, events);
		towMang = new TowerManager(towers, enemies, events, path);
	}
	@Deprecated
	public void addEnemyAndTower(Enemy[] es,Tower[] ts)
	{
		enemies.addAll(Arrays.asList(es));
		towers.addAll(Arrays.asList(ts));
	}

	public void tick()
	{
		tick(staticT);
	}

	/**
	 * update game by dt milliseconds
	 *
	 * @param dt time in ms
	 */
	public void tick(double dt)
	{
		enemyMang.move(dt);
		towMang.updateEnemiesInSight();
		towMang.cooling(dt);
		towMang.fireAll();
		processEvents();
	}

	public void processEvents()
	{

		GameEvent ev;
		while (!events.isEmpty())
		{
			ev = events.poll();
			switch (ev.getEvent())
			{
				case ENEMY_KILLED:
					processSlaughterHouse(ev);
					break;
				case SHOTS_FIRED:
					processFireRange(ev);
					break;
				case BASE_BREACHED:
					processBreach(ev);
					break;
				default:
					throw new IllegalStateException(
							"[EXP]:UnKnown State For Event Processing");
			}
		}
//		throw new UnsupportedOperationException("Not supported yet.");

	}

	private void processFireRange(GameEvent ev)
	{
		if (ev.getEvent() != EventEnum.SHOTS_FIRED)
			throw new IllegalStateException(
					"got:" + ev.getEvent() + ", expected: " + EventEnum.SHOTS_FIRED);
		enemyMang.TakeDamage(ev.getTargetTower().getDamage(),
				ev.getTargetEnemy());
	}

	private void processSlaughterHouse(GameEvent ev)
	{
		if (ev.getEvent() != EventEnum.ENEMY_KILLED)
			throw new IllegalStateException(
					"got:" + ev.getEvent() + ", expected: " + EventEnum.ENEMY_KILLED);
		System.out.printf("[DEBUG]:enemy killed  \n");
	}

	private void processBreach(GameEvent ev)
	{
		if (ev.getEvent() != EventEnum.BASE_BREACHED)
			throw new IllegalStateException(
					"got:" + ev.getEvent() + ", expected: " + EventEnum.BASE_BREACHED);
		System.out.printf("[DEBUG]: base breached\n");
	}

	public void drawOnCanvas(double xOffset, double yOffset, double scale,
			GraphicsContext gc)
	{
		//TODO
		double x1,y1,x2,y2;
		gc.setGlobalAlpha(1);
		gc.setFill(Color.WHITE);
		gc.fillRect(xOffset, yOffset, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		
		gc.setStroke(Color.BURLYWOOD);
		gc.setGlobalAlpha(.2);
		int n = path.getNoOfWaypoints();
		Vector v1,v2;
		for(int i=0;i<n-1;i++)
		{
			v1 = path.getWaypoint(i);
			v2 = path.getWaypoint(i+1);
			x1 = v1.getX()*1 +xOffset;
			y1 = v1.getY()*1 +yOffset;
			x2 = v1.getX()*1 +xOffset;
			y2 = v1.getY()*1 +yOffset;
			
			gc.strokeLine(x1,y1,x2,y2);
		}
		gc.setGlobalAlpha(.9);
		gc.setFill(Color.DARKSLATEGRAY);
		gc.setStroke(Color.ORANGE);
		enemies.forEach((t) ->
		{
			double x,y,a,b;
			x = t.getPos().getX() + xOffset - 5;
			y = t.getPos().getY() + yOffset - 5;
			gc.fillOval(x, y, 10, 10);
			a = x + t.getSpeed().getX()/t.getSpeed().getLen() * 10;
			b = y + t.getSpeed().getY()/t.getSpeed().getLen() * 10;
			gc.setLineWidth(2);
			gc.strokeLine(x, y, a, b);
		});
		gc.setGlobalAlpha(.9);
		gc.setFill(Color.LIGHTSEAGREEN);
		gc.setStroke(Color.LIME);
		towers.forEach((t) ->
		{
			double x,y,r;
			x = t.getPos().getX() + xOffset - 5;
			y = t.getPos().getY() + yOffset - 5;
			gc.fillOval(x, y, 10, 10);
			r = t.getRange();
			gc.strokeOval(x+5 - r/2, y+5-r/2, r, r);
		});
//		throw new UnsupportedOperationException("Not supported yet.");
		
	}
	
}

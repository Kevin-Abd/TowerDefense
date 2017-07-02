/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game;

import java.util.Arrays;
import java.util.LinkedList;
import javafx.beans.property.DoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import towerdefense.Game.Enemy.Enemy;
import towerdefense.Game.Enemy.EnemyManager;
import towerdefense.Game.Enemy.EnemyTypes;
import towerdefense.Game.Path.Path;
import towerdefense.Game.Path.Vector;
import towerdefense.Game.Tower.Tower;
import towerdefense.Game.Tower.TowerManager;
import towerdefense.Game.Tower.TowerTypes;

/**
 *
 * @author Habedi
 */
public class FieldManger
{

	private EnemyManager enemyMang;
	private TowerManager towMang;
	private LinkedList<Enemy> enemies;
	private LinkedList<Tower> towers;
	private LinkedList<GameEvent> events;
	private Path path;
	private double staticT;
	private int score;
	private Integer money;

	public FieldManger(double dt, Integer m)
	{
		this(m);
		staticT = dt;
	}

	public FieldManger(Integer money)
	{
		//TEMP 
		//TODO read from file

		this.staticT = 10;
		enemies = new LinkedList<>();
		towers = new LinkedList<>();
		events = new LinkedList<>();
		score = 0;
		this.money = money;

	}

	public void initlize(Path p)
	{
		path = p;
		if (p == null)
			throw new NullPointerException("Got null for Path");
		enemyMang = new EnemyManager(enemies, path, events);
		towMang = new TowerManager(towers, enemies, events, path);
	}

	public Integer getMoney()
	{
		return money;
	}

	public void setPath(Path p)
	{
		path = p;
	}

	public void addEnemy(Enemy e)
	{
		System.out.printf("[DEBUG]: Adding enemy:%s \n", e);
		enemyMang.addNewEnemy(e);
//		enemies.add(e);
	}

	public void addTower(Tower t)
	{
		towMang.addTower(t);
//		towers.add(t);
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int s)
	{
		score = s;
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
	public void addEnemyAndTower(Enemy[] es, Tower[] ts)
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
		score += ev.getTargetEnemy().getType().getValue();
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
		double x1, y1, x2, y2;

		gc.setStroke(Color.BURLYWOOD);
		gc.setGlobalAlpha(.4);
		gc.setLineWidth(6);
		int n = path.getNoOfWaypoints();
		Vector v1, v2;
		for (int i = 0; i < n - 1; i++)
		{
			v1 = path.getWaypoint(i);
			v2 = path.getWaypoint(i + 1);
			x1 = v1.getX() + xOffset;
			y1 = v1.getY() + yOffset;
			x2 = v2.getX() + xOffset;
			y2 = v2.getY() + yOffset;

			gc.strokeLine(x1, y1, x2, y2);
		}
		gc.setGlobalAlpha(.9);
		gc.setFill(Color.DARKSLATEGRAY);
		gc.setStroke(Color.ORANGE);
		enemies.forEach((en) ->
		{
			en.getType().draw(gc, en);
		});

		towers.forEach((t) ->
		{
			t.getType().draw(gc, t);
		});

//		DEBUG
		{

		}
		//DEBUG END
	}

}

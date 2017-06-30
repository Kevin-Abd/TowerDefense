/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game.Enemy;

import java.util.Arrays;
import java.util.LinkedList;
import javafx.scene.transform.Rotate;
import towerdefense.Game.EventEnum;
import towerdefense.Game.GameEvent;
import towerdefense.Game.Path.Path;
import towerdefense.Game.Path.Vector;

/**
 *
 * @author Habedi
 */
public class EnemyManager
{

	private LinkedList<Enemy> enemies;
	private Path path;
	private LinkedList<GameEvent> events;

	public EnemyManager(LinkedList<Enemy> enemies, Path path,
			LinkedList<GameEvent> events)
	{
		this.enemies = enemies;
		this.path = path;
		this.events = events;
		System.out.printf("[DEBUG]: intilizing EnemyManager with %s \n",path);
		this.enemies.forEach((t) ->
		{
			this.updateSpeedAndRotaion(t);
		});
	}

	/**
	 * 
	 * @param dt by MilliSeconds 
	 */
	public void move(double dt)
	{

		enemies.forEach((t) ->
		{
//			System.out.printf("[DEBUG]: enemy BEFOR:%s \n",t);
			Vector wayPoint;
			t.pos.moveBy(t.getSpeed(), dt/1000);
			try
			{
				wayPoint = path.getWaypoint(t.getLastWaypoint() + 1);
				if (isNear(t.pos, wayPoint))
					moveOnFromWaypoint(t);
			}
			catch (Exception e)
			{
				System.err.println("Enemy: "+t+"\nalredy at last WP!");
				
			}
//			System.out.printf("[DEBUG]: enemy AFTER:%s \n",t);
		});
	}

	private void kill(Enemy e)
	{
		e.pos = new Vector(-10, -10);
		enemies.remove(e);
		
		//TODO
	}

	public void TakeDamage(double dmg, Enemy e)
	{
		if (e.hitPoint - dmg <= 0)
		{
			events.add(new GameEvent(e, null, EventEnum.ENEMY_KILLED));
			kill(e);
		}
		else
			e.hitPoint -= dmg;
	}

	public boolean isNear(Vector p, Vector v)
	{
		return p.getDistTo(v) < 10;
	}

	private void moveOnFromWaypoint(Enemy e)
	{
		e.lastWaypoint += 1;
//		System.out.println("[DEBUG]: moving from WP enemy: " + e);
		if (e.lastWaypoint == path.getNoOfWaypoints() - 1)
		{
			reachBase(e);
			return;
		}
		updateSpeedAndRotaion(e);

//		Vector v = new Vector(end.getX() - pos.getX(), end.getY() - pos.getY());
//		rt.setAngle(Math.atan(v.getY() / v.getX()) * (180 / Math.PI));
//		double sp = e.speed.getLen();
//		e.getSpeed().setX(v.getX() / v.getLen() * sp);
//		e.getSpeed().setY(v.getY() / v.getLen() * sp);
//		System.err.printf("MOVE ON FROM WP: at %s to\n %s angel of %.1f \n",
//				pos, end, e.rotation.getAngle());
	}

	private void reachBase(Enemy en)
	{
		en.speed = Vector.getVectorZero();
		en.pos = new Vector(-50, -50);
		try
		{
			System.out.println("[debug]new base breach");
			events.add(new GameEvent(en, null, EventEnum.BASE_BREACHED));
			this.kill(en);
			
		}
		catch (Exception ex)
		{
			System.err.println(ex);
		}
		//TODO

	}

	private void updateSpeedAndRotaion(Vector sp, Vector from, Vector to,
			Rotate rt)
	{
		Vector v = new Vector(to.getX() - from.getX(), to.getY() - from.getY());
		rt.setAngle(Math.atan(v.getY() / v.getX()) * (180 / Math.PI));

		double s = sp.getLen();
		sp.setX(v.getX() / v.getLen() * s);
		sp.setY(v.getY() / v.getLen() * s);
//		System.err.printf("[DEBUG]: got dV %s with angel: %.2f \n"+
//				"\tand result speed %s\n",v,rt.getAngle(),sp);
	}

	public void updateSpeedAndRotaion(Enemy e)
	{
//		System.err.printf("[DEBUG]: speed before %s \n",e.getSpeed());
		updateSpeedAndRotaion(e.getSpeed(), e.getPos(), path.getWaypoint(
				e.getLastWaypoint() + 1), e.getRotation());
//		System.err.printf("[DEBUG]: speed after %s \n",e.getSpeed());

//		System.err.printf(
//				"[DEBUG]: 'updateSpeedAndRotaion' from  %s to %s with %.2f\n",
//				e.getPos(),
//				path.getWaypoint(e.getLastWaypoint() + 1),
//				e.getRotation().getAngle());
	}

	public void addNewEnemy(Enemy e)
	{
		if (e == null)
			throw new NullPointerException(
					"[EXP]:got null Enemy (EnemyManager) ");
		if (e.lastWaypoint != 0)
			throw new IllegalArgumentException(
					"[EXP]: no jumping ahead (EnemyManager)");
		if (e.hitPoint <= 0)
			throw new IllegalArgumentException(
					"[EXP]: no zombies allowed (EnemyManager)");
		enemies.add(e);
		updateSpeedAndRotaion(e);
	}
}

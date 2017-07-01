/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game.Tower;

import java.util.LinkedList;
import towerdefense.Game.Enemy.Enemy;
import towerdefense.Game.EventEnum;
import towerdefense.Game.GameEvent;
import towerdefense.Game.Path.Path;
import towerdefense.Game.Path.Vector;

/**
 *
 * @author Habedi
 */
public class TowerManager
{

	protected LinkedList<Tower> towers;
	protected LinkedList<Enemy> enemies;
	protected LinkedList<GameEvent> events;
	protected Path path;

	public TowerManager(LinkedList<Tower> towers, LinkedList<Enemy> enemies,
			LinkedList<GameEvent> events, Path path)
	{
		this.towers = towers;
		this.enemies = enemies;
		this.events = events;
		this.path = path;
	}

	public void updateEnemiesInSight()
	{
		towers.forEach((tow) ->
		{
			try
			{
				tow.enemiesInRange.clear();
				enemies.forEach((en) ->
				{
					if (tow.pos.getDistTo(en.getPos()) < tow.range)
					{
						tow.enemiesInRange.add(en);
//						System.out.printf("[DEBUG]: got enemy in sight %.1f \n"
//								+ "\t %s %s\n",
//								tow.pos.getDistTo(en.getPos()), tow.getPos(),
//								en.getPos());
					}
				});
				updateTarget(tow);
			}
			catch (Exception e)
			{
				System.err.println("[ERR]:Error in Updating enemis in sight:\n"
						+ "Tower: " + tow + "\n" + e);
			}

		});
		//TODO
	}

	public void updateTarget(Tower t)
	{
		t.target = t.enemiesInRange.peek();
		if (t.target == null)
			return;
		t.enemiesInRange.forEach((en) ->
		{
			if (en.getLastWaypoint() > t.target.getLastWaypoint())
			{
				t.target = en;
			}
			if (en.getLastWaypoint() == t.target.getLastWaypoint())
			{
				if (en.getPos().getDistTo(path.getWaypoint(
						en.getLastWaypoint() + 1))
						< t.target.getPos().getDistTo(path.getWaypoint(
								en.getLastWaypoint() + 1)))
					t.target = en;
			}
		});
		updateRotation(t);
//		throw new UnsupportedOperationException("Not supported yet.");

	}

	public void fireAll()
	{
		towers.forEach((t) ->
		{
			if (t.coolDown <= 0 && t.target != null)
			{
				System.out.printf("[DEBUG]: firing \n");

				events.add(new GameEvent(t.target, t, EventEnum.SHOTS_FIRED));
				t.coolDown = t.type.getCoolDown();
			}
			else
			{
//				System.out.printf("[DEBUG]: no good CoolDown:%.1f \n",t.coolDown);
//				System.out.printf("[DEBUG]: no good %.1f %s \n",t.coolDown,t.target);
			}
		});
	}

	public void cooling(double dt)
	{
		towers.forEach((t) ->
		{
			if (t.coolDown > 0)
				t.coolDown -= dt;
			else if (t.coolDown < 0)
				t.coolDown = 0;
		});
	}

	private void updateRotation(Tower t)
	{
		if (t.target != null)
		{
			Vector v = new Vector(
					t.target.getPos().getX() - t.pos.getX(),
					t.target.getPos().getY() - t.pos.getY());
			double rt = Math.atan((v.getY()) / (v.getX())) * (180 / Math.PI);
//			System.out.printf("[DEBUG]: Angle before %.1f \n",rt);
			if (rt < 0)
				rt += 360;
			else if (rt > 360)
				rt -= 360;
//			System.out.printf("[DEBUG]: Angle after %.1f \n", rt);
			
			t.getRotation().setAngle( (v.getX()<0)?(rt+180):(rt) );
		}
	}
	public void addTower(Tower t)
	{
		towers.add(t);
	}
}

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
						System.out.printf("[DEBUG]: got enemy in sight %.1f \n",tow.pos.getDistTo(en.getPos()));
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
				if (en.getPos().getDistTo(path.getWaypoint(en.getLastWaypoint()+1))
						< t.target.getPos().getDistTo(path.getWaypoint(
								en.getLastWaypoint()+1)))
					t.target = en;
			}
		});
//		throw new UnsupportedOperationException("Not supported yet.");
		//TODO
		
	}

	public void fireAll()
	{
		towers.forEach((t) ->
		{
			if (t.coolDown <= 0 && t.target != null)
			{
				System.out.printf("[DEBUG]: firing \n");
				
				events.add(new GameEvent(t.target, t, EventEnum.SHOTS_FIRED));
			}
			else
			{
				System.out.printf("[DEBUG]: no good %.1f %s \n",t.coolDown,t.target);
			}
		});
	}
	public void cooling(double dt)
	{
		towers.forEach((t) ->
		{
			if( t.coolDown > 0)
				t.coolDown-=dt;
		});
	}
}

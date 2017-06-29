/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game.Tower;

import towerdefense.Game.Enemy.Enemy;

import java.util.LinkedList;
import javafx.scene.transform.Rotate;
import towerdefense.Game.Path.Vector;

/**
 *
 * @author Habedi
 */
public abstract class Tower
{
	protected TowerTypes type;
	protected Vector pos;
	protected double range;
	/**
	 * in miliSeconds
	 */
	protected double coolDown;
	protected double damage;
	protected Rotate rotation;
	protected LinkedList<Enemy> enemiesInRange;
	protected Enemy target;

	
	public Tower( Vector pos, double range, double coolDown, double dmg)
	{
		this.pos = pos;
		this.range = range;
		this.coolDown = coolDown;
		this.rotation = new Rotate();
		this.enemiesInRange = new LinkedList<>();
		this.target = null;
		damage = dmg;
	}
	
	public Tower( Vector pos, double range, double coolDown,
			Rotate rotation, LinkedList<Enemy> enemiesInRange, Enemy target)
	{
		this.pos = pos;
		this.range = range;
		this.coolDown = coolDown;
		this.rotation = rotation;
		this.enemiesInRange = enemiesInRange;
		this.target = target;
	}

	public double getDamage()
	{
		return damage;
	}

	public TowerTypes getType()
	{
		return type;
	}

	public Vector getPos()
	{
		return pos;
	}

	public double getRange()
	{
		return range;
	}

	public double getCoolDown()
	{
		return coolDown;
	}

	public Rotate getRotation()
	{
		return rotation;
	}

	public LinkedList<Enemy> getEnemiesInRange()
	{
		return enemiesInRange;
	}

	public Enemy getTarget()
	{
		return target;
	}
	
	
	
	protected void updateTraget(Enemy t)
	{
		target = t;
	}
	
}

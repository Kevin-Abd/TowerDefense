/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game.Enemy;

import towerdefense.Game.Path.Vector;

/**
 *
 * @author Habedi
 */
public enum EnemyTypes
{

	NULL("N/A", 0, Vector.getVectorZero(), 0),
	TEST("test", 1, Vector.getVectorZero(), 0),
	CLASS0("class 0", 55, new Vector(40, 0), 10);
	private final String name;
	private final double hitPoint;
	/**
	 * speed is pixels per  <b>SECOND</b>
	 */
	private final Vector speed;
	private final int value;

	private EnemyTypes(String name, double hitPoint, Vector speed, int v)
	{
		this.name = name;
		this.hitPoint = hitPoint;
		this.speed = speed;
		value = v;
	}

	public String getName()
	{
		return name;
	}

	public double getHitPoint()
	{
		return hitPoint;
	}

	public Vector getSpeed()
	{
		return speed.clone();
	}

}

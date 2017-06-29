/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game.Enemy;

import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import towerdefense.Game.Path.Vector;

/**
 *
 * @author Habedi
 */
public abstract class Enemy
{

	protected double hitPoint;
	protected EnemyTypes type;
	protected Rotate rotation;
	protected int lastWaypoint;
	protected Vector pos;
	protected Vector speed;

	public Enemy()
	{
		lastWaypoint = 0;
		speed = pos = Vector.getVectorZero();
		rotation = null;
		type = EnemyTypes.NULL;
		hitPoint = 0;
	}

	public Enemy(int lastWaypoint, Vector pos, Vector speed, double hp)
	{
		this.lastWaypoint = lastWaypoint;
		this.pos = pos;
		this.speed = speed;
		hitPoint = hp;
		rotation = new Rotate();
	}

	public double getHitPoint()
	{
		return hitPoint;
	}

	public EnemyTypes getType()
	{
		return type;
	}

	public Rotate getRotation()
	{
		return rotation;
	}

	public int getLastWaypoint()
	{
		return lastWaypoint;
	}

	public Vector getPos()
	{
		return pos;
	}

	public Vector getSpeed()
	{
		return speed;
	}

	@Override
	public String toString()
	{
		return "[Enemy: " + type + " pos:" + pos + " speed:" + speed +
				" rotation:" + rotation.getAngle()+" lastWP:"+lastWaypoint+"]";
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game.Enemy;

import javafx.scene.transform.Rotate;
import towerdefense.Game.Path.Vector;

/**
 *
 * @author Habedi
 */
public class EnClass0 extends Enemy
{
	
	public EnClass0(int lastWaypoint, Vector pos, Vector speed, double hp, EnemyTypes type)
	{
		super(lastWaypoint, pos, speed, hp);
		this.type = type;
	}
	public EnClass0(Vector pos, double speed)
	{
		super(0, pos, new Vector(speed, 0), 100);
	}
	/**
	 * Preferred constructor
	 * uses date from EnemyTypes
	 * @param pos 
	 * @see EnemyTypes
	 */
	public EnClass0(Vector pos)
	{
		super(0, pos, EnemyTypes.CLASS0.getSpeed(), EnemyTypes.CLASS0.getHitPoint());
		type = EnemyTypes.CLASS0;
	}
}

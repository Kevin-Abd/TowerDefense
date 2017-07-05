/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game;

import java.util.Arrays;
import towerdefense.Game.Enemy.Enemy;
import towerdefense.Game.Enemy.EnemyTypes;
import towerdefense.Game.Path.Vector;
import towerdefense.Game.Tower.Tower;
import towerdefense.Game.Tower.TowerTypes;


/**
 *
 * @author Habedi
 */
public class Data
{
	public Vector[] wayPoints;
	public Vector[] enemyPos;
	public Vector[] towerPos;
	public EnemyTypes[] EnemyT;
	public TowerTypes[] TowerT;
	public int money;

	public Data()
	{
	}

	public Data(Vector[] wayPoints, Vector[] enemyPos, Vector[] towerPos,
			EnemyTypes[] EnemyT, TowerTypes[] TowerT, int money)
	{
		this.wayPoints = wayPoints;
		this.enemyPos = enemyPos;
		this.towerPos = towerPos;
		this.EnemyT = EnemyT;
		this.TowerT = TowerT;
		this.money = money;
	}

	public Vector[] getWayPoints()
	{
		return wayPoints;
	}

	public Vector[] getEnemyPos()
	{
		return enemyPos;
	}

	public Vector[] getTowerPos()
	{
		return towerPos;
	}

	public EnemyTypes[] getEnemyT()
	{
		return EnemyT;
	}

	public TowerTypes[] getTowerT()
	{
		return TowerT;
	}

	public int getMoney()
	{
		return money;
	}

	@Override
	public String toString()
	{
		String s = "";
		s+= Arrays.toString(EnemyT)+"\n";
		s+=Arrays.toString(enemyPos)+"\n";
		s+=Arrays.toString(TowerT)+"\n";
		s+=Arrays.toString(towerPos)+"\n";
		s+=Arrays.toString(wayPoints)+"\n";
		return s;
	}

	
	
	
}

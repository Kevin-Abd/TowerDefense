/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game;

import towerdefense.Game.Enemy.Enemy;
import towerdefense.Game.Tower.Tower;
/**
 *
 * @author Habedi
 */
public class GameEvent
{
	private final Enemy targetEnemy;
	private final Tower targetTower;
	private final EventEnum event;

	public GameEvent(Enemy targetEnemy, Tower targetTower, EventEnum event)
	{
		this.targetEnemy = targetEnemy;
		this.targetTower = targetTower;
		this.event = event;
	}

	public Enemy getTargetEnemy()
	{
		return targetEnemy;
	}

	public Tower getTargetTower()
	{
		return targetTower;
	}

	public EventEnum getEvent()
	{
		return event;
	}
	
}

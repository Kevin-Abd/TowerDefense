/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game.Tower;

/**
 *
 * @author Habedi
 */
public enum TowerTypes
{
	NULL("N/A",0,0,1,0),
	TEST("test",0,100,1,1),
	CLASS0("class 0",5,100,2,1);
	private final String name;
	private final int value;
	private final double range;
	private final double coolDown;
	private final double damage;
	
	private TowerTypes(String name, int value, double range, double coolDown,double damage)
	{
		this.name = name;
		this.value = value;
		this.range = range;
		this.coolDown = coolDown;
		this.damage = damage;
	}

	public int getValue()
	{
		return value;
	}

	public double getDamage()
	{
		return damage;
	}

	public double getRange()
	{
		return range;
	}

	public double getCoolDown()
	{
		return coolDown;
	}
	
	
	public String getName()
	{
		return name;
	}
}

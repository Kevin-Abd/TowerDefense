/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game;

/**
 *
 * @author Habedi
 */
public class IntNumber
{
	private int value;

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}
	public int add(int a)
	{
		value += a;
		return value;
	}
}

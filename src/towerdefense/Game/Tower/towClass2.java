/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game.Tower;

import towerdefense.Game.Path.Vector;

/**
 *
 * @author Habedi
 */
public class towClass2 extends Tower
{
	public towClass2(Vector pos)
	{
		super(pos, TowerTypes.CLASS2.getRange(), TowerTypes.CLASS2.getCoolDown()
		,TowerTypes.CLASS2.getDamage());
		type = TowerTypes.CLASS2;
	}
}

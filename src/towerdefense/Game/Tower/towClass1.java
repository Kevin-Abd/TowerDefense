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
public class towClass1 extends Tower
{
	public towClass1(Vector pos)
	{
		super(pos, TowerTypes.CLASS1.getRange(), TowerTypes.CLASS1.getCoolDown()
		,TowerTypes.CLASS1.getDamage());
		type = TowerTypes.CLASS1;
	}
}

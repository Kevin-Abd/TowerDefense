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
public class towClass0 extends Tower
{

	public towClass0(Vector pos)
	{
		super(pos, TowerTypes.CLASS0.getRange(), TowerTypes.CLASS0.getCoolDown()
		,TowerTypes.CLASS0.getDamage());
		type = TowerTypes.CLASS0;
	}
	
	
}

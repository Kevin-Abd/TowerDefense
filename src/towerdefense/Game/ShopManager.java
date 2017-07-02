/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import towerdefense.Game.Tower.TowerTypes;

/**
 *
 * @author Habedi
 */
public class ShopManager
{
	private TowerTypes selected;
	private Integer money;

	public ShopManager(Integer money)
	{
		this.money = money;
	}

	
	
	public void drawOnCanvas(GraphicsContext gc)
	{
		gc.setFill(Color.SNOW);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		double r = 15;
		gc.setFill(Color.LIGHTSTEELBLUE);
		gc.fillOval(50 -r , 75-r, 2*r, 2*r);
	}

	public TowerTypes getSelected()
	{
		return selected;
	}

	public void setSelected(TowerTypes selected)
	{
		this.selected = selected;
	}
	
	/**
	 * 
	 * @return 0 for success <br> 
	 * 1 for lack of money <br>
	 * 2 for no selection <br>
	 * TODO <br>
	 * 
	 * -1 for unknown error
	 */
	public int attempToBuy()
	{
		//TODO
		if ( selected == null )
			return 2;
		if( selected.getValue() > money )
			return 1;
		
		return -1;
	}
	
	
}

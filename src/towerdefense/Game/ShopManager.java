/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Blend;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Shadow;
import javafx.scene.paint.Color;
import towerdefense.Game.Tower.TowerTypes;

/**
 *
 * @author Habedi
 */
public class ShopManager
{
	
	private TowerTypes selected;
	private IntNumber money;
	private boolean needsUpdate;
	
	public ShopManager(IntNumber money)
	{
		this.money = money;
		needsUpdate = true;
		selected = TowerTypes.CLASS1;
	}
	
	public void drawOnCanvas(GraphicsContext gc)
	{
//		if (!needsUpdate)	return;
		gc.setGlobalAlpha(1);
		gc.setFill(Color.SNOW);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		
		gc.setFill(Color.BLACK);
		gc.fillText("Money: "+money.getValue(), 0, 10);
		
		double x, y, r, dx;
		y = 75;
		x = 75;
		
		TowerTypes[] tts = TowerTypes.values();
		dx = gc.getCanvas().getWidth() / (tts.length + 4);
		
		for (int i = 2; i < tts.length; i++, x += i * dx)
		{
			r = tts[i].getRadius();
			gc.setFill(tts[i].getColor());
			gc.fillOval(x - r, y - r, 2 * r, 2 * r);
			
			if (selected == tts[i])
			{
				gc.setLineDashes(0);
				gc.setGlobalAlpha(.8);
				gc.setEffect(new Shadow(5, Color.GOLDENROD));
				gc.setLineWidth(4);
				gc.setStroke(Color.GOLD);
				gc.strokeOval(x - (r + 4), y - (r + 4), 2 * (r + 4), 2 * (r + 4));
				gc.setEffect(null);
//				for (double rr = .1; rr < 20; rr += .2)
//				{
//					gc.setGlobalAlpha(1 - (rr / 20) * (rr / 20));
//					gc.strokeOval(x - (r + rr), y - (r + rr), 2 * (r + rr),
//							2 * (r + rr));
//				}
				gc.setGlobalAlpha(1);
			}
			
			r = tts[i].getRange();
			gc.setStroke(Color.MIDNIGHTBLUE);
			gc.setLineWidth(2);
			gc.setLineDashes(2, 10);
//			System.out.printf("[DEBUG]: rad of %d : %.1f \n",i,r);
			gc.strokeOval(x - r, y - r, 2 * r, 2 * r);
			
		}
		
		needsUpdate = false;
	}
	
	public TowerTypes getSelected()
	{
		return selected;
	}
	
	public void setSelected(TowerTypes selected)
	{
		this.selected = selected;
		needsUpdate = true;
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
		if (selected == null)
			return 2;
		else if (selected.getValue() > money.getValue())
			return 1;
		else
		{
			money.add(-selected.getValue());
			needsUpdate = true;
			return 0;
		}
		
//		return -1;
	}
	
}

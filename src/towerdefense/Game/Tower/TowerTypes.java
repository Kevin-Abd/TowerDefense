/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game.Tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import towerdefense.Game.Enemy.Enemy;

/**
 *
 * @author Habedi
 */
public enum TowerTypes
{
	//		name value range coolDown damge radius
	NULL("N/A", 0, 0, 1, 0, 0),
	TEST("test", 0, 100, 500, 1000, 5),
	CLASS0("basic", 5, 60, 1000, 10, 9),
	CLASS1("machine gun", 10, 40, 300, 4, 6),
	CLASS2("sniper", 30, 110, 2000, 40, 16);
	private final String name;
	private final int value;
	/**
	 * in pixels
	 */
	private final double range;
	/**
	 * in MilliSeconds
	 */
	private final double coolDown;
	private final double damage;
	/**
	 * in pixels
	 */
	private final double radius;

	private TowerTypes(
			String name,
			int value,
			double range,
			double coolDown,
			double damage,
			double radius)
	{
		this.name = name;
		this.value = value;
		this.range = range;
		this.coolDown = coolDown;
		this.damage = damage;
		this.radius = radius;
	}

	public void draw(GraphicsContext gc, Tower t)
	{
		double x, y, a, b, c;
		x = t.pos.getX();
		y = t.pos.getY();
		a = Math.cos(
				t.getRotation().getAngle() * Math.PI / 180);
		b = Math.sin(
				t.getRotation().getAngle() * Math.PI / 180);
		c = (t.getCoolDown() / t.getType().getCoolDown()) * 360;

		gc.setGlobalAlpha(1);

		switch (this)
		{
			case CLASS0:
			{
				gc.setFill(Color.MIDNIGHTBLUE);
				gc.setStroke(Color.MISTYROSE.darker());
				gc.setLineWidth( 2 );
			}
			break;
			case CLASS1:
			{
				gc.setFill(Color.FIREBRICK);
				gc.setStroke(Color.CRIMSON.brighter());
				gc.setLineWidth( 2 );
			}
			break;
			case CLASS2:
			{
				gc.setFill(Color.DARKOLIVEGREEN);
				gc.setStroke(Color.PALEGOLDENROD);
				gc.setLineWidth( 3 );
			}
			break;
			case TEST:
			{
				gc.setFill(Color.DIMGRAY);
				gc.setStroke(Color.BLACK);
			}
			break;
			default:
				throw new IllegalStateException(
						"Tower type drawing not supported");
		}

		//Body
		gc.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
		//Barrel
		gc.strokeLine(x, y, x + a * radius, y + b * radius);

		//CoolDown
		gc.setStroke(Color.TOMATO);
		gc.setLineWidth(2);
		gc.strokeArc(x - radius - 2, y - radius - 2, 2 * radius + 4,
				2 * radius + 4, 0, c,
				ArcType.OPEN);

		//Range
		gc.setGlobalAlpha(.6);
		gc.setStroke(Color.SPRINGGREEN);
		gc.setLineWidth(1.3);
		gc.strokeOval(x - range, y - range, 2 * range, 2 * range);

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

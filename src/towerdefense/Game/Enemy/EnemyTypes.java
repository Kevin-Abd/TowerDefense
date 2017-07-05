/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import towerdefense.Game.Path.Vector;

/**
 *
 * @author Habedi
 */
public enum EnemyTypes
{

	NULL("N/A", 0, Vector.getVectorZero(), 0, 0),
	TEST("test", 1, Vector.getVectorZero(), 0, 5),
	CLASS0("basic", 55, new Vector(40, 0), 10, 8),
	CLASS1("runner", 17, new Vector(90, 0), 6, 5),
	CLASS2("heavy", 120, new Vector(20, 0), 20, 17);
	private final String name;
	private final double hitPoint;
	/**
	 * speed is pixels per  <b>SECOND</b>
	 */
	private final Vector speed;
	private final int value;
	/**
	 * in pixels
	 */
	private final double radius;

	private EnemyTypes(String name, double hitPoint, Vector speed, int v,
			double radius)
	{
		this.name = name;
		this.hitPoint = hitPoint;
		this.speed = speed;
		value = v;
		this.radius = radius;
	}

	public void draw(GraphicsContext gc, Enemy e)
	{
		double x, y, a, b, c;
		x = e.pos.getX();
		y = e.pos.getY();
		a = Math.cos(
				e.getRotation().getAngle() * Math.PI / 180);
		b = Math.sin(
				e.getRotation().getAngle() * Math.PI / 180);
		c = (e.hitPoint) / (e.getType().getHitPoint()) * 360;

		gc.setGlobalAlpha(1);

		switch (this)
		{
			case CLASS0:
			{
				gc.setFill(Color.MAROON);
				gc.setStroke(Color.MISTYROSE.darker());
				gc.setLineWidth( 2 );
			}
			break;
			case CLASS1:
			{
				gc.setFill(Color.LIGHTCORAL.brighter());
				gc.setStroke(Color.ORCHID.darker());
				gc.setLineWidth( 2 );
			}
			break;
			case CLASS2:
			{
				gc.setFill(Color.TEAL.darker());
				gc.setStroke(Color.MEDIUMPURPLE);
				gc.setLineWidth( 2 );
			}
			break;
			case TEST:
			{
				gc.setFill(Color.DIMGRAY);
				gc.setStroke(Color.BLACK);
				gc.setLineWidth( 2 );
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
		gc.strokeArc(x - radius - .5, y - radius - .5, 2 * radius + 1,
				2 * radius + 1, 0, c,
				ArcType.OPEN);

	}

	public String getName()
	{
		return name;
	}

	public double getHitPoint()
	{
		return hitPoint;
	}

	public Vector getSpeed()
	{
		return speed.clone();
	}

	public int getValue()
	{
		return value;
	}
}

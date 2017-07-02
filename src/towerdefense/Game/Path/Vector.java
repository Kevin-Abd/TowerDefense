/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game.Path;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Habedi
 */
public class Vector implements Cloneable
{

	private static final Vector ZERO = new Vector(0, 0);
	@Expose() protected double x, y;

	public Vector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector(Vector v)
	{
		x = v.x;
		y = v.y;
	}

	public double getDistTo(Vector p)
	{
		return Math.hypot(x - p.x, y - p.y);
	}

	public double getLen()
	{
		return Math.hypot(x, y);
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	@Override
	public String toString()
	{
		return String.format("(%.2f , %.2f)", x,y);
	}

	public void moveBy(Vector speed, double dt)
	{
		x += speed.x * dt;
		y += speed.y * dt;
	}

	@Override
	public Vector clone()
	{
		return new Vector(x, y);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Vector)
		{
			Vector v = (Vector) obj;
			return (x == v.x) && (y == v.y);
		}
		else
			return false;
	}

	public static Vector getVectorZero()
	{
		return  ZERO.clone();
	}
}

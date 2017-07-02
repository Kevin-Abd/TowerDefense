/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game.Path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Habedi
 */
public class Path
{

	protected ArrayList<Vector> wayPoints;
	protected int noOfWaypoints;

	public Path(Vector[] wps)
	{
		wayPoints =new ArrayList<>();
		for(Vector v:wps)
			wayPoints.add(v.clone());
		noOfWaypoints = wps.length;
	}
	public Path(List wps)
	{
		if( !(wps.get(0) instanceof  Vector))
			throw new IllegalArgumentException("require Vector");
		else
		{
			wayPoints =new ArrayList<>();
			wayPoints.addAll(wps);
			noOfWaypoints = wps.size();
		}
	}
	public int getNoOfWaypoints()
	{
		return noOfWaypoints;
	}

	public Vector getWaypoint(int n)
	{
		if( n>=wayPoints.size() )
			throw new ArrayIndexOutOfBoundsException("request of:"+n+" with size of"+wayPoints.size());
		return wayPoints.get(n);
	}

	private void test()
	{
	}

	@Override
	public String toString()
	{
		String a= "[Path:";
		for(Vector v:wayPoints)
			a+=" "+v;
		a+="]";
		return a;
	}
	
}

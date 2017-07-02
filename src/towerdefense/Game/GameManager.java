/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense.Game;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import javafx.scene.canvas.Canvas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import towerdefense.Game.Enemy.Enemy;
import towerdefense.Game.Enemy.EnemyTypes;
import towerdefense.Game.Path.Path;
import towerdefense.Game.Path.Vector;
import towerdefense.Game.Tower.Tower;
import towerdefense.Game.Tower.TowerTypes;

/**
 *
 * @author Habedi
 */
public class GameManager
{

	private Integer money;
	private FieldManger fieldMg;
	private ShopManager shopMg;
	private Canvas field;
	private Canvas shop;
	private Data data;

	public GameManager(int money,
			Canvas field, Canvas shop)
	{
		this.money = money;
		this.field = field;
		this.shop = shop;
		fieldMg = new FieldManger(money);
		shopMg = new ShopManager(money);
	}

	public void tick(double dt)
	{
		fieldMg.tick(dt);
		fieldMg.drawOnCanvas(0, 0, 0, field.getGraphicsContext2D());
		shopMg.drawOnCanvas(shop.getGraphicsContext2D());
	}

	public void readData(String s)
	{
		clean();
		initilize();
		
		data = new Data();
		try
		{
			FileReader f = new FileReader(s);
			Gson g = new Gson();
			data = g.fromJson(f, data.getClass());
			f.close();
			System.out.printf("[DEBUG]: %s \n",data);
			
			money = data.money;
			fieldMg.initlize(new Path(data.wayPoints));
			
			for (int i = 0; i < data.enemyPos.length; i++)
			{
				fieldMg.addEnemy(new Enemy(data.EnemyT[i], data.enemyPos[i], 0));
			}
			for(int i=0;i<data.towerPos.length;i++)
			{
				fieldMg.addTower(new Tower(data.TowerT[i], data.towerPos[i]));
			}
			

		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		catch (IOException ex)
		{
			Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null,
					ex);
		}

	}

	public void clean()
	{
		data = null;
		fieldMg = null;
		money = 0;
		System.gc();
	}
	public void initilize()
	{
		fieldMg = new FieldManger(money);
	}
	
	public static void main(String[] args)
	{
		System.out.printf("[DEBUG]: writing Gson file \n");

		EnemyTypes[] et = new EnemyTypes[4];
		Vector[] e = new Vector[4];
		e[0] = new Vector(0, 40);
		et[0] = EnemyTypes.CLASS2;
		e[1] = new Vector(40, 40);
		et[1] = EnemyTypes.CLASS1;
		e[2] = new Vector(30, 40);
		et[2] = EnemyTypes.CLASS1;
		e[3] = new Vector(10, 40);
		et[3] = EnemyTypes.CLASS0;

		Vector[] wp = new Vector[5];
		wp[0] = new Vector(40, 40);
		wp[1] = new Vector(400, 40);
		wp[2] = new Vector(300, 100);
		wp[3] = new Vector(20, 100);
		wp[4] = new Vector(20, 250);

		Vector t[] = new Vector[3];
		TowerTypes[] tt = new TowerTypes[3];
		t[0] = new Vector(200, 60);
		tt[0] = TowerTypes.CLASS1;
		t[1] = new Vector(300, 120);
		tt[1] = TowerTypes.CLASS2;
		t[2] = new Vector(25, 200);
		tt[2] = TowerTypes.CLASS0;

		Data d = new Data(wp, e, t, et, tt, 100);

		Gson g = new Gson();


		try
		{
			BufferedWriter bf = new BufferedWriter(new FileWriter("1.level"));
			bf.write(g.toJson(d));
			bf.close();
		}
		catch (IOException ex)
		{
			Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}
}

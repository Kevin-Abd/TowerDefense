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

	private IntNumber money;
	private FieldManger fieldMg;
	private ShopManager shopMg;
	private Canvas field;
	private Canvas shop;
	private Data data;

	public GameManager(
			Canvas field, Canvas shop)
	{
		this.field = field;
		this.shop = shop;
		money = new IntNumber();
//		fieldMg = new FieldManger(money);
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
//			System.out.printf("[DEBUG]: %s \n",data);

			money.setValue(data.money);
			fieldMg.initlize(new Path(data.wayPoints));

			for (int i = 0; i < data.enemyPos.length; i++)
			{
				fieldMg.addEnemy(new Enemy(data.EnemyT[i], data.enemyPos[i], 0));
			}
			for (int i = 0; i < data.towerPos.length; i++)
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
		money.setValue(0);
		System.gc();
	}

	public void initilize()
	{
		fieldMg = new FieldManger(money);
	}

	public void setShopSelection(TowerTypes type)
	{
		shopMg.setSelected(type);
	}

	public int buy(Vector pos)
	{
		if (shopMg.attempToBuy() != 0)
		{
			return -1;
		}
		else
		{
			fieldMg.addTower(new Tower(shopMg.getSelected(), pos));
			return 0;
		}

	}

	public static void main(String[] args)
	{
		System.out.printf("[DEBUG]: writing Gson file \n");

		EnemyTypes[] et = new EnemyTypes[9];
		Vector[] e = new Vector[9];

		e[0] = new Vector(20, 40);
		et[0] = EnemyTypes.CLASS1;
		e[1] = new Vector(40, 40);
		et[1] = EnemyTypes.CLASS1;
		e[2] = new Vector(30, 40);
		et[2] = EnemyTypes.CLASS1;
		e[3] = new Vector(10, 40);
		et[3] = EnemyTypes.CLASS1;

		e[4] = new Vector(-20, 40);
		et[4] = EnemyTypes.CLASS0;
		e[5] = new Vector(-10, 40);
		et[5] = EnemyTypes.CLASS0;
		e[6] = new Vector(-30, 40);
		et[6] = EnemyTypes.CLASS0;

		e[7] = new Vector(-100, 40);
		et[7] = EnemyTypes.CLASS2;
		e[8] = new Vector(-130, 40);
		et[8] = EnemyTypes.CLASS2;
		e[8] = new Vector(-160, 40);
		et[8] = EnemyTypes.CLASS2;

		Vector[] wp = new Vector[5];
		wp[0] = new Vector(40, 40);
		wp[1] = new Vector(400, 40);
		wp[2] = new Vector(300, 100);
		wp[3] = new Vector(20, 100);
		wp[4] = new Vector(20, 250);

		Vector t[] = new Vector[1];
		TowerTypes[] tt = new TowerTypes[1];
		t[0] = new Vector(25, 200);
		tt[0] = TowerTypes.CLASS0;

		Data d = new Data(wp, e, t, et, tt, 100);

		Gson g = new Gson();

		try
		{
			BufferedWriter bf = new BufferedWriter(new FileWriter("3.level"));
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

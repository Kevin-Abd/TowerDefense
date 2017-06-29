/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import towerdefense.Game.Enemy.EnClass0;
import towerdefense.Game.Enemy.Enemy;
import towerdefense.Game.Enemy.EnemyManager;
import towerdefense.Game.GameManger;
import towerdefense.Game.Path.Path;
import towerdefense.Game.Path.Vector;
import towerdefense.Game.Tower.Tower;
import towerdefense.Game.Tower.towClass0;

/**
 *
 * @author Habedi
 */
public class TowerDefense extends Application
{

	static Canvas can;

	@Override
	public void start(Stage stage) throws Exception
	{

//		Parent root = FXMLLoader.load(
//				getClass().getResource("Debug.fxml"));
		Group root = new Group();
		Scene scene = new Scene(root);
		can = new Canvas(400, 400);
		root.getChildren().add(can);
		GraphicsContext gc = can.getGraphicsContext2D();
		stage.setScene(scene);
		stage.show();
		//DEBUG
		Enemy[] es = new Enemy[2];
		es[0] = new EnClass0(new Vector(50, 0));
		es[1] = new EnClass0(new Vector(0, 0));
		Tower[] tows = new Tower[1];
		tows[0] = new towClass0(new Vector(50, 50));
		GameManger gm = new GameManger(2e-2);
		gm.addEnemyAndTower(es, tows);
		gm.initDEBUG();

		final long startNanoTime = System.nanoTime();

		new AnimationTimer()
		{
			@Override
			public void handle(long currentNanoTime)
			{
				double t = (currentNanoTime - startNanoTime) / 1000000000.0;
				
				gm.tick(2e-2);
				gm.drawOnCanvas(5, 5, 1, gc);
			}
		}.start();

		//DEBUG END
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import towerdefense.Game.Enemy.Enemy;
import towerdefense.Game.Enemy.EnemyManager;
import towerdefense.Game.Enemy.EnemyTypes;
import towerdefense.Game.FieldManger;
import towerdefense.Game.GameManager;
import towerdefense.Game.Path.Path;
import towerdefense.Game.Path.Vector;
import towerdefense.Game.ShopManager;
import towerdefense.Game.Tower.Tower;
import towerdefense.Game.Tower.TowerTypes;

/**
 *
 * @author Habedi
 */
public class TowerDefense extends Application
{

	static Canvas fieldCanvas;
	static Canvas shopCanvas;

	@Override
	public void start(Stage stage) throws Exception
	{

		Parent root = FXMLLoader.load(
				getClass().getResource("Debug.fxml"));
		((StackPane) root).setBackground(new Background(new BackgroundFill(
				Color.KHAKI,
				CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(root, 700, 500);
//		System.out.println(root);
//		System.out.println(root.getChildrenUnmodifiable().get(0));
		fieldCanvas = (Canvas) root.getChildrenUnmodifiable().get(0);
		shopCanvas = (Canvas) root.getChildrenUnmodifiable().get(1);

		GraphicsContext fieldGC = fieldCanvas.getGraphicsContext2D();
		GraphicsContext shopGC = shopCanvas.getGraphicsContext2D();
		stage.setScene(scene);
		stage.show();
		//DEBUG
		GameManager gm= new GameManager(100, fieldCanvas, shopCanvas);
		gm.readData("1.level");

		final long[] startNanoTime = new long[1];
		startNanoTime[0] = System.nanoTime();

		new AnimationTimer()
		{
			@Override
			public void handle(long currentNanoTime)
			{

				double dt = (currentNanoTime - startNanoTime[0]) / 1e9;
				dt *= 1e3;
				startNanoTime[0] = currentNanoTime;
//				System.out.println("[time] :"+t);
				fieldGC.setGlobalAlpha(1);

				
				gm.tick(dt);
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

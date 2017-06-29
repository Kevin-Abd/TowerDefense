/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense;

import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
/**
 *
 * @author Habedi
 */
public class Debug implements Initializable
{
	@FXML
	Canvas can;

	
	public Canvas getCan()
	{
		return can;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
	}
}

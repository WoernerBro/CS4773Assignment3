package app;

import java.awt.Toolkit;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import planet.detail.Planet;
import planet.detail.PlanetBuilder;
import planet.detail.PlanetController;

public class AppMain extends Application {
	public AppMain() {
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

	//FXML startup method
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Planet planet = PlanetBuilder.makeEmptyPlanet();
		PlanetController controller = new PlanetController(planet);

		FXMLLoader loader = new FXMLLoader(controller.getClass().getResource("PlanetView.fxml"));
		loader.setController(controller);
		
		
		Pane pane = (Pane) loader.load();

		Scene scene = new Scene(pane, 590, 400);
		primaryStage.setTitle("CS 4773 Assignment 3");
		primaryStage.getIcons().add(new Image("https://www.iconexperience.com/_img/o_collection_png/green_dark_grey/512x512/plain/planet.png"));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
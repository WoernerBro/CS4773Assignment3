package planet.detail;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PlanetController {

    @FXML
    private ImageView planetImage;

    @FXML
    private Button selectImageButton;

    @FXML
    private TextField planetName;

    @FXML
    private TextField planetDiameterKM;

    @FXML
    private TextField planetDiameterM;

    @FXML
    private TextField planetMeanSurfaceTempC;

    @FXML
    private TextField planetMeanSurfaceTempF;

    @FXML
    private TextField planetNumberOfMoons;

    @FXML
    private Label fancyPlanetName;
    
    private Planet planet;

    public PlanetController(Planet planet) {
    	this.planet = planet;
    }
    
    @FXML
    void selectImage(ActionEvent event) {
    	try {
    		
    	} catch(GatewayException e) {
    		System.err.println(e.getMessage());
    	}
    }

    @FXML
    void loadPlanet(ActionEvent event) {
    	try {
    		
    	} catch(GatewayException e) {
    		System.err.println(e.getMessage());
    	}
    }
    
    @FXML
    void savePlanet(ActionEvent event) {
    	try {
    		planet.save();
    	} catch(GatewayException e) {
    		System.err.println(e.getMessage());
    	}
    }
}
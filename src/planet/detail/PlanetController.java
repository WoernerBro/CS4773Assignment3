package planet.detail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class PlanetController implements Initializable {
	
	FileChooser fileChooser = new FileChooser();

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	planetDiameterM.setEditable(false);
    	planetMeanSurfaceTempF.setEditable(false);
    	planetImage.setImage(new Image("file:images/no_image.png"));
        planet.setPlanetImage("file:images/no_image.png");
    	planetName.setText("");
    	planet.setPlanetName("");
		planetDiameterKM.setText("");
		planet.setPlanetDiameterKM(-1);
		planetMeanSurfaceTempC.setText("");
		planet.setPlanetMeanSurfaceTempC(-300);
		planetNumberOfMoons.setText("");
		planet.setPlanetNumberOfMoons(-1);
	}
    
    @FXML
    void selectImage(ActionEvent event) {
    	 FileChooser fileChooser = new FileChooser();
    	 FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
         fileChooser.getExtensionFilters().add(extFilterPNG);
         File file = fileChooser.showOpenDialog(null);
                    
         try {
             BufferedImage bufferedImage = ImageIO.read(file);
             Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             planetImage.setImage(image);
             planet.setPlanetImage("file:images/"+file.getName());
             System.out.println(planet.getPlanetImage());
    	} catch(IOException e) {
    		System.err.println(e.getMessage());
    	}
    }

    @FXML
    void loadPlanet(ActionEvent event) {
    	try {
    		planet = planet.load();
    		planet.setPlanetImage(planet.getPlanetImage());
    		planetName.setText(planet.getPlanetName());
			planetDiameterKM.setText(String.valueOf(planet.getPlanetDiameterKM()));
			planetMeanSurfaceTempC.setText(String.valueOf(planet.getPlanetMeanSurfaceTempC()));
			planetNumberOfMoons.setText(String.valueOf(planet.getPlanetNumberOfMoons()));
    	} catch(GatewayException e) {
    		System.err.println(e.getMessage());
    	}
    }
    
    @FXML
    void savePlanet(ActionEvent event) {
    	try {
    		planet.save(planet);
    	} catch(GatewayException e) {
    		System.err.println(e.getMessage());
    	}
    }
}
package planet.detail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;

import mvc.InvalidPersonException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class PlanetController {
	
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
    	planetDiameterM.setEditable(false);
    	planetMeanSurfaceTempF.setEditable(false);
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
             planet.setPlanetImage(file.toString());
    	} catch(IOException e) {
    		System.err.println(e.getMessage());
    	}
    }

    @FXML
    void loadPlanet(ActionEvent event) {
    	try {
    		planet = planet.load();
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
    		planet.save();
    	} catch(GatewayException e) {
    		System.err.println(e.getMessage());
    	}
    }
    
    private class PlanetNameChangeListener implements ChangeListener<String> {
		private boolean skipLabelMessage = false;

		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			try {
				planet.setPlanetName(newValue);
				if(!skipLabelMessage)
					planetNameMessage.setText("");
				skipLabelMessage = false;
			} catch(InvalidPersonException e) {
				//System.err.println(e.getMessage());
				firstNameMessage.setText(e.getMessage());
				skipLabelMessage = true;
				//reset firstName to last good value
				//Note this calls this changed event with the oldValue
				//which is why we need bypass switch to avoid immediately clearing the error message
				firstName.setText(oldValue);
			}
		}
    	
    }
}
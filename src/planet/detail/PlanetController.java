package planet.detail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    	planetImage.setImage(new Image("file:images/no_image.png"));
        planet.setPlanetImage("file:images/no_image.png");
        
    	planetName.setText("");
    	planet.setPlanetName("");
    	
		planetDiameterKM.setText("");
		planet.setPlanetDiameterKM(-1);
    	planetDiameterM.setEditable(false);
    	
		planetMeanSurfaceTempC.setText("");
		planet.setPlanetMeanSurfaceTempC(-300);
    	planetMeanSurfaceTempF.setEditable(false);
    	
		planetNumberOfMoons.setText("");
		planet.setPlanetNumberOfMoons(-1);
		
		FocusChangeListeners();
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
    	} catch(IOException e) {
    		System.err.println(e.getMessage());
    	}
    }

    @FXML
    void savePlanet(ActionEvent event) {
    	try {
    		if(!planet.isValidPlanetName(planetName.getText())) {
    			System.out.println("Name: "+planetName.getText());
    			planetName.setText("");
    			fancyPlanetName.setText("");
    			throw new InvalidPlanetException("Planet name is invalid");
    		}
    		if(!planet.isValidPlanetDiameterKM(Float.valueOf(planetDiameterKM.getText()))) {
    			planetDiameterKM.setText("");
    			planetDiameterM.setText("");
    			throw new InvalidPlanetException("Planet diameter(KM) is invalid");
    		}
    		if(!planet.isValidPlanetMeanSurfaceTempC(Float.valueOf(planetMeanSurfaceTempC.getText()))) {
    			planetMeanSurfaceTempC.setText("");
    			planetMeanSurfaceTempF.setText("");
    			throw new InvalidPlanetException("Planet mean surface temperature(C) is invalid");
    		}
    		if(!planet.isValidPlanetNumberOfMoons(Integer.valueOf(planetNumberOfMoons.getText()))) {
    			planetNumberOfMoons.setText("");
    			throw new InvalidPlanetException("Planet number of moons is invalid");
    		}
    		
    		planet.setPlanetName(planetName.getText());
    		planet.setPlanetDiameterKM(Float.valueOf(planetDiameterKM.getText()));
    		planet.setPlanetDiameterM(Float.valueOf(planetDiameterM.getText()));
    		planet.setPlanetMeanSurfaceTempC(Float.valueOf(planetMeanSurfaceTempC.getText()));
    		planet.setPlanetMeanSurfaceTempF(Float.valueOf(planetMeanSurfaceTempF.getText()));
    		planet.setPlanetNumberOfMoons(Integer.valueOf(planetNumberOfMoons.getText()));
    		planet.setFancyPlanetName(fancyPlanetName.getText());
    		planet.save();
    	} catch(GatewayException e) {
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
			planetDiameterM.setText(String.valueOf(planet.getPlanetDiameterM()));
			planetMeanSurfaceTempC.setText(String.valueOf(planet.getPlanetMeanSurfaceTempC()));
			planetMeanSurfaceTempF.setText(String.valueOf(planet.getPlanetMeanSurfaceTempF()));
			planetNumberOfMoons.setText(String.valueOf(planet.getPlanetNumberOfMoons()));
    		fancyPlanetName.setText(planet.getFancyPlanetName());
    	} catch(GatewayException e) {
    		System.err.println(e.getMessage());
    	}
    }
    
   void FocusChangeListeners() {
    	planetName.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		        if (!newPropertyValue)
		        	fancyPlanetName.setText(planetName.getText());
		    }
		});
    	
    	planetDiameterKM.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		        if (!newPropertyValue)
		        	planetDiameterM.setText(String.valueOf(Float.valueOf(planetDiameterKM.getText())/8f*5f));
		    }
		});
    	
    	planetMeanSurfaceTempC.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		        if (!newPropertyValue)
		        	planetMeanSurfaceTempF.setText(String.valueOf(Float.valueOf(planetMeanSurfaceTempC.getText())*(9f/5f)+32));
		    }
		});
    }
}
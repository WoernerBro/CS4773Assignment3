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
    private TextField planetName, planetDiameterKM, planetDiameterM, planetMeanSurfaceTempC,
    				  planetMeanSurfaceTempF, planetNumberOfMoons;

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
    		validatePlanetName();
    		validatePlanetDiameter();
    		validatePlanetSurfaceTemp();
    		validatePlanetNumberOfMoons();
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
    
    void validatePlanetName() {
    	if(!planet.isValidPlanetName(planetName.getText())) {
			planetName.setText("Invalid");
			fancyPlanetName.setText("");
			throw new InvalidPlanetException("Planet name is invalid");
		}
    }
    
    void validatePlanetDiameter() {
    	if(!planet.isValidPlanetDiameterKM(Float.valueOf(planetDiameterKM.getText()))) {
			planetDiameterKM.setText("Invalid");
			planetDiameterM.setText("");
			throw new InvalidPlanetException("Planet diameter(KM) is invalid");
		}
    }
    
    void validatePlanetSurfaceTemp() {
    	if(!planet.isValidPlanetMeanSurfaceTempC(Float.valueOf(planetMeanSurfaceTempC.getText()))) {
			planetMeanSurfaceTempC.setText("Invalid");
			planetMeanSurfaceTempF.setText("");
			throw new InvalidPlanetException("Planet mean surface temperature(C) is invalid");
		}
    }
    
    void validatePlanetNumberOfMoons() {
		if(!planet.isValidPlanetNumberOfMoons(Integer.valueOf(planetNumberOfMoons.getText()))) {
			planetNumberOfMoons.setText("Invalid");
			throw new InvalidPlanetException("Planet number of moons is invalid");
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
    	planetNameChangeListener();
    	planetDiameterChangeListener();
    	planetSurfaceTempChangeListener();
    	planetNumberOfMoonsChangeListener();
    }
    
    void planetNameChangeListener() {
    	planetName.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		        if (!newPropertyValue) {
		        	if(!PlanetValidator.validatePlanetName(planetName.getText())){
		        		System.out.println("Invalid planet name");
		        		planetName.setText("Invalid");
		        		fancyPlanetName.setText("");
		        	} else
		        		fancyPlanetName.setText(planetName.getText());
		        } else if (planetName.getText().equals("Invalid"))
		        	planetName.setText("");
		    }
		});
	}
	   
	void planetDiameterChangeListener() {
		planetDiameterKM.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		        if (!newPropertyValue) {
		        	if(!PlanetValidator.validateDiameter(Float.parseFloat(planetDiameterKM.getText()))){
		        		System.out.println("Invalid diameter");
		        		planetDiameterKM.setText("Invalid");
		        		planetDiameterM.setText("");
		        	} else
		        		planetDiameterM.setText(String.valueOf(Float.valueOf(planetDiameterKM.getText())/8f*5f));
		        } else if (planetDiameterKM.getText().equals("Invalid"))
		        	planetDiameterKM.setText("");
		    }
		});
	}
	   
	void planetSurfaceTempChangeListener() {
		planetMeanSurfaceTempC.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		       if (!newPropertyValue) {
		        	if(!PlanetValidator.validateSurfaceTemperature(Float.parseFloat(planetMeanSurfaceTempC.getText()))){
		        		System.out.println("Invalid Surface Temp");
		        		planetMeanSurfaceTempC.setText("Invalid");
		        		planetMeanSurfaceTempF.setText("");
		        	} else
		        		planetMeanSurfaceTempF.setText(String.valueOf(Float.valueOf(planetMeanSurfaceTempC.getText())*(9f/5f)+32));
		        } else if (planetMeanSurfaceTempC.getText().equals("Invalid"))
		        	planetMeanSurfaceTempC.setText("");
		    }
		});
	}
	   
	void planetNumberOfMoonsChangeListener() {
		planetNumberOfMoons.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		    	if(!newPropertyValue) {
		    		if(!PlanetValidator.validateNumberOfMoons((int) Float.parseFloat(planetNumberOfMoons.getText()))){
		        		System.out.println("Invalid Number of Moons");
		        		planetNumberOfMoons.setText("Invalid");
		        	}
		    	} else if (newPropertyValue && planetNumberOfMoons.getText().equals("Invalid"))
		    		planetNumberOfMoons.setText("");
		    }
		});
	}
}
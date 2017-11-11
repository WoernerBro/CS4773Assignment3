package planet.detail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

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
    public FileChooser fileChooser;

    public PlanetController(Planet planet) {
    	this.planet = planet;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fileChooser = new FileChooser();
		
    	planetImage.setImage(new Image("file:images/no_image.png"));
    	planetName.setText("");
		planetDiameterKM.setText("");
		planetMeanSurfaceTempC.setText("");
		planetNumberOfMoons.setText("");
		
		PlanetListener.planetNameChangeListener(planetName, fancyPlanetName);
		PlanetListener.planetDiameterChangeListener(planetDiameterKM, planetDiameterM);
		PlanetListener.planetSurfaceTempChangeListener(planetMeanSurfaceTempC, planetMeanSurfaceTempF);
		PlanetListener.planetNumberOfMoonsChangeListener(planetNumberOfMoons);
		
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
             planet.setPlanetImage("file:images/"+file.getName());
    	} catch(IOException e) {
    		System.err.println(e.getMessage());
    	}
    }
    
    @FXML
    void loadPlanet(ActionEvent event) {
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	int response = JOptionPane.showConfirmDialog(null,
    					"Do you really want to load new values into fields?",
    					"Confirm Loading New Planet",
    					JOptionPane.YES_NO_OPTION,
    					JOptionPane.QUESTION_MESSAGE);
    	if (response == JOptionPane.YES_OPTION)
    		startLoad();
    }
    
    void startLoad() {
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

    @FXML
    void savePlanet(ActionEvent event) {
    	try {
    		validatePlanetName();
    		validatePlanetDiameter();
    		validatePlanetSurfaceTemp();
    		validatePlanetNumberOfMoons();
    		planet = PlanetBuilder.updatePlanet(planet, planetName.getText(), Float.parseFloat(planetDiameterKM.getText()), 
    				Float.parseFloat(planetDiameterM.getText()), Float.parseFloat(planetMeanSurfaceTempC.getText()), 
    				Float.parseFloat(planetMeanSurfaceTempF.getText()), (int) Float.parseFloat(planetNumberOfMoons.getText()), fancyPlanetName.getText());
    		System.out.println(planet.toString());
    		planet.save();
    	} catch(GatewayException e) {
    		System.err.println(e.getMessage());
    	}
    }
    
    void validatePlanetName() {
    	if(!PlanetValidator.validatePlanetName(planetName.getText())) {
			planetName.setText("Invalid");
			fancyPlanetName.setText("");
			throw new InvalidPlanetException("Planet name is invalid");
		}
    }
    
    void validatePlanetDiameter() {
    	if(!PlanetValidator.validateDiameter(Float.valueOf(planetDiameterKM.getText()))) {
			planetDiameterKM.setText("Invalid");
			planetDiameterM.setText("");
			throw new InvalidPlanetException("Planet diameter(KM) is invalid");
		}
    }
    
    void validatePlanetSurfaceTemp() {
    	if(!PlanetValidator.validateSurfaceTemperature(Float.valueOf(planetMeanSurfaceTempC.getText()))) {
			planetMeanSurfaceTempC.setText("Invalid");
			planetMeanSurfaceTempF.setText("");
			throw new InvalidPlanetException("Planet mean surface temperature(C) is invalid");
		}
    }
    
    void validatePlanetNumberOfMoons() {
		if(!PlanetValidator.validateNumberOfMoons(Integer.valueOf(planetNumberOfMoons.getText()))) {
			planetNumberOfMoons.setText("Invalid");
			throw new InvalidPlanetException("Planet number of moons is invalid");
		}
    }
}
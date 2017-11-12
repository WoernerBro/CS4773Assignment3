package planet.detail;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PlanetListener {
    public static void planetNameChangeListener(TextField planetName, Label fancyPlanetName) {
    	planetName.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		    	ListenerHandler.handleName(newPropertyValue, planetName, fancyPlanetName );
		      /** if (!newPropertyValue) {
		        	if(!PlanetValidator.validatePlanetName(planetName.getText())){
		        		System.out.println("Invalid planet name");
		        		planetName.setText("Invalid");
		        		fancyPlanetName.setText("");
		        	} else
		        		fancyPlanetName.setText(planetName.getText());
		        } else if (planetName.getText().equals("Invalid"))
		        	planetName.setText("");**/
		    }
		});
	}
	   
    public static void planetDiameterChangeListener(TextField planetDiameterKM, TextField planetDiameterM) {
		planetDiameterKM.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		    	ListenerHandler.handleDiameter(newPropertyValue, planetDiameterKM, planetDiameterM);
		       /** if (!newPropertyValue) {
		        	if(!PlanetValidator.validateDiameter(planetDiameterKM.getText())){
		        		System.out.println("Invalid diameter");
		        		planetDiameterKM.setText("Invalid");
		        		planetDiameterM.setText("");
		        	} else {
		        		planetDiameterKM.setText(String.format("%,.2f", Float.parseFloat(planetDiameterKM.getText().replace(",", ""))));
		        		planetDiameterM.setText(String.format("%,.2f", Float.parseFloat(planetDiameterKM.getText().replace(",", ""))/8f*5f));
		        	}
		        } else if (planetDiameterKM.getText().equals("Invalid"))
		        	planetDiameterKM.setText(""); **/
		    }
		});
	}
	   
    public static void planetSurfaceTempChangeListener(TextField planetMeanSurfaceTempC, TextField planetMeanSurfaceTempF) {
		planetMeanSurfaceTempC.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		    ListenerHandler.handleSurfaceTemperature(newPropertyValue, planetMeanSurfaceTempC, planetMeanSurfaceTempF);
			  /**     
		       if (!newPropertyValue) {
		        	if(!PlanetValidator.validateSurfaceTemperature(planetMeanSurfaceTempC.getText())){
		        		System.out.println("Invalid Surface Temp");
		        		planetMeanSurfaceTempC.setText("Invalid");
		        		planetMeanSurfaceTempF.setText("");
		        	} else {
		        		planetMeanSurfaceTempF.setText(String.format("%,.2f", Float.parseFloat(planetMeanSurfaceTempC.getText())));
		        		planetMeanSurfaceTempF.setText(String.format("%,.2f", Float.parseFloat(planetMeanSurfaceTempC.getText())*(9f/5f)+32));
		        	}
		        } else if (planetMeanSurfaceTempC.getText().equals("Invalid"))
		        	planetMeanSurfaceTempC.setText("");**/
		    }
		});
	}
	   
    public static void planetNumberOfMoonsChangeListener(TextField planetNumberOfMoons) {
		planetNumberOfMoons.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		    	 ListenerHandler.handleNumberOfMoons( newPropertyValue, planetNumberOfMoons);
		 /**   	if(!newPropertyValue) {
	    			if(!PlanetValidator.validateNumberOfMoons(planetNumberOfMoons.getText())){
		    			System.out.println("Invalid Number of Moons");
		        		planetNumberOfMoons.setText("Invalid");
		        	} else
		        		planetNumberOfMoons.setText(String.format("%,d", Integer.parseInt(planetNumberOfMoons.getText().replace(",", ""))));
		    	} else if (newPropertyValue && planetNumberOfMoons.getText().equals("Invalid"))
		    		planetNumberOfMoons.setText("");**/
		    }
		});
	}
}

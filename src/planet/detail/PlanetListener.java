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
		    }
		});
	}
	   
    public static void planetDiameterChangeListener(TextField planetDiameterKM, TextField planetDiameterM) {
		planetDiameterKM.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		    	ListenerHandler.handleDiameter(newPropertyValue, planetDiameterKM, planetDiameterM);
		    }
		});
	}
	   
    public static void planetSurfaceTempChangeListener(TextField planetMeanSurfaceTempC, TextField planetMeanSurfaceTempF) {
		planetMeanSurfaceTempC.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		    	ListenerHandler.handleSurfaceTemperature(newPropertyValue, planetMeanSurfaceTempC, planetMeanSurfaceTempF);
		    }
		});
	}
	   
    public static void planetNumberOfMoonsChangeListener(TextField planetNumberOfMoons) {
		planetNumberOfMoons.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
		    	 ListenerHandler.handleNumberOfMoons( newPropertyValue, planetNumberOfMoons);
		    }
		});
	}
}

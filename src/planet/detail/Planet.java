package planet.detail;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.FloatProperty;

public class Planet {
	private SimpleStringProperty planetImage;
	private SimpleStringProperty planetName;
	private SimpleFloatProperty planetDiameterKM;
	private SimpleFloatProperty planetDiameterM;
	private SimpleFloatProperty planetMeanSurfaceTempC;
	private SimpleFloatProperty planetMeanSurfaceTempF;
	private SimpleIntegerProperty planetNumberOfMoons;
	private SimpleStringProperty fancyPlanetName;
	
	
	private PlanetGateway gateway;
	
	public Planet(String planetImage, String planetName, 
					float planetDiameterKM, float planetDiameterM, 
					float planetMeanSurfaceTempC, float planetMeanSurfaceTempF, 
					int planetNumberOfMoons, String fancyPlanetName) throws InvalidPlanetException {
		try {
			this.planetImage = new SimpleStringProperty();
			this.planetName = new SimpleStringProperty();
			this.planetDiameterKM = new SimpleFloatProperty();
			this.planetDiameterM = new SimpleFloatProperty();
			this.planetMeanSurfaceTempC = new SimpleFloatProperty();
			this.planetMeanSurfaceTempF = new SimpleFloatProperty();
			this.planetNumberOfMoons = new SimpleIntegerProperty();
			this.fancyPlanetName = new SimpleStringProperty();
			
			setPlanetImage(planetImage);
			setPlanetName(planetName);
			setPlanetDiameterKM(planetDiameterKM);
			setPlanetDiameterM(planetDiameterKM);
			setPlanetMeanSurfaceTempC(planetMeanSurfaceTempC);
			setPlanetMeanSurfaceTempF(planetMeanSurfaceTempC);
			setPlanetNumberOfMoons(planetNumberOfMoons);
			setFancyPlanetName(planetName);
		} catch(InvalidPlanetException e) {
			throw new InvalidPlanetException(e);
		}
	}

	//implement biz logic
	
	public void eat() {
		//eat
	}
	
	public void sleep() {
		//sleep
	}
	
	public void work() {
		//work
	}
	
	//delegate complexity
	
	public void save() throws GatewayException {
		try {
			gateway.save(this);
		} catch(GatewayException e) {
			throw new GatewayException(e);
		}
	}

	//validators

	public boolean isValidPlanetName(String testPlanetName) {
		Pattern validCharacters = Pattern.compile("[^A-Za-z0-9 .-]");
		Matcher validPlanetName = validCharacters.matcher(testPlanetName);
		
		if(testPlanetName.length() < 1 || testPlanetName.length() > 255)
			return false;
		if(!validPlanetName.find())
			return false;
		return true;
	}

	public boolean isValidPlanetDiameterKM(float testPlanetDiameterKM) {
		if(testPlanetDiameterKM < 0.0 || testPlanetDiameterKM > 200000.0)
			return false;
		return true;
	}
	
	public boolean isValidPlanetMeanSurfaceTempC(float testPlanetMeanSurfaceTempC) {
		if(testPlanetMeanSurfaceTempC < -273.15 || testPlanetMeanSurfaceTempC > 500.0)
			return false;
		return true;
	}
	
	public boolean isValidPlanetNumberOfMoons(int testPlanetNumberOfMoons) {
		if(testPlanetNumberOfMoons < 0 || testPlanetNumberOfMoons > 1000)
			return false;
		return true;
	}
	
	//accessors
	
	public String getPlanetImage() {
		return planetImage.getValue();
	}

	public void setPlanetImage(String planetImage) {
		this.planetImage.setValue(planetImage);
	}

	public StringProperty planetImageProperty() {
		return planetName;
	}
	
	public String getPlanetName() {
		return planetName.getValue();
	}

	public void setPlanetName(String planetName) {
		if(!isValidPlanetName(planetName))
			throw new InvalidPlanetException("Planet name is invalid");
		
		this.planetName.setValue(planetName);
		setFancyPlanetName(getPlanetName());
	}

	public StringProperty planetNameProperty() {
		return planetName;
	}
	
	public float getPlanetDiameterKM() {
		return planetDiameterKM.getValue();
	}

	public void setPlanetDiameterKM(float planetDiameterKM) {
		if(!isValidPlanetDiameterKM(planetDiameterKM))
			throw new InvalidPlanetException("Planet diameter(KM) is invalid");
		
		this.planetDiameterKM.setValue(planetDiameterKM);
		setPlanetDiameterM(getPlanetDiameterKM());
	}

	public FloatProperty planetDiameterKMProperty() {
		return planetDiameterKM;
	}
	
	public float getPlanetDiameterM() {
		return planetDiameterM.getValue();
	}

	public void setPlanetDiameterM(float planetDiameterKM) {
		this.planetDiameterM.setValue(planetDiameterKM*1000.0);
	}

	public FloatProperty planetDiameterMProperty() {
		return planetDiameterM;
	}
	
	public float getPlanetMeanSurfaceTempC() {
		return planetMeanSurfaceTempC.getValue();
	}

	public void setPlanetMeanSurfaceTempC(float planetMeanSurfaceTempC) {
		if(!isValidPlanetMeanSurfaceTempC(planetMeanSurfaceTempC))
			throw new InvalidPlanetException("Planet mean surface tempuratue(C) is invalid");
		
		this.planetMeanSurfaceTempC.setValue(planetMeanSurfaceTempC);
		setPlanetMeanSurfaceTempF(getPlanetMeanSurfaceTempC());
	}

	public FloatProperty planetMeanSurfaceTempC() {
		return planetMeanSurfaceTempC;
	}
	
	public float getPlanetMeanSurfaceTempF() {
		return planetMeanSurfaceTempF.getValue();
	}

	public void setPlanetMeanSurfaceTempF(float planetMeanSurfaceTempC) {
		this.planetMeanSurfaceTempF.setValue(planetMeanSurfaceTempC*(9/5)+32.0);
	}

	public FloatProperty planetMeanSurfaceTempF() {
		return planetMeanSurfaceTempF;
	}
	
	public int getPlanetNumberOfMoons() {
		return planetNumberOfMoons.getValue();
	}

	public void setPlanetNumberOfMoons(int planetNumberOfMoons) {
		if(!isValidPlanetNumberOfMoons(planetNumberOfMoons))
			throw new InvalidPlanetException("Planet number of moons is invalid");
		
		this.planetNumberOfMoons.setValue(planetNumberOfMoons);
	}

	public IntegerProperty planetNumberOfMoons() {
		return planetNumberOfMoons;
	}
	
	public String getFancyPlanetName() {
		return fancyPlanetName.getValue();
	}

	public void setFancyPlanetName(String planetName) {
		this.fancyPlanetName.setValue(planetName);
	}

	public StringProperty fancyPlanetNameProperty() {
		return fancyPlanetName;
	}

	public PlanetGateway getGateway() {
		return gateway;
	}

	public void setGateway(PlanetGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public String toString() {
		return getPlanetImage() +"\n"+
		getPlanetName() +"\n"+
		getPlanetDiameterKM() +"\n"+
		getPlanetDiameterM() +"\n"+
		getPlanetMeanSurfaceTempC() +"\n"+
		getPlanetMeanSurfaceTempF() +"\n"+
		getPlanetNumberOfMoons() +"\n"+
		getFancyPlanetName();
	}
}
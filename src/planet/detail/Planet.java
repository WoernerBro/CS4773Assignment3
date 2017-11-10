package planet.detail;

public class Planet {
	private String planetImage;
	private String planetName;
	private float planetDiameterKM;
	private float planetDiameterM;
	private float planetMeanSurfaceTempC;
	private float planetMeanSurfaceTempF;
	private int planetNumberOfMoons;
	private String fancyPlanetName;
	
	private PlanetGateway gateway;
	
	public Planet() {
		try {
			setPlanetImage("no_image.png");
	    	setPlanetName("");
			setPlanetDiameterKM(-1);
			setPlanetDiameterM(-1);
			setPlanetMeanSurfaceTempC(-300);
			setPlanetMeanSurfaceTempF(-530);
			setPlanetNumberOfMoons(-1);
			setFancyPlanetName("");
		} catch(InvalidPlanetException e) {
			throw new InvalidPlanetException(e);
		}
	}
	
	public Planet(Planet planet) throws InvalidPlanetException {
		try {
			setPlanetImage(planet.planetImage);
			setPlanetName(planet.planetName);
			setPlanetDiameterKM(planet.planetDiameterKM);
			setPlanetDiameterM(planet.planetDiameterM);
			setPlanetMeanSurfaceTempC(planet.planetMeanSurfaceTempC);
			setPlanetMeanSurfaceTempC(planet.planetMeanSurfaceTempF);
			setPlanetNumberOfMoons(planet.planetNumberOfMoons);
			setFancyPlanetName(planet.fancyPlanetName);
		} catch(InvalidPlanetException e) {
			throw new InvalidPlanetException(e);
		}
	}
	
	//delegate complexity
	
	public void save() throws GatewayException {
		try {
			gateway.save(this);
		} catch(GatewayException e) {
			throw new GatewayException(e);
		}
	}
	
	public Planet load() throws GatewayException {
		try {
			return gateway.load();
		} catch(GatewayException e) {
			throw new GatewayException(e);
		}
	}

	//validators

	public boolean isValidPlanetName(String testPlanetName) {
		return PlanetValidator.validatePlanetName(testPlanetName);
	}

	public boolean isValidPlanetDiameterKM(float testPlanetDiameterKM) {
		return PlanetValidator.validateDiameter(testPlanetDiameterKM);
	}
	
	public boolean isValidPlanetMeanSurfaceTempC(float testPlanetMeanSurfaceTempC) {
		return PlanetValidator.validateSurfaceTemperature(testPlanetMeanSurfaceTempC);
	}
	
	public boolean isValidPlanetNumberOfMoons(int testPlanetNumberOfMoons) {
		return PlanetValidator.validateNumberOfMoons(testPlanetNumberOfMoons);
	}
	
	//accessors
	
	public String getPlanetImage() {
		return planetImage;
	}

	public void setPlanetImage(String planetImage) {
		this.planetImage = planetImage;
	}
	
	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}
	
	public float getPlanetDiameterKM() {
		return planetDiameterKM;
	}

	public void setPlanetDiameterKM(float planetDiameterKM) {
		this.planetDiameterKM = planetDiameterKM;
	}
	
	public float getPlanetDiameterM() {
		return planetDiameterM;
	}

	public void setPlanetDiameterM(float planetDiameterKM) {
		this.planetDiameterM = planetDiameterKM;
	}
	
	public float getPlanetMeanSurfaceTempC() {
		return planetMeanSurfaceTempC;
	}

	public void setPlanetMeanSurfaceTempC(float planetMeanSurfaceTempC) {
		this.planetMeanSurfaceTempC = planetMeanSurfaceTempC;
	}
	
	public float getPlanetMeanSurfaceTempF() {
		return planetMeanSurfaceTempF;
	}

	public void setPlanetMeanSurfaceTempF(float planetMeanSurfaceTempF) {
		this.planetMeanSurfaceTempF = planetMeanSurfaceTempF;
	}
	
	public int getPlanetNumberOfMoons() {
		return planetNumberOfMoons;
	}

	public void setPlanetNumberOfMoons(int planetNumberOfMoons) {
		this.planetNumberOfMoons = planetNumberOfMoons;
	}
	
	public String getFancyPlanetName() {
		return fancyPlanetName;
	}

	public void setFancyPlanetName(String fancyPlanetName) {
		this.fancyPlanetName = fancyPlanetName;
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
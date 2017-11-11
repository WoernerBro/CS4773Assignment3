package planet.detail;

public class PlanetBuilder {

	public static Planet makeEmptyPlanet() {
		return new Planet();
	}
	
	public static Planet updatePlanet(Planet planet, String planetName, float planetDiameterKM
			,float planetDiameterM, float planetMeanSurfaceTempC, float planetMeanSurfaceTempF
			,int planetNumberOfMoons, String fancyName) {
		
		planet.setPlanetName(planetName);
		planet.setPlanetDiameterKM(planetDiameterKM);
		planet.setPlanetDiameterM(Float.valueOf(planetDiameterM));
		planet.setPlanetMeanSurfaceTempC(Float.valueOf(planetMeanSurfaceTempC));
		planet.setPlanetMeanSurfaceTempF(planetMeanSurfaceTempF);
		planet.setPlanetNumberOfMoons(planetNumberOfMoons);
		planet.setFancyPlanetName(fancyName);
				
		return planet; 
	}
}

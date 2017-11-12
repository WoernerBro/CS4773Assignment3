package planet.detail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlanetValidator {
	public static boolean validatePlanetName(String testPlanetName) {
		Pattern validCharacters = Pattern.compile("[^A-Za-z0-9 .-]");
		Matcher invalidPlanetName = validCharacters.matcher(testPlanetName);
		
		if(testPlanetName.length() < 1 || testPlanetName.length() > 255)
			return false;
		if(invalidPlanetName.find())
			return false;
		return true;
	}

	public static boolean validateDiameter(String testPlanetDiameterKM) {
		testPlanetDiameterKM = testPlanetDiameterKM.replaceAll(",","");
		Float planetDiameterKM = Float.valueOf(testPlanetDiameterKM);
		if(planetDiameterKM <= 0.00 || planetDiameterKM > 200000.00)
			return false;
		return true;
	}
	
	public static boolean validateSurfaceTemperature(String testPlanetMeanSurfaceTempC) {
		Float planetMeanSurfaceTempC = Float.valueOf(testPlanetMeanSurfaceTempC);
		if(planetMeanSurfaceTempC < -273.15 || planetMeanSurfaceTempC > 500.00)
			return false;
		return true;
	}
	
	public static boolean validateNumberOfMoons(String testPlanetNumberOfMoons) {
		testPlanetNumberOfMoons = testPlanetNumberOfMoons.replaceAll(",","");
		int planetNumberOfMoons = Integer.valueOf(testPlanetNumberOfMoons);
		if(planetNumberOfMoons < 0 || planetNumberOfMoons > 1000)
			return false;
		return true;
	}
}

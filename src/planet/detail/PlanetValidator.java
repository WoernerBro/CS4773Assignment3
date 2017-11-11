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

	public static boolean validateDiameter(float testPlanetDiameterKM) {
		if(testPlanetDiameterKM <= 0 || testPlanetDiameterKM > 200000)
			return false;
		return true;
	}
	
	public static boolean validateSurfaceTemperature(float testPlanetMeanSurfaceTempC) {
		if(testPlanetMeanSurfaceTempC < -273.15 || testPlanetMeanSurfaceTempC > 500.0)
			return false;
		return true;
	}
	
	public static boolean validateNumberOfMoons(int testPlanetNumberOfMoons) {
		if(testPlanetNumberOfMoons < 0 || testPlanetNumberOfMoons > 1000)
			return false;
		return true;
	}
}

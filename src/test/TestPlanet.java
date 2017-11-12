package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import planet.detail.*;

import org.junit.Test;

public class TestPlanet {
	
	private static String expectedOutput;
	private static Planet planet = new Planet();
	
	public String getExpected(String fileName)  throws Exception {
		StringBuffer fileContents = new StringBuffer();
		Scanner fileInput = new Scanner(new File(fileName));
		while(fileInput.hasNextLine())
			fileContents.append(fileInput.nextLine() + "\n");
		expectedOutput = fileContents.toString();
		fileInput.close();
		return expectedOutput;
	}
	
	@Test
	public void testCase1() throws Exception {		
		assertEquals(PlanetValidator.validatePlanetName("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"),
				false);
	}
	
	@Test
	public void testCase2() throws Exception {
		assertEquals(PlanetValidator.validatePlanetName("Ke$ha"), false);
	}
	
	@Test
	public void testCase3() throws Exception {
		assertEquals(PlanetValidator.validateNumberOfMoons("-1"), false);
	}
	
	@Test
	public void testCase4() throws Exception {
		assertEquals(PlanetValidator.validateNumberOfMoons("1,001"), false);
	}
	
	@Test
	public void testCase5() throws Exception {
		assertEquals(PlanetValidator.validateDiameter("-1"), false);
	}
	
	@Test
	public void testCase6() throws Exception {
		assertEquals(PlanetValidator.validateDiameter("200,001"), false);
	}
	
	@Test
	public void testCase7() throws Exception {
		assertEquals(PlanetValidator.validateSurfaceTemperature("-274"), false);
	}
	
	@Test
	public void testCase8() throws Exception {
		assertEquals(PlanetValidator.validateSurfaceTemperature("501"), false);
	}
	
	@Test
	public void testCase9() throws Exception {
		planet.setPlanetName("Test10");
		planet.setPlanetDiameterKM(42000);
		planet.setPlanetDiameterM(26250);
		planet.setPlanetMeanSurfaceTempC(30);
		planet.setPlanetMeanSurfaceTempF(86);
		planet.setPlanetNumberOfMoons(30);
		planet.setFancyPlanetName("Test10");
		
		try {
			planet.save();
		} catch (GatewayException saveException) {
			System.err.println(saveException.getMessage());
		}
		
		assertEquals(getExpected("Test10.txt"), planet.toString() + "\n");
	}
	
	@Test
	public void testCase10() throws Exception {
		assertEquals(planet.toString(), load().toString());
	}
	
	public Planet load() throws GatewayException {
        Planet planet = null;
		System.out.println("Loading ...");
		
		//	The only change was forcing "file" to open "Earf.txt" by removing
		//	the code which would open a file browser to choose it manually
        File file = new File ("Test10.txt");
        
        try {
        	planet = readFile(file);
        } catch (FileNotFoundException loadException) {
        	System.err.println(loadException.getMessage());
        }
		
		System.out.println(planet.getPlanetName() + " loaded!");
		return planet;
	}
	
	public Planet readFile(File file) throws FileNotFoundException {
		Scanner textReader = new Scanner(file);
		
		Planet planet = new Planet();
		planet.setPlanetImage(textReader.nextLine());
		planet.setPlanetName(textReader.nextLine());
		planet.setPlanetDiameterKM(textReader.nextFloat());
		planet.setPlanetDiameterM(textReader.nextFloat());
		planet.setPlanetMeanSurfaceTempC(textReader.nextFloat());
		planet.setPlanetMeanSurfaceTempF(textReader.nextFloat());
		planet.setPlanetNumberOfMoons(textReader.nextInt());
		textReader.nextLine();
		planet.setFancyPlanetName(textReader.nextLine());
		
		textReader.close();
		
		return planet;
	}
}
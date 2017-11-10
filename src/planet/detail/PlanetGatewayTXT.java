package planet.detail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.stage.FileChooser;

import java.io.IOException;

public class PlanetGatewayTXT extends PlanetGateway {

	private String filePath;
	
	@Override
	public void save(Planet planet) throws GatewayException {
		System.out.println("Saving " + planet.getPlanetName() + "...");
		filePath = planet.getPlanetName() + ".txt";
		
		try {
			writeFile(planet);
		} catch (IOException writeException) {
			System.err.println(writeException.getMessage());
		}
		
		System.out.println(planet.getPlanetName() + " saved!");
	}
	
	public void writeFile(Planet planet) throws IOException {
		FileWriter writer = new FileWriter(filePath);
		PrintWriter printer = new PrintWriter(writer);
		printer.print(planet.toString());
		printer.close();
		writer.close();
	}
	
	@Override
	public Planet load() throws GatewayException {
        Planet planet = null;
		System.out.println("Loading ...");
		
		FileChooser fileChooser = new FileChooser();
   	 	FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.TXT");
        fileChooser.getExtensionFilters().add(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        
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

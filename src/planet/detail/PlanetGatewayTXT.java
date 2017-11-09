package planet.detail;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class PlanetGatewayTXT extends PlanetGateway {

	private String filePath;
	
	@Override
	public void save(Planet planet) throws GatewayException {
		filePath = planet.getPlanetName() + ".txt";
		try {
			writeFile(planet);
		} catch (IOException writeException) {
			System.err.print(writeException);
		}
		System.out.println("Saving " + planet);
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
		
		//Browse for and read from .txt file of saved planet
		
		System.out.println("Loading " + planet);
		
		return planet;
	}

}

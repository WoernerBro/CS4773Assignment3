package planet.detail;

public class PlanetGatewayTXT extends PlanetGateway {

	@Override
	public void save(Planet planet) throws GatewayException {
		//Create .txt file with saved planet

		System.out.println("Saving " + planet);
	}
	
//	@Override
//	public Planet load() throws GatewayException {
//		Planet planet = null;
//		
//		//Browse for and read from .txt file of saved planet
//		
//		System.out.println("Loading " + planet);
//		
//		return planet;
//	}

}

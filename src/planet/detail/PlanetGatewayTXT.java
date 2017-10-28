package planet.detail;

public class PlanetGatewayTXT extends PlanetGateway {

	@Override
	public void save(Planet planet) throws GatewayException {
		//Create .txt file with saved planet

		System.out.println("Saving " + planet);
	}

}

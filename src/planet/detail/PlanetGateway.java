package planet.detail;

public abstract class PlanetGateway {
	public abstract void save(Planet planet) throws GatewayException;
	public abstract Planet load() throws GatewayException;
}

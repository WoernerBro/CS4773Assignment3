package planet.detail;

public class GatewayException extends RuntimeException {

	public GatewayException(RuntimeException e) {
		super(e);
	}
	
	public GatewayException(String message) {
		super(message);
	}
}

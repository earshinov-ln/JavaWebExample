package name.earshinov.WebExample;

public class HandlingException extends Exception {

	private static final long serialVersionUID = 1L;

	public HandlingException() {
	}

	public HandlingException(String message) {
		super(message);
	}

	public HandlingException(Throwable cause) {
		super(cause);
	}

	public HandlingException(String message, Throwable cause) {
		super(message, cause);
	}

}

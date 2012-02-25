package usr.erichschroeter.applib.calculator;

/**
 * An <code>OperationException</code> is thrown when an {@link Operation} cannot
 * perform its logic due to an error.
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public class OperationException extends Exception {

	public OperationException() {
		super();
	}

	public OperationException(String msg) {
		super(msg);
	}

	public OperationException(Throwable throwable) {
		super(throwable);
	}

	public OperationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}

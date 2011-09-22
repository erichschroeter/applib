package org.javamvc;

/**
 * A <code>NotSupportedException</code> should be used in exceptional cases
 * where something needs to be supported but is not. An example is provided
 * below
 * 
 * <pre>
 * 
 * public boolean isMaximized() {
 * 	if (getApplicationWindow() instanceof JFrame) {
 * 		return (((JFrame) getApplicationWindow()).getExtendedState() &amp; JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH;
 * 	} else {
 * 		throw new NotSupportedException();
 * 	}
 * }
 * 
 * </pre>
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public class NotSupportedException extends Exception {

	public NotSupportedException() {
		super();
	}

	public NotSupportedException(String msg) {
		super(msg);
	}

	public NotSupportedException(Throwable throwable) {
		super(throwable);
	}

	public NotSupportedException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}

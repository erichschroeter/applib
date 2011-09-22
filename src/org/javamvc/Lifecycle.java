package org.javamvc;

/**
 * The <code>Lifecycle enum</code> provides the possible lifecycle events that
 * may occur in an application.
 * 
 * @author Erich Schroeter
 */
public enum Lifecycle {
	/** Occurs when the application is first starting up. */
	STARTING("STARTING"),
	/** Occurs when the application has been started. */
	STARTED("STARTED"),
	/** Occurs when the application begins shutting down. */
	STOPPING("STOPPING"),
	/** Occurs when the application has shut down. */
	STOPPED("STOPPED");

	private final String string;

	private Lifecycle(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}
}

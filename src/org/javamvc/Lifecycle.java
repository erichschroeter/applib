package org.javamvc;

/**
 * The <code>Lifecycle enum</code> provides the possible lifecycle events that
 * may occur in an application.
 * 
 * @author Erich Schroeter
 */
public enum Lifecycle {
	/** Occurs when the application is first starting up. */
	STARTING,
	/** Occurs when the application has been started. */
	STARTED,
	/** Occurs when the application begins shutting down. */
	STOPPING,
	/** Occurs when the application has shut down. */
	STOPPED
}

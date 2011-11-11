package org.javamvc;

/**
 * The <code>LifecycleListener</code> interface provides methods for updating an
 * object listening for lifecycle events in an application.
 * 
 * @author Erich Schroeter
 */
public interface LifecycleListener {

	/**
	 * Handles performing logic when the <code>LifecycleEvent</code> occurs.
	 * 
	 * @param e
	 *            the lifecycle event
	 */
	public void lifecycleChanged(LifecycleEvent e);
}

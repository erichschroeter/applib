package org.javamvc.view;

/**
 * The <code>ViewManagerListener</code> interface provides methods for listeners
 * wanting to be notified of view management events.
 * 
 * @author Erich Schroeter
 */
public interface ViewManagerListener {

	/**
	 * Performs the logic when a <code>ViewManagedEvent</code> is fired.
	 * 
	 * @param e
	 *            the event fired
	 */
	public void wasManaged(ViewManagedEvent e);
}

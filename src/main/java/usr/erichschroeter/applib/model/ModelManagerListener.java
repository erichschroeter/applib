package usr.erichschroeter.applib.model;

/**
 * The <code>ModelManagerListener</code> interface provides methods for
 * listeners wanting to be notified of model management events.
 * 
 * @author Erich Schroeter
 */
public interface ModelManagerListener {

	/**
	 * Performs the logic when a <code>ModelManagedEvent</code> is fired.
	 * 
	 * @param e
	 *            the event fired
	 */
	public void wasManaged(ModelManagedEvent e);
}

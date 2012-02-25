package usr.erichschroeter.applib;

import javax.swing.AbstractAction;

/**
 * A <code>GUIApplicationAction</code> is a an action that contains a reference
 * to the {@link GUIApplicationImpl}.
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public abstract class GUIApplicationAction extends AbstractAction {

	/** The application this action is used in. */
	private GUIApplicationImpl<?> application;

	/**
	 * Constructs a <code>GUIApplicationAction</code> specifying the application
	 * this action is used in.
	 * 
	 * @param application
	 *            the application
	 */
	public GUIApplicationAction(GUIApplicationImpl<?> application) {
		setApplication(application);
	}

	/**
	 * Returns the application this action is used in.
	 * 
	 * @return the application
	 */
	public GUIApplicationImpl<?> getApplication() {
		return application;
	}

	/**
	 * Sets the application this action is used in.
	 * 
	 * @param application
	 *            the application
	 */
	public void setApplication(GUIApplicationImpl<?> application) {
		this.application = application;
	}

}

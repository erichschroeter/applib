package org.javamvc;

import javax.swing.JPanel;

/**
 * A <code>View</code>
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public class View extends JPanel {

	/** A reference to the GUI application. */
	private GUIApplication application;

	/**
	 * Constructs a <code>View</code>. This initializes the view by calling
	 * {@link #initializeView())}.
	 */
	public View(GUIApplication app) {
		super();
		setApplication(app);
		initializeView();
	}

	/**
	 * Initializes the view. This is where you should customize the view itself
	 * and add components to the view. This can be overridden in derived classes
	 * to customize the view further. Make sure to call
	 * <code>super.initializeView()</code> to ensure the default initialization
	 * values are kept.
	 */
	protected void initializeView() {
	}

	/**
	 * Returns the reference to the GUI application this view belongs to.
	 * 
	 * @return the GUI application
	 */
	public GUIApplication getApplication() {
		return application;
	}

	/**
	 * Returns the reference to the GUI application this view belongs to.
	 * 
	 * @param application
	 *            the GUI application
	 */
	public void setApplication(GUIApplication application) {
		this.application = application;
	}

}

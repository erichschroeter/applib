package org.javamvc;

import java.awt.Container;

import javax.swing.JFrame;

import org.javamvc.view.ViewManager;

/**
 * A <code>MVCDesktopApplication</code> is a {@link GUIApplication} which
 * implements the <em>Model-View-Controller (MVC)</em> framework. This class
 * serves as the <em>Controller</em> and provides methods for managing views and
 * models.
 * 
 * @author Erich Schroeter
 */
public abstract class MVCDesktopApplication extends GUIApplication {

	/** The object managing the application's views. */
	protected ViewManager viewManager;

	/**
	 * Constructs a default <code>MVCDesktopApplication</code>.
	 * <p>
	 * This is equivalent to <code>MVCDesktopApplication(new JFrame())</code>.
	 * 
	 * @see #MVCDesktopApplication(Container)
	 */
	public MVCDesktopApplication() {
		this(new JFrame());
	}

	/**
	 * Constructs a <code>MVCDesktopApplication</code> specifying the type of
	 * <code>Container</code> to use for the main application window.
	 * <p>
	 * This is equivalent to <code>super(applicationWindow)</code>.
	 * 
	 * @see GUIApplication#GUIApplication(Container)
	 * @param applicationWindow
	 *            the object to use as the main application window
	 */
	public MVCDesktopApplication(Container applicationWindow) {
		super(applicationWindow); // initialize preferences and window
		setViewManager(new ViewManager());
		installViews();
	}

	/**
	 * Installs the views to be used by the application.
	 */
	protected abstract void installViews();

	/**
	 * Returns the object managing the application's views.
	 * <p>
	 * This is provided publicly so classes can register to listen to manager
	 * events. It is recommended to not use the mutator methods in the
	 * <code>ViewManager</code> directly and instead use the methods provied by
	 * {@link MVCDesktopApplication}.
	 * 
	 * @return the view manager
	 */
	public ViewManager getViewManager() {
		return viewManager;
	}

	/**
	 * Sets the object to manage the application's views.
	 * 
	 * @param viewManager
	 *            the view manager to set
	 */
	protected void setViewManager(ViewManager viewManager) {
		this.viewManager = viewManager;
	}

}

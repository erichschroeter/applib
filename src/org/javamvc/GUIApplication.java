package org.javamvc;

import java.awt.Container;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * A <code>GUIApplication</code> is a desktop application which provides methods
 * for initializing a <em>Graphical User Interface (GUI)</em>.
 * 
 * @author Erich Schroeter
 */
public abstract class GUIApplication extends DesktopApplication {

	/** The reference to the application window. */
	protected Container applicationWindow;

	/**
	 * Constructs a default <code>GUIApplication</code>.
	 * <p>
	 * <em><b>Note:</b> the application window will return
	 * <code>null</code> until you specify it with 
	 * {@link #setApplicationWindow(Container)}.</em>
	 */
	public GUIApplication() {
	}

	/**
	 * Constructs a <code>GUIApplication</code> specifying the type of
	 * <code>Container</code> to use for the main application window.
	 * <p>
	 * The <code>applicationWindow</code> is what will be returned by the
	 * {@link #getApplicationWindow()}.
	 * 
	 * @param applicationWindow
	 *            the object to use as the main application window
	 */
	public GUIApplication(Container applicationWindow) {
		setApplicationWindow(applicationWindow);
	}

	/**
	 * Returns the main application window.
	 * <p>
	 * The application window is returned as a <code>Container</code> object in
	 * order to give users the flexibility of using the several choices
	 * available, such as {@link JFrame}, {@link Window}, or some third party
	 * solution that extends <code>Container</code>.
	 * 
	 * @return the application window
	 */
	public Container getApplicationWindow() {
		return applicationWindow;
	}

	/**
	 * Sets the object to be used as the main application window.
	 * 
	 * @param applicationWindow
	 *            the main application window
	 */
	protected void setApplicationWindow(Container applicationWindow) {
		this.applicationWindow = applicationWindow;
	}

	@Override
	public void setApplicationIcon(Icon applicationIcon) {
		super.setApplicationIcon(applicationIcon);
		// set the upper left icon of frame
		if ((getApplicationWindow() instanceof JFrame)
				&& (getApplicationIcon() instanceof ImageIcon)) {
			((JFrame) getApplicationWindow())
					.setIconImage(((ImageIcon) getApplicationIcon()).getImage());
		}
	}

	/**
	 * Starts the application. This is equivalent to <code>run(null)</code>.
	 */
	@Override
	public void run() {
		run(null);
	}

	/**
	 * Starts the application with the specified <code>args</code>. This method
	 * handles the following
	 * <ul>
	 * <li>processing the <code>args</code></li>
	 * <li>invoking application life cycle events</li>
	 * <li>showing the application GUI</li>
	 * </ul>
	 * 
	 * @param args
	 *            arguments from the command line
	 */
	@Override
	public void run(String[] args) {
		if (getApplicationWindow() != null) {
			getApplicationWindow().setVisible(true);
		}
	}

}

package org.javamvc;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.util.prefs.Preferences;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * A <code>GUIApplication</code> is a desktop application which provides methods
 * for initializing a <em>Graphical User Interface (GUI)</em>.
 * <p>
 * The properties available in this class are in the table below.
 * <table>
 * <thead>
 * <tr>
 * <th>Property</th>
 * <th>Description</th>
 * </tr>
 * </thead><tbody> </tbody>
 * </table>
 * <p>
 * The preferences used in this class are in the table below.
 * <table>
 * <thead>
 * <tr>
 * <th>Preference</th>
 * <th>Default</th>
 * <th>Description</th>
 * </tr>
 * </thead><tbody>
 * <tr>
 * <td><code>"window.location.x"</code></td>
 * <td>100</td>
 * <td>The number of pixels to the right from the top left of the screen the
 * window will position itself on startup</td>
 * </tr>
 * <tr>
 * <td><code>"window.location.y"</code></td>
 * <td>100</td>
 * <td>The number of pixels down from the top left of the screen the window will
 * position itself on startup</td>
 * </tr>
 * <tr>
 * <td><code>"window.size.width"</code></td>
 * <td>100</td>
 * <td>The number of pixels wide the window will be on startup</td>
 * </tr>
 * <tr>
 * <td><code>"window.size.height"</code></td>
 * <td>100</td>
 * <td>The number of pixels high the window will be on startup</td>
 * </tr>
 * </tbody>
 * </table>
 * 
 * @author Erich Schroeter
 */
public abstract class GUIApplication extends DesktopApplication {

	/** The reference to the application window. */
	protected Container applicationWindow;

	/**
	 * Constructs a default <code>GUIApplication</code>.
	 * <p>
	 * This is equivalent to <code>GUIApplication(new JFrame())</code>.
	 */
	public GUIApplication() {
		this(new JFrame());
	}

	/**
	 * Constructs a <code>GUIApplication</code> specifying the type of
	 * <code>Container</code> to use for the main application window. This
	 * initializes the application window by calling
	 * {@link #initializeWindow(Container)} and initializes the application
	 * preferences by calling {@link #installApplicationPreferences()}.
	 * <p>
	 * The <code>applicationWindow</code> is what will be returned by the
	 * {@link #getApplicationWindow()}.
	 * 
	 * @see #getApplicationWindow()
	 * @param applicationWindow
	 *            the object to use as the main application window
	 */
	public GUIApplication(Container applicationWindow) {
		super(); // initialize preferences
		setApplicationWindow(applicationWindow);
		initializeWindow(applicationWindow);
	}

	/**
	 * Initializes the desktop application window. This can be overridden in
	 * derived classes to customize the window further. Make sure to call
	 * <code>super.initializeWindow(applicationWindow)</code> to ensure the
	 * default initialization values are kept.
	 * <p>
	 * The default initialization handles
	 * <ul>
	 * <li>sets the preferred size to values of application preferences for
	 * <code>"window.size.width"</code> and <code>"window.size.height"</code></li>
	 * <li>sets the default location to values of application preferences for
	 * <code>"window.location.x"</code> and <code>"window.location.y"</code></li>
	 * <li>if {@link #applicationWindow} is a {@link JFrame} the default close
	 * operation is set to {@link JFrame#EXIT_ON_CLOSE}</li>
	 * </ul>
	 * 
	 * @param applicationWindow
	 *            the application window to initialize
	 */
	protected void initializeWindow(Container applicationWindow) {
		if (applicationWindow instanceof JFrame) {
			((JFrame) applicationWindow)
					.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		applicationWindow.setPreferredSize(new Dimension(
				getApplicationPreferences().getInt("window.size.width", 100),
				getApplicationPreferences().getInt("window.size.height", 100)));
		applicationWindow.setLocation(new Point(getApplicationPreferences()
				.getInt("window.location.x", 100), getApplicationPreferences()
				.getInt("window.location.y", 100)));
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation will only set a preference value if there is
	 * no existing preference value. See the class documentation for preferences
	 * and default values.
	 */
	@Override
	protected void installApplicationPreferences() {
		super.installApplicationPreferences();
		Preferences p = getApplicationPreferences();
		if (p.get("window.location.x", null) == null) {
			p.putInt("window.location.x", 100);
		}
		if (p.get("window.location.y", null) == null) {
			p.putInt("window.location.y", 100);
		}
		if (p.get("window.size.width", null) == null) {
			p.putInt("window.size.width", 100);
		}
		if (p.get("window.size.height", null) == null) {
			p.putInt("window.size.height", 100);
		}
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

	/**
	 * Returns whether the window is maximized or not.
	 * <p>
	 * This method attempts to find the state of the <code>Container</code> to
	 * see if it is maximized. A <code>JFrame</code> provides different methods
	 * than <code>Window</code> in order to determine whether they are
	 * maximized.
	 * 
	 * @return <code>true</code> if maximized, else <code>false</code>
	 * @throws NotSupportedException
	 *             if the {@link #applicationWindow} is not supported for this
	 *             function
	 */
	public boolean isMaximized() throws NotSupportedException {
		if (getApplicationWindow() instanceof JFrame) {
			return (((JFrame) getApplicationWindow()).getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH;
		} else {
			throw new NotSupportedException();
		}
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
	 * Starts the application.
	 * <p>
	 * This is equivalent to <code>run(null)</code>.
	 */
	@Override
	public void run() {
		run(null);
	}

	/**
	 * Starts the application with the specified <code>args</code>. This method
	 * should handle the following
	 * <ul>
	 * <li>processing the <code>args</code></li>
	 * <li>invoking application life cycle events</li>
	 * <li>showing the application GUI</li>
	 * </ul>
	 * <p>
	 * The default implementation of this is
	 * 
	 * <pre>
	 * fireLifecycleChange(Lifecycle.STARTING);
	 * if (getApplicationWindow() != null) {
	 * 	getApplicationWindow().setVisible(true);
	 * }
	 * fireLifecycleChange(Lifecycle.STARTED);
	 * </pre>
	 * 
	 * @param args
	 *            arguments from the command line
	 */
	@Override
	public void run(String[] args) {
		fireLifecycleChange(Lifecycle.STARTING);
		if (getApplicationWindow() != null) {
			getApplicationWindow().setVisible(true);
		}
		fireLifecycleChange(Lifecycle.STARTED);
	}

}

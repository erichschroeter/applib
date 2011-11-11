package usr.erichschroeter.applib;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.JFrame;

import usr.erichschroeter.applib.utils.Utils;

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
 * <tr>
 * <td><code>"window.maximized"</code></td>
 * <td>false</td>
 * <td>Whether the window will be maximized on startup. If you want the window
 * to be maximized, a <code>JFrame</code> must be visible when setting the
 * extended state to <code>MAXIMIZED_BOTH</code>.</td>
 * </tr>
 * </tbody>
 * </table>
 * 
 * @author Erich Schroeter
 */
public abstract class GUIApplicationImpl<W extends Window> extends
		DesktopApplicationImpl implements GUIApplication<W> {

	/** The reference to the application window. */
	protected W applicationWindow;
	/** The application action map. */
	protected ActionMap actionMap;

	/**
	 * Constructs a <code>GUIApplication</code> specifying the type of
	 * <code>Window</code> to use for the main application window. This
	 * initializes the application window by calling
	 * {@link #initializeWindow(Window)} and initializes the application
	 * preferences by calling {@link #installApplicationPreferences()}.
	 * <p>
	 * The <code>applicationWindow</code> is what will be returned by the
	 * {@link #getApplicationWindow()}.
	 * 
	 * @see #getApplicationWindow()
	 * @param applicationWindow
	 *            the object to use as the main application window
	 * @param objects
	 *            objects that need initialization
	 */
	protected GUIApplicationImpl(W applicationWindow, Object... objects) {
		super(objects); // initialize preferences
		setActionMap(new ActionMap());
		installActions(getActionMap());
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
	 * <li>if {@link #applicationWindow} is a {@link JFrame}
	 * <ul>
	 * <li>the default close operation is set to {@link JFrame#EXIT_ON_CLOSE}</li>
	 * <li>a window listener is registered to call {@link #exit()} when window
	 * is closing or closed</li>
	 * </ul>
	 * </li>
	 * </ul>
	 * 
	 * @param applicationWindow
	 *            the application window to initialize
	 */
	protected void initializeWindow(W applicationWindow) {
		applicationWindow.setPreferredSize(new Dimension(
				getApplicationPreferences().getInt("window.size.width", 100),
				getApplicationPreferences().getInt("window.size.height", 100)));
		applicationWindow.setLocation(new Point(getApplicationPreferences()
				.getInt("window.location.x", 100), getApplicationPreferences()
				.getInt("window.location.y", 100)));

		if (applicationWindow instanceof JFrame) {
			((JFrame) applicationWindow)
					.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// handle calling our exit() method when the application is closed
			((JFrame) applicationWindow).addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					exit();
				}

				@Override
				public void windowClosing(WindowEvent e) {
					exit();
				}
			});
		}
	}

	/**
	 * Installs the actions to be available throughout the application.
	 * 
	 * @param actionMap
	 *            the application's action map
	 */
	protected void installActions(ActionMap actionMap) {
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation will only set a preference value if there is
	 * no existing preference value. See the {@link GUIApplicationImpl} class
	 * documentation for preferences and default values.
	 * 
	 * @see GUIApplicationImpl
	 */
	@Override
	protected void installApplicationPreferences(Preferences p) {
		super.installApplicationPreferences(p);
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
		if (p.get("window.maximized", null) == null) {
			p.putBoolean("window.maximized", false);
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default behavior will save the current values of the preferences
	 * listed in the class documentation.
	 * <p>
	 * Note that {@link #isMaximized()} is used to save the
	 * <code>"window.maximized"</code> value, so if you are not using
	 * <code>JFrame</code> you may need to override that method. Otherwise, it
	 * will return <code>false</code> every time.
	 * 
	 * @see #isMaximized()
	 * @see GUIApplicationImpl
	 */
	@Override
	protected void saveApplicationPreferences() {
		super.saveApplicationPreferences();
		Preferences p = getApplicationPreferences();
		// if the window is maximized we don't want to overwrite the size and
		// location values since they will be the size of the screen
		if (isMaximized()) {
			p.putBoolean("window.maximized", true);
		} else {
			p.putBoolean("window.maximized", false);

			if (getApplicationWindow().getLocation() != null) {
				p.putInt("window.location.x", getApplicationWindow()
						.getLocation().x);
				p.putInt("window.location.y", getApplicationWindow()
						.getLocation().y);
			}
			if (getApplicationWindow().getSize() != null) {
				p.putInt("window.size.width",
						getApplicationWindow().getSize().width);
				p.putInt("window.size.height",
						getApplicationWindow().getSize().height);
			}
		}
	}

	/**
	 * Returns the application action map.
	 * <p>
	 * This contains actions to be available throughout the GUI application.
	 * 
	 * @return the application's action map
	 */
	public ActionMap getActionMap() {
		return actionMap;
	}

	/**
	 * Sets the application's action map.
	 * <p>
	 * This contains actions to be available throughout the GUI application.
	 * 
	 * @param actionMap
	 *            the application's action map
	 */
	protected void setActionMap(ActionMap actionMap) {
		this.actionMap = actionMap;
	}

	/**
	 * Returns the main application window.
	 * <p>
	 * The application window is returned as a <code>Window</code> object in
	 * order to give users the flexibility of using the several choices
	 * available, such as {@link JFrame}, {@link Window}, or some third party
	 * solution that extends <code>Window</code>.
	 * 
	 * @return the application window
	 */
	@Override
	public W getApplicationWindow() {
		return applicationWindow;
	}

	/**
	 * Sets the object to be used as the main application window.
	 * 
	 * @param applicationWindow
	 *            the main application window
	 */
	protected void setApplicationWindow(W applicationWindow) {
		this.applicationWindow = applicationWindow;
	}

	/**
	 * Returns whether the window is maximized or not.
	 * <p>
	 * This method attempts to find the state of the <code>Window</code> to see
	 * if it is maximized. A <code>JFrame</code> provides different methods than
	 * <code>Window</code> in order to determine whether they are maximized.
	 * 
	 * @return <code>true</code> if maximized, else <code>false</code>
	 */
	public boolean isMaximized() {
		boolean maximized = false;
		if (getApplicationWindow() instanceof Frame) {
			maximized = (((JFrame) getApplicationWindow()).getExtendedState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH;
		}
		return maximized;
	}

	@Override
	public Icon getApplicationIcon() {
		return applicationIcon;
	}

	/**
	 * Sets the application icon. This is the icon to be associated with the
	 * application and may be displayed in multiple locations depending on the
	 * platform (e.g. Windows, Linux, Mac, etc).
	 * <p>
	 * A <code>PropertyChangeEvent</code> is fired when the application icon is
	 * changed.
	 * 
	 * @param applicationIcon
	 *            the application icon
	 */
	@Override
	public void setApplicationIcon(Icon applicationIcon) {
		Icon old = getApplicationIcon();
		this.applicationIcon = applicationIcon;
		// set the upper left icon of frame
		getApplicationWindow().setIconImage(Utils.iconToImage(applicationIcon));
		firePropertyChange("application.icon", old, applicationIcon);
	}

	/**
	 * Starts the GUI application.
	 * <p>
	 * This method handles
	 * <ul>
	 * <li>firing application life cycle events</li>
	 * <li>showing the application GUI</li>
	 * </ul>
	 * <p>
	 * The default implementation is below
	 * 
	 * <pre>
	 * if (getApplicationWindow() != null) {
	 * 	getApplicationWindow().setVisible(true);
	 * }
	 * fireLifecycleChange(new LifecycleEvent(this, Lifecycle.STARTED));
	 * </pre>
	 */
	@Override
	public void run() {
		if (getApplicationWindow() != null) {
			getApplicationWindow().setVisible(true);
		}
		fireLifecycleChange(new LifecycleEvent(this, Lifecycle.STARTED));
	}

}

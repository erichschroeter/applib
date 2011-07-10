package example.javamvc;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.javamvc.GUIApplication;
import org.javamvc.Lifecycle;

/**
 * This is an example of how an end user would use the <b>javamvc</b> library in
 * their own projects.
 * 
 * @author Erich Schroeter
 */
public class TestGUIApplication extends GUIApplication {

	private static final String PREFERENCE_LOCATION_X = "window.location.x";
	private static final String PREFERENCE_LOCATION_Y = "window.location.y";
	private static final String PREFERENCE_SIZE_WIDTH = "window.size.width";
	private static final String PREFERENCE_SIZE_HEIGHT = "window.size.height";

	/**
	 * Constructs a default <code>TestGUIApplication</code> which sets the main
	 * application window to a <code>JFrame</code>.
	 */
	public TestGUIApplication() {
		super(new JFrame());
		init();
	}

	private void init() {
		JFrame frame = (JFrame) getApplicationWindow();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// a window listener must be implemented to call
		// DesktopApplication.exit() in order for our overridden exit() method
		// to be called and save our preferences
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				exit();
			}
		});
		frame.setTitle("Test Application");
		frame.setPreferredSize(new Dimension(getApplicationPreferences()
				.getInt(PREFERENCE_SIZE_WIDTH, 100),
				getApplicationPreferences().getInt(PREFERENCE_SIZE_HEIGHT, 100)));
		frame.setLocation(new Point(getApplicationPreferences().getInt(
				PREFERENCE_LOCATION_X, 100), getApplicationPreferences()
				.getInt(PREFERENCE_LOCATION_Y, 100)));
		setApplicationIcon(new ImageIcon(TestGUIApplication.class
				.getClassLoader().getResource(
						"example/resources/application-icon.png")));
		frame.pack();
	}

	@Override
	public void exit(int code) {
		fireLifecycleChange(Lifecycle.STOPPING);
		// save preferences
		if (getApplicationWindow().getSize() != null) {
			getApplicationPreferences().putInt(PREFERENCE_SIZE_WIDTH,
					getApplicationWindow().getSize().width);
			getApplicationPreferences().putInt(PREFERENCE_SIZE_HEIGHT,
					getApplicationWindow().getSize().height);
		}
		if (getApplicationWindow().getLocation() != null) {
			getApplicationPreferences().putInt(PREFERENCE_LOCATION_X,
					getApplicationWindow().getLocation().x);
			getApplicationPreferences().putInt(PREFERENCE_LOCATION_Y,
					getApplicationWindow().getLocation().y);
		}
		fireLifecycleChange(Lifecycle.STOPPED);
		System.exit(code);
	}

	/**
	 * The application version. This should be incremented prior to each
	 * release.
	 */
	public static final String VERSION = "0.1";

	@Override
	public String getVersion() {
		return VERSION;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				new TestGUIApplication().run();
			}
		});
	}
}

package example.javamvc;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.javamvc.GUIApplication;

/**
 * This is an example of how an end user would use the <b>javamvc</b> library in
 * their own projects.
 * 
 * @author Erich Schroeter
 */
public class TestGUIApplication extends GUIApplication {

	private static final String PREFERENCE_LOCATION_X = "window.location.x";
	private static final String PREFERENCE_LOCATION_Y = "window.location.y";
	private static final String PREFERENCE_SIZE_X = "window.size.x";
	private static final String PREFERENCE_SIZE_Y = "window.size.y";

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
		frame.setTitle("Test Application");
		frame.setPreferredSize(new Dimension(getApplicationPreferences()
				.getInt(PREFERENCE_SIZE_X, 100), getApplicationPreferences()
				.getInt(PREFERENCE_SIZE_Y, 100)));
		frame.setLocation(new Point(getApplicationPreferences().getInt(
				PREFERENCE_LOCATION_X, 100), getApplicationPreferences()
				.getInt(PREFERENCE_LOCATION_Y, 100)));
		setApplicationIcon(new ImageIcon(TestGUIApplication.class
				.getClassLoader().getResource(
						"example/resources/application-icon.png")));
		frame.pack();
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

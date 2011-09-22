package example.javamvc;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

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

	/**
	 * The application version. This should be incremented prior to each
	 * release.
	 */
	public static final String VERSION = "0.1";

	/**
	 * Constructs a default <code>TestGUIApplication</code> which sets the main
	 * application window to a <code>JFrame</code>.
	 */
	public TestGUIApplication() {
		super(new JFrame());
	}

	@Override
	protected void initializeWindow(Container applicationWindow) {
		super.initializeWindow(applicationWindow);
		JFrame frame = (JFrame) applicationWindow;
		// a window listener must be implemented to call
		// DesktopApplication.exit() in order for our overridden exit() method
		// to be called and save our preferences
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				exit(0);
			}
		});
		frame.setTitle("Test Application");
		setApplicationIcon(new ImageIcon(TestGUIApplication.class
				.getClassLoader().getResource(
						"example/resources/application-icon.png")));
		frame.pack();
	}

	@Override
	public Preferences getApplicationPreferences() {
		return getApplicationPreferences("test-gui-application");
	}

	@Override
	public void exit(int code) {
		fireLifecycleChange(Lifecycle.STOPPING);
		// save preferences
		if (getApplicationWindow().getSize() != null) {
			getApplicationPreferences().putInt("window.size.width",
					getApplicationWindow().getSize().width);
			getApplicationPreferences().putInt("window.size.height",
					getApplicationWindow().getSize().height);
		}
		if (getApplicationWindow().getLocation() != null) {
			getApplicationPreferences().putInt("window.location.x",
					getApplicationWindow().getLocation().x);
			getApplicationPreferences().putInt("window.location.y",
					getApplicationWindow().getLocation().y);
		}
		fireLifecycleChange(Lifecycle.STOPPED);
		System.exit(code);
	}

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

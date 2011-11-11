package example.javamvc;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.javamvc.GUIApplication;

/**
 * This is an example of how an end user would use the <b>javamvc</b> library in
 * their own GUI application. This example is intended to be extremely simple
 * showing how to quickly get a {@link GUIApplication} implemented. This does
 * not include any views.
 * 
 * @author Erich Schroeter
 */
public class SimpleGUIApplication extends GUIApplication<JFrame> {

	/**
	 * The application version. This should be incremented prior to each
	 * release.
	 */
	public static final String VERSION = "0.1";

	/**
	 * Constructs a default <code>SimpleGUIApplication</code> which sets the
	 * main application window to a <code>JFrame</code>.
	 */
	public SimpleGUIApplication() {
		super(new JFrame());
		setSavePreferencesOnExit(true);
	}

	@Override
	protected void initializeWindow(JFrame applicationWindow) {
		super.initializeWindow(applicationWindow);
		// a window listener must be implemented to call
		// DesktopApplication.exit() in order for our overridden exit() method
		// to be called and save our preferences
		applicationWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				exit(0);
			}
		});
		applicationWindow.setTitle("Simple Application");
		setApplicationIcon(new ImageIcon(SimpleGUIApplication.class
				.getClassLoader().getResource(
						"example/resources/application-icon.png")));
		applicationWindow.pack();
	}

	@Override
	public Preferences getApplicationPreferences() {
		return getApplicationPreferences("simple-gui-application");
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
				new SimpleGUIApplication().run();
			}
		});
	}

}

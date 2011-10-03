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
import org.javamvc.MVCDesktopApplication;

/**
 * This is an example of how an end user would use the <b>javamvc</b> library in
 * their own GUI application. This example is intended to be extremely simple
 * showing how to quickly get a {@link GUIApplication} implemented. This does
 * not include any views.
 * 
 * @author Erich Schroeter
 */
public class SimpleGUIApplication extends MVCDesktopApplication {

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
		frame.setTitle("Simple Application");
		setApplicationIcon(new ImageIcon(SimpleGUIApplication.class
				.getClassLoader().getResource(
						"example/resources/application-icon.png")));
		frame.pack();
	}

	@Override
	protected void installModels() {
		// do nothing since we're not using models for this simple example
	}

	@Override
	protected void installViews() {
		// do nothing since we're not using views for this simple example
	}

	@Override
	protected void mapViewsAndModels() {
		// do nothing since we're not using views or models for this simple example
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

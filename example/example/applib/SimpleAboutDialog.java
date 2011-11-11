package example.applib;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import usr.erichschroeter.applib.AboutDialog;

/**
 * This example shows the simple usage of the {@link AboutDialog}.
 * 
 * @author Erich Schroeter
 */
public class SimpleAboutDialog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				JFrame frame = new JFrame();
				AboutDialog about = new AboutDialog(frame);
				about.setLogo(new ImageIcon(SimpleAboutDialog.class
						.getClassLoader().getResource(
								"example/resources/application-icon.png")));
				about.setApplicationName("Simple About Dialog");
				about.setVersion("1.0.0.0");
				about.setApplicationOwner("Example, Inc.");
				about.setCopyright("\u00A9 2010 Example, Inc. All rights reserved");
				about.setWebsite("http://www.example.com");
				about.setDescription("This application shows the simple usage of the AboutDialog class.");
				about.setFeatures(new String[] { "Show the basic usage of using the AboutDialog class" });
				about.setAuthors(new String[] { "Erich Schroeter" });
				about.setLicenses(new String[] {});

				about.setLocationRelativeTo(frame);
				about.setVisible(true);
				about.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						System.exit(0);
					}

					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
			}
		});
	}

}

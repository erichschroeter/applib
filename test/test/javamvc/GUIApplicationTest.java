package test.javamvc;

import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.javamvc.DesktopApplicationImpl;
import org.javamvc.GUIApplication;
import org.junit.Before;
import org.junit.Test;

public class GUIApplicationTest {

	private GUIApplication<JFrame> app;

	@Before
	public void setUp() throws Exception {
		app = new GUIApplication<JFrame>(new JFrame()) {

			@Override
			public String getVersion() {
				return "1.2.3";
			}
		};
	}

	/**
	 * Tests to make sure a <code>PropertyChangeEvent</code> is fired for both
	 * the specific property listener and all property listeners.
	 */
	@Test
	public void testApplicationIconChangeEvent() {
		final Icon firstIcon = new ImageIcon();
		final Icon secondIcon = new ImageIcon();

		app.setApplicationIcon(firstIcon);
		PropertyChangeListener allListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				assertEquals(evt.getOldValue(), firstIcon);
			}
		};
		PropertyChangeListener specificListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				assertEquals(evt.getOldValue(), firstIcon);
			}
		};
		app.addPropertyChangeListener(allListener);
		app.addPropertyChangeListener("application.icon", specificListener);

		app.setApplicationIcon(secondIcon);
	}

	/**
	 * Tests to ensure the <code>JFrame</code> icon image is set when
	 * {@link DesktopApplicationImpl#setApplicationIcon(Icon)} is called.
	 */
	@Test
	public void testApplicationIconSetsJFrameImages() {
		final Icon icon = new ImageIcon(GUIApplicationTest.class
				.getClassLoader().getResource(
						"test/resources/application-icon.png"));

		app = new GUIApplication<JFrame>(new JFrame()) {

			@Override
			public String getVersion() {
				return "1.2.3";
			}
		};

		assertNull(((JFrame) app.getApplicationWindow()).getIconImage());
		app.setApplicationIcon(icon);
		assertNotNull(((JFrame) app.getApplicationWindow()).getIconImage());

	}

}

package usr.erichschroeter.applib;

import java.awt.Window;

import javax.swing.Icon;

/**
 * The <code>GUIApplication</code> interface is a {@link DesktopApplication}
 * that provides a <em>graphical user interface</em> to the user.
 * 
 * @author Erich Schroeter
 */
public interface GUIApplication<W extends Window> extends DesktopApplication {

	/**
	 * Returns the application's window. This is graphical user interface
	 * presented to the user.
	 * 
	 * @return the application user interface
	 */
	public W getApplicationWindow();

	/**
	 * Returns the application icon. This is the icon to be associated with the
	 * application and may be displayed in multiple locations depending on the
	 * platform (e.g. Windows, Linux, Mac, etc).
	 * <p>
	 * Examples: Windows has the icon in the upper left hand corner of window
	 * frames as well as in the taskbar. Kubuntu Linux is the same as Windows.
	 * Mac would have it in the upper left hand of the top menu panel and
	 * perhaps in the dock.
	 * 
	 * @return the application icon
	 */
	public Icon getApplicationIcon();

	/**
	 * Sets the application icon. This is the icon to be associated with the
	 * application and may be displayed in multiple locations depending on the
	 * platform (e.g. Windows, Linux, Mac, etc).
	 * <p>
	 * Examples: Windows has the icon in the upper left hand corner of window
	 * frames as well as in the taskbar. Kubuntu Linux is the same as Windows.
	 * Mac would have it in the upper left hand of the top menu panel and
	 * perhaps in the dock.
	 * 
	 * @param applicationIcon
	 *            the application icon
	 */
	public void setApplicationIcon(Icon applicationIcon);

}

package usr.erichschroeter.applib;

import java.awt.Window;

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

}

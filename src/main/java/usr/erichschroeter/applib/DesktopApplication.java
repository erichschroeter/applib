package usr.erichschroeter.applib;

import java.util.prefs.Preferences;

/**
 * The <code>IDesktopAppliction</code> interface provides methods common among
 * all desktop applications. This interface gives the framework the flexibility
 * to provide different types of desktop applications. For example, a GUI
 * application may contain different features than a Console application, but
 * both should have common functionality provided via this interface.
 * 
 * @author Erich Schroeter
 */
public interface DesktopApplication extends Runnable {

	/**
	 * Returns the version of the application. This may follow whatever
	 * versioning convention you like.
	 * <ul>
	 * <li><em>&lt;major&gt;.&lt;minor&gt;.&lt;revision&gt;</em> (e.g. 1.0.26)</li>
	 * <li>word(s) based on incrementing alphabet first letter (e.g. Ubuntu
	 * <em>Hardy Heron</em>, <em>Lucid Lynx</em>)</li>
	 * </ul>
	 * <p>
	 * Setting the version is not accessible at the interface level in order to
	 * prevent <em>any</em> object with a reference to the application the
	 * ability to change the version. Changing the version should be done in the
	 * derived <code>DesktopApplication</code>; most likely hard coded, but
	 * could be read in from a file or property.
	 * 
	 * @return the application version
	 */
	public String getVersion();

	/**
	 * Exits the application specifying the error code to print to the console.
	 * The typical value of <code>code</code> is 0 if everything is ok.
	 * 
	 * @param code
	 *            the error code
	 */
	public void exit(int code);

	/**
	 * Returns the preferences for this application.
	 * <p>
	 * This may include preferences such as the last known window size, last
	 * known location, whether the window was maximized in the last instance.
	 * 
	 * @see #getApplicationPreferences(String)
	 * @return the application preferences
	 */
	public Preferences getApplicationPreferences();

	/**
	 * Returns the preferences for this application for the specified
	 * preferences <code>node</code>.
	 * <p>
	 * This may include preferences such as the last known window size, last
	 * known location, whether the window was maximized in the last instance.
	 * 
	 * @see #getApplicationPreferences()
	 * @param node
	 *            the preferences node
	 * @return the application preferences for the specified <code>node</code>
	 */
	public Preferences getApplicationPreferences(String node);

}

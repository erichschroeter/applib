package usr.erichschroeter.applib;

/**
 * The <code>R</code> class contains methods for specifying resources in the
 * <code>usr.erichschroeter.applib.res</code> package.
 * 
 * @author Erich Schroeter
 */
public class R {

	/** The root package resources are located in. */
	public static final String resourcePackage = "usr/erichschroeter/applib";

	/**
	 * Returns the resource string that refers to the <code>resource</code> file
	 * in the <code>usr.erichschroeter.applib.res.png</code> package.
	 * 
	 * @param resource
	 *            the file in the <code>png</code> package
	 * @return the full resource string
	 */
	public static String png(String resource) {
		return String.format("%s/png/%s", resourcePackage, resource);
	}

}

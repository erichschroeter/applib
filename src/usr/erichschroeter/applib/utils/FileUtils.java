package usr.erichschroeter.applib.utils;

import java.io.File;

/**
 * The <code>FileUtils</code> class consists of public static utility for files
 * whose methods may not be unique for any particular class.
 * 
 * @author Erich Schroeter
 */
public abstract class FileUtils {

	/**
	 * Returns the file's extension. An empty string is returned if
	 * <code>file</code> is <code>null</code> or the file's name does not
	 * contain a period (<code>'.'</code>).
	 * 
	 * @param file
	 *            the file to check
	 * @return <code>file</code>'s extension, or an empty string
	 */
	public static String getExtension(File file) {
		if (file == null) {
			return "";
		}
		String ext = "";
		int dot = file.getName().lastIndexOf('.');
		if (dot != -1) {
			ext = file.getName().substring(dot);
		}
		return ext;
	}

	/**
	 * Returns a new file with the specified extension.
	 * 
	 * @param file
	 *            the file to change
	 * @param ext
	 *            the extension (excluding the <code>'.'</code>)
	 * @return a new file based on <code>file</code> with the <code>ext</code>
	 *         extension
	 */
	public static File changeExtension(File file, String ext) {
		if (file == null) {
			throw new NullPointerException("file cannot be null");
		}
		if (ext == null) {
			ext = "";
		}
		String name = file.getName();
		int dot = name.lastIndexOf('.');
		if (dot != -1) {
			name = name.substring(0, dot);
		}
		name = ext.isEmpty() ? name : name.concat('.' + ext);
		return new File(file.getParent(), name);
	}

}

package usr.erichschroeter.applib.filters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import usr.erichschroeter.applib.utils.FileUtils;

/**
 * A <code>CsvFileFilter</code> filters CSV files based on their extensions.
 * 
 * @author Erich Schroeter
 */
public class CsvFileFilter extends FileFilter implements java.io.FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String ext = FileUtils.getExtension(f);
		if (ext.equalsIgnoreCase(".csv") || ext.equalsIgnoreCase(".txt")) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "Comma Separated Values (*.csv, *.txt)";
	}

}

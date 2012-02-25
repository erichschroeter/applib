package usr.erichschroeter.applib.filters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import usr.erichschroeter.applib.utils.FileUtils;

/**
 * A <code>XmlFileFilter</code> filters XML files based on their extensions.
 * <p>
 * Specific files pertaining to XML protocol may be narrowed with the following
 * methods:
 * <ul>
 * <li>{@link #setIncludeXml(boolean)}</li>
 * <li>{@link #setIncludeSchema(boolean)}</li>
 * <li>{@link #setIncludeTemplate(boolean)}</li>
 * </ul>
 * 
 * @author Erich Schroeter
 */
public class XmlFileFilter extends FileFilter implements java.io.FileFilter {

	/** Whether to include XML extensions. */
	private boolean includeXml;
	/** Whether to include XML schema extensions. */
	private boolean includeSchema;
	/** Whether to include XML template extensions. */
	private boolean includeTemplate;

	public XmlFileFilter() {
		this(true, true, true);
	}

	public XmlFileFilter(boolean includeXml, boolean includeSchema,
			boolean includeTemplate) {
		setIncludeXml(includeXml);
		setIncludeSchema(includeSchema);
		setIncludeTemplate(includeTemplate);
	}

	public boolean isIncludeXml() {
		return includeXml;
	}

	public void setIncludeXml(boolean includeXml) {
		this.includeXml = includeXml;
	}

	public boolean isIncludeSchema() {
		return includeSchema;
	}

	public void setIncludeSchema(boolean includeSchema) {
		this.includeSchema = includeSchema;
	}

	public boolean isIncludeTemplate() {
		return includeTemplate;
	}

	public void setIncludeTemplate(boolean includeTemplate) {
		this.includeTemplate = includeTemplate;
	}

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String ext = FileUtils.getExtension(f);
		if (includeXml && ext.equalsIgnoreCase(".xml")) {
			return true;
		} else if (includeSchema && ext.equalsIgnoreCase(".xsd")) {
			return true;
		} else if (includeTemplate
				&& (ext.equalsIgnoreCase(".xsl") || ext
						.equalsIgnoreCase(".xslt"))) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		StringBuilder ext = new StringBuilder();
		if (isIncludeXml()) {
			ext.append("*.xml");
		}
		if (isIncludeSchema()) {
			if (ext.length() > 0) {
				ext.append(", ");
			}
			ext.append("*.xsd");
		}
		if (isIncludeTemplate()) {
			if (ext.length() > 0) {
				ext.append(", ");
			}
			ext.append("*.xsl, *.xslt");
		}
		return "Extensible Markup Language (" + ext.toString() + ")";
	}

}

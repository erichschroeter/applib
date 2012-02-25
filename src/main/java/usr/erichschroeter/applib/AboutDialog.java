package usr.erichschroeter.applib;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

import usr.erichschroeter.applib.utils.SwingLink;

/**
 * The <code>AboutDialog</code> provides methods for setting the basic
 * information an about dialog should contain.
 * <p>
 * The information available to customize in the about dialog is below
 * <ul>
 * <li>{@link #setLogo(Icon)}</li>
 * <li>{@link #setVersion(String)}</li>
 * <li>{@link #setApplicationName(String)}</li>
 * <li>{@link #setApplicationOwner(String)}</li>
 * <li>{@link #setWebsite(String)}</li>
 * <li>{@link #setCopyright(String)}</li>
 * <li>{@link #setDescription(String)}</li>
 * <li>{@link #setFeatures(String[])}</li>
 * <li>{@link #setAuthors(String[])}</li>
 * <li>{@link #setLicenses(String[])}</li>
 * </ul>
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

	/** The application logo. */
	protected Icon logo;
	/** The application's name. */
	protected String name;
	/** The owner the application belongs to. */
	protected String owner;
	/** The application copyright. */
	protected String copyright;
	/** A description of the application. */
	protected String description;
	/** A list of some of the application's features. */
	protected String[] features;
	/** The application version. */
	protected String version;
	/** The application author(s). */
	protected String[] authors;
	/**
	 * The application's license(s). This can be URLs to the licenses or just
	 * titles.
	 */
	protected String[] licenses;
	/** The application website. */
	protected String website;

	public AboutDialog(Frame parent) {
		this(parent, "About");
	}

	public AboutDialog(Frame parent, boolean modal) {
		super(parent, modal);
	}

	public AboutDialog(Frame parent, String title) {
		super(parent, title);
	}

	public AboutDialog(Frame parent, String title, boolean modal) {
		super(parent, modal);
		setTitle(title);
	}

	@Override
	public void setVisible(boolean b) {
		initializeDialog();
		super.setVisible(b);
	}

	protected void initializeDialog() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		JLabel logoLabel = new JLabel(logo);
		JLabel nameLabel = new JLabel(name);
		JLabel versionLabel = new JLabel(version);
		JLabel ownerLabel = new JLabel(owner);
		JLabel copyrightLabel = new JLabel(copyright);
		Component websiteComponent = null;
		try {
			// use the URI constructor to validate the website
			SwingLink link = new SwingLink(website, new URI(website));
			websiteComponent = link;
		} catch (URISyntaxException e) {
			websiteComponent = new JLabel(website);
		}
		JLabel descriptionLabel = new JLabel(description);

		Component tabs = null;
		// make sure at least one of the lists has content
		if (features != null || authors != null || licenses != null) {
			JTabbedPane pane = new JTabbedPane();

			if (features != null && features.length > 0) {
				Component featureList = new JScrollPane(new JList(features));
				((JScrollPane) featureList).setBorder(BorderFactory
						.createBevelBorder(BevelBorder.LOWERED));
				pane.addTab("Features", featureList);
			}

			if (authors != null && authors.length > 0) {
				Component authorList = new JScrollPane(new JList(authors));
				((JScrollPane) authorList).setBorder(BorderFactory
						.createBevelBorder(BevelBorder.LOWERED));
				pane.addTab("Authors", authorList);
			}

			if (licenses != null && licenses.length > 0) {
				Component licenseList = new JScrollPane(new JList(licenses));
				((JScrollPane) licenseList).setBorder(BorderFactory
						.createBevelBorder(BevelBorder.LOWERED));
				pane.addTab("Licenses", licenseList);
			}

			tabs = pane;
		} else {
			tabs = new JLabel(); // empty component
		}

		JButton closeButton = new JButton(new AbstractAction("Close") {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AboutDialog.this.dispose();
			}
		});

		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(layout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(logoLabel)
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		nameLabel)
																.addComponent(
																		versionLabel))
												.addComponent(ownerLabel)
												.addComponent(copyrightLabel)))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(websiteComponent)
								.addComponent(descriptionLabel)
								.addComponent(tabs))
				.addComponent(closeButton, Alignment.TRAILING));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(logoLabel)
								.addGroup(
										layout.createSequentialGroup()
												.addGroup(
														layout.createParallelGroup()
																.addComponent(
																		nameLabel,
																		Alignment.LEADING)
																.addComponent(
																		versionLabel,
																		Alignment.TRAILING))
												.addComponent(ownerLabel)
												.addComponent(copyrightLabel)))
				.addComponent(websiteComponent).addComponent(descriptionLabel)
				.addComponent(tabs).addComponent(closeButton));
		pack();
		setLocationRelativeTo(getParent());
	}

	public Icon getLogo() {
		return logo;
	}

	public AboutDialog setLogo(Icon logo) {
		this.logo = logo;
		return this;
	}

	public String getApplicationName() {
		return name;
	}

	public AboutDialog setApplicationName(String name) {
		this.name = name;
		return this;
	}

	public String getApplicationOwner() {
		return owner;
	}

	public AboutDialog setApplicationOwner(String owner) {
		this.owner = owner;
		return this;
	}

	public String getCopyright() {
		return copyright;
	}

	public AboutDialog setCopyright(String copyright) {
		this.copyright = copyright;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public AboutDialog setDescription(String description) {
		this.description = description;
		return this;
	}

	public String[] getFeatures() {
		return features;
	}

	public AboutDialog setFeatures(String[] features) {
		this.features = features;
		return this;
	}

	public String getVersion() {
		return version;
	}

	public AboutDialog setVersion(String version) {
		this.version = version;
		return this;
	}

	public String[] getAuthors() {
		return authors;
	}

	public AboutDialog setAuthors(String[] authors) {
		this.authors = authors;
		return this;
	}

	public String[] getLicenses() {
		return licenses;
	}

	public AboutDialog setLicenses(String[] licenses) {
		this.licenses = licenses;
		return this;
	}

	public String getWebsite() {
		return website;
	}

	public AboutDialog setWebsite(String website) {
		this.website = website;
		return this;
	}

}

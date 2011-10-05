package example.javamvc.views;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import org.javamvc.GUIApplication;
import org.javamvc.view.ViewImpl;

/**
 * A <code>CompanyView</code> is a simple example to show users how to use a
 * {@link ViewImpl}. It displays components for a user to enter company information.
 * 
 * @author Erich Schroeter
 */
public class CompanyView extends ViewImpl<JPanel> {

	public CompanyView(GUIApplication app) {
		super(app, new JPanel());
	}

	@Override
	protected void initializeView(JPanel view) {
		GroupLayout layout = new GroupLayout(view);
		view.setLayout(layout);

		JLabel companyLabel = new JLabel("Company:");
		JTextField companyField = new JTextField(10);

		JLabel addressLabel = new JLabel("Address:");
		JTextField addressField = new JTextField(20);
		JLabel zipLabel = new JLabel("Zip:");
		JTextField zipField = new JTextField(5);

		JLabel phoneLabel = new JLabel("Phone");
		JComboBox countryCodeCombo = new JComboBox(new Integer[] { 1 });
		JTextField areaCodeField = new JTextField(3);
		JTextField exchangeField = new JTextField(3);
		JTextField subscriberField = new JTextField(4);

		// JLabel faxLabel = new JLabel("Fax");

		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(companyLabel)
								.addComponent(addressLabel)
								.addComponent(phoneLabel))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(companyField)
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(addressField)
												.addComponent(zipLabel)
												.addComponent(zipField))
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(countryCodeCombo)
												.addComponent(areaCodeField)
												.addComponent(exchangeField)
												.addComponent(subscriberField))));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(companyLabel)
								.addComponent(companyField))
				.addGroup(
						layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(addressLabel)
								.addComponent(addressField)
								.addComponent(zipLabel).addComponent(zipField))
				.addGroup(
						layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(phoneLabel)
								.addComponent(countryCodeCombo)
								.addComponent(areaCodeField)
								.addComponent(exchangeField)
								.addComponent(subscriberField)));

		// register the fields we want access to
		registerSubcomponent("company", companyField);
		registerSubcomponent("address", addressField);
		registerSubcomponent("zip", zipField);
		registerSubcomponent("country-code", countryCodeCombo);
		registerSubcomponent("area-code", areaCodeField);
		registerSubcomponent("exchange", exchangeField);
		registerSubcomponent("subscriber", subscriberField);
	}
}

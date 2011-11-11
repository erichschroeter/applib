package example.javamvc.views;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;



import usr.erichschroeter.applib.GUIApplication;
import usr.erichschroeter.applib.view.ViewImpl;

/**
 * A <code>PersonView</code> is a simple example to show users how to use a
 * {@link ViewImpl}. It displays components for a user to enter personal
 * information.
 * 
 * @author Erich Schroeter
 */
public class PersonView extends ViewImpl<JPanel> {

	public PersonView(GUIApplication<?> app) {
		super(app, new JPanel());
	}

	@Override
	protected void initializeView(JPanel view) {
		GroupLayout layout = new GroupLayout(view);
		view.setLayout(layout);

		JLabel nameLabel = new JLabel("Name:");
		JTextField nameField = new JTextField(10);

		JLabel genderLabel = new JLabel("Gender:");
		JRadioButton maleButton = new JRadioButton("male");
		JRadioButton femaleButton = new JRadioButton("female");
		JRadioButton noneButton = new JRadioButton("none");

		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(maleButton);
		genderGroup.add(femaleButton);
		genderGroup.add(noneButton);

		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(nameLabel)
								.addComponent(genderLabel))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(nameField))
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(maleButton)
												.addComponent(femaleButton)
												.addComponent(noneButton))));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(nameLabel)
								.addComponent(nameField))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(genderLabel)
								.addComponent(maleButton)
								.addComponent(femaleButton)
								.addComponent(noneButton)));

		// register the fields we want access to
		registerSubcomponent("name", nameField);
		registerSubcomponent("male", maleButton);
		registerSubcomponent("female", femaleButton);
		registerSubcomponent("none", noneButton);
	}
}

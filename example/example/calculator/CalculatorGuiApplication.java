package example.calculator;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.prefs.Preferences;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import usr.erichschroeter.applib.GUIApplicationImpl;
import usr.erichschroeter.applib.utils.Utils;

public class CalculatorGuiApplication extends GUIApplicationImpl<JFrame> {

	private JTextField textField;

	public CalculatorGuiApplication() {
		super(new JFrame("Calculator"));
		setApplicationIcon(null);
		setSavePreferencesOnExit(true);
	}

	@SuppressWarnings("serial")
	@Override
	protected void installActions(ActionMap actionMap) {
		super.installActions(actionMap);
		actionMap.put("0", new AbstractAction("0") {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat("0"));
			}
		});
		actionMap.put("1", new AbstractAction("1") {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat("1"));
			}
		});
		actionMap.put("2", new AbstractAction("2") {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat("2"));
			}
		});
		actionMap.put("3", new AbstractAction("3") {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat("3"));
			}
		});
		actionMap.put("4", new AbstractAction("4") {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat("4"));
			}
		});
		actionMap.put("5", new AbstractAction("5") {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat("5"));
			}
		});
		actionMap.put("6", new AbstractAction("6") {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat("6"));
			}
		});
		actionMap.put("7", new AbstractAction("7") {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat("7"));
			}
		});
		actionMap.put("8", new AbstractAction("8") {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat("8"));
			}
		});
		actionMap.put("9", new AbstractAction("9") {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat("9"));
			}
		});
		actionMap.put(".", new AbstractAction(".") {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat("."));
			}
		});
	}

	@Override
	protected void initializeWindow(JFrame applicationWindow) {
		super.initializeWindow(applicationWindow);

		JButton addButton = new JButton("+");
		JButton subtractButton = new JButton("-");
		JButton multiplyButton = new JButton("*");
		JButton divideButton = new JButton("/");
		JButton calculateButton = new JButton("=");

		JPanel mathOperationsPanel = new JPanel(new GridLayout(0, 1));
		mathOperationsPanel.add(addButton);
		mathOperationsPanel.add(subtractButton);
		mathOperationsPanel.add(multiplyButton);
		mathOperationsPanel.add(divideButton);
		mathOperationsPanel.add(calculateButton);

		JButton zeroButton = new JButton(getActionMap().get("0"));
		JButton oneButton = new JButton(getActionMap().get("1"));
		JButton twoButton = new JButton(getActionMap().get("2"));
		JButton threeButton = new JButton(getActionMap().get("3"));
		JButton fourButton = new JButton(getActionMap().get("4"));
		JButton fiveButton = new JButton(getActionMap().get("5"));
		JButton sixButton = new JButton(getActionMap().get("6"));
		JButton sevenButton = new JButton(getActionMap().get("7"));
		JButton eightButton = new JButton(getActionMap().get("8"));
		JButton nineButton = new JButton(getActionMap().get("9"));
		JButton decimalButton = new JButton(getActionMap().get("."));

		JPanel gridPanel = new JPanel(new GridLayout(3, 3));
		gridPanel.add(sevenButton);
		gridPanel.add(eightButton);
		gridPanel.add(nineButton);
		gridPanel.add(fourButton);
		gridPanel.add(fiveButton);
		gridPanel.add(sixButton);
		gridPanel.add(oneButton);
		gridPanel.add(twoButton);
		gridPanel.add(threeButton);

		JPanel bottomPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c;
		c = new GridBagConstraints(0, 0, 2, 1, 0.75, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0);
		bottomPanel.add(zeroButton, c);
		c = new GridBagConstraints(2, 0, 1, 1, 0.25, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0);
		bottomPanel.add(decimalButton, c);

		JPanel numberPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.75,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		numberPanel.add(gridPanel, c);
		c = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.25,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		numberPanel.add(bottomPanel, c);

		textField = new JTextField();

		applicationWindow.getContentPane().add(textField, BorderLayout.NORTH);
		applicationWindow.getContentPane()
				.add(numberPanel, BorderLayout.CENTER);
		applicationWindow.getContentPane().add(mathOperationsPanel,
				BorderLayout.EAST);
	}

	@Override
	public void setApplicationIcon(Icon applicationIcon) {
		getApplicationWindow()
				.setIconImages(
						Arrays.asList(
								Utils.iconToImage(Utils
										.imageIcon("example/resources/calculator-16x16.png")),
								Utils.iconToImage(Utils
										.imageIcon("example/resources/calculator-24x24.png")),
								Utils.iconToImage(Utils
										.imageIcon("example/resources/calculator-32x32.png")),
								Utils.iconToImage(Utils
										.imageIcon("example/resources/calculator-48x48.png"))));
	}

	@Override
	public Preferences getApplicationPreferences() {
		return super.getApplicationPreferences("example.calculator.gui");
	}

	@Override
	public String getVersion() {
		return "beta";
	}

}

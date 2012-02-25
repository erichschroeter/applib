package usr.erichschroeter.applib.calculator;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class CalculatorGui {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				CalculatorGuiApplication calculator = new CalculatorGuiApplication();
				calculator.run();
				calculator.getApplicationWindow().pack();
			}
		});
	}

}

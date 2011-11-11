package example.calculator;

import usr.erichschroeter.applib.DesktopApplicationImpl;

public class CalculatorApplication extends DesktopApplicationImpl {

	/** The math operation the calculator is to perform. */
	private MathOperation operation;

	public CalculatorApplication(MathOperation operation) {
		this.operation = operation;
	}

	@Override
	public String getVersion() {
		return "beta";
	}

	@Override
	public void run(Object... args) {
		if (operation != null) {
			try {
				operation.perform();
				Number result = operation.getResult();
				System.out.print(result);
			} catch (OperationException e) {
				e.printStackTrace();
			}
		}
	}

}

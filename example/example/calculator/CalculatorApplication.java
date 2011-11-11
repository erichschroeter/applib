package example.calculator;

import usr.erichschroeter.applib.DesktopApplicationImpl;
import usr.erichschroeter.applib.Lifecycle;
import usr.erichschroeter.applib.LifecycleEvent;

/**
 * The <code>CalculatorApplication</code> handles performing a
 * {@link MathOperation} and printing the result to standard output.
 * 
 * @author Erich Schroeter
 */
public class CalculatorApplication extends DesktopApplicationImpl {

	/** The math operation the calculator is to perform. */
	private MathOperation operation;

	public CalculatorApplication(MathOperation operation) {
		super();
		this.operation = operation;
	}

	@Override
	public String getVersion() {
		return "beta";
	}

	@Override
	public void run() {
		if (operation != null) {
			try {
				operation.perform();
				Number result = operation.getResult();
				System.out.print(result);
			} catch (OperationException e) {
				e.printStackTrace();
			}
		}
		fireLifecycleChange(new LifecycleEvent(this, Lifecycle.STARTED));
	}

}

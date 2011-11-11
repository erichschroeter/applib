package example.calculator.operations;

import java.math.BigDecimal;

import example.calculator.MathOperation;
import example.calculator.OperationException;

/**
 * The <code>AddOperation</code> adds multiple numbers together.
 * 
 * @author Erich Schroeter
 */
public class AddOperation implements MathOperation {

	/** The numbers to add. */
	private Number[] numbers;
	/** The result of the addition. */
	private Number result;

	public AddOperation(Number... numbers) {
		this.numbers = numbers;
	}

	@Override
	public void perform() throws OperationException {
		result = 0;
		if (numbers != null && numbers.length > 0) {
			for (Number number : numbers) {
				result = new BigDecimal(result.doubleValue())
						.add(new BigDecimal(number.doubleValue()));
			}
		}
	}

	@Override
	public Number getResult() {
		return result;
	}

}

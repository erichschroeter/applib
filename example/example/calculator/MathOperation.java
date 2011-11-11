package example.calculator;

/**
 * A <code>MathOperation</code> is an {@link Operation} which performs a math
 * operation and results in a value after performing the operation's logic.
 * 
 * @author Erich Schroeter
 */
public interface MathOperation extends Operation {

	/**
	 * Returns the result of the operation logic.
	 * 
	 * @return the value of the math operation, or <code>null</code> if the
	 *         operation has not been performed yet or could not be performed
	 */
	public Number getResult();

}

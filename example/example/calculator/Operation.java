package example.calculator;

/**
 * An <code>Operation</code> interface provides methods for objects that can
 * perform logic to do so when called upon.
 * 
 * @author Erich Schroeter
 */
public interface Operation {

	/**
	 * Performs the operation's logic.
	 * 
	 * @throws OperationException
	 *             if an error occurs while performing the operation
	 */
	public void perform() throws OperationException;

}

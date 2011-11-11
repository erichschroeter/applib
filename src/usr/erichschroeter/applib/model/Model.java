package usr.erichschroeter.applib.model;

/**
 * A <code>Model</code> wraps a model object. This class simply provides an
 * abstraction level to decouple the actual model from the framework.
 * 
 * @author Erich Schroeter
 * 
 * @param <T>
 *            the model object type
 */
public class Model<T> {

	/** The model object being wrapped. */
	private T model;

	/**
	 * Constructs a <code>Model</code> specifying the model object to wrap.
	 * 
	 * @param model
	 *            the model object to wrap
	 */
	public Model(T model) {
		this.model = model;
	}

	/**
	 * Returns the wrapped model object.
	 * 
	 * @return
	 */
	public T getModel() {
		return model;
	}
}

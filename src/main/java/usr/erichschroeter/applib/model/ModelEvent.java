package usr.erichschroeter.applib.model;

import java.util.EventObject;

/**
 * A <code>ModelEvent</code> object is the root class from which all model
 * events shall be derived. It provides methods for accessing the {@link Model}
 * object for which the event is about.
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public class ModelEvent extends EventObject {

	/** The model for which the event is for. */
	private Model<?> model;

	/**
	 * Constructs a <code>ModelEvent</code> specifying the model for which the
	 * event is for.
	 * 
	 * @param source
	 *            the object on which the event initially occurred
	 * @param model
	 *            the model for which the event is for
	 */
	public ModelEvent(Object source, Model<?> model) {
		super(source);
		this.model = model;
	}

	/**
	 * Returns the model for which this event occurred for.
	 * 
	 * @return the model
	 */
	public Model<?> getModel() {
		return model;
	}

}

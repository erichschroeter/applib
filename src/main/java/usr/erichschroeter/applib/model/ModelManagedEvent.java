package usr.erichschroeter.applib.model;

/**
 * A <code>ModelRegistrationEvent</code> gets delivered when a model has been
 * managed by the {@link ModelManager}.
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public class ModelManagedEvent extends ModelEvent {

	/** The action representing registration. */
	public static final int REGISTERED = 0;
	/** The action representing unregistration. */
	public static final int UNREGISTERED = 1;

	/** The action for which this event was initiated on. */
	private int action;

	/**
	 * 
	 * @param source
	 *            the object on which the event initially occurred
	 * @param model
	 *            the model for which the event is for
	 * @param action
	 *            the action for which this event was initiated on
	 */
	public ModelManagedEvent(Object source, Model<?> model, int action) {
		super(source, model);
		this.action = action;
	}

	/**
	 * Returns what management action this event occurred for.
	 * 
	 * @return the management action value
	 */
	public int getAction() {
		return action;
	}

}

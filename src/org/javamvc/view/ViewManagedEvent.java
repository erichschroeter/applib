package org.javamvc.view;

/**
 * A <code>ViewRegistrationEvent</code> gets delivered when a view has been
 * managed by the {@link ViewManager}.
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public class ViewManagedEvent extends ViewEvent {

	/** The action representing registration. */
	public static int REGISTERED = 0;
	/** The action representing unregistration. */
	public static int UNREGISTERED = 1;
	/** The action representing the focused view changing. */
	public static int FOCUSED = 2;

	/** The action for which this event was initiated on. */
	private int action;
	/** The view identifier. */
	private String identifier;

	/**
	 * 
	 * @param source
	 *            the object on which the event initially occurred
	 * @param view
	 *            the view for which the event is for
	 * @param action
	 *            the action for which this event was initiated on
	 */
	public ViewManagedEvent(Object source, View<?> view, String identifier,
			int action) {
		super(source, view);
		this.action = action;
		this.identifier = identifier;
	}

	/**
	 * Returns what management action this event occurred for.
	 * 
	 * @return the management action value
	 */
	public int getAction() {
		return action;
	}

	/**
	 * Returns the view identifier the view manager identifies the view by.
	 * 
	 * @return the view identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

}

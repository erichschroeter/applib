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

	/** The action for which this event was initiated on. */
	private int action;

	/**
	 * 
	 * @param source
	 *            the object on which the event initially occurred
	 * @param view
	 *            the view for which the event is for
	 * @param action
	 *            the action for which this event was initiated on
	 */
	public ViewManagedEvent(Object source, View view, int action) {
		super(source, view);
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

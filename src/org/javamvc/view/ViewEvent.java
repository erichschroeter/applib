package org.javamvc.view;

import java.util.EventObject;

/**
 * A <code>ViewEvent</code> object is the root class from which all view events
 * shall be derived. It provides methods for accessing the {@link View} object
 * for which the event is about.
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public class ViewEvent extends EventObject {

	/** The view for which the event is for. */
	private View<?> view;

	/**
	 * Constructs a <code>ViewEvent</code> specifying the view for which the
	 * event is for.
	 * 
	 * @param source
	 *            the object on which the event initially occurred
	 * @param view
	 *            the view for which the event is for
	 */
	public ViewEvent(Object source, View<?> view) {
		super(source);
		this.view = view;
	}

	/**
	 * Returns the view for which this event occurred for.
	 * 
	 * @return the view
	 */
	public View<?> getView() {
		return view;
	}

}

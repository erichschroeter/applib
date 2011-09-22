package org.javamvc.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * A <code>ViewManager</code> manages multiple views for an application. It
 * provides a means to access views throughout the application that have been
 * registered.
 * 
 * @author Erich Schroeter
 */
public class ViewManager {

	/** The map of view keys to the View instance. */
	private Map<String, View<?>> views;
	/**
	 * Whether to auto unregister a view when trying to register a key already
	 * being used.
	 */
	private boolean autoUnregister;
	/** The listeners to be notified when a management event is fired. */
	private List<ViewManagerListener> listeners;

	public ViewManager() {
		views = new HashMap<String, View<?>>();
		listeners = new Vector<ViewManagerListener>();
	}

	/**
	 * Returns whether the view manager is configured to auto unregister an
	 * existing key being used to map a view.
	 * 
	 * @return <code>true</code> if auto unregister is enabled,
	 *         <code>false</code> if disabled
	 */
	public boolean isAutoUnregister() {
		return autoUnregister;
	}

	/**
	 * Sets whether the view manager should auto unregister an existing key
	 * being used to map a view.
	 * 
	 * @param autoUnregister
	 *            <code>true</code> to enable auto unregister,
	 *            <code>false</code> to disable
	 */
	public void setAutoUnregister(boolean autoUnregister) {
		this.autoUnregister = autoUnregister;
	}

	/**
	 * Returns the <code>View</code> mapped by the specified <code>key</code>.
	 * 
	 * @param key
	 *            the key mapped to the view
	 * @return the mapped view, or <code>null</code> if <code>key</code> doesn't
	 *         exist
	 */
	public View<?> getView(String key) {
		return views.get(key);
	}

	/**
	 * Handles notifying the view manager listeners with the <code>event</code>.
	 * 
	 * @param event
	 *            the event to pass to the listeners
	 */
	protected void fireViewManagedEvent(ViewManagedEvent event) {
		for (ViewManagerListener l : listeners) {
			l.wasManaged(event);
		}
	}

	/**
	 * Registers the <code>view</code> with the view manager. The
	 * <code>key</code> should be used for accessing the <code>view</code>.
	 * <p>
	 * If auto unregister is enabled and the <code>key</code> is being used
	 * already, the mapped view will be unregistered before registering the
	 * specified <code>view</code>. If auto unregister is disabled (default),
	 * then nothing changes.
	 * <p>
	 * A {@link ViewManagedEvent} is fired when successfully registered.
	 * 
	 * @param key
	 *            the key to access the <code>view</code> in the view manager
	 * @param view
	 *            the view being mapped to <code>key</code>
	 */
	public void registerView(String key, View<?> view) {
		if (!views.containsKey(key)) {
			// key not being used, so simply add to the map
			views.put(key, view);
			fireViewManagedEvent(new ViewManagedEvent(this, view,
					ViewManagedEvent.REGISTERED));
		} else {
			// key being used by another view, so we have to unregister it if
			// we are going to use the same key
			if (isAutoUnregister()) {
				unregisterView(key);
				views.put(key, view);
				fireViewManagedEvent(new ViewManagedEvent(this, view,
						ViewManagedEvent.REGISTERED));
			}
		}
	}

	/**
	 * Unregisters the mapping between the <code>key</code> and the {@link View}
	 * it was mapped to.
	 * <p>
	 * A {@link ViewManagedEvent} is fired when successfully unregistered.
	 * 
	 * @param key
	 *            the key currently mapped to a view
	 */
	public void unregisterView(String key) {
		if (views.containsKey(key)) {
			View<?> view = views.remove(key);
			fireViewManagedEvent(new ViewManagedEvent(this, view,
					ViewManagedEvent.UNREGISTERED));
		}
	}

}

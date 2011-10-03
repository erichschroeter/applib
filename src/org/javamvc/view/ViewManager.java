package org.javamvc.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * A <code>ViewManager</code> manages multiple views for an application. It
 * provides a means to access views throughout the application that have been
 * registered.
 * <p>
 * One feature the view manager has is the ability to set the focus on one
 * particular {@link View}. This may be useful in cases where you want to see
 * which view the user is currently focused on; keep in mind that the
 * application must specifically set the focused view though (the
 * <code>ViewManager</code> does not handle this automatically; it just keeps a
 * reference to the focused view).
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
	/** The view which currently has focus. */
	private View<?> focusedView;

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
	 * Returns the focused view.
	 * <p>
	 * This must be set specifically by the application, as the view manager
	 * does not handle this automatically.
	 * 
	 * @return the focused view, or <code>null</code> if no view is focused
	 */
	public View<?> getFocusedView() {
		return focusedView;
	}

	/**
	 * Sets the focused view. If <code>focusedView</code> is <code>null</code>
	 * or is not registered, nothing is changed.
	 * <p>
	 * This must be set specifically by the application, as the view manager
	 * does not handle this automatically.
	 * <p>
	 * A {@link ViewManagedEvent} is fired when the focus is successfully
	 * changed.
	 * 
	 * @param focusedView
	 *            the view which currently has focus
	 */
	public void setFocusedView(String focusedView) {
		if (focusedView != null && views.containsKey(focusedView)) {
			this.focusedView = views.get(focusedView);
			fireViewManagedEvent(new ViewManagedEvent(this, this.focusedView,
					focusedView, ViewManagedEvent.FOCUSED));
		}
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
	 * Returns whether the specified <code>key</code> is registered to a
	 * {@link View}.
	 * 
	 * @param key
	 *            the view identifier to check
	 * @return <code>true</code> if <code>key</code> is registered, else
	 *         <code>false</code>
	 */
	public boolean isRegistered(String key) {
		return views.containsKey(key);
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
		if (!isRegistered(key)) {
			// key not being used, so simply add to the map
			views.put(key, view);
			fireViewManagedEvent(new ViewManagedEvent(this, view, key,
					ViewManagedEvent.REGISTERED));
		} else {
			// key being used by another view, so we have to unregister it if
			// we are going to use the same key
			if (isAutoUnregister()) {
				unregisterView(key);
				views.put(key, view);
				fireViewManagedEvent(new ViewManagedEvent(this, view, key,
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
		if (isRegistered(key)) {
			View<?> view = views.remove(key);
			fireViewManagedEvent(new ViewManagedEvent(this, view, key,
					ViewManagedEvent.UNREGISTERED));
		}
	}

	/**
	 * Adds a listener to be notified of management events.
	 * <p>
	 * If <code>listener</code> is <code>null</code>, this method does nothing.
	 * 
	 * @param listener
	 *            the listener to add
	 */
	public void addViewManagerListener(ViewManagerListener listener) {
		if (listener != null) {
			listeners.add(listener);
		}
	}

	/**
	 * Removes a listener from being notified of management events.
	 * <p>
	 * If <code>listener</code> is <code>null</code>, this method does nothing.
	 * 
	 * @param listener
	 *            the listener to remove
	 */
	public void removeViewManagerListener(ViewManagerListener listener) {
		if (listener != null) {
			listeners.remove(listener);
		}
	}

}

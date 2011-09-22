package org.javamvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.Icon;

/**
 * <code>DesktopApplication</code> provides a default implementation of the
 * {@link IDesktopApplication} interface.
 * <p>
 * The properties available in this class are in the table below.
 * <table>
 * <thead>
 * <tr>
 * <th>Property</th>
 * <th>Description</th>
 * </tr>
 * </thead><tbody>
 * <tr>
 * <td><code>"application.icon"</code></td>
 * <td>The application icon</td>
 * </tr>
 * </tbody>
 * </table>
 * 
 * @author Erich Schroeter
 */
public abstract class DesktopApplication implements IDesktopApplication,
		IPropertyChangeNotifier, ILifecycleNotifier {

	/** Used for managing property listeners. */
	protected PropertyChangeSupport properties;
	/**
	 * The container keeping track of objects listening for any/all
	 * <code>Lifecycle</code> types.
	 */
	protected List<ILifecycleListener> lifecycleListeners;
	/**
	 * The container keeping track of objects listening for specific
	 * <code>Lifecycle</code> type.
	 */
	protected Map<Lifecycle, List<ILifecycleListener>> specificLifecycleListeners;
	/** The reference to the application icon. */
	protected Icon applicationIcon;
	/** The state of the application in its life cycle. */
	protected Lifecycle applicationLifecycleState;

	/**
	 * Constructs a default <code>DesktopApplication</code>. This initializes
	 * the application preferences by calling
	 * {@link #installApplicationPreferences()}.
	 */
	public DesktopApplication() {
		properties = new PropertyChangeSupport(this);
		installApplicationPreferences();
	}

	@Override
	public void exit() {
		exit(0);
	}

	/**
	 * Exits the application specifying the error code to print to the console.
	 * The typical value of <code>code</code> is 0 if everything is ok.
	 * <p>
	 * This is equivalent to
	 * 
	 * <pre>
	 * {@code
	 *  fireLifecycleChange(Lifecycle.STOPPING);
	 *  fireLifecycleChange(Lifecycle.STOPPED);
	 *  System.exit(code);
	 * }
	 * </pre>
	 * 
	 * @param code
	 *            the error code
	 */
	@Override
	public void exit(int code) {
		fireLifecycleChange(Lifecycle.STOPPING);
		fireLifecycleChange(Lifecycle.STOPPED);
		System.exit(code);
	}

	@Override
	public Icon getApplicationIcon() {
		return applicationIcon;
	}

	/**
	 * Sets the application icon. This is the icon to be associated with the
	 * application and may be displayed in multiple locations depending on the
	 * platform (e.g. Windows, Linux, Mac, etc).
	 * <p>
	 * A <code>PropertyChangeEvent</code> is fired when the application icon is
	 * changed.
	 * 
	 * @param applicationIcon
	 *            the application icon
	 */
	@Override
	public void setApplicationIcon(Icon applicationIcon) {
		Icon old = getApplicationIcon();
		this.applicationIcon = applicationIcon;
		firePropertyChange("application.icon", old, applicationIcon);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This is equivalent to <code>getApplicationPreferences(null)</code>.
	 * <p>
	 * A suggestion for derived classes is to override this method by calling
	 * {@link #getApplicationPreferences(String)} with a unique path for your
	 * application.
	 */
	@Override
	public Preferences getApplicationPreferences() {
		return getApplicationPreferences(null);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * If <code>node</code> is <code>null</code> then
	 * {@link Preferences#userRoot()} is returned.
	 */
	@Override
	public Preferences getApplicationPreferences(String node) {
		Preferences prefs = null;
		if (node == null) {
			prefs = Preferences.userRoot();
		} else {
			prefs = Preferences.userRoot().node(node);
		}
		return prefs;
	}

	/**
	 * Installs the preferences for the desktop application. This can be
	 * overridden in derived classes to add additional preferences or to
	 * override the preferences themselves. Make sure to call
	 * <code>super.installApplicationPreferences()</code> to ensure the default
	 * preferences are kept.
	 */
	protected void installApplicationPreferences() {
	}

	//
	// IPropertyChangeNotifier members
	//

	/**
	 * Add a PropertyChangeListener to the listener list. The listener is
	 * registered for all properties. The same listener object may be added more
	 * than once, and will be called as many times as it is added. If
	 * <code>listener</code> is <code>null</code>, no exception is thrown and no
	 * action is taken.
	 * 
	 * @param listener
	 *            the property change listener to be added
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		properties.addPropertyChangeListener(listener);
	}

	/**
	 * Add a PropertyChangeListener for a specific property. The listener will
	 * be invoked only when a call on firePropertyChange names that specific
	 * property. The same listener object may be added more than once. For each
	 * property, the listener will be invoked the number of times it was added
	 * for that property. If <code>propertyName</code> or <code>listener</code>
	 * is <code>null</code>, no exception is thrown and no action is taken.
	 * 
	 * @param propertyName
	 *            the name of the property to listen on
	 * @param listener
	 *            the property change listener to be added
	 */
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		properties.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * Removes a <code>PropertyChangeListener</code> from the listener list.
	 * This removes a PropertyChangeListener that was registered for all
	 * properties. If <code>listener</code> was added more than once to the same
	 * event source, it will be notified one less time after being removed. If
	 * <code>listener</code> is <code>null</code>, or was never added, no
	 * exception is thrown and no action is taken.
	 * 
	 * @param listener
	 *            the property change listener to be removed
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		properties.removePropertyChangeListener(listener);
	}

	/**
	 * Remove a PropertyChangeListener for a specific property. If
	 * <code>listener</code> was added more than once to the same event source
	 * for the specified property, it will be notified one less time after being
	 * removed. If <code>propertyName</code> is null, no exception is thrown and
	 * no action is taken. If <code>listener</code> is <code>null</code>, or was
	 * never added for the specified property, no exception is thrown and no
	 * action is taken.
	 * 
	 * @param propertyName
	 *            the name of the property that was listened on
	 * @param listener
	 *            the property change listener to be removed
	 */
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		properties.removePropertyChangeListener(propertyName, listener);
	}

	/**
	 * Returns an array of all the listeners that were added to the
	 * PropertyChangeSupport object with addPropertyChangeListener().
	 * <p>
	 * If some listeners have been added with a named property, then the
	 * returned array will be a mixture of PropertyChangeListeners and
	 * <code>PropertyChangeListenerProxy</code>s. If the calling method is
	 * interested in distinguishing the listeners then it must test each element
	 * to see if it's a <code>PropertyChangeListenerProxy</code>, perform the
	 * cast, and examine the parameter.
	 * 
	 * <pre>
	 * PropertyChangeListener[] listeners = bean.getPropertyChangeListeners();
	 * for (int i = 0; i &lt; listeners.length; i++) {
	 * 	if (listeners[i] instanceof PropertyChangeListenerProxy) {
	 * 		PropertyChangeListenerProxy proxy = (PropertyChangeListenerProxy) listeners[i];
	 * 		if (proxy.getPropertyName().equals(&quot;foo&quot;)) {
	 * 			// proxy is a PropertyChangeListener which was associated
	 * 			// with the property named &quot;foo&quot;
	 * 		}
	 * 	}
	 * }
	 * </pre>
	 * 
	 * @return all of the <code>PropertyChangeListener</code>s added or an empty
	 *         array if no listeners have been added
	 */
	public PropertyChangeListener[] getPropertyChangeListeners() {
		return properties.getPropertyChangeListeners();
	}

	/**
	 * Returns an array of all the listeners which have been associated with the
	 * named property.
	 * 
	 * @param propertyName
	 *            the name of the property being listened to
	 * @return all of the <code>PropertyChangeListeners</code> associated with
	 *         the named property. If no such listeners have been added, or if
	 *         <code>propertyName</code> is <code>null</code>, an empty array is
	 *         returned.
	 */
	public PropertyChangeListener[] getPropertyChangeListeners(
			String propertyName) {
		return properties.getPropertyChangeListeners(propertyName);
	}

	/**
	 * Report a bound property update to any registered listeners. No event is
	 * fired if old and new are equal and non-null.
	 * <p>
	 * This is merely a convenience wrapper around the more general
	 * firePropertyChange method that takes <code>PropertyChangeEvent</code>
	 * value.
	 * 
	 * @param propertyName
	 *            one of the property names listed above
	 * @param oldValue
	 *            the old value of the property
	 * @param newValue
	 *            the new value of the property
	 */
	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		properties.firePropertyChange(propertyName, oldValue, newValue);
	}

	//
	// ILifecycleNotifier members
	//

	@Override
	public void addLifecycleListener(ILifecycleListener listener) {
		// don't instantiate until it's needed
		lifecycleListeners = lifecycleListeners != null ? lifecycleListeners
				: new Vector<ILifecycleListener>();
		lifecycleListeners.add(listener);
	}

	@Override
	public void addLifecycleListener(Lifecycle lifecycle,
			ILifecycleListener listener) {
		// don't instantiate anything until it's needed
		specificLifecycleListeners = specificLifecycleListeners != null ? specificLifecycleListeners
				: new HashMap<Lifecycle, List<ILifecycleListener>>();
		// if a list doesn't exist, create it
		if (!specificLifecycleListeners.containsKey(lifecycle)) {
			specificLifecycleListeners.put(lifecycle,
					new Vector<ILifecycleListener>());
		}
		specificLifecycleListeners.get(lifecycle).add(listener);
	}

	@Override
	public void removeLifecycleListener(ILifecycleListener listener) {
		if (lifecycleListeners != null && listener != null) {
			lifecycleListeners.remove(listener);
		}
	}

	@Override
	public void removeLifecycleListener(Lifecycle lifecycle,
			ILifecycleListener listener) {
		if (specificLifecycleListeners != null && listener != null
				&& specificLifecycleListeners.containsKey(lifecycle)) {
			specificLifecycleListeners.get(lifecycle).remove(listener);
		}
	}

	@Override
	public List<ILifecycleListener> getLifecycleListeners() {
		return lifecycleListeners;
	}

	@Override
	public List<ILifecycleListener> getLifecycleListeners(Lifecycle lifecycle) {
		return specificLifecycleListeners.get(lifecycle);
	}

	/**
	 * Handles notifying the <code>LifecycleListener</code>s for the specific
	 * <code>lifecycle</code>.
	 * <p>
	 * This notifies both listeners for all lifecycle types as well as listeners
	 * for a specific lifecycle types. Specific lifecycle listeners are notified
	 * before listeners for all lifecycle types.
	 * 
	 * @param lifecycle
	 *            the lifecycle type representing the state of the application
	 */
	protected void fireLifecycleChange(Lifecycle lifecycle) {
		// notify listeners for specific properties
		if (specificLifecycleListeners != null
				&& specificLifecycleListeners.containsKey(lifecycle)) {
			for (ILifecycleListener l : specificLifecycleListeners
					.get(lifecycle)) {
				l.lifecycleChanged(new LifecycleChangeEvent(this, lifecycle));
			}
		}
		if (lifecycleListeners != null) {
			// notify listeners for all properties
			for (ILifecycleListener l : lifecycleListeners) {
				l.lifecycleChanged(new LifecycleChangeEvent(this, lifecycle));
			}
		}
	}

}
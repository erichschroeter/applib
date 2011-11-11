package usr.erichschroeter.applib;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

import javax.swing.Icon;
import javax.swing.event.EventListenerList;

/**
 * <code>DesktopApplication</code> provides a default implementation of the
 * {@link DesktopApplication} interface.
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
 * <p>
 * The application preferences have the ability to automatically be saved upon
 * exiting the application. To enable this feature use the
 * {@link DesktopApplicationImpl#setSavePreferencesOnExit(boolean)}.
 * 
 * @author Erich Schroeter
 */
public abstract class DesktopApplicationImpl implements DesktopApplication {

	/** Used for managing property listeners. */
	protected transient PropertyChangeSupport properties;
	/** Listeners to be notified of any and all lifecycle events. */
	protected transient EventListenerList lifecycleListeners;
	/** Listeners to be notified of a specific lifecycle event. */
	protected transient Map<Lifecycle, EventListenerList> specificLifecycleListeners;
	/** The reference to the application icon. */
	protected Icon applicationIcon;
	/** The state of the application in its life cycle. */
	protected Lifecycle applicationLifecycleState;
	/** Whether the preferences will be saved on exiting the application. */
	private boolean savePreferencesOnExit;

	/**
	 * Constructs a default <code>DesktopApplication</code>. This initializes
	 * the application preferences by calling
	 * {@link #installApplicationPreferences(Preferences)} passing the result of
	 * {@link #getApplicationPreferences()}.
	 * 
	 * @see #getApplicationPreferences()
	 * @see #installApplicationPreferences(Preferences)
	 * @see #initializeApplication(Object...)
	 * @param objects
	 *            objects that need initialization in
	 *            <code>initializeApplication(Object...)</code>
	 */
	public DesktopApplicationImpl(Object... objects) {
		properties = new PropertyChangeSupport(this);
		lifecycleListeners = new EventListenerList();
		initializeApplication(objects);
		installApplicationPreferences(getApplicationPreferences());
	}

	/**
	 * Initializes the desktop application.
	 * <p>
	 * This method is the first method called when an application is
	 * instantiated. Any objects that need initialization in a derived
	 * constructor should be initialized in this method, which is guaranteed to
	 * be called before returning to the derived class's constructor (assuming
	 * that the <code>DesktopApplication</code> constructor is called).
	 * 
	 * @param objects
	 *            objects that need initialization
	 */
	protected void initializeApplication(Object... objects) {
	}

	/**
	 * Starts the application with no arguments.
	 * <p>
	 * This is equivalent to <code>run((Object[]) null)</code>.
	 * 
	 * @see #run(Object...)
	 */
	public void run() {
		run((Object[]) null);
	}

	/**
	 * Starts the application with the specified <code>args</code>. This method
	 * should handle things such as
	 * <ul>
	 * <li>processing the <code>args</code></li>
	 * <li>invoking application life cycle events</li>
	 * <li>showing the application GUI</li>
	 * </ul>
	 * <p>
	 * This is equivalent to <code>run((Object[]) args)</code>.
	 * 
	 * @see #run(Object...)
	 * @param args
	 *            arguments from the command line
	 */
	public void run(String... args) {
		run((Object[]) args);
	}

	/**
	 * Exits the application specifying the error code of 0 to indicate that
	 * everything is ok. This will automatically save the application
	 * preferences if the feature is enabled.
	 * <p>
	 * This is equivalent to <code>exit(0)</code>.
	 * 
	 * @see #exit(int)
	 * @see #isSavePreferencesOnExit()
	 * @see #setSavePreferencesOnExit(boolean)
	 */
	public void exit() {
		exit(0);
	}

	/**
	 * Exits the application specifying the error code to print to the console.
	 * The typical value of <code>code</code> is 0 if everything is ok. This
	 * will automatically save the application preferences if the feature is
	 * enabled.
	 * <p>
	 * This is equivalent to
	 * 
	 * <pre>
	 * {@code
	 *  fireLifecycleChange(Lifecycle.STOPPING);
	 *  if (isSavePreferencesOnExit()) {
	 *   saveApplicationPreferences();
	 *  }
	 *  fireLifecycleChange(Lifecycle.STOPPED);
	 *  System.exit(code);
	 * }
	 * </pre>
	 * 
	 * @see #exit()
	 * @see #isSavePreferencesOnExit()
	 * @see #setSavePreferencesOnExit(boolean)
	 * @param code
	 *            the error code
	 */
	@Override
	public void exit(int code) {
		fireLifecycleChange(new LifecycleEvent(this, Lifecycle.STOPPING));
		if (isSavePreferencesOnExit()) {
			saveApplicationPreferences();
		}
		fireLifecycleChange(new LifecycleEvent(this, Lifecycle.STOPPED));
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
	 * Installs the preferences for the desktop application.
	 * <p>
	 * This can be overridden in derived classes to add additional preferences
	 * or to override the preferences themselves. Make sure to call
	 * <code>super.installApplicationPreferences(preferences)</code> to ensure
	 * the default preferences are kept.
	 * 
	 * @see #saveApplicationPreferences()
	 * @param preferences
	 *            the application preferences node
	 */
	protected void installApplicationPreferences(Preferences preferences) {
	}

	/**
	 * Saves the preferences for the desktop application.
	 * <p>
	 * This can be overridden in derived classes to save preferences added or
	 * overridden in derived classes. Make sure to call
	 * <code>super.installApplicationPreferences()</code> to ensure the default
	 * preference values are saved.
	 * 
	 * @see #installApplicationPreferences()
	 */
	protected void saveApplicationPreferences() {
	}

	/**
	 * Returns whether the application will save the preferences on exit.
	 * 
	 * @see #exit()
	 * @see #exit(int)
	 * @return <code>true</code> if they will be saved on exit,
	 *         <code>false</code> if not
	 */
	public boolean isSavePreferencesOnExit() {
		return savePreferencesOnExit;
	}

	/**
	 * Sets whether the application will save the preferences on exit.
	 * 
	 * @see #exit()
	 * @see #exit(int)
	 * @param enable
	 *            <code>true</code> to save on exit, <code>false</code> to not
	 */
	public void setSavePreferencesOnExit(boolean enable) {
		this.savePreferencesOnExit = enable;
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

	public void addLifecycleListener(LifecycleListener listener) {
		// don't instantiate until it's needed
		if (lifecycleListeners == null) {
			lifecycleListeners = new EventListenerList();
		}
		lifecycleListeners.add(LifecycleListener.class, listener);
	}

	public void addLifecycleListener(Lifecycle lifecycle,
			LifecycleListener listener) {
		// don't instantiate anything until it's needed
		if (specificLifecycleListeners == null) {
			specificLifecycleListeners = new HashMap<Lifecycle, EventListenerList>();
		}
		// if a list doesn't exist, create it
		if (!specificLifecycleListeners.containsKey(lifecycle)) {
			specificLifecycleListeners.put(lifecycle, new EventListenerList());
		}
		specificLifecycleListeners.get(lifecycle).add(LifecycleListener.class,
				listener);
	}

	public void removeLifecycleListener(LifecycleListener listener) {
		if (lifecycleListeners != null && listener != null) {
			lifecycleListeners.remove(LifecycleListener.class, listener);
		}
	}

	public void removeLifecycleListener(Lifecycle lifecycle,
			LifecycleListener listener) {
		if (specificLifecycleListeners != null && listener != null
				&& specificLifecycleListeners.containsKey(lifecycle)) {
			specificLifecycleListeners.get(lifecycle).remove(
					LifecycleListener.class, listener);
		}
	}

	public LifecycleListener[] getLifecycleListeners() {
		return lifecycleListeners.getListeners(LifecycleListener.class);
	}

	public LifecycleListener[] getLifecycleListeners(Lifecycle lifecycle) {
		return specificLifecycleListeners.get(lifecycle).getListeners(
				LifecycleListener.class);
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
	protected void fireLifecycleChange(LifecycleEvent event) {
		// Guaranteed to return a non-null array
		Object[] listeners;
		if (specificLifecycleListeners != null
				&& specificLifecycleListeners.containsKey(event.getLifecycle())) {
			listeners = specificLifecycleListeners.get(event.getLifecycle())
					.getListenerList();
			// Process the listeners last to first, notifying
			// those that are interested in this event
			for (int i = listeners.length - 2; i >= 0; i -= 2) {
				if (listeners[i] == LifecycleListener.class) {
					((LifecycleListener) listeners[i + 1])
							.lifecycleChanged(event);
				}
			}
		}
		if (lifecycleListeners != null) {
			listeners = lifecycleListeners.getListenerList();
			// Process the listeners last to first, notifying
			// those that are interested in this event
			for (int i = listeners.length - 2; i >= 0; i -= 2) {
				if (listeners[i] == LifecycleListener.class) {
					((LifecycleListener) listeners[i + 1])
							.lifecycleChanged(event);
				}
			}
		}
	}

}

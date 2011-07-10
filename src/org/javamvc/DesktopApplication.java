package org.javamvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.Icon;

/**
 * <code>DesktopApplication</code> provides a default implementation of the
 * {@link IDesktopApplication} interface.
 * 
 * @author Erich Schroeter
 */
public abstract class DesktopApplication implements IDesktopApplication,
		IPropertyChangeNotifier, ILifecycleNotifier {

	/** The property identifier for the application icon */
	public static final String PROPERTY_APPLICATION_ICON = "application.icon";

	/**
	 * The container keeping track of objects listening for any/all property
	 * changes.
	 */
	protected List<PropertyChangeListener> propertyListeners;
	/**
	 * The container keeping track of objects listening for specific property
	 * changes.
	 */
	protected Map<String, List<PropertyChangeListener>> specificPropertyListeners;
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
		firePropertyChange(PROPERTY_APPLICATION_ICON, old, applicationIcon);
	}

	@Override
	public Preferences getApplicationPreferences() {
		return Preferences.userNodeForPackage(DesktopApplication.class);
	}

	//
	// IPropertyChangeNotifier members
	//

	/**
	 * Adds a <code>PropertyChangeListener</code> to the listener list. The
	 * <code>listener</code> is registered for all bound properties of this
	 * class.
	 * <p>
	 * <ul>
	 * <li>{@link #PROPERTY_NETWORK_ADDRESS} with value of
	 * {@value #PROPERTY_NETWORK_ADDRESS}</li>
	 * </ul>
	 * <p>
	 * If <code>listener</code> is <code>null</code>, no exception is thrown and
	 * no action is performed.
	 * 
	 * @param listener
	 *            the property change listener to be added
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// don't instantiate until it's needed
		propertyListeners = propertyListeners != null ? propertyListeners
				: new Vector<PropertyChangeListener>();
		propertyListeners.add(listener);
	}

	/**
	 * Adds a PropertyChangeListener to the listener list. The listener is
	 * registered for all bound properties of this class.
	 * <p>
	 * <ul>
	 * <li>{@link #PROPERTY_NETWORK_ADDRESS} with value of
	 * {@value #PROPERTY_NETWORK_ADDRESS}</li>
	 * </ul>
	 * <p>
	 * If <code>listener</code> is <code>null</code>, no exception is thrown and
	 * no action is performed
	 * 
	 * @param listener
	 *            the property change listener to be added
	 */
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		// don't instantiate anything until it's needed
		specificPropertyListeners = specificPropertyListeners != null ? specificPropertyListeners
				: new HashMap<String, List<PropertyChangeListener>>();
		// if a list doesn't exist, create it
		if (!specificPropertyListeners.containsKey(propertyName)) {
			specificPropertyListeners.put(propertyName,
					new Vector<PropertyChangeListener>());
		}
		specificPropertyListeners.get(propertyName).add(listener);
	}

	/**
	 * Removes a <code>PropertyChangeListener</code> from the listener list.
	 * This method should be used to remove <code>PropertyChangeListener</code>s
	 * that were registered for all bound properties of this class.
	 * <p>
	 * If <code>listener</code> is <code>null</code>, no exception is thrown and
	 * no action is performed.
	 * 
	 * @param listener
	 *            the property change listener to be removed
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		if (propertyListeners != null && listener != null) {
			propertyListeners.remove(listener);
		}
	}

	/**
	 * Removes a <code>PropertyChangeListener</code> from the listener list for
	 * a specific property of this class.
	 * <p>
	 * If <code>propertyName</code> or <code>listener</code> is
	 * <code>null</code>, no exception is thrown and no action is performed.
	 * <p>
	 * <ul>
	 * <li>{@link #PROPERTY_NETWORK_ADDRESS} with value of
	 * {@value #PROPERTY_NETWORK_ADDRESS}</li>
	 * </ul>
	 * 
	 * @param listener
	 *            the property change listener to be removed
	 */
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		if (specificPropertyListeners != null && listener != null
				&& specificPropertyListeners.containsKey(propertyName)) {
			specificPropertyListeners.get(propertyName).remove(listener);
		}
	}

	/**
	 * Returns the list of all the property change listeners registered for all
	 * properties of this class.
	 * <p>
	 * <ul>
	 * <li>{@link #PROPERTY_NETWORK_ADDRESS} with value of
	 * {@value #PROPERTY_NETWORK_ADDRESS}</li>
	 * </ul>
	 * 
	 * @return all of this class's <code>PropertyChangeListeners</code> or
	 *         <code>null</code> if no property change listeners are currently
	 *         registered
	 */
	public List<PropertyChangeListener> getPropertyChangeListeners() {
		return propertyListeners;
	}

	/**
	 * Returns the list of all the property change listeners registered for a
	 * specific property of this class.
	 * <p>
	 * <ul>
	 * <li>{@link #PROPERTY_NETWORK_ADDRESS} with value of
	 * {@value #PROPERTY_NETWORK_ADDRESS}</li>
	 * </ul>
	 * 
	 * @return all of this class's <code>PropertyChangeListeners</code> for the
	 *         specified <code>propertyName</code> or <code>null</code> if no
	 *         property change listeners are currently registered
	 */
	public List<PropertyChangeListener> getPropertyChangeListeners(
			String propertyName) {
		return specificPropertyListeners.get(propertyName);
	}

	/**
	 * Handles notifying the property listeners for the specific
	 * <code>propertyName</code>.
	 * <p>
	 * This notifies both listeners for all properties as well as listeners for
	 * a specific property. Specific property listeners are notified before
	 * listeners for all properties.
	 * <p>
	 * <ul>
	 * <li>{@link #PROPERTY_NETWORK_ADDRESS} with value of
	 * {@value #PROPERTY_NETWORK_ADDRESS}</li>
	 * </ul>
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
		// notify listeners for specific properties
		if (specificPropertyListeners != null
				&& specificPropertyListeners.containsKey(propertyName)) {
			for (PropertyChangeListener l : specificPropertyListeners
					.get(propertyName)) {
				l.propertyChange(new PropertyChangeEvent(this, propertyName,
						oldValue, newValue));
			}
		}
		if (propertyListeners != null) {
			// notify listeners for all properties
			for (PropertyChangeListener l : propertyListeners) {
				l.propertyChange(new PropertyChangeEvent(this, propertyName,
						oldValue, newValue));
			}
		}
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

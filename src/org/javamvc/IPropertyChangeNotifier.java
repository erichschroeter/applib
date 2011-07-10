package org.javamvc;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The <code>PropertyChangeNotifier</code> interface provides methods for
 * objects to notify {@link PropertyChangeListener} instances. This is simply a
 * convenience interface for automatically including methods via an IDE (such as
 * Eclipse) auto-generate feature.
 * <p>
 * Classes implementing this interface will need to provide methods for
 * notifying the <code>PropertyChangeListener</code>s such as
 * <ul>
 * <li>
 * <code>protected void firePropertyChange(String propertyName, Object oldValue,
 * Object newValue);</code></li>
 * </ul>
 * or for specific data types
 * <ul>
 * <li>
 * <code>protected void firePropertyChange(String propertyName, boolean oldValue,
 * boolean newValue);</code></li>
 * <li>
 * <code>protected void firePropertyChange(String propertyName, int oldValue, int
 * newValue);</code></li>
 * <li>
 * <code>protected void firePropertyChange(String propertyName, byte oldValue,
 * byte newValue);</code></li>
 * <li>
 * <code>protected void firePropertyChange(String propertyName, char oldValue,
 * char newValue);</code></li>
 * <li>
 * <code>protected void firePropertyChange(String propertyName, short oldValue,
 * short newValue);</code></li>
 * <li>
 * <code>protected void firePropertyChange(String propertyName, long oldValue,
 * long newValue);</code></li>
 * <li>
 * <code>protected void firePropertyChange(String propertyName, float oldValue,
 * float newValue);</code></li>
 * <li>
 * <code>protected void firePropertyChange(String propertyName, double oldValue,
 * double newValue);</code></li>
 * </ul>
 * <p>
 * A implementation example is provided below to cut down on implementation
 * time.
 * 
 * <pre>
 *  {@code
 *  
 * 	/** The property string used when the {&#064;link #address} changes *&#047;
 * 	public static final String PROPERTY_NETWORK_ADDRESS = "CHANGE_ME";
 * 
 * 	//
 * 	// PropertyChangeNotifier members
 * 	//
 * 
 * 	/**
 * 	 * Adds a &lt;code&gt;PropertyChangeListener&lt;/code&gt; to the listener list. The &lt;code&gt;listener&lt;/code&gt; is
 * 	 * registered for all bound properties of this class.
 * 	 * &lt;p&gt;
 * 	 * &lt;ul&gt;
 * 	 * &lt;li&gt;{&#064;link #PROPERTY_NETWORK_ADDRESS} with value of
 * 	 * {&#064;value #PROPERTY_NETWORK_ADDRESS}&lt;/li&gt;
 * 	 * &lt;/ul&gt;
 * 	 * &lt;p&gt;
 * 	 * If &lt;code&gt;listener&lt;/code&gt; is &lt;code&gt;null&lt;/code&gt;, no exception is
 * 	 * thrown and no action is performed.
 * 	 * 
 * 	 * &#064;param listener
 * 	 *            the property change listener to be added
 * 	 *&#047;
 * 	public void addPropertyChangeListener(PropertyChangeListener listener) {
 * 		// don't instantiate until it's needed
 * 		propertyListeners = propertyListeners != null ? propertyListeners
 * 				: new Vector&lt;PropertyChangeListener&gt;();
 * 		propertyListeners.add(listener);
 * 	}
 * 
 * 	/**
 * 	 * Adds a PropertyChangeListener to the listener list. The listener is
 * 	 * registered for all bound properties of this class.
 * 	 * &lt;p&gt;
 * 	 * &lt;ul&gt;
 * 	 * &lt;li&gt;{&#064;link #PROPERTY_NETWORK_ADDRESS} with value of
 * 	 * {&#064;value #PROPERTY_NETWORK_ADDRESS}&lt;/li&gt;
 * 	 * &lt;/ul&gt;
 * 	 * &lt;p&gt;
 * 	 * If &lt;code&gt;listener&lt;/code&gt; is &lt;code&gt;null&lt;/code&gt;, no exception is thrown and
 * 	 * no action is performed
 * 	 * 
 * 	 * &#064;param listener
 * 	 *         the property change listener to be added
 * 	 *&#047;
 * 	public void addPropertyChangeListener(String propertyName,
 * 			PropertyChangeListener listener) {
 * 		// don't instantiate anything until it's needed
 * 		specificPropertyListeners = specificPropertyListeners != null ? specificPropertyListeners
 * 				: new HashMap&lt;String, List&lt;PropertyChangeListener&gt;&gt;();
 * 		// if a list doesn't exist, create it
 * 		if (!specificPropertyListeners.containsKey(propertyName)) {
 * 			specificPropertyListeners.put(propertyName,
 * 					new Vector<&lt;PropertyChangeListener&gt;());
 * 		}
 * 		specificPropertyListeners.get(propertyName).add(listener);
 * 	}
 * 
 * 	/**
 * 	 * Removes a &lt;code&gt;PropertyChangeListener&lt;/code&gt; from the listener list.
 * 	 * This method should be used to remove &lt;code&gt;PropertyChangeListener&lt;/code&gt;s
 * 	 * that were registered for all bound properties of this class.
 * 	 * &lt;p&gt;
 * 	 * If &lt;code&gt;listener&lt;/code&gt; is &lt;code&gt;null&lt;/code&gt;, no exception is thrown and
 * 	 * no action is performed.
 * 	 * 
 * 	 * &#064;param listener
 * 	 *          the property change listener to be removed
 * 	 *&#047;
 * 	public void removePropertyChangeListener(PropertyChangeListener listener) {
 * 		if (listener != null) {
 * 			propertyListeners.remove(listener);
 * 		}
 * 	}
 * 
 * 	/**
 * 	 * Removes a &lt;code&gt;PropertyChangeListener&lt;/code&gt; from the listener list for
 * 	 * a specific property of this class.
 * 	 * &lt;p&gt;
 * 	 * If &lt;code&gt;propertyName&lt;/code&gt; or &lt;code&gt;listener&lt;/code&gt; is
 * 	 * &lt;code&gt;null&lt;/code&gt;, no exception is thrown and no action is performed.
 * 	 * &lt;p&gt;
 * 	 * &lt;ul&gt;
 * 	 * &lt;li&gt;{&#064;link #PROPERTY_NETWORK_ADDRESS} with value of
 * 	 * {&#064;value #PROPERTY_NETWORK_ADDRESS}&lt;/li&gt;
 * 	 * &lt;/ul&gt;
 * 	 * 
 * 	 * &#064;param listener
 * 	 *         the property change listener to be removed
 * 	 *&#047;
 * 	public void removePropertyChangeListener(String propertyName,
 * 			PropertyChangeListener listener) {
 * 		if (listener != null
 * 				&& specificPropertyListeners.containsKey(propertyName)) {
 * 			specificPropertyListeners.get(propertyName).remove(listener);
 * 		}
 * 	}
 * 
 * 	/**
 * 	 * Returns the list of all the property change listeners registered for all
 * 	 * properties of this class.
 * 	 * &lt;p&gt;
 * 	 * &lt;ul&gt;
 * 	 * &lt;li&gt;{&#064;link #PROPERTY_NETWORK_ADDRESS} with value of
 * 	 * {&#064;value #PROPERTY_NETWORK_ADDRESS}&lt;/li&gt;
 * 	 * &lt;/ul&gt;
 * 	 * 
 * 	 * &#064;return all of this class's &lt;code&gt;PropertyChangeListeners&lt;/code&gt; or
 * 	 *      &lt;code&gt;null&lt;/code&gt; if no property change listeners are currently
 * 	 *      registered
 * 	 *&#047;
 * 	public List&lt;PropertyChangeListener&gt; getPropertyChangeListeners() {
 * 		return propertyListeners;
 * 	}
 * 
 * 	/**
 * 	 * Returns the list of all the property change listeners registered for a
 * 	 * specific property of this class.
 * 	 * &lt;p&gt;
 * 	 * &lt;ul&gt;
 * 	 * &lt;li&gt;{&#064;link #PROPERTY_NETWORK_ADDRESS} with value of
 * 	 * {&#064;value #PROPERTY_NETWORK_ADDRESS}&lt;/li&gt;
 * 	 * &lt;/ul&gt;
 * 	 * 
 * 	 * &#064;return all of this class's &lt;code&gt;PropertyChangeListeners&lt;/code&gt; for the
 * 	 *      specified &lt;code&gt;propertyName&lt;/code&gt; or &lt;code&gt;null&lt;/code&gt; if no
 * 	 *      property change listeners are currently registered
 * 	 *&#047;
 * 	public List&lt;PropertyChangeListener&gt; getPropertyChangeListeners(
 * 			String propertyName) {
 * 		return specificPropertyListeners.get(propertyName);
 * 	}
 * 
 * 	/**
 * 	 * Handles notifying the property listeners for the specific
 * 	 * &lt;code&gt;propertyName&lt;/code&gt;.
 * 	 * &lt;p&gt;
 * 	 * This notifies both listeners for all properties as well as listeners for
 * 	 * a specific property. Specific property listeners are notified before
 * 	 * listeners for all properties.
 * 	 * &lt;p&gt;
 * 	 * &lt;ul&gt;
 * 	 * &lt;li&gt;{&#064;link #PROPERTY_NETWORK_ADDRESS} with value of
 * 	 * {&#064;value #PROPERTY_NETWORK_ADDRESS}&lt;/li&gt;
 * 	 * &lt;/ul&gt;
 * 	 * 
 * 	 * &#064;param propertyName
 * 	 *         one of the property names listed above
 * 	 * &#064;param oldValue
 * 	 *         the old value of the property
 * 	 * &#064;param newValue
 * 	 *         the new value of the property
 * 	 *&#047;
 * 	protected void firePropertyChange(String propertyName, Object oldValue,
 * 			Object newValue) {
 * 		// notify listeners for specific properties
 * 		if (specificPropertyListeners.containsKey(propertyName)) {
 * 			for (PropertyChangeListener l : specificPropertyListeners
 * 					.get(propertyName)) {
 * 				l.propertyChange(new PropertyChangeEvent(this, propertyName,
 * 						oldValue, newValue));
 * 			}
 * 		}
 * 		// notify listeners for all properties
 * 		for (PropertyChangeListener l : propertyListeners) {
 * 			l.propertyChange(new PropertyChangeEvent(this, propertyName,
 * 					oldValue, newValue));
 * 		}
 * 	}
 * </pre>
 * 
 * @author Erich Schroeter
 */
public interface IPropertyChangeNotifier {

	/**
	 * Adds a <code>PropertyChangeListener</code> to the listener list. The
	 * listener is registered for all bound properties of this class.
	 * <p>
	 * If <code>listener</code> is <code>null</code>, no exception is thrown and
	 * no action is performed.
	 * <p>
	 * <em><b>NOTE:</b> It would be wise if classes implementing this method 
	 * provide within the Javadoc a list of the class properties available.</em>
	 * 
	 * @param listener
	 *            the property change listener to be added
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Adds a <code>PropertyChangeListener</code> to the listener list for a
	 * specific property of this class.
	 * <p>
	 * If <code>propertyName</code> or <code>listener</code> is
	 * <code>null</code>, no exception is thrown and no action is performed.
	 * <p>
	 * <em><b>NOTE:</b> It would be wise if classes implementing this method 
	 * provide within the Javadoc a list of the class properties available.</em>
	 * 
	 * @param propertyName
	 *            one of the property names listed above
	 *            <em>(provided the implementor included them)</em>
	 * @param listener
	 *            the property change listener to be added
	 */
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

	/**
	 * Removes a <code>PropertyChangeListener</code> from the listener list.
	 * This method should be used to remove PropertyChangeListeners that were
	 * registered for all bound properties of this class.
	 * <p>
	 * If <code>listener</code> is <code>null</code>, no exception is thrown and
	 * no action is performed.
	 * <p>
	 * <em><b>NOTE:</b> It would be wise if classes implementing this method 
	 * provide within the Javadoc a list of the class properties available.</em>
	 * 
	 * @param listener
	 *            the property change listener to be removed
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Removes a <code>PropertyChangeListener</code> from the listener list for
	 * a specific property of this class.
	 * <p>
	 * If <code>propertyName</code> or <code>listener</code> is
	 * <code>null</code>, no exception is thrown and no action is performed.
	 * <p>
	 * <em><b>NOTE:</b> It would be wise if classes implementing this method 
	 * provide within the Javadoc a list of the class properties available.</em>
	 * 
	 * @param propertyName
	 *            one of the property names listed above
	 *            <em>(provided the implementor included them)</em>
	 * @param listener
	 *            the property change listener to be removed
	 */
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

	/**
	 * Returns the list of all the property change listeners registered for all
	 * properties of this class.
	 * 
	 * @return all of this class's <code>PropertyChangeListener</code>s or
	 *         <code>null</code> if no property change listeners are currently
	 *         registered.
	 */
	public List<PropertyChangeListener> getPropertyChangeListeners();

	/**
	 * Returns the list of all the property change listeners registered for a
	 * specific property of this class.
	 * <p>
	 * <em><b>NOTE:</b> It would be wise if classes implementing this method 
	 * provide within the Javadoc a list of the class properties available.</em>
	 * 
	 * @param propertyName
	 *            one of the property names listed above
	 *            <em>(provided the implementor included them)</em>
	 * @return all of this class's <code>PropertyChangeListener</code>s or
	 *         <code>null</code> if no property change listeners are currently
	 *         registered.
	 */
	public List<PropertyChangeListener> getPropertyChangeListeners(
			String propertyName);

}

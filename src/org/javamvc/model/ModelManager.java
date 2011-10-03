package org.javamvc.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * A <code>ModelManager</code> manages multiple models for an application. It
 * provides a means to access models throughout the application that have been
 * registered.
 * 
 * @author Erich Schroeter
 */
public class ModelManager {

	/** The map of model keys to the Model instance. */
	private Map<String, Model<?>> models;
	/**
	 * Whether to auto unregister a model when trying to register a key already
	 * being used.
	 */
	private boolean autoUnregister;
	/** The listeners to be notified when a management event is fired. */
	private List<ModelManagerListener> listeners;

	public ModelManager() {
		models = new HashMap<String, Model<?>>();
		listeners = new Vector<ModelManagerListener>();
	}

	/**
	 * Returns whether the model manager is configured to auto unregister an
	 * existing key being used to map a model.
	 * 
	 * @return <code>true</code> if auto unregister is enabled,
	 *         <code>false</code> if disabled
	 */
	public boolean isAutoUnregister() {
		return autoUnregister;
	}

	/**
	 * Sets whether the model manager should auto unregister an existing key
	 * being used to map a model.
	 * 
	 * @param autoUnregister
	 *            <code>true</code> to enable auto unregister,
	 *            <code>false</code> to disable
	 */
	public void setAutoUnregister(boolean autoUnregister) {
		this.autoUnregister = autoUnregister;
	}

	/**
	 * Returns the <code>Model</code> mapped by the specified <code>key</code>.
	 * 
	 * @param key
	 *            the key mapped to the model
	 * @return the mapped model, or <code>null</code> if <code>key</code> 
	 *         doesn't exist
	 */
	public Model<?> getModel(String key) {
		return models.get(key);
	}

	/**
	 * Handles notifying the model manager listeners with the <code>event</code>
	 * .
	 * 
	 * @param event
	 *            the event to pass to the listeners
	 */
	protected void fireModelManagedEvent(ModelManagedEvent event) {
		for (ModelManagerListener l : listeners) {
			l.wasManaged(event);
		}
	}

	/**
	 * Returns whether the specified <code>key</code> is registered to a
	 * {@link Model}.
	 * 
	 * @param key
	 *            the model identifier to check
	 * @return <code>true</code> if <code>key</code> is registered, else
	 *         <code>false</code>
	 */
	public boolean isRegistered(String key) {
		return models.containsKey(key);
	}

	/**
	 * Registers the <code>model</code> with the model manager. The
	 * <code>key</code> should be used for accessing the <code>model</code>.
	 * <p>
	 * If auto unregister is enabled and the <code>key</code> is being used
	 * already, the mapped model will be unregistered before registering the
	 * specified <code>model</code>. If auto unregister is disabled (default),
	 * then nothing changes.
	 * <p>
	 * A {@link ModelManagedEvent} is fired when successfully registered.
	 * 
	 * @param key
	 *            the key to access the <code>model</code> in the model manager
	 * @param model
	 *            the model being mapped to <code>key</code>
	 */
	public void registerView(String key, Model<?> model) {
		if (!isRegistered(key)) {
			// key not being used, so simply add to the map
			models.put(key, model);
			fireModelManagedEvent(new ModelManagedEvent(this, model,
					ModelManagedEvent.REGISTERED));
		} else {
			// key being used by another model, so we have to unregister it if
			// we are going to use the same key
			if (isAutoUnregister()) {
				unregisterView(key);
				models.put(key, model);
				fireModelManagedEvent(new ModelManagedEvent(this, model,
						ModelManagedEvent.REGISTERED));
			}
		}
	}

	/**
	 * Unregisters the mapping between the <code>key</code> and the
	 * {@link Model} it was mapped to.
	 * <p>
	 * A {@link ModelManagedEvent} is fired when successfully unregistered.
	 * 
	 * @param key
	 *            the key currently mapped to a model
	 */
	public void unregisterView(String key) {
		if (isRegistered(key)) {
			Model<?> model = models.remove(key);
			fireModelManagedEvent(new ModelManagedEvent(this, model,
					ModelManagedEvent.UNREGISTERED));
		}
	}

}

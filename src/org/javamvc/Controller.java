package org.javamvc;

import java.util.HashMap;
import java.util.Map;

import org.javamvc.model.Model;
import org.javamvc.model.ModelManager;
import org.javamvc.view.View;
import org.javamvc.view.ViewManager;

/**
 * A <code>Controller</code> is, as the name would suggest, the Controller in
 * the <em>Model-View-Controller (MVC)</em> framework. This provides methods for
 * managing views and models.
 * 
 * @author Erich Schroeter
 */
public abstract class Controller {

	/** The object managing the application's models. */
	protected ModelManager modelManager;
	/** The object managing the application's views. */
	protected ViewManager viewManager;
	/** The map of view identifiers to model identifiers. */
	private Map<String, String> modelMap;
	/** The map of model identifiers to view identifiers. */
	private Map<String, String> viewMap;

	/**
	 * Constructs a default <code>Controller</code> initializing default fields.
	 * <p>
	 * After initializing fields, the sequence of method calls is
	 * <ol>
	 * <li>{@link #installModels()}</li>
	 * <li>{@link #installViews()}</li>
	 * <li>{@link #mapViewsAndModels()}</li>
	 * </ol>
	 */
	public Controller() {
		modelMap = new HashMap<String, String>();
		viewMap = new HashMap<String, String>();
		setModelManager(new ModelManager());
		setViewManager(new ViewManager());
		installModels();
		installViews();
		mapViewsAndModels();
	}

	/**
	 * Installs the models to be used by the application.
	 * 
	 * @see #register(String, Model)
	 */
	protected abstract void installModels();

	/**
	 * Installs the views to be used by the application.
	 * 
	 * @see #register(String, View)
	 */
	protected abstract void installViews();

	/**
	 * Maps views and models together. This may be useful to let the framework
	 * automatically retrieve a view for a specific model.
	 * 
	 * @see #map(String, String)
	 */
	protected void mapViewsAndModels() {
		// no mappings by default
	}

	/**
	 * Returns the object managing the application's models.
	 * <p>
	 * This is provided publicly so classes can register to listen to manager
	 * events. It is recommended to not use the mutator methods in the
	 * <code>ModelManager</code> directly and instead use the methods provided
	 * by {@link Controller}.
	 * 
	 * @return the model manager
	 */
	public ModelManager getModelManager() {
		return modelManager;
	}

	/**
	 * Sets the object to manage the application's models.
	 * 
	 * @param modelManager
	 *            the model manager to set
	 */
	protected void setModelManager(ModelManager modelManager) {
		this.modelManager = modelManager;
	}

	/**
	 * Returns the object managing the application's views.
	 * <p>
	 * This is provided publicly so classes can register to listen to manager
	 * events. It is recommended to not use the mutator methods in the
	 * <code>ViewManager</code> directly and instead use the methods provided by
	 * {@link Controller}.
	 * 
	 * @return the view manager
	 */
	public ViewManager getViewManager() {
		return viewManager;
	}

	/**
	 * Sets the object to manage the application's views.
	 * 
	 * @param viewManager
	 *            the view manager to set
	 */
	protected void setViewManager(ViewManager viewManager) {
		this.viewManager = viewManager;
	}

	/**
	 * Creates a mapping between the model and the view.
	 * 
	 * @see #register(String, Model)
	 * @see #register(String, View)
	 * @param modelId
	 *            the model identifier to be mapped to <code>viewId</code>
	 * @param viewId
	 *            the view identifier to be mapped to <code>modelId</code>
	 * @throws IllegalArgumentException
	 *             if <code>viewId</code> is not a registered view, or
	 *             <code>modelId</code> is not a registered model
	 */
	public void map(String modelId, String viewId) {
		if (!viewManager.isRegistered(viewId)) {
			throw new IllegalArgumentException(viewId
					+ " not registered as a view");
		}
		if (!modelManager.isRegistered(modelId)) {
			throw new IllegalArgumentException(modelId
					+ " not registered as a model");
		}
		modelMap.put(viewId, modelId);
		viewMap.put(modelId, viewId);
	}

	/**
	 * Creates a mapping between the model identifier and the model.
	 * 
	 * @param modelId
	 *            the model identifier to be mapped to <code>model</code>
	 * @param model
	 *            the model to be mapped to <code>modelId</code>
	 */
	public void register(String modelId, Model<?> model) {
		modelManager.registerView(modelId, model);
	}

	/**
	 * Creates a mapping between the view identifier and the view.
	 * 
	 * @param viewId
	 *            the view identifier to be mapped to <code>view</code>
	 * @param view
	 *            the view to be mapped to <code>viewId</code>
	 */
	public void register(String viewId, View<?> view) {
		viewManager.registerView(viewId, view);

	}

	/**
	 * Returns the model identified by the specified <code>modelId</code>.
	 * 
	 * @param modelId
	 *            the model identifier
	 * @return the model identified by <code>modelId</code>
	 */
	public Model<?> getModel(String modelId) {
		return modelManager.getModel(modelId);
	}

	/**
	 * Returns the view identified by the specified <code>viewId</code>.
	 * 
	 * @param viewId
	 *            the view identifier
	 * @return the model identified by <code>viewId</code>
	 */
	public View<?> getView(String viewId) {
		return viewManager.getView(viewId);
	}

	/**
	 * Returns the model mapped to the view identified by <code>viewId</code>.
	 * 
	 * @param viewId
	 *            the view identifier mapped to the model
	 * @return the model mapped to <code>viewId</code>
	 * @throws NullPointerException
	 *             if <code>viewId</code> is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if <code>viewId</code> is not registered
	 */
	public Model<?> getModelMappedTo(String viewId) {
		if (viewId == null) {
			throw new NullPointerException("view identifier cannot be null");
		}
		if (!viewManager.isRegistered(viewId)) {
			throw new IllegalArgumentException("\"" + viewId
					+ "\" is not registered");
		}
		return modelManager.getModel(modelMap.get(viewId));
	}

	/**
	 * Returns the view mapped to the model identified by <code>modelId</code>.
	 * 
	 * @param modelId
	 *            the model identifier mapped to the view
	 * @return the model mapped to <code>viewId</code>
	 * @throws NullPointerException
	 *             if <code>modelId</code> is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if <code>modelId</code> is not registered
	 */
	public View<?> getViewMappedTo(String modelId) {
		if (modelId == null) {
			throw new NullPointerException("model identifier cannot be null");
		}
		if (!modelManager.isRegistered(modelId)) {
			throw new IllegalArgumentException("\"" + modelId
					+ "\" is not registered");
		}
		return viewManager.getView(viewMap.get(modelId));
	}

}

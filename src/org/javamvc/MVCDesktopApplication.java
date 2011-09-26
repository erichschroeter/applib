package org.javamvc;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.javamvc.model.Model;
import org.javamvc.model.ModelManager;
import org.javamvc.view.View;
import org.javamvc.view.ViewManager;

/**
 * A <code>MVCDesktopApplication</code> is a {@link GUIApplication} which
 * implements the <em>Model-View-Controller (MVC)</em> framework. This class
 * serves as the <em>Controller</em> and provides methods for managing views and
 * models.
 * 
 * @author Erich Schroeter
 */
public abstract class MVCDesktopApplication extends GUIApplication {

	/** The object managing the application's models. */
	protected ModelManager modelManager;
	/** The object managing the application's views. */
	protected ViewManager viewManager;
	/** The map of views to models. */
	private Map<String, String> modelMap;
	/** The map of view identifiers to models. */
	private Map<String, Model<?>> modelIdMap;
	/** The map of models to views. */
	private Map<String, String> viewMap;
	/** The map of model identifiers to views. */
	private Map<String, View<?>> viewIdMap;

	/**
	 * Constructs a default <code>MVCDesktopApplication</code>.
	 * <p>
	 * This is equivalent to <code>MVCDesktopApplication(new JFrame())</code>.
	 * 
	 * @see #MVCDesktopApplication(Container)
	 */
	public MVCDesktopApplication() {
		this(new JFrame());
	}

	/**
	 * Constructs a <code>MVCDesktopApplication</code> specifying the type of
	 * <code>Container</code> to use for the main application window.
	 * <p>
	 * This is equivalent to <code>super(applicationWindow)</code>.
	 * 
	 * @see GUIApplication#GUIApplication(Container)
	 * @param applicationWindow
	 *            the object to use as the main application window
	 */
	public MVCDesktopApplication(Container applicationWindow) {
		super(applicationWindow); // initialize preferences and window
		modelMap = new HashMap<String, String>();
		viewMap = new HashMap<String, String>();
		setViewManager(new ViewManager());
		installViews();
	}

	/**
	 * Installs the views to be used by the application.
	 */
	protected abstract void installViews();

	/**
	 * Installs the models to be used by the application.
	 */
	protected abstract void installModels();

	/**
	 * Returns the object managing the application's models.
	 * <p>
	 * This is provided publicly so classes can register to listen to manager
	 * events. It is recommended to not use the mutator methods in the
	 * <code>ModelManager</code> directly and instead use the methods provied by
	 * {@link MVCDesktopApplication}.
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
	 * <code>ViewManager</code> directly and instead use the methods provied by
	 * {@link MVCDesktopApplication}.
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
	 *            the model to be mapped to <code>viewId</code>
	 * @param viewId
	 *            the view to be mapped to <code>modelId</code>
	 */
	public void map(String modelId, String viewId) {
		modelMap.put(viewId, modelId);
		viewMap.put(modelId, viewId);
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
		viewIdMap.put(viewId, view);
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
		modelIdMap.put(modelId, model);
	}

	/**
	 * Returns the model identified by the specified <code>modelId</code>.
	 * 
	 * @param modelId
	 *            the model identifier
	 * @return the model identified by <code>modelId</code>
	 */
	public Model<?> getModel(String modelId) {
		return modelIdMap.get(modelId);
	}

	/**
	 * Returns the view identified by the specified <code>viewId</code>.
	 * 
	 * @param viewId
	 *            the view identifier
	 * @return the model identified by <code>viewId</code>
	 */
	public View<?> getView(String viewId) {
		return viewIdMap.get(viewId);
	}

}

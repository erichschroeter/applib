package usr.erichschroeter.applib.view;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import usr.erichschroeter.applib.GUIApplicationImpl;

/**
 * A <code>ViewImpl</code> is a default implementation of the {@link View}
 * interface. It represents a component displayed on screen to the user. This
 * class provides methods for customizing the view.
 * 
 * @author Erich Schroeter
 */
public abstract class ViewImpl<C extends Component> implements View<C> {

	/**
	 * The view component. This is the object which, for all intents and
	 * purposes, is the view.
	 */
	protected C view;
	/** A reference to the GUI application. */
	protected GUIApplicationImpl<?> application;
	/** A map of keys to subcomponents in the {@link #view}. */
	protected Map<String, ? super Component> subcomponents;

	/**
	 * Constructs a <code>View</code> specifying the application the view
	 * belongs to and the view component. This initializes the view by calling
	 * {@link #initializeView(Component)}.
	 * <p>
	 * The order in which methods in this constructor are called is
	 * <ol>
	 * <li>{@link #setApplication(GUIApplicationImpl)}</li>
	 * <li>{@link #setView(Component)}</li>
	 * <li>{@link #initialize(Object...)}</li>
	 * <li>{@link #initializeView(Component)}</li>
	 * </ol>
	 * 
	 * @see #setApplication(GUIApplicationImpl)
	 * @see #setView(Component)
	 * @see #initialize(Object...)
	 * @see #initializeView(Component)
	 * @param app
	 *            the application this view belongs to
	 * @param view
	 *            the view component to be displayed on screen
	 * @param objects
	 *            parameters to be initialized prior to anything else in the
	 *            view
	 */
	public ViewImpl(GUIApplicationImpl<?> app, C view, Object... objects) {
		subcomponents = new HashMap<String, Component>();
		setApplication(app);
		setView(view);
		initialize(objects);
		initializeView(view);
	}

	/**
	 * Initializes the view objects.
	 * <p>
	 * This method is the first method called when a view is instantiated. Any
	 * objects that need initialization in a derived constructor should be
	 * initialized in this method, which is guaranteed to be called before
	 * returning to the derived class's constructor (assuming that the
	 * <code>ViewImpl</code> constructor is called).
	 * 
	 * @param objects
	 *            objects that need initialization
	 */
	protected void initialize(Object... objects) {
	}

	/**
	 * Initializes the view. This is where you should customize the view itself
	 * and add components to the {@link #view}. This can be overridden in
	 * derived classes to customize the view further. Make sure to call
	 * <code>super.initializeView()</code> to ensure the default initialization
	 * values are kept.
	 * 
	 * @param view
	 *            the view to initialize
	 */
	protected abstract void initializeView(C view);

	/**
	 * Returns the view object which, for all intents and purposes, is the view.
	 * This is the UI component which is displayed on the screen.
	 * 
	 * @return the view object
	 */
	@Override
	public C getView() {
		return view;
	}

	/**
	 * Sets the view object which, for all intents and purposes, is the view.
	 * This is the UI component which is displayed on the screen.
	 * 
	 * @param view
	 *            the view object
	 */
	protected void setView(C view) {
		this.view = view;
	}

	/**
	 * Returns a map of keys to subcomponents in the {@link #view}. All
	 * subcomponents to allow access to classes outside of the <code>View</code>
	 * need to be added to the map.
	 * 
	 * @return the map of keys to subcomponents
	 */
	public Map<String, ? super Component> getSubcomponents() {
		return subcomponents;
	}

	/**
	 * Registers the <code>subcomponent</code> with the specified
	 * <code>key</code>. The <code>subcomponent</code> may be accessed by
	 * passing the <code>key</code> to {@link #getSubcomponent(String)}.
	 * 
	 * @see #unregisterSubcomponent(String)
	 * @param key
	 *            the key to access <code>subcomponent</code>
	 * @param subcomponent
	 *            the subcomponent being mapped by <code>key</code>
	 */
	protected void registerSubcomponent(String key, Component subcomponent) {
		subcomponents.put(key, subcomponent);
	}

	/**
	 * Unregisters the <code>subcomponent</code> mapped by the specified
	 * <code>key</code>.
	 * 
	 * @see #registerSubcomponent(String, Component)
	 * @param key
	 *            the key to access <code>subcomponent</code>
	 */
	protected void unregisterSubcomponent(String key) {
		subcomponents.remove(key);
	}

	/**
	 * Returns the subcomponent mapped to the specified <code>key</code>.
	 * 
	 * @param key
	 *            the key mapped to the desired subcomponent
	 * @return the subcomponent mapped to <code>key</code>
	 */
	public Component getSubcomponent(String key) {
		return (Component) subcomponents.get(key);
	}

	/**
	 * Returns the reference to the GUI application this view belongs to.
	 * 
	 * @return the GUI application
	 */
	@Override
	public GUIApplicationImpl<?> getApplication() {
		return application;
	}

	/**
	 * Returns the reference to the GUI application this view belongs to.
	 * 
	 * @param application
	 *            the GUI application
	 */
	public void setApplication(GUIApplicationImpl<?> application) {
		this.application = application;
	}

}

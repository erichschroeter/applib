package usr.erichschroeter.applib.view;

import java.awt.Component;

import usr.erichschroeter.applib.GUIApplicationImpl;

/**
 * The <code>View</code> interface provides methods required for a view. A view
 * is a component displayed on screen to the user. This interface provides
 * methods for customizing the view.
 * 
 * @author Erich Schroeter
 */
public interface View<C extends Component> {

	/**
	 * Returns the reference to the GUI application this view belongs to.
	 * 
	 * @return the GUI application
	 */
	public GUIApplicationImpl<?> getApplication();

	/**
	 * Returns the view object which, for all intents and purposes, is the view.
	 * This is the UI component which is displayed on the screen.
	 * 
	 * @return the view object
	 */
	public C getView();

}

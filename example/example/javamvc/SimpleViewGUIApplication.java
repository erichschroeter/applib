package example.javamvc;

import java.awt.Component;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.javamvc.GUIApplication;
import org.javamvc.ILifecycleListener;
import org.javamvc.Lifecycle;
import org.javamvc.LifecycleChangeEvent;
import org.javamvc.model.Model;

import example.javamvc.models.Person;
import example.javamvc.views.CompanyView;
import example.javamvc.views.PersonView;

/**
 * This is an example of how an end user would use the <b>javamvc</b> library in
 * their own GUI application. This example is intended to be simple showing how
 * to quickly get a {@link GUIApplication} implemented. This differs from the
 * {@link SimpleGUIApplication} in that includes setting up a view.
 * 
 * @author Erich Schroeter
 */
public class SimpleViewGUIApplication extends SimpleGUIApplication {

	/**
	 * Constructs a default <code>SimpleViewGUIApplication</code> which piggy
	 * backs on the {@link SimpleGUIApplication} default constructor.
	 */
	public SimpleViewGUIApplication() {
		super();
	}

	@Override
	protected void initializeWindow(JFrame applicationWindow) {
		super.initializeWindow(applicationWindow);
		applicationWindow.setTitle("Simple View Application");
	}

	@Override
	protected void installModels() {
		Model<Person> personModel = new Model<Person>(new Person("Nikola", "",
				"Tesla"));
		register("nikola", personModel);
	}

	@Override
	protected void installViews() {
		// example that we can anonymously create views and retrieve later
		getViewManager().registerView("person", new PersonView(this));
		// example to show that we can work with dynamically created views
		final CompanyView companyView = new CompanyView(this);
		getViewManager().registerView("company", companyView);

		addLifecycleListener(Lifecycle.STARTED, new ILifecycleListener() {

			@Override
			public void lifecycleChanged(LifecycleChangeEvent e) {
				// example of how to retrieve a view through the ViewManager
				Component personView = (Component) getViewManager().getView(
						"person").getView();
				// wrap the personal info in a panel to add a title
				JPanel personForm = new JPanel();
				personForm.add(personView);
				personForm.setBorder(BorderFactory
						.createTitledBorder("Personal Information"));

				// add a title to company info form
				companyView
						.getView()
						.setBorder(
								BorderFactory
										.createTitledBorder("Company Information"));

				JPanel form = new JPanel();
				form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

				form.add(personForm);
				form.add(companyView.getView());

				((JFrame) getApplicationWindow()).add(form);
			}
		});
	}

	@Override
	public Preferences getApplicationPreferences() {
		return getApplicationPreferences("simple-view-gui-application");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				new SimpleViewGUIApplication().run();
			}
		});
	}
}

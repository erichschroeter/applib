package example.javamvc.models;

/**
 * A <code>Person</code> is a model encapsulating the information about a
 * person.
 * 
 * @author Erich Schroeter
 */
public class Person {

	/** The first name of the person. */
	private String firstName;
	/** The middle name of the person. */
	private String middleName;
	/** The last name of the person. */
	private String lastName;

	/**
	 * Constructs a <code>Person</code> specifying each piece of data about the
	 * person.
	 * 
	 * @param first
	 *            the person's first name
	 * @param middle
	 *            the person's middle name
	 * @param last
	 *            the person's last name
	 */
	public Person(String first, String middle, String last) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}

package org.javamvc;

import java.util.EventObject;

/**
 * A <code>LifecycleEvent</code> is fired by an application at specific times in
 * the life cycle of an application. The supported life cycle types are listed
 * in the {@link Lifecycle} <code>enum</code>.
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public class LifecycleChangeEvent extends EventObject {

	/** The lifecycle type this event represents. */
	private Lifecycle lifecycle;

	public LifecycleChangeEvent(Object source, Lifecycle lifecycle) {
		super(source);
		setLifecycle(lifecycle);
	}

	/**
	 * Returns the lifecycle type that this event represents.
	 * 
	 * @return the lifecycle type
	 */
	public Lifecycle getLifecycle() {
		return lifecycle;
	}

	/**
	 * Sets the lifecycle type that this event represents.
	 * 
	 * @param lifecycle
	 *            the lifecycle type
	 */
	protected void setLifecycle(Lifecycle lifecycle) {
		this.lifecycle = lifecycle;
	}
}

package org.javamvc;

import java.util.List;

/**
 * A <code>LifecycleNotifier</code> implementor handles keeping references to
 * {@link ILifecycleListener}s and provides methods to manage the containers
 * holding those references.
 * 
 * @author Erich Schroeter
 */
public interface ILifecycleNotifier {

	/**
	 * Adds a <code>LifecycleListener</code> to the listener list. The listener
	 * is registered for all lifecycle types.
	 * <p>
	 * If <code>listener</code> is <code>null</code>, no exception is thrown and
	 * no action is performed.
	 * 
	 * @param listener
	 *            the lifecycle listener to be added
	 */
	public void addLifecycleListener(ILifecycleListener listener);

	/**
	 * Adds a <code>LifecycleListener</code> to the listener list for a specific
	 * lifecycle type.
	 * <p>
	 * If <code>lifecycle</code> or <code>listener</code> is <code>null</code>,
	 * no exception is thrown and no action is performed.
	 * 
	 * @param lifecycle
	 *            the lifecycle type
	 * @param listener
	 *            the lifecycle listener to be added
	 */
	public void addLifecycleListener(Lifecycle lifecycle,
			ILifecycleListener listener);

	/**
	 * Removes a <code>LifecycleListener</code> from the listener list. This
	 * method should be used to remove <code>LifecycleListener</code>s that were
	 * registered for all lifecycle types.
	 * <p>
	 * If <code>listener</code> is <code>null</code>, no exception is thrown and
	 * no action is performed.
	 * 
	 * @param listener
	 *            the lifecycle listener to be removed
	 */
	public void removeLifecycleListener(ILifecycleListener listener);

	/**
	 * Removes a <code>LifecycleListener</code> from the listener list for a
	 * specific lifecycle type.
	 * <p>
	 * If <code>lifecycle</code> or <code>listener</code> is <code>null</code>,
	 * no exception is thrown and no action is performed.
	 * 
	 * @param lifecycle
	 *            the lifecycle type
	 * @param listener
	 *            the lifecycle listener to be removed
	 */
	public void removeLifecycleListener(Lifecycle lifecycle,
			ILifecycleListener listener);

	/**
	 * Returns the list of all the lifecycle listeners registered for all
	 * <code>Lifecycle</code> types.
	 * 
	 * @return all of this class's <code>LifecycleListener</code>s or
	 *         <code>null</code> if no listeners are currently registered.
	 */
	public List<ILifecycleListener> getLifecycleListeners();

	/**
	 * Returns the list of all the lifecycle listeners registered for a specific
	 * <code>Lifecycle</code> type.
	 * 
	 * @param lifecycle
	 *            the lifecycle type
	 * @return all of this class's <code>LifecycleListener</code>s or
	 *         <code>null</code> if no listeners are currently registered.
	 */
	public List<ILifecycleListener> getLifecycleListeners(Lifecycle lifecycle);

}

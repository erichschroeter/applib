package usr.erichschroeter.applib.utils;

import java.util.Arrays;

/**
 * The <code>ArrayUtils</code> class consists of public static utility for
 * arrays whose methods may not be unique for any particular class.
 * 
 * @author Erich Schroeter
 */
public class ArrayUtils {

	public static byte[] filter(byte[] array, byte... filters) {
		if (array == null) {
			return new byte[0];
		}
		if (filters.length < 1) {
			return array;
		}
		int filteredCount = 0;
		for (int i = 0; i < array.length; i++) {
			for (byte filter : filters) {
				if (array[i] == filter) {
					filteredCount++;
				}
			}
		}
		byte[] filtered = new byte[array.length - filteredCount];
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			boolean isFiltered = false;
			for (byte filter : filters) {
				if (array[i] == filter) {
					isFiltered = true;
					break; // exit filter loop immediately
				}
			}
			if (!isFiltered) {
				filtered[index] = array[i];
				index++;
			}
		}
		return filtered;
	}

	/**
	 * Rearranges the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 */
	public static <T> void reverse(T[] data) {
		for (int left = 0, right = data.length - 1; left < right; left++, right--) {
			// swap the values at the left and right indices
			T temp = data[left];
			data[left] = data[right];
			data[right] = temp;
		}
	}

	/**
	 * Returns a copy of the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 * @return reverse order copy of <code>data</code>
	 */
	public static <T> T[] reverseCopy(T[] data) {
		T[] copy = Arrays.copyOf(data, data.length);
		reverse(copy);
		return copy;
	}

	/**
	 * Concatenates the given arrays into one array.
	 * 
	 * @see Arrays#copyOf(Object[], int)
	 * @see System#arraycopy(Object, int, Object, int, int)
	 * @param <T>
	 *            the type of array
	 * @param first
	 *            the first array
	 * @param rest
	 *            all other arrays
	 * @return a concatenated array
	 */
	public static <T> T[] concat(T[] first, T[]... rest) {
		int totalLength = first.length;
		for (T[] array : rest) {
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	/**
	 * Rearranges the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 */
	public static void reverse(byte[] data) {
		for (int left = 0, right = data.length - 1; left < right; left++, right--) {
			// swap the values at the left and right indices
			byte temp = data[left];
			data[left] = data[right];
			data[right] = temp;
		}
	}

	/**
	 * Returns a copy of the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 * @return reverse order copy of <code>data</code>
	 */
	public static byte[] reverseCopy(byte[] data) {
		byte[] copy = Arrays.copyOf(data, data.length);
		reverse(copy);
		return copy;
	}

	/**
	 * Concatenates the given arrays into one array.
	 * 
	 * @see Arrays#copyOf(Object[], int)
	 * @see System#arraycopy(Object, int, Object, int, int)
	 * @param first
	 *            the first array
	 * @param rest
	 *            all other arrays
	 * @return a concatenated array
	 */
	public static byte[] concat(byte[] first, byte[]... rest) {
		int totalLength = first.length;
		for (byte[] array : rest) {
			totalLength += array.length;
		}
		byte[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (byte[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	/**
	 * Rearranges the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 */
	public static void reverse(int[] data) {
		for (int left = 0, right = data.length - 1; left < right; left++, right--) {
			// swap the values at the left and right indices
			int temp = data[left];
			data[left] = data[right];
			data[right] = temp;
		}
	}

	/**
	 * Returns a copy of the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 * @return reverse order copy of <code>data</code>
	 */
	public static int[] reverseCopy(int[] data) {
		int[] copy = Arrays.copyOf(data, data.length);
		reverse(copy);
		return copy;
	}

	/**
	 * Rearranges the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 */
	public static void reverse(double[] data) {
		for (int left = 0, right = data.length - 1; left < right; left++, right--) {
			// swap the values at the left and right indices
			double temp = data[left];
			data[left] = data[right];
			data[right] = temp;
		}
	}

	/**
	 * Returns a copy of the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 * @return reverse order copy of <code>data</code>
	 */
	public static double[] reverseCopy(double[] data) {
		double[] copy = Arrays.copyOf(data, data.length);
		reverse(copy);
		return copy;
	}

	/**
	 * Rearranges the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 */
	public static void reverse(float[] data) {
		for (int left = 0, right = data.length - 1; left < right; left++, right--) {
			// swap the values at the left and right indices
			float temp = data[left];
			data[left] = data[right];
			data[right] = temp;
		}
	}

	/**
	 * Returns a copy of the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 * @return reverse order copy of <code>data</code>
	 */
	public static float[] reverseCopy(float[] data) {
		float[] copy = Arrays.copyOf(data, data.length);
		reverse(copy);
		return copy;
	}

	/**
	 * Rearranges the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 */
	public static void reverse(long[] data) {
		for (int left = 0, right = data.length - 1; left < right; left++, right--) {
			// swap the values at the left and right indices
			long temp = data[left];
			data[left] = data[right];
			data[right] = temp;
		}
	}

	/**
	 * Returns a copy of the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 * @return reverse order copy of <code>data</code>
	 */
	public static long[] reverseCopy(long[] data) {
		long[] copy = Arrays.copyOf(data, data.length);
		reverse(copy);
		return copy;
	}

	/**
	 * Rearranges the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 */
	public static void reverse(short[] data) {
		for (int left = 0, right = data.length - 1; left < right; left++, right--) {
			// swap the values at the left and right indices
			short temp = data[left];
			data[left] = data[right];
			data[right] = temp;
		}
	}

	/**
	 * Returns a copy of the given array in reverse order.
	 * 
	 * @param data
	 *            the array to reverse
	 * @return reverse order copy of <code>data</code>
	 */
	public static short[] reverseCopy(short[] data) {
		short[] copy = Arrays.copyOf(data, data.length);
		reverse(copy);
		return copy;
	}

}

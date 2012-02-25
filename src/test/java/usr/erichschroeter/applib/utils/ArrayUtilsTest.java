package usr.erichschroeter.applib.utils;

import static org.junit.Assert.*;
import org.junit.Test;

import usr.erichschroeter.applib.utils.ArrayUtils;

public class ArrayUtilsTest {

	@Test
	public void testFilterByte() {
		byte[] a = new byte[] { 0x20, 0x4, 0x4, 0x4, 0x4, 0x2e, 0x6 };
		byte[] filtered = ArrayUtils.filter(a, (byte) 0x20);
		assertArrayEquals(new byte[] { 0x4, 0x4, 0x4, 0x4, 0x2e, 0x6 },
				filtered);

		a = new byte[] { 0x20, 0x4, 0x4, 0x4, 0x4, 0x2e, 0x6, 0x20 };
		filtered = ArrayUtils.filter(a, (byte) 0x20, (byte) 0x4);
		assertArrayEquals(new byte[] { 0x2e, 0x6 }, filtered);
	}
}

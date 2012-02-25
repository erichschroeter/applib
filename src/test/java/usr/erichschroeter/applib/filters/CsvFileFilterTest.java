package usr.erichschroeter.applib.filters;

import static junit.framework.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import usr.erichschroeter.applib.filters.CsvFileFilter;

public class CsvFileFilterTest {

	@Test
	public void testAccept() {
		CsvFileFilter filter = new CsvFileFilter();
		File file = new File("test.something");
		assertEquals(false, filter.accept(file));
		file = new File("testFile");
		assertEquals(false, filter.accept(file));
		file = new File("testFile.");
		assertEquals(false, filter.accept(file));
	}

	@Test
	public void testAcceptCsv() {
		CsvFileFilter filter = new CsvFileFilter();
		File file = new File("test.csv");
		assertEquals(true, filter.accept(file));
		file = new File("csv.something");
		assertEquals(false, filter.accept(file));
	}

	@Test
	public void testAcceptTxt() {
		CsvFileFilter filter = new CsvFileFilter();
		File file = new File(".txt");
		assertEquals(true, filter.accept(file));
		file = new File("test.txt");
		assertEquals(true, filter.accept(file));
		file = new File("txt");
		assertEquals(false, filter.accept(file));
	}

}

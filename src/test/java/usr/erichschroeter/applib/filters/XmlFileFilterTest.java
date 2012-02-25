package usr.erichschroeter.applib.filters;

import static junit.framework.Assert.*;

import java.io.File;

import org.junit.Test;

import usr.erichschroeter.applib.filters.XmlFileFilter;

public class XmlFileFilterTest {

	@Test
	public void testAccept() {
		XmlFileFilter filter = new XmlFileFilter();
		File file = new File("test.something");
		assertEquals(false, filter.accept(file));
		file = new File("testFile");
		assertEquals(false, filter.accept(file));
		file = new File("testFile.");
		assertEquals(false, filter.accept(file));
	}

	@Test
	public void testAcceptXml() {
		XmlFileFilter filter = new XmlFileFilter();
		File file = new File("test.xml");
		assertEquals(true, filter.accept(file));
		file = new File(".xml");
		assertEquals(true, filter.accept(file));
		file = new File("xml");
		assertEquals(false, filter.accept(file));
	}

	@Test
	public void testAcceptXmlSchema() {
		XmlFileFilter filter = new XmlFileFilter();
		File file = new File("test.xsd");
		assertEquals(true, filter.accept(file));
		file = new File(".xsd");
		assertEquals(true, filter.accept(file));
		file = new File("xsd");
		assertEquals(false, filter.accept(file));
	}

	@Test
	public void testAcceptXmlTemplate() {
		XmlFileFilter filter = new XmlFileFilter();
		File file = new File("test.xsl");
		assertEquals(true, filter.accept(file));
		file = new File("test.xslt");
		assertEquals(true, filter.accept(file));
		file = new File(".xslt");
		assertEquals(true, filter.accept(file));
		file = new File(".xsl");
		assertEquals(true, filter.accept(file));
		file = new File("xslt");
		assertEquals(false, filter.accept(file));
		file = new File("xsl");
		assertEquals(false, filter.accept(file));
	}
	
}

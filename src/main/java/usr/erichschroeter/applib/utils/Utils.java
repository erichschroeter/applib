package usr.erichschroeter.applib.utils;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * The <code>Utils</code> class consists of public static utility methods whose
 * functionality may not be unique for any particular class.
 * 
 * @author Erich Schroeter
 */
public class Utils {

	/**
	 * Returns the <code>resource</code> as an <code>ImageIcon</code>.
	 * 
	 * @see #imageIcon(String, Class)
	 * @param resource
	 *            the icon resource
	 * @return the <code>resource</code> as an <code>ImageIcon</code>
	 */
	public static ImageIcon imageIcon(String resource) {
		return imageIcon(resource, Utils.class.getClassLoader());
	}

	/**
	 * Returns the <code>resource</code> as an <code>ImageIcon</code>.
	 * 
	 * @param resource
	 *            the icon resource
	 * @param classLoader
	 *            the class loader from which to get the resource as a stream
	 * @return the <code>resource</code> as an <code>ImageIcon</code>
	 */
	public static ImageIcon imageIcon(String resource, ClassLoader classLoader) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(classLoader
					.getResourceAsStream(resource)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return icon;
	}

	/**
	 * Converts the specified <code>Icon</code> to an <code>Image</code>.
	 * <p>
	 * If <code>icon</code> is an <code>ImageIcon</code> this simply calls
	 * {@link ImageIcon#getImage()}, otherwise the <code>icon</code> is painted
	 * in a <code>BufferedImage</code>.
	 * 
	 * @param icon
	 *            the icon to convert
	 * @return the converted image
	 */
	public static Image iconToImage(Icon icon) {
		if (icon instanceof ImageIcon) {
			return ((ImageIcon) icon).getImage();
		} else {
			int w = icon.getIconWidth();
			int h = icon.getIconHeight();
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			GraphicsDevice gd = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gd.getDefaultConfiguration();
			BufferedImage image = gc.createCompatibleImage(w, h);
			Graphics2D g = image.createGraphics();
			icon.paintIcon(null, g, 0, 0);
			g.dispose();
			return image;
		}
	}

	/**
	 * Handles opening the <code>url</code> in the default web browser and
	 * returns whether it was successful.
	 * 
	 * @param url
	 *            a valid website address
	 * @return <code>true</code> if successful, else <code>false</code> if
	 *         <code>url</code> is invalid
	 */
	public static boolean openInWebBrowser(String url) {
		boolean successful = false;
		if (url != null) {
			try {
				Desktop.getDesktop().browse(URI.create(url));
				successful = true;
			} catch (IllegalArgumentException e1) {
				successful = false;
			} catch (IOException e1) {
				successful = false;
			}
		}
		return successful;
	}

}

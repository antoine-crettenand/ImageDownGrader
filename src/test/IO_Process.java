package gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IO_Process {

	public static BufferedImage readFile(File file) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Cannot read File... " + file.getName());
			e.printStackTrace();
		}
		return img;
	}

	public static void writeFile(File file, BufferedImage img) {
		try {
			// retrieve image
			File outputfile = new File(file.getName());
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			System.out.println("Cannot write File... " + file.getName());
			e.printStackTrace();
		}
	}
}

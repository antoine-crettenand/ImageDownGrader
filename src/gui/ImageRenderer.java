package gui;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRenderer {

	private final static String DEFAULT_IMG = "http://mikecann.co"
			+ ".uk/wp-content/uploads/2009/12/javafx_logo_color_1.jpg";
	private ImageView displayedImg;

	ImageRenderer(){
			displayedImg = new ImageView(DEFAULT_IMG);
	}

	public ImageView getDisplayedImg() {
		return displayedImg;
	}

	public void setDisplayedImg(Image img) {
		displayedImg.setImage(img);
	}

	public void setDisplayedImg(File file) {
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			displayedImg.setImage(image);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

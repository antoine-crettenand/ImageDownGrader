package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
}

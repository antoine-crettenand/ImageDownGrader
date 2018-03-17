package gui;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import pixelazer.Transformer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public final class Controler {
	private ImageView imageView = new ImageView(DEFAULT_IMG);
	private final static String DEFAULT_IMG = "http://mikecann.co.uk/wp-content/uploads/2009/12/javafx_logo_color_1.jpg";

	public Controler() {}

	public ImageView getImageView() {
		imageView.setPreserveRatio(true);
		return imageView;
	}

	public EventHandler<ActionEvent> handle(Event eventType) {
		switch (eventType) {
		case applyGrayscale:
			return applyGrayscale;
		case applyBrighter:
			return applyBrighter;
		case applyDarker:
			return applyDarker;
		case drawMandelBrot:
			return drawMandelbrotSet;
		case applyInvert:
			return applyInvert;
		}
		throw new NoSuchElementException("Such eventType " + eventType + " not found");
	}

	public EventHandler<ActionEvent> handle(IOEvent ioEvent){
		switch (ioEvent) {
		case loadFile:
			return loadFile;
		case saveFile:
			return saveFile;
		}
		throw new NoSuchElementException("Such eventType " + ioEvent + " not found");
	}

	private EventHandler<ActionEvent> loadFile = event -> {
		FileChooser fileChooser = new FileChooser();

		//Set extension filter
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		//Show open file dialog
		File file = fileChooser.showOpenDialog(null);
		Image img_in = null;
		try {
			img_in = SwingFXUtils.toFXImage(ImageIO.read(file), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageView.setImage(img_in);
		imageView.setFitHeight(img_in.getHeight());
		imageView.setFitWidth(img_in.getWidth());
	};

	private EventHandler<ActionEvent> saveFile = event -> {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Image");

		File file = fileChooser.showSaveDialog(null);
		if (file != null) {
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null), "png", file);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	};

	/*
	private EventHandler<javafx.scene.input.MouseEvent> drawCircle = event -> {
		Image image_in = imageView.getImage();
		Image image_out = Transformer.drawCircle(image_in, int x, int y);
		imageView.setImage(image_out);
	};*/

	private EventHandler<ActionEvent> applyGrayscale = event -> {
		Image image_in = imageView.getImage();
		Image image_out = Transformer.grayscale(image_in);
		imageView.setImage(image_out);
	};

	private EventHandler<ActionEvent> applyBrighter = event -> {
		Image image_in = imageView.getImage();
		Image image_out = Transformer.brighter(image_in);
		imageView.setImage(image_out);
	};

	private EventHandler<ActionEvent> applyDarker = event -> {
		Image image_in = imageView.getImage();
		Image image_out = Transformer.darker(image_in);
		imageView.setImage(image_out);
	};

	private EventHandler<ActionEvent> drawMandelbrotSet = event -> {
		Image image_in = imageView.getImage();
		Image image_out = Transformer.drawMandelbrot(image_in);
		imageView.setImage(image_out);
		imageView.preserveRatioProperty().setValue(true);
	};

	private EventHandler<ActionEvent> applyInvert = event -> {
		Image image_in = imageView.getImage();
		Image image_out = Transformer.invert(image_in);
		imageView.setImage(image_out);
	};

}

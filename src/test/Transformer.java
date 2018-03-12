package gui;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.Objects;

public class Transformer {

	private BufferedImage image_in;

	Transformer(BufferedImage img) {
		Objects.requireNonNull(img);
		image_in = img;
	}

	protected BufferedImage reduireImage() {
		BufferedImage imageReduite = new BufferedImage((int) (image_in.getWidth() * 0.5),
				(int) (image_in.getHeight() * 0.5), image_in.getType());
		AffineTransform reduire = AffineTransform.getScaleInstance(0.5, 0.5);
		int interpolation = AffineTransformOp.TYPE_BICUBIC;
		AffineTransformOp retaillerImage = new AffineTransformOp(reduire, interpolation);
		retaillerImage.filter(image_in, imageReduite);
		return imageReduite;
	}

	protected BufferedImage agrandirImage() {
		BufferedImage imageZoomer = new BufferedImage((int) (image_in.getWidth() * 1.5),
				(int) (image_in.getHeight() * 1.5), image_in.getType());
		AffineTransform agrandir = AffineTransform.getScaleInstance(1.5, 1.5);
		int interpolation = AffineTransformOp.TYPE_BICUBIC;
		AffineTransformOp retaillerImage = new AffineTransformOp(agrandir, interpolation);
		retaillerImage.filter(image_in, imageZoomer);
		return imageZoomer;
	}

	protected BufferedImage imageConvolue() {
		//on va utiliser le masque flou
		BufferedImage imageFlou = new BufferedImage(image_in.getWidth(), image_in.getHeight(), image_in.getType());
		float[] masqueFlou = { 0.1f, 0.1f, 0.1f, 0.1f, 0.2f, 0.1f, 0.1f, 0.1f, 0.1f };

		Kernel masque = new Kernel(3, 3, masqueFlou);
		ConvolveOp operation = new ConvolveOp(masque);
		operation.filter(image_in, imageFlou);
		System.out.println("convolution effectuee");
		return imageFlou;
	}

	protected BufferedImage imageEclaircie() {
		/*
		 *    RescaleOp brillance = new RescaleOp(A, K, null);
		 *    1.  A< 1, l�image devient plus sombre.
   			  2.  A > 1, l�image devient  plus brillante.
   			  3. K est compris entre 0 et 256 et ajoute un �clairement .
		 */
		BufferedImage imgBrillant = new BufferedImage(image_in.getWidth(), image_in.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		RescaleOp brillance = new RescaleOp(1.2f, 0, null);
		brillance.filter(image_in, imgBrillant);
		return imgBrillant;
	}

	protected BufferedImage imageSombre() {
		/* RescaleOp assombrir = new RescaleOp(A, K, null);
		 *
		 *    1.  A < 1, l�image devient plus sombre.
   			  2.  A > 1, l�image devient  plus brillante.
   			  3.  K est compris entre 0 et 256 et ajoute un �clairement .
		 *
		 */
		BufferedImage imgSombre = new BufferedImage(image_in.getWidth(), image_in.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		RescaleOp assombrir = new RescaleOp(0.7f, 10, null);
		assombrir.filter(image_in, imgSombre);
		System.out.println("assombrir effectu�e");
		return imgSombre;
	}

	protected BufferedImage imageBinaire() {
		BufferedImage imgBinaire = new BufferedImage(image_in.getWidth(), image_in.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D surfaceImg = imgBinaire.createGraphics();
		surfaceImg.drawImage(image_in, null, null);
		return imgBinaire;
	}

	protected BufferedImage imageEnNiveauGris() {
		BufferedImage imageGris = new BufferedImage(image_in.getWidth(), image_in.getHeight(),
				BufferedImage.TYPE_USHORT_GRAY);
		Graphics2D surfaceImg = imageGris.createGraphics();
		surfaceImg.drawImage(image_in, null, null);
		return imageGris;
	}
}

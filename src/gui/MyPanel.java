package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class MyPanel extends JPanel  {

	private BufferedImage current_img = null;

	MyPanel() {
		super();
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(current_img != null)
			g.drawImage(current_img, 0, 0, null);
	}


	protected void reduireImage() {
		current_img = new Transformer(current_img).reduireImage();
		repaint();
	}

	protected void agrandirImage() {
		current_img = new Transformer(current_img).agrandirImage();
		repaint();
	}

	protected void imageConvolue() {
	//on va utiliser le masque flou
		current_img = new Transformer(current_img).imageConvolue();
		repaint();
	}

	protected void imageEclaircie() {
		current_img = new Transformer(current_img).imageEclaircie();
		repaint();
	}

	protected void imageSombre() {
		current_img = new Transformer(current_img).imageSombre();
		repaint();
	}

	protected void imageBinaire()
	{   
		current_img = new Transformer(current_img).imageBinaire();
		repaint();
	}

	protected void imageEnNiveauGris()
	{
		current_img = new Transformer(current_img).imageEnNiveauGris();
		repaint(); 
	}

	protected void loadImage(File imgFile)
	{
		current_img = IO_Process.readFile(imgFile);
		repaint();
	}

	protected void saveImage(File imgFile)
	{
		IO_Process.writeFile(imgFile, current_img);

	}

	/*
	protected BufferedImage getImagePanneau()
	{      // r�cup�rer une image du panneau
		int width  = getWidth();
		int height = getHeight();
		BufferedImage image = new BufferedImage(width, height,  BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();

		paintAll(g);
		g.dispose();
		return image;
	}*/
}

package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyFrame extends JFrame implements ActionListener {

	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu fichierMenu = new JMenu();
	private final JMenuItem ouvrirMenu = new JMenuItem();
	private final JMenu filtreMenu = new JMenu();
	private final MyPanel panneau = new MyPanel();
	private final JMenuItem enregistrerMenu = new JMenuItem();
	private final JMenuItem niveauGrisMenu = new JMenuItem();
	private final JMenuItem assombrirMenu = new JMenuItem();
	private final JMenuItem brillanceMenu = new JMenuItem();
	private final JMenuItem binarisationMenu = new JMenuItem();
	private final JMenuItem convolutionMenu = new JMenuItem();
	private final JMenu retaillerMenu = new JMenu();
	private final JMenuItem agrandirMenu = new JMenuItem();
	private final JMenuItem reduireMenu = new JMenuItem();

	public MyFrame() {
		super();
		setBounds(100, 100, 500, 375);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		try {
			creerMenu();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		//
	}

	private void creerMenu() throws Exception {

		// construction du menu
		setJMenuBar(menuBar);	
		menuBar.add(fichierMenu);
		fichierMenu.setText("Fichier");
		fichierMenu.add(ouvrirMenu);
		ouvrirMenu.addActionListener((ActionListener)this);
		ouvrirMenu.setText("ouvrir");

		fichierMenu.add(enregistrerMenu);
		enregistrerMenu.addActionListener((ActionListener)this);
		enregistrerMenu.setText("enregistrer");
		menuBar.add(filtreMenu);	
		filtreMenu.setText("Filtre");

		filtreMenu.add(niveauGrisMenu);
		niveauGrisMenu.addActionListener((ActionListener)this);
		niveauGrisMenu.setText("niveau de gris");

		filtreMenu.add(binarisationMenu);
		binarisationMenu.addActionListener((ActionListener)this);
		binarisationMenu.setText("binarisation");

		filtreMenu.add(assombrirMenu);
		assombrirMenu.addActionListener((ActionListener)this);
		assombrirMenu.setText("assombrir");

		filtreMenu.add(brillanceMenu);
		brillanceMenu.addActionListener((ActionListener)this);
		brillanceMenu.setText("brillance");

		filtreMenu.add(convolutionMenu);
		convolutionMenu.addActionListener((ActionListener)this);
		convolutionMenu.setText("convolution");

		menuBar.add(retaillerMenu);
		retaillerMenu.setText("retailler");

		retaillerMenu.add(agrandirMenu);
		agrandirMenu.addActionListener((ActionListener)this);
		agrandirMenu.setText("agrandir");

		retaillerMenu.add(reduireMenu);
		reduireMenu.addActionListener((ActionListener)this);
		reduireMenu.setText("reduire");

		// ajouter le panneau de dessin
		getContentPane().add(panneau);
	}
	public void actionPerformed(ActionEvent cliqueMenu) {
		JFileChooser jFileChooser = new JFileChooser();
		if (cliqueMenu.getSource().equals(ouvrirMenu))
		{
			if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				//Load the image
				panneau.loadImage(new File(jFileChooser.getSelectedFile()
						.getAbsolutePath()));
			}
		} else if (cliqueMenu.getSource().equals(enregistrerMenu)) {
			if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {

				String filePath = jFileChooser.getSelectedFile().getAbsolutePath();

				//Save image
				File fichierEnregistrement = new File(filePath + ".JPG");
				panneau.saveImage(fichierEnregistrement);
			}
		} else {
			if (cliqueMenu.getSource().equals(niveauGrisMenu)) {
				panneau.imageEnNiveauGris();
			} else if (cliqueMenu.getSource().equals(brillanceMenu)) {
				panneau.imageEclaircie();
			} else if (cliqueMenu.getSource().equals(binarisationMenu)) {
				panneau.imageBinaire();
			} else if (cliqueMenu.getSource().equals(convolutionMenu)) {
				panneau.imageConvolue();
				System.out.println("appel convolution");
			} else if (cliqueMenu.getSource().equals(agrandirMenu)) {
				panneau.agrandirImage();
			} else if (cliqueMenu.getSource().equals(reduireMenu)) {
				panneau.reduireImage();
			} else if (cliqueMenu.getSource().equals(assombrirMenu)) {
				panneau.imageSombre();
			}
		}
	}
}












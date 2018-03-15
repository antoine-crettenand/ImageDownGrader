package gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import static gui.Events.*;

public final class View {
	private final MenuBar top;
	private final ScrollPane center;
	private final Controler controler;

	public Pane getRoot() {
		BorderPane root = new BorderPane();
		root.setTop(top);
		root.setCenter(center);
		return root;
	}

	public View(Controler controler) {
		this.controler = controler;
		top = setUpMenuBar();
		center = setUpImage();
	}

	private MenuBar setUpMenuBar() {

		MenuBar menuBar = new MenuBar();

		Menu menuFile = new Menu("File");
		MenuItem menuFile_newFile = new MenuItem("New");
		menuFile_newFile.setOnAction(controler.getHandler(loadFile));
		MenuItem menuFile_saveFile = new MenuItem("Save As");
		menuFile_saveFile.setOnAction(controler.getHandler(saveFile));
		menuFile.getItems().addAll(menuFile_newFile, menuFile_saveFile);

		Menu menuEdit = new Menu("Edit");
		MenuItem menuEdit_applyGreyscale = new MenuItem("Greyscale");
		menuEdit_applyGreyscale.setOnAction(controler.getHandler(applyGrayscale));
		MenuItem menuEdit_applyBrighter = new MenuItem("Brighter");
		menuEdit_applyBrighter.setOnAction(controler.getHandler(applyBrighter));
		MenuItem menuEdit_applyDarker = new MenuItem("Darker");
		menuEdit_applyDarker.setOnAction(controler.getHandler(applyDarker));
		menuEdit.getItems().addAll(menuEdit_applyGreyscale, menuEdit_applyBrighter, menuEdit_applyDarker);

		Menu menuView = new Menu("View");
		MenuItem menduView_drawMandelbrotSet = new MenuItem("MandelBrot Set");
		menduView_drawMandelbrotSet.setOnAction(controler.getHandler(drawMandelBrot));
		menuView.getItems().addAll(menduView_drawMandelbrotSet);


		menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
		return menuBar;
	}

	private ScrollPane setUpImage() {
		return new ScrollPane(controler.getImageView());
	}
}


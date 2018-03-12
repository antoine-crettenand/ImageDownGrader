import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Pixelazer extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override public void start(Stage primaryStage) throws Exception {

		BorderPane root = new gui.RootScene().getRoot();

		Scene scene = new Scene(root, 800, 850);
		primaryStage.setTitle("Pixelazer");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

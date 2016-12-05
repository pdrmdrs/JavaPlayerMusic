package br.ufrn.imd.main;

import br.ufrn.imd.domain.Player;
import br.ufrn.imd.io.ReaderObject;
import br.ufrn.imd.io.WriterObject;
import br.ufrn.imd.navigation.Navigation;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * 
 * Main class of the app. It will launch the app and start the load of the
 * database class
 * 
 * @author Pedro Paulo
 *
 */
public class Main extends Application {

	private static Stage primaryStage;
	private static BorderPane rootLayout;

	public static Player player;
	
	/**
	 * Function to start the JavaFX app
	 */
	@Override
	public void start(Stage primaryStage) {
		ReaderObject ro = ReaderObject.getInstance();
		ro.readFiles();
		
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("Java Music Player");

		Main.primaryStage.setResizable(false);
		Main.primaryStage.resizableProperty().setValue(false);

		initRootLayout();

		Navigation.goTo("LoginView");
	}
	
	/**
	 * Fuction called when the user closes the app
	 */
	@Override
	public void stop() {
		WriterObject wo = WriterObject.getInstance();
		wo.saveFiles();
	}

	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../views/RootLayout.fxml"));
			Main.rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			Main.primaryStage.setScene(scene);
			Main.primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the primaryStage attribute
	 * 
	 * @return the primaryStage
	 */
	public static Stage getPrimaryStage() {
		return Main.primaryStage;
	}

	/**
	 * Get the rootLayout attribute
	 * 
	 * @return the rootLayout
	 */
	public static BorderPane getRootLayout() {
		return Main.rootLayout;
	}

	/**
	 * Main function
	 * 
	 * @param args
	 *            the args to run the app
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

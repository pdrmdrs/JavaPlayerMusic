package br.ufrn.imd.main;

import java.io.IOException;

import br.ufrn.imd.database.DataBase;
import br.ufrn.imd.domain.User;
import br.ufrn.imd.exceptions.UserAlreadyExistsException;
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

	/**
	 * Function to start the JavaFX app
	 */
	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("Java Music Player");

		Main.primaryStage.setResizable(false);
		Main.primaryStage.resizableProperty().setValue(false);

		initRootLayout();

		Navigation.goTo("LoginView");
	}

	private void initRootLayout() {

		System.out.println("Initializing root layout...");

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
		// TODO: testing users
		DataBase db = DataBase.getInstance();

		try {
			db.addUser(new User("user", "123456", false));// user not vip
			db.addUser(new User("user1", "123456", true));// user vip
		} catch (UserAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		//TODO: testing the musics file
		WriterObject wo = WriterObject.getInstance();
		try {
			wo.writeMusicsFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		launch(args);
	}
}

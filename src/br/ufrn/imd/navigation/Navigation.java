package br.ufrn.imd.navigation;

import br.ufrn.imd.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * Class that switch between the Views
 * 
 * @author Pedro Paulo
 *
 */
public class Navigation {

	/**
	 * Function that sets the new View on the app
	 * 
	 * @param view
	 *            the name of the new view file on the view package
	 */
	public static void goTo(String view) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../views/" + view + ".fxml"));

			AnchorPane nextView = loader.load();

			Main.getRootLayout().setCenter(nextView);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

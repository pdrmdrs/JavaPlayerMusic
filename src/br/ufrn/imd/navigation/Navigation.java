package br.ufrn.imd.navigation;

import br.ufrn.imd.main.Main;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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

			FadeTransition ft = new FadeTransition(Duration.millis(1000), nextView);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			ft.play();

			Main.getRootLayout().setCenter(nextView);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

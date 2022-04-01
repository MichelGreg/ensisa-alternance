package fr.ensisa.alt.presence;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
	@FXML
	private Label welcomeText;

	@FXML
	protected void onHelloButtonClick() {
		welcomeText.setText("Test du bouton fonctionel : Hello world ! :)");
	}
}
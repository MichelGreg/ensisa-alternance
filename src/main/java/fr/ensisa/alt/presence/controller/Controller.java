package fr.ensisa.alt.presence.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

	@FXML private TextField name;
	@FXML private ChoiceBox<String> year;
	@FXML private ChoiceBox<String> sector;
	@FXML private TextField label;
	@FXML private TextField url;
	@FXML private ListView<String> list;
	@FXML private Button add;
	@FXML private Button edit;
	@FXML private Button delete;
	@FXML private ChoiceBox<String> month;
	@FXML private Button auto;
	@FXML private Button manual;


	@FXML private Label welcomeText;
	@FXML protected void onHelloButtonClick() {
		welcomeText.setText("Test du bouton fonctionel : Hello world ! :)");
	}

}
package fr.ensisa.alt.presence.controller;

import fr.ensisa.alt.presence.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
	User user;

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


	@FXML private Label check;
	@FXML protected void onHelloButtonClick() {
		check.setText("Nom = " + name.getText() + " Année = " + year.getValue() + " Filière = " + sector.getValue());
	}

	public void initialize() {
		this.user = new User();

		name.textProperty().set(user.getNameProperty());
		year.itemsProperty().bind(user.yearsProperty());
		year.valueProperty().set(user.getYearProperty());
		sector.itemsProperty().bind(user.sectorsProperty());
		sector.valueProperty().set(user.getSectorProperty());
	}

}
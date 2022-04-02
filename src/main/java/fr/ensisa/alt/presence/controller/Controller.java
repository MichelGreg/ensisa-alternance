package fr.ensisa.alt.presence.controller;

import fr.ensisa.alt.presence.model.Calendar;
import fr.ensisa.alt.presence.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
	User user;
	Calendar calendar;

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
		check.setText("Nom = " + name.getText() + " Label = " + list.getSelectionModel().getSelectedItem() + " url = " + calendar.getUrl(list.getSelectionModel().getSelectedItem()));
	}
	@FXML protected void onAddButtonClick() {
		calendar.addCalendar(label.getText(), url.getText());
		list.itemsProperty().bind(calendar.calendarsNameProperty());
		check.setText("Click !  label = " + label.getText() + " url = " + url.getText());
	}
	@FXML protected  void onDeleteButtonClick() {
		calendar.rmCalendar(list.getSelectionModel().getSelectedItem());
		list.itemsProperty().bind(calendar.calendarsNameProperty());
		check.setText("Delete !");
	}
	@FXML protected  void onEditButtonClick() {
		calendar.editCalendar(list.getSelectionModel().getSelectedItem(), label.getText(), url.getText());
		list.itemsProperty().bind(calendar.calendarsNameProperty());
		check.setText("Updated !");
	}

	public void initialize() {
		this.user = new User();
		this.calendar = new Calendar();

		name.textProperty().set(user.getNameProperty());
		year.itemsProperty().bind(user.yearsProperty());
		year.valueProperty().set(user.getYearProperty());
		sector.itemsProperty().bind(user.sectorsProperty());
		sector.valueProperty().set(user.getSectorProperty());
		month.itemsProperty().bind(calendar.monthsProperty());
		month.valueProperty().set(calendar.getCurrMonthProperty());
		//list.itemsProperty().bind(calendar.calendarsNameProperty());
	}

}
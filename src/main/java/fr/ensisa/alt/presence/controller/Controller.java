package fr.ensisa.alt.presence.controller;

import fr.ensisa.alt.presence.model.Calendar;
import fr.ensisa.alt.presence.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {
	private final String TEST_URL = "https://www.emploisdutemps.uha.fr/jsp/custom/modules/plannings/anonymous_cal.jsp?data=bf2c64d11bfda874e5e5e7e10fcd13a50b42f0976007a22e3029ca7f36487f162a2c262ab3ba48506729f6560ae33af62704eb6c3e6444d06eebeb5635bb9f49,1";

	private User user;
	private Calendar calendar;
	private final ExcelController excelController = new ExcelController();
	private final CourseController courseController = new CourseController();

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
		try {
			if (label.getText().isBlank() || url.getText().isBlank()) {
				throw new IllegalArgumentException("Can't add a calendar with empty label or url");
			}
			calendar.addCalendar(label.getText(), url.getText());
			list.itemsProperty().bind(calendar.calendarsNameProperty());
			check.setText("Click !  label = " + label.getText() + " url = " + url.getText());
		} catch (NullPointerException e) {
			//TODO: coloration des champs vides.
			System.out.println("label or url field is empty");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	@FXML protected void onDeleteButtonClick() {
		try {
			calendar.rmCalendar(list.getSelectionModel().getSelectedItem());
			list.itemsProperty().bind(calendar.calendarsNameProperty());
			check.setText("Delete !");
		} catch (NullPointerException e) {
			System.out.println("Can't delete if no calendar selected");
		}
	}
	@FXML protected void onEditButtonClick() {
		try {
			calendar.editCalendar(list.getSelectionModel().getSelectedItem(), label.getText(), url.getText());
			list.itemsProperty().bind(calendar.calendarsNameProperty());
			check.setText("Updated !");
		} catch (NullPointerException e) {
			System.out.println("Can't edit if no calendar selected");
		}
	}
	@FXML protected void onOpenFileClick() {
		this.courseController.setIcalendarFromFile(selectFile(0));

		this.excelController.basicScript(courseController.getSortedCourses());
		this.excelController.saveFile(selectFile(1));
	}
	@FXML protected void onGenerateFileClick() {
		// Traitement du fichier excel
		this.courseController.setIcalendarFromURL(TEST_URL);

		this.excelController.basicScript(courseController.getSortedCourses());
		File outputFile = selectFile(1);
		excelController.saveFile(outputFile);
	}
	@FXML protected void onListSelection() {
		try {
			String key = list.getSelectionModel().getSelectedItem();
			label.setText(key);
			url.setText(calendar.getUrl(key));
		} catch (NullPointerException e){
			System.out.println("No calendar selected");
		}
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
	}

	/**
	 * Select the file to upload or to save accordingly to the mode.
	 * Use the mode 0 to open a file
	 * Use the mode 1 to save a file
	 * @param mode the int to select the mode
	 * @return File the file describing the opened file or the saved file or null if a bad argument is given
	 */
	private File selectFile(int mode) {
		Stage stage = (Stage) manual.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		if (mode == 0) {
			fileChooser.setTitle("Générer à partir d'un fichier .ics");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Calendar File (*.ics)", "*.ics"));
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Downloads"));
			return fileChooser.showOpenDialog(stage);
		} else if (mode == 1) {
			fileChooser.setTitle("Enregistrer l'Excel généré");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files (*.xlsx)", "*.xlsx"));
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Downloads"));
			fileChooser.setInitialFileName("Fiche_présence");
			return fileChooser.showSaveDialog(stage);
		} else {
			return null;
		}
	}
}
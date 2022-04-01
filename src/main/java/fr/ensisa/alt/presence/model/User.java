package fr.ensisa.alt.presence.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;

public class User {
	private final StringProperty name = new SimpleStringProperty("Prénom NOM");

	private final StringProperty year = new SimpleStringProperty("2A");
	private final ListProperty<String> years = new SimpleListProperty<>(FXCollections.observableArrayList("2A", "3A"));

	private final StringProperty sector = new SimpleStringProperty("Informatique & Réseaux");
	private final ListProperty<String> sectors = new SimpleListProperty<>(FXCollections.observableArrayList("Informatique & Réseaux", "Automatisme & Systèmes embarqués"));

	public StringProperty nameProperty() {
		return name;
	}
	public String getNameProperty() {
		return name.get();
	}

	public StringProperty yearProperty() {
		return year;
	}
	public String getYearProperty() {
		return year.get();
	}
	public ListProperty<String> yearsProperty() {
		return years;
	}

	public StringProperty sectorProperty() {
		return sector;
	}
	public String getSectorProperty() {
		return sector.get();
	}
	public ListProperty<String> sectorsProperty() {
		return sectors;
	}

}

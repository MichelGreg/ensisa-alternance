package fr.ensisa.alt.presence.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

@XmlRootElement
public class User {
	private Calendar calendar;

	private String name;

	private String year;
	private final ListProperty<String> years = new SimpleListProperty<>(FXCollections.observableArrayList("2A", "3A"));

	private String sector;
	private final ListProperty<String> sectors = new SimpleListProperty<>(FXCollections.observableArrayList("Informatique & Réseaux", "Automatisme & Systèmes embarqués"));

	public User(String name, String year, String sector, Calendar cal) {
		this.name = name;
		this.year = year;
		this.sector = sector;
		this.calendar = cal;
	}

	public User() {
		name = "";
		year = "2A";
		sector = "Informatique & Réseaux";
		calendar = new Calendar();
	}

	public String getName() {
		return this.name;
	}
	public void setName(String _name) {
		this.name = _name;
	}

	public String getYear() {
		return this.year;
	}
	public void setYear(String _year) {
		this.year = _year;
	}
	public ListProperty<String> yearsProperty() {
		return years;
	}

	public String getSector() {
		return this.sector;
	}
	public void setSector(String _sector) {
		this.sector = _sector;
	}
	public ListProperty<String> sectorsProperty() {
		return sectors;
	}

	public Calendar getCalendar() {
		return this.calendar;
	}
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
}

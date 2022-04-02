package fr.ensisa.alt.presence.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import java.util.*;

public class Calendar {
	private static final TreeMap<Integer, String> MONTHS = new TreeMap<>(Map.ofEntries(
			Map.entry(1, "Janvier"),
			Map.entry(2, "Février"),
			Map.entry(3, "Mars"),
			Map.entry(4, "Avril"),
			Map.entry(5, "Mai"),
			Map.entry(6, "Juin"),
			Map.entry(7, "Juillet"),
			Map.entry(8, "Aout"),
			Map.entry(9, "Septembre"),
			Map.entry(10, "Octobre"),
			Map.entry(11, "Novembre"),
			Map.entry(12, "Décembre")
	));

	private final TreeMap<String, String> calendars = new TreeMap<>();
	public void addCalendar(String label, String url) {
		calendars.put(label, url);
	}
	public String getUrl(String label) {
		return calendars.get(label);
	}
	public void rmCalendar(String calToDel) {
		calendars.remove(calToDel);
	}
	public void editCalendar(String selectedItem, String newLabel, String newUrl) {
		this.rmCalendar(selectedItem);
		this.addCalendar(newLabel, newUrl);
	}

	public ListProperty<String> calendarsNameProperty() {
		return new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>(calendars.keySet())));
	}

	private final ListProperty<String> months = new SimpleListProperty<>(FXCollections.observableArrayList(MONTHS.values()));
	public ListProperty<String> monthsProperty() {
		return months;
	}

	private final StringProperty currMonth = new SimpleStringProperty();
	public StringProperty currMonthProperty() {
		return currMonth;
	}
	public String getCurrMonthProperty() {
		return currMonth.get();
	}

	public Calendar() {
		this.currMonth.set(MONTHS.get(java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)+1));
	}


}

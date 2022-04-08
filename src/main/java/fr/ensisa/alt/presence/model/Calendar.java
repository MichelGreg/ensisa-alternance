package fr.ensisa.alt.presence.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import java.util.*;

public class Calendar {
	private static final TreeMap<Integer, String> MONTHS = new TreeMap<>(Map.ofEntries(
			Map.entry(1, "JANVIER"),
			Map.entry(2, "FÉVRIER"),
			Map.entry(3, "MARS"),
			Map.entry(4, "AVRIL"),
			Map.entry(5, "MAI"),
			Map.entry(6, "JUIN"),
			Map.entry(7, "JUILLET"),
			Map.entry(8, "AOÛT"),
			Map.entry(9, "SEPTEMBRE"),
			Map.entry(10, "OCTOBRE"),
			Map.entry(11, "NOVEMBRE"),
			Map.entry(12, "DECEMBRE")
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

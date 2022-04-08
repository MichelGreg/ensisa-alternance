package fr.ensisa.alt.presence.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.util.*;

public class Calendar {

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

	private final ListProperty<Integer> months = new SimpleListProperty<>(FXCollections.observableArrayList(List.of(1,2,3,4,5,6,7,8,9,10,11,12)));
	public ListProperty<Integer> monthsProperty() {
		return months;
	}

	private final IntegerProperty currMonth = new SimpleIntegerProperty();
	public IntegerProperty currMonthProperty() {
		return currMonth;
	}
	public Integer getCurrMonthProperty() {
		return currMonth.get();
	}

	public Calendar() {
		this.currMonth.set(java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1);
	}


}

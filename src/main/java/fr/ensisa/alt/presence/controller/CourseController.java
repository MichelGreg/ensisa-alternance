package fr.ensisa.alt.presence.controller;

import fr.ensisa.alt.presence.model.Course;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.CalendarComponent;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class CourseController {
	private Calendar icalendar;

	public CourseController() {
	}

	public CourseController(File file) {
		setIcalendarFromFile(file);
	}

	public void setIcalendarFromFile (File ical) {
		try {
			CalendarBuilder iCalBuilder = new CalendarBuilder();
			this.icalendar = iCalBuilder.build(new FileInputStream(ical));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setIcalendarFromURL(String url) {
		try {
			CalendarBuilder iCalBuilder = new CalendarBuilder();
			this.icalendar = iCalBuilder.build(new URL(url).openStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Course> getSortedCourses() {
		ArrayList<Course> courses = new ArrayList<>();
		for (CalendarComponent item : icalendar.getComponents()) {
			courses.add(new Course(item));
		}
		Collections.sort(courses);
		return courses;
	}
}

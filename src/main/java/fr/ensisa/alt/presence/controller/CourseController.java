package fr.ensisa.alt.presence.controller;

import fr.ensisa.alt.presence.model.Course;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.CalendarComponent;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class CourseController {
	private Calendar icalendar;

	public CourseController() {
		try {
			FileInputStream calInputStream = new FileInputStream(new File(Objects.requireNonNull(Controller.class.getResource("ADECal.ics")).toURI()));
			CalendarBuilder iCalBuilder = new CalendarBuilder();
			this.icalendar = iCalBuilder.build(calInputStream);

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

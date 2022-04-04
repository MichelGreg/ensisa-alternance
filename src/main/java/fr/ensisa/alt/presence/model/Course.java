package fr.ensisa.alt.presence.model;

import net.fortuna.ical4j.model.component.CalendarComponent;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Course implements Comparable<Course> {
	private final String name;
	private final String prof;
	private final String strDate;
	private final String strHours;
	private final String duration;
	private final ZonedDateTime start;
	private final ZonedDateTime end;

	public Course(CalendarComponent item) {
		this.prof = parseProf(item.getProperty("DESCRIPTION").get().getValue());
		this.name = item.getProperty("SUMMARY").get().getValue();
		this.start = LocalDateTime.parse(item.getProperty("DTSTART").get().getValue(), DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'")).atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Europe/Paris"));
		this.end = LocalDateTime.parse(item.getProperty("DTEND").get().getValue(), DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'")).atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Europe/Paris"));
		this.strDate = this.start.format(DateTimeFormatter.ofPattern("dd/MM"));
		this.strHours = this.start.format(DateTimeFormatter.ofPattern("HH'h'mm")) + " - " + this.end.format(DateTimeFormatter.ofPattern("HH'h'mm"));
		this.duration = String.valueOf(Duration.between(start, end).toHoursPart()) + 'h' + Duration.between(start, end).toMinutesPart();
	}

	private String parseProf(String desc) {
		for (String descItem : desc.split("\n")) {
			if (!descItem.isBlank()) {
				for (char character : descItem.toCharArray()) {
					if (Character.isDigit(character)) {
						break;
					}
					return descItem;
				}
			}
		}
		return null;
	}

	public String getProf() {
		return prof;
	}

	public String getName() {
		return name;
	}

	public ZonedDateTime getStart() {
		return start;
	}

	public ZonedDateTime getEnd() {
		return end;
	}

	public String getHours() {
		return strHours;
	}

	public String getDuration() {
		return duration;
	}

	public String getStrDate() {
		return strDate;
	}

	public String getStrHours() {
		return strHours;
	}

	@Override
	public int compareTo(Course o) {
		return (int) Duration.between(o.start, this.start).toMinutes();
	}

	@Override
	public String toString() {
		return '['+strDate+", "+strHours+", "+name+']';
	}
}

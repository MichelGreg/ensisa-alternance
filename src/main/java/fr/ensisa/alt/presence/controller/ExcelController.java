package fr.ensisa.alt.presence.controller;

import fr.ensisa.alt.presence.model.Course;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.Duration;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static java.lang.String.format;

public class ExcelController {

	private XSSFWorkbook wb;
	private XSSFSheet sheet;

	public ExcelController() {
		try {
			this.wb = new XSSFWorkbook(new File(Objects.requireNonNull(Controller.class.getResource("Fiche_presence.xlsx")).toURI()));
			this.wb.setSheetName(0, "default");
			this.sheet = wb.getSheetAt(0);
			this.wb.cloneSheet(0, "clone");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resetFile () {
		try {
			this.wb.removeSheetAt(wb.getSheetIndex("default"));
			this.wb.cloneSheet(wb.getSheetIndex("clone"), "default");
			this.wb.setActiveSheet(wb.getSheetIndex("default"));
			this.sheet = wb.getSheet("default");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateFile(List<Course> courses, String name, String year, String sector, Integer month) {
		resetFile();
		String upperMonth = Month.of(month).getDisplayName(TextStyle.FULL, Locale.FRENCH).toUpperCase();
		String FILIERE = "";
		if (year.matches("[1-3]A")) {
			if (year.charAt(0) == '1') {
				FILIERE = "1ère";
			} else {
				FILIERE = year.charAt(0) + "ème";
			}
			FILIERE += " année " + sector;
		}

		CellReference cr = new CellReference("C3");
		sheet.getRow(cr.getRow()).getCell(cr.getCol()).setCellValue(name);

		CellReference cr2 = new CellReference("C4");
		sheet.getRow(cr2.getRow()).getCell(cr2.getCol()).setCellValue(FILIERE);

		CellReference cr3 = new CellReference("A2");
		sheet.getRow(cr3.getRow()).getCell(cr3.getCol()).setCellValue("du mois de " + upperMonth + " 2022");

		int line = 7;
		Duration total = Duration.ZERO;
		for (Course c : courses) {
			if (c.getStart().getMonthValue() != month) {
				continue;
			}
			if (line < 60) {
				if (line == 36) {
					CellReference cr4 = new CellReference("G"+(line+1));
					sheet.getRow(cr4.getRow()).getCell(cr4.getCol()).setCellValue(String.valueOf(total.toHours()) + 'h' + format("%02d", total.toMinutesPart()));
					line = 39;
				}
				Row row = sheet.getRow(line);
				row.getCell(0).setCellValue(c.getStrDate());
				row.getCell(1).setCellValue(c.getName());
				row.getCell(2).setCellValue(c.getHours());
				row.getCell(3).setCellValue(c.getProf());
				row.getCell(6).setCellValue(c.getStrDuration());
				line++;
				total = total.plus(c.getDuration());
			} else {
				System.out.println("erreur lors du remplissage des lignes du tableau");
			}
		}
		String strTotal = String.valueOf(total.toHours()) + 'h' + format("%02d", total.toMinutesPart());
		CellReference cr5 = new CellReference("C71");
		sheet.getRow(cr5.getRow()).getCell(cr5.getCol()).setCellValue(strTotal);
		CellReference cr6 = new CellReference("G60");
		sheet.getRow(cr6.getRow()).getCell(cr6.getCol()).setCellValue(strTotal);

	}

	public void saveFile(File file) {
		try {
			FileOutputStream out = new FileOutputStream(file);
			wb.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

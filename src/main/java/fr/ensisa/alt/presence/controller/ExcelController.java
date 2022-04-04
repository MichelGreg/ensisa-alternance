package fr.ensisa.alt.presence.controller;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Objects;

public class ExcelController {

	private XSSFWorkbook wb;
	private XSSFSheet sheet;

	public ExcelController() {
		try {
			FileInputStream excelInputStream = new FileInputStream(new File(Objects.requireNonNull(Controller.class.getResource("Fiche_presence.xlsx")).toURI()));
			this.wb = new XSSFWorkbook(excelInputStream);
			this.sheet = wb.getSheetAt(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

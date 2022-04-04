package fr.ensisa.alt.presence.controller;

import org.apache.poi.ss.util.CellReference;
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

	public void basicScript() throws IOException {
		String NAME_Surname = "MICHEL Grégoire";
		String FILIERE = "2ème année Informatique et Réseaux";
		String EX_FILE = "Fiche_presence.xlsx";
		String ONGLET = "Feuil1";
		String CAL_FILE = "ADECal.ics";

		CellReference cr = new CellReference("C3");
		sheet.getRow(cr.getRow()).getCell(cr.getCol()).setCellValue(NAME_Surname);

		CellReference cr2 = new CellReference("C4");
		sheet.getRow(cr2.getRow()).getCell(cr2.getCol()).setCellValue(FILIERE);

		CellReference cr3 = new CellReference("A2");
		sheet.getRow(cr3.getRow()).getCell(cr3.getCol()).setCellValue("du mois de " + "TEST" + " 2022");


		FileOutputStream out = new FileOutputStream("howtodoinjava_demo.xlsx");
		wb.write(out);
		out.close();
	}

}

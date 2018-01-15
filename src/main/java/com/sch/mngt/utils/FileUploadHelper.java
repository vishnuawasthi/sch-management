package com.sch.mngt.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sch.mngt.dto.StudentDetailsDTO;

@Component
public class FileUploadHelper {
	// https://www.journaldev.com/2562/apache-poi-tutorial

	// https://www.mkyong.com/java/apache-poi-reading-and-writing-excel-file-in-java/
	//

	public List<StudentDetailsDTO> parseFile(MultipartFile multipartFile, List<StudentDetailsDTO> studentDetailsList) {
		System.out.println("parseFile() - start");

		String fileName = multipartFile.getOriginalFilename();

		// Create Workbook instance for xlsx/xls file input stream
		Workbook workbook = null;

		try {
			if (fileName.toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(multipartFile.getInputStream());
			} else if (fileName.toLowerCase().endsWith("xls")) {
				workbook = new HSSFWorkbook(multipartFile.getInputStream());
			}

			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			// Avoid headers
			if (rowIterator.hasNext()) {
				rowIterator.next();
			}

			// Process the records
			while (rowIterator.hasNext()) {
				Row currentRow = rowIterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING: {
						System.out.println(cell.getNumericCellValue());
						break;
					}
					case Cell.CELL_TYPE_FORMULA: {
						System.out.println("Cell Formula=" + cell.getCellFormula());
						System.out.println("Cell Formula Result Type=" + cell.getCachedFormulaResultType());
						if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC) {
							System.out.println("Formula Value=" + cell.getNumericCellValue());
						}
					}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("parseFile() - end");
		return studentDetailsList;
	}

}

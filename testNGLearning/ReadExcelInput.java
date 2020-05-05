package testNGLearning;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ReadExcelInput {

	@Test
	@Parameters({ "fileName", "sheetName" })
	public void ReadExcelFile(String inputFileName, String sheetName) throws IOException {

		XSSFWorkbook readExcel = new XSSFWorkbook("./inputFiles/" + inputFileName + ".xlsx");
		// to read the number of sheets in a excel file
		int noOfSheet = readExcel.getNumberOfSheets();
		// System.out.println(noOfSheet);

		// to print the names of the sheet in the excel
		for (int i = 0; i < noOfSheet; i++) {
			// String sheetName = readExcel.getSheetName(i);
			// System.out.println(readExcel.getSheetName(i));
		}

		// print the name of active sheet
		int activeSheetIndex = readExcel.getActiveSheetIndex();
		System.out.println(readExcel.getSheetName(activeSheetIndex));

		/*
		 * List<XSSFName> allNames = readExcel.getAllNames(); for(XSSFName xssfName :
		 * allNames) { }
		 */

		XSSFSheet sheetAt = readExcel.getSheet(sheetName);
		int lastRowNum = sheetAt.getLastRowNum();
		// System.out.println("the last row of the document is "+lastRowNum);

		// to get the no of valid column
		short cellCount = sheetAt.getRow(0).getLastCellNum();
		// System.out.println(cellCount);

		for (int i = 1; i <= lastRowNum; i++) {
			XSSFRow row = sheetAt.getRow(i);
			for (int j = 0; j < cellCount; j++) {
				XSSFCell cell = row.getCell(j);
				String data = cell.getStringCellValue();
				System.out.println(data);
			}

		}

	}

}

package cz.agel.demo.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;

import cz.agel.demo.Constants;
import cz.agel.demo.datamodel.NumbersDataModel;

public class ExcelExporter {


	public static void export(File f, NumbersDataModel dataModel) throws IOException {
		
        int startingNumber = dataModel.getStartingNumber();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		org.apache.poi.ss.usermodel.CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);

		HSSFSheet firstSheet = workbook.createSheet("Sheet 1");

        HSSFRow row0 = firstSheet.createRow(0);
        HSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue(Constants.TABLE_LABEL);
		for (int i = 0; i < Constants.ROW_COUNT; i++) {
	        HSSFRow row = firstSheet.createRow(i + 1);
			for (int j = 0; j < Constants.COLUMN_COUNT; j++) {
		        HSSFCell cell = row.createCell(j);
				cell.setCellValue(startingNumber);
		        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		        if(dataModel.isPrimeNumber(startingNumber)) {
		        	cell.setCellStyle(style);
		        }
		        startingNumber++;
			}
		}


		FileOutputStream fileOut = new FileOutputStream(f);
		workbook.write(fileOut);
		fileOut.close();
	}
}

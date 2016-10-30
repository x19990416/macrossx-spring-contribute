package com.github.x19990416.macrossx.spring.msoffice.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Component;

/**
 * office 97（-2007）
 * 
 * @author guolimin
 *
 */
@Component
public class ExcelHelper {
	/**
	 * office 97(-2007)
	 * 
	 * @param sheetName
	 * @param rowName
	 * @param rowValue
	 * @return
	 */
	public Optional<ByteBuffer> createHSSFExcel(String sheetName, String[] rowName, List<List<String>> rowValue) {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(sheetName);
			int rowCnt = 0;
			if (rowName != null) {
				HSSFRow hssfRow = sheet.createRow(rowCnt++);
				int i = 0;
				for (String name : rowName) {
					hssfRow.createCell(i++, CellType.STRING).setCellValue(name);
				}
			}
			for (List<String> rows : rowValue) {
				HSSFRow hssfRow = sheet.createRow(rowCnt++);
				int i = 0;
				for (String value : rows) {
					hssfRow.createCell(i++, CellType.STRING).setCellValue(value);
				}
			}
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			wb.write(stream);
			stream.close();
			wb.close();
			return Optional.of(ByteBuffer.wrap(stream.toByteArray()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Optional.empty();
		}
	}

}

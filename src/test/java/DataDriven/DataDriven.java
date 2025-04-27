package DataDriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class DataDriven {

	public ArrayList<String> getData(String testName, String sheetName) throws IOException {

		File file = new File("src//test//resources//DDTest.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		int sheets =wb.getNumberOfSheets();
		ArrayList<String> list = new ArrayList<>();
		for(int i=0;i<sheets;i++)
		{
			if(wb.getSheetName(i).equalsIgnoreCase(sheetName))
			{
				XSSFSheet sheet = wb.getSheetAt(i);
				Iterator<Row> rowIterator = sheet.iterator();
				int k=0;
				int column=0;
					Row firstRow = rowIterator.next();
					Iterator<Cell> cellIterator = firstRow.cellIterator();
					
					while(cellIterator.hasNext())
					{
						Cell cell = cellIterator.next();
						if(cell.getStringCellValue().equalsIgnoreCase("Course")) {
						column=k;
						break;
						}
					}
					k++;
				
				while(rowIterator.hasNext())
				{
					Row r = rowIterator.next();
					if(r.getCell(column).getStringCellValue().equals(testName))
{
						Iterator<Cell> cellIterator2 = r.cellIterator();
						while(cellIterator2.hasNext())
						{
							Cell c = cellIterator2.next();
							if(c.getCellType() == CellType.STRING)
							{
							list.add(c.getStringCellValue());
							}
							else
							{
								list.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
}
				}
				
			}
		}
		return list;
	}

}

package model.components;

import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlToExcelConverter
{   
	public void WriteToExcel(String xmlfilepath) throws Exception
	{
		HSSFWorkbook workbook = new HSSFWorkbook();  
		HSSFSheet sheet = workbook.createSheet("Invoice");   
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		style.setFont(font);
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setValidating(true);
		
		
		DocumentBuilder db=dbf.newDocumentBuilder();
		Document doc=db.parse(xmlfilepath);
		
		Element rootElement=doc.getDocumentElement();
		HSSFRow row,col_names;
		int row_num=0;
		
		
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
		sheet.addMergedRegion(new CellRangeAddress(0,0,5,7));
		sheet.addMergedRegion(new CellRangeAddress(1,1,0,2));
		sheet.addMergedRegion(new CellRangeAddress(1,1,5,7));
		sheet.addMergedRegion(new CellRangeAddress(2,2,0,2));
		sheet.addMergedRegion(new CellRangeAddress(3,3,0,2));
		sheet.addMergedRegion(new CellRangeAddress(4,4,0,2));
		
		HSSFRow details = sheet.createRow(row_num++);  
		int t=0;
		
		details.createCell(5).setCellValue("Invoice number : "+rootElement.getChildNodes().item(t++).getFirstChild().getNodeValue());  
		details.createCell(0).setCellValue("Customer number: "+rootElement.getChildNodes().item(t+1).getChildNodes().item(0).getFirstChild().getNodeValue());
		details = sheet.createRow(row_num++);  
		details.createCell(5).setCellValue("Invoice date : "+rootElement.getChildNodes().item(t++).getFirstChild().getNodeValue()); 
		details.createCell(0).setCellValue("Customer name: "+rootElement.getChildNodes().item(t).getChildNodes().item(1).getFirstChild().getNodeValue());
		details = sheet.createRow(row_num++);  
		details.createCell(0).setCellValue("Customer address: "+rootElement.getChildNodes().item(t).getChildNodes().item(2).getFirstChild().getNodeValue());
		details = sheet.createRow(row_num++);  
		details.createCell(0).setCellValue("Customer email: "+rootElement.getChildNodes().item(t).getChildNodes().item(3).getFirstChild().getNodeValue());
		details = sheet.createRow(row_num++);  
		details.createCell(0).setCellValue("Customer ph.no: "+rootElement.getChildNodes().item(t).getChildNodes().item(4).getFirstChild().getNodeValue());
		
		row_num++;
		col_names=sheet.createRow(row_num++);
		col_names.createCell(0).setCellValue("Item no.");
		col_names.createCell(1).setCellValue("Item name");
		col_names.createCell(2).setCellValue("Unit");
		col_names.createCell(3).setCellValue("Price");
		col_names.createCell(4).setCellValue("Quantity");
		col_names.createCell(5).setCellValue("Total");

		t++;
		for(int j=0;j<rootElement.getChildNodes().item(t).getChildNodes().getLength();j++)
		{
			row=sheet.createRow(row_num++);
			for(int k=0;k<rootElement.getChildNodes().item(t).getChildNodes().item(j).getChildNodes().getLength();k++)
			{
				row.createCell(k).setCellValue(rootElement.getChildNodes().item(t).getChildNodes().item(j).getChildNodes().item(k).getFirstChild().getNodeValue()); 
			}
		}
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\samee\\eclipse-workspace\\ShoppingCart\\Invoice.xlsx");  
		workbook.write(fos);   
		fos.close();  
		workbook.close(); 	
	}
	
}
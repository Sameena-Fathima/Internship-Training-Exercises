package model.components;

import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class XmlToPDFConverter{
	public void WriteToPDF(String xmlfilepath) throws Exception
	   {
		System.out.println("Creating PDF");
	      Document document = new Document();
	      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\samee\\eclipse-workspace\\ShoppingCart\\Invoice.pdf"));
	      document.open();
	      
	      PdfPTable table = new PdfPTable(6); 
	      table.setWidthPercentage(100); 
	      table.setSpacingBefore(10f); 
	      table.setSpacingAfter(10f);
	      
	      float[] columnWidths = {1f, 1f, 1f, 1f, 1f,1f};
	      table.setWidths(columnWidths);
	      
	      
	      this.insertCell(table,"Item no.");
	      this.insertCell(table,"Item name");
	      this.insertCell(table, "Unit");
	      this.insertCell(table,"Price");
	      this.insertCell(table,"Quantity");
	      this.insertCell(table,"Total");
		  
	      DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		  dbf.setIgnoringElementContentWhitespace(true);
	      dbf.setValidating(true);
	      
	      DocumentBuilder db=dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc=db.parse(xmlfilepath);
		  
		  org.w3c.dom.Element rootElement=doc.getDocumentElement();
		  
		  String cust_arr[]=new String[5];
		  int t=0;
		  Paragraph invno=new Paragraph("Invoice number:"+rootElement.getChildNodes().item(t++).getFirstChild().getNodeValue());
		  invno.setAlignment(Paragraph.ALIGN_RIGHT);
		  Paragraph invdate=new Paragraph("Invoice date:"+rootElement.getChildNodes().item(t++).getFirstChild().getNodeValue());
		  invdate.setAlignment(Paragraph.ALIGN_RIGHT);
		  document.add(invno);
		  document.add(invdate);
		  for(int i=t;i<rootElement.getChildNodes().getLength();i++)
		  {
			  if(i==t)
			  {
				  for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++)
				  {
					  cust_arr[j]=rootElement.getChildNodes().item(i).getChildNodes().item(j).getFirstChild().getNodeValue();
				  }
			  }
			  else
			  {
				  for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++)
				  {
					  for(int k=0;k<rootElement.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().getLength();k++)
					  {
						  this.insertCell(table,rootElement.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getFirstChild().getNodeValue()); 
					  }
				  }
			  }
		  }
		  Paragraph cust_details = new Paragraph(cust_arr[1]+"\n"+cust_arr[2]+"\n"+cust_arr[3]+"\n"+cust_arr[4]+"\n");
		  document.add(cust_details);
		  document.add(table);	    
		  document.close();
	      writer.close();
	      System.out.println("PDF created");
	   }
	private void insertCell(PdfPTable table,String content){
		  PdfPCell cell = new PdfPCell(new Paragraph(content));
		  cell.setPaddingLeft(10);
	      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		  table.addCell(cell);	
	}
}

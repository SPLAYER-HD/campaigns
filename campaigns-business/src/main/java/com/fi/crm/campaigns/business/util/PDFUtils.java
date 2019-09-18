package com.fi.crm.campaigns.business.util;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.cyberneko.html.parsers.DOMParser;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFUtils {

	public static void main (String args[]) {
        Document document = new Document(PageSize.LETTER);
        try {
        	String pdfFileName = "/home/andres/temp/salida.pdf"; 
        	FileOutputStream fos = new FileOutputStream(pdfFileName);
			PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
	        document.open();
	        File file = new File("/home/andres/workspace/HtmlToPDF/campania1.html");
	        BufferedReader in = new BufferedReader(new FileReader(file));
	        String line = null;
	        StringBuffer b = new StringBuffer();
	        while((line = in.readLine()) != null){
	        	b.append(line);
	        }
	        in.close();
	        String urltocheck = extractImageURL(b.toString(),1);
	        writeImageToAbsolutePosition(urltocheck, 50f, 480f, 200, 200, document);
	        String[][] data = new String[10][2];
	        String[] headers = new String[2];
	        for (int i=0; i<10; i++){
	        	for (int j=0; j <2; j++){
	        		data[i][j] = new String("i:"+i+"j:"+j);
	        	}

	        }	        	
	        headers[0] = "Header0";
	        headers[1] = "Header1";
	        Font helvetica = new Font(FontFamily.HELVETICA, 12);
	        BaseFont bf_helv = helvetica.getCalculatedBaseFont(false);
	        PdfContentByte canvas = pdfWriter.getDirectContent();
	        canvas.beginText();
	        canvas.setFontAndSize(bf_helv, 12);
	        canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_LEFT, "Información de Envío", 50, 700, 0);
	        canvas.endText();
	        canvas.setLineWidth(4f);
	        canvas.moveTo(50, 690);
	        canvas.lineTo(560, 690);
	        canvas.stroke();        
	        PdfPTable table = createTable(null, data, 200, 0, null, null);
	        writeTableToAbsolutePosition(table, 250f, 680f, canvas);
		        
			document.close();
			pdfWriter.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param urltocheck
	 * @return
	 */
	public static boolean checkURL (String urltocheck){
        URL url;
		try {
			url = new URL(urltocheck.trim());
			HttpURLConnection.setFollowRedirects(false);
	        HttpURLConnection u = (HttpURLConnection)url.openConnection();
	        u.setRequestMethod("HEAD");
	        u.setConnectTimeout(5000);
	        u.setReadTimeout(5000);
	        int responseCode = u.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK)
	        	return true;

		} catch (MalformedURLException e) {
			System.out.println ("URL Mal formada: " + e.getMessage());
		} catch (IOException e) {
			System.out.println ("URL No existe: " + e.getMessage());
		}
		
        return false;
	}
	/**
	 * 
	 * @param headers
	 * @param data
	 * @param width
	 * @return
	 */
	public static PdfPTable createTable (String[] headers, String[][] data, int width, int cellborder, int[] cellaligment, int[] widths){
		
		int columns = data[0].length;
		int rows = data.length;
		PdfPTable table = new PdfPTable(columns);
		table.setTotalWidth(width);
		table.setWidthPercentage(width / 5.23f);
		if (widths != null){
	        try {
				table.setWidths(widths);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (headers != null){
			table.setHeaderRows(1);
			for (int i=0; i < columns; i++){//Setting Header
				PdfPCell cell = new PdfPCell(new Phrase(headers[i]));
				cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
				cell.setBorder(cellborder);
				cell.setBorderWidthLeft(cellborder);
				cell.setBorderWidthBottom(cellborder);
				cell.setBorderWidthRight(cellborder);
				cell.setBorderWidthTop(cellborder);				
				table.addCell(cell);
			}
		}
		for (int i=0; i < rows; i++){//Setting content
			for (int j=0; j < columns; j++){
				PdfPCell cell = new PdfPCell(new Phrase(data[i][j]));
				cell.setBorder(cellborder);
				cell.setBorderWidthLeft(cellborder);
				cell.setBorderWidthBottom(cellborder);
				cell.setBorderWidthRight(cellborder);
				cell.setBorderWidthTop(cellborder);
				if (cellaligment != null)
					cell.setHorizontalAlignment(cellaligment[j]);
				else
					cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
				table.addCell(cell);
			}
		}
		
		return table;
	}
	
	/**
	 * 
	 * @param table
	 * @param x
	 * @param y
	 * @param canvas
	 */
	public static void writeTableToAbsolutePosition (PdfPTable table, float x, float y, PdfContentByte canvas) {
		table.writeSelectedRows(0, -1, x, y, canvas);
	}
	
	/**
	 * 
	 * @param image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param document
	 * @throws DocumentException
	 */
	public static void writeImageToAbsolutePosition (com.itextpdf.text.Image image, float x, float y, float width, float height, Document document) throws DocumentException {
		image.setAbsolutePosition(x,y);
		image.scaleToFit(width, height);
		document.add(image);
	}
	
	/**
	 * 
	 * @param url
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param document
	 */
	public static void writeImageToAbsolutePosition (String url, float x, float y, float width, float height, Document document) {
		if (checkURL(url)){
			URL tempurl;
			try {
				tempurl = new URL(url);
				Image image = ImageIO.read(tempurl);
		        com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(image, null);
		        img.scaleToFit(width, height);  
				img.setAbsolutePosition(x,y);
				img.setBorder(2);
				img.setBorderWidth(2);
				img.setBorderWidthTop(2);
				img.setBorderWidthLeft(2);
				img.setBorderWidthRight(2);
				document.add(img);

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) { //Problems reading the image
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @param html
	 * @param ocurrence
	 * @return
	 */
	public static String extractImageURL (String html, int ocurrence){
        DOMParser parser = new DOMParser();
        String urltocheck = "";
        try {
			parser.parse(new InputSource(new StringReader(html)));
	        org.w3c.dom.Document d = parser.getDocument();
	        DOMReader reader = new DOMReader();
	        org.dom4j.Document doc = reader.read(d);
	        @SuppressWarnings("unchecked")
			List<Element> nodes = doc.selectNodes("//IMG");
	        if (nodes != null && nodes.size() > 0){
		        Element node = nodes.get(ocurrence-1);
		        urltocheck = node.attributeValue("src").trim();
	        }
	        return urltocheck;

        } catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}

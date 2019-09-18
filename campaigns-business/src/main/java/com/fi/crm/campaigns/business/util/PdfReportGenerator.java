package com.fi.crm.campaigns.business.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.fi.crm.campaigns.business.services.parametrization.ParametrizationServiceInterface;
import com.fi.crm.campaigns.business.services.report.ReportServiceInterface;
import com.fi.crm.campaigns.common.dto.BusinessUnitDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageClicksReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageEffectivenessReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageHeaderReportDTO;
import com.fi.crm.campaigns.common.dto.CustomPhoneHeaderReportDTO;
import com.fi.crm.campaigns.common.dto.CustomPhoneHeaderReportEffectiveCallsDTO;
import com.fi.crm.campaigns.common.dto.MarcasDTO;
import com.fi.crm.campaigns.common.enums.CampaignStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.util.Messages;
import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfReportGenerator {
	private ParametrizationServiceInterface parametrizationService;

	public PdfReportGenerator (ReportServiceInterface reportService, ParametrizationServiceInterface parametrizationService) {
		this.parametrizationService = parametrizationService;
	}
	
	public String generatePhoneCampaignReport (CustomPhoneHeaderReportDTO customPhoneHeaderReportDTO,
			List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportEffectiveCallsDTOList,
			List<CustomMessageEffectivenessReportDTO> customMessageEffectivenessReportDTO) 
					throws BusinessException {
        Document document = new Document(PageSize.LETTER);
        String pdfFileName = null;
        try {
        	String newstring = new SimpleDateFormat("ddMMyyyy_HHmmssS").format(Calendar.getInstance().getTime());
        	pdfFileName = "report"+customPhoneHeaderReportDTO.getCampaignId()+"_"+newstring+".pdf"; 
        	FileOutputStream fos = new FileOutputStream(pdfFileName);
			PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
	        document.open();
	        
	        Font helvetica = new Font(FontFamily.HELVETICA, 12);
	        BaseFont bf_helv = helvetica.getCalculatedBaseFont(false);
	        PdfContentByte canvas = pdfWriter.getDirectContent();
	        canvas.beginText();
	        canvas.setFontAndSize(bf_helv, 12);
	        canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_LEFT, Messages.getString(customPhoneHeaderReportDTO.getContactType().getViewKey()), 50, 750, 0);
	        canvas.endText();

	        canvas.beginText();
	        canvas.setFontAndSize(bf_helv, 12);
	        canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_CENTER, customPhoneHeaderReportDTO.getName(), 300, 750, 0);
	        canvas.endText();
	        canvas.beginText();
	        canvas.setFontAndSize(bf_helv, 12);
	        canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_LEFT, "Información de Envío", 50, 700, 0);
	        canvas.endText();
	        canvas.setLineWidth(4f);
	        canvas.moveTo(50, 690);
	        canvas.lineTo(560, 690);
	        canvas.stroke();        
	        String[][] data = buildGeneralPhoneInformation(customPhoneHeaderReportDTO);
	        PdfPTable table = PDFUtils.createTable(null,data , 350, 0, null, new int[] {1,2} );
	        PDFUtils.writeTableToAbsolutePosition(table, 50f, 680f, canvas);

	        canvas.beginText();
	        canvas.setFontAndSize(bf_helv, 12);
	        canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_LEFT, "Estadísticas Generales", 50, 350, 0);
	        canvas.endText();
	        
	        canvas.setLineWidth(4f);
	        canvas.moveTo(50, 340);
	        canvas.lineTo(560, 340);
	        canvas.stroke();

	        String[][] statistics = fillPhoneTables(customPhoneHeaderReportDTO, customPhoneHeaderReportEffectiveCallsDTOList);
	        String[] headers = {Messages.getString("Report.message.tableheader.one"),
	        		Messages.getString("Report.message.tableheader.two"),
	        		Messages.getString("Report.message.tableheader.three")};
	        int[] cellaligment = {com.itextpdf.text.Element.ALIGN_LEFT, com.itextpdf.text.Element.ALIGN_RIGHT, com.itextpdf.text.Element.ALIGN_RIGHT};
	        int[] widths = {2,1,1};
	        PdfPTable statisticsTable = PDFUtils.createTable(headers,statistics , 300, 1, cellaligment, widths);
	        PDFUtils.writeTableToAbsolutePosition(statisticsTable, 50f, 320f, canvas);
	        
	        document.newPage();
	        canvas.beginText();
	        canvas.setFontAndSize(bf_helv, 12);
	        canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_LEFT, "Efectividad de "+ Messages.getString(customPhoneHeaderReportDTO.getContactType().getViewKey()), 50, 700, 0);
	        canvas.endText();
	        
	        canvas.setLineWidth(4f);
	        canvas.moveTo(50, 690);
	        canvas.lineTo(560, 690);
	        canvas.stroke();
	        String[][] effectivityHeader = buildEffectivityPhoneInformationHeader(customPhoneHeaderReportDTO);
	        PdfPTable tableEffectivityHeader = PDFUtils.createTable(null,effectivityHeader , 350, 0, null, null);
	        PDFUtils.writeTableToAbsolutePosition(tableEffectivityHeader, 50f, 680f, canvas);
	        String[][] effectiveness = buildEffectivenessInformation(customMessageEffectivenessReportDTO);
	        int[] cellaligmentEffectiveness = {com.itextpdf.text.Element.ALIGN_LEFT, com.itextpdf.text.Element.ALIGN_RIGHT, com.itextpdf.text.Element.ALIGN_RIGHT,com.itextpdf.text.Element.ALIGN_RIGHT};
	        String[] headersEffectiveness = {"","Totales", "Clientes Envio", "Total"};
	        PdfPTable tableEffectiveness = PDFUtils.createTable(headersEffectiveness, effectiveness, 400, 1, cellaligmentEffectiveness, new int[]{1,2,2,1}); 
	        PDFUtils.writeTableToAbsolutePosition(tableEffectiveness, 50f, 580f, canvas);
			document.close();
			pdfWriter.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return pdfFileName;
		
	}

	public String generateMessagesCampaignReport (CustomMessageHeaderReportDTO customMessageHeaderReportDTO,
			List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportEffectiveCallsDTO,
			List<CustomMessageClicksReportDTO> customMessageClicksReportList,
			List<CustomMessageEffectivenessReportDTO> customMessageEffectivenessReportDTO) throws BusinessException {
        Document document = new Document(PageSize.LETTER);
        String pdfFileName = null;
        try {
        	String newstring = new SimpleDateFormat("ddMMyyyy_HHmmssS").format(Calendar.getInstance().getTime());
        	pdfFileName = "report"+customMessageHeaderReportDTO.getCampaignId()+"_"+newstring+".pdf"; 
        	FileOutputStream fos = new FileOutputStream(pdfFileName);
			PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
	        document.open();
	        if (customMessageClicksReportList != null){
		        String urltocheck = PDFUtils.extractImageURL(customMessageHeaderReportDTO.getMessage(),1);
		        if (urltocheck != null){
		        	PDFUtils.writeImageToAbsolutePosition(urltocheck, 50f, 480f, 200, 200, document);	        	
		        }
	        }
	        
	        Font helvetica = new Font(FontFamily.HELVETICA, 12);
	        BaseFont bf_helv = helvetica.getCalculatedBaseFont(false);
	        PdfContentByte canvas = pdfWriter.getDirectContent();
	        canvas.beginText();
	        canvas.setFontAndSize(bf_helv, 12);
	        canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_LEFT, Messages.getString(customMessageHeaderReportDTO.getContactType().getViewKey()), 50, 750, 0);
	        canvas.endText();

	        canvas.beginText();
	        canvas.setFontAndSize(bf_helv, 12);
	        canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_CENTER, customMessageHeaderReportDTO.getName(), 300, 750, 0);
	        canvas.endText();
	        canvas.beginText();
	        canvas.setFontAndSize(bf_helv, 12);
	        canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_LEFT, "Información de Envío", 50, 700, 0);
	        canvas.endText();
	        canvas.setLineWidth(4f);
	        canvas.moveTo(50, 690);
	        canvas.lineTo(560, 690);
	        canvas.stroke();        
	        String[][] data = buildGeneralInformation(customMessageHeaderReportDTO);
	        PdfPTable table = PDFUtils.createTable(null,data , 350, 0, null, new int[] {1,2} );
	        PDFUtils.writeTableToAbsolutePosition(table, 260f, 680f, canvas);

	        canvas.beginText();
	        canvas.setFontAndSize(bf_helv, 12);
	        canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_LEFT, "Estadísticas Generales", 50, 350, 0);
	        canvas.endText();
	        
	        canvas.setLineWidth(4f);
	        canvas.moveTo(50, 340);
	        canvas.lineTo(560, 340);
	        canvas.stroke();

	        String[][] statistics = fillTables(customMessageHeaderReportDTO, customPhoneHeaderReportEffectiveCallsDTO);
	        String[] headers = {Messages.getString("Report.message.tableheader.one"),
	        		Messages.getString("Report.message.tableheader.two"),
	        		Messages.getString("Report.message.tableheader.three")};
	        int[] cellaligment = {com.itextpdf.text.Element.ALIGN_LEFT, com.itextpdf.text.Element.ALIGN_RIGHT, com.itextpdf.text.Element.ALIGN_RIGHT};
	        int[] widths = {2,1,1};
	        PdfPTable statisticsTable = PDFUtils.createTable(headers,statistics , 300, 1, cellaligment, widths);
	        PDFUtils.writeTableToAbsolutePosition(statisticsTable, 50f, 320f, canvas);
	        
	        
	        if (customMessageClicksReportList != null){
		        canvas.beginText();
		        canvas.setFontAndSize(bf_helv, 16);
		        canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_CENTER, "Aperturas/Clicks", 450, 320, 0);
		        canvas.endText();

	        	String[][] clicks = buildClicksInformation(customMessageClicksReportList);
		        String[] headersClicks = {"Tiempo","Absoluto", "Relativo"};
		        PdfPTable clicksTable = PDFUtils.createTable(headersClicks, clicks, 200, 1, cellaligment, new int[]{1,1,1});
		        PDFUtils.writeTableToAbsolutePosition(clicksTable, 360f, 310f, canvas);
	        }
	        
	        document.newPage();
	        canvas.beginText();
	        canvas.setFontAndSize(bf_helv, 12);
	        canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_LEFT, "Efectividad de "+ Messages.getString(customMessageHeaderReportDTO.getContactType().getViewKey()), 50, 700, 0);
	        canvas.endText();
	        
	        canvas.setLineWidth(4f);
	        canvas.moveTo(50, 690);
	        canvas.lineTo(560, 690);
	        canvas.stroke();
	        String[][] effectivityHeader = buildEffectivityInformationHeader(customMessageHeaderReportDTO);
	        PdfPTable tableEffectivityHeader = PDFUtils.createTable(null,effectivityHeader , 350, 0, null, null);
	        PDFUtils.writeTableToAbsolutePosition(tableEffectivityHeader, 50f, 680f, canvas);
	        String[][] effectiveness = buildEffectivenessInformation(customMessageEffectivenessReportDTO);
	        int[] cellaligmentEffectiveness = {com.itextpdf.text.Element.ALIGN_LEFT, com.itextpdf.text.Element.ALIGN_RIGHT, com.itextpdf.text.Element.ALIGN_RIGHT,com.itextpdf.text.Element.ALIGN_RIGHT};
	        String[] headersEffectiveness = {"","Totales", "Clientes Envio", "Total"};
	        PdfPTable tableEffectiveness = PDFUtils.createTable(headersEffectiveness, effectiveness, 400, 1, cellaligmentEffectiveness, null); 
	        PDFUtils.writeTableToAbsolutePosition(tableEffectiveness, 50f, 580f, canvas);
			document.close();
			pdfWriter.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return pdfFileName;
	}

	private String[][] buildGeneralInformation (final CustomMessageHeaderReportDTO customMessageHeaderReportDTO) throws BusinessException{
		String[][] salida = new String[8][2];
		salida[0][0] = Messages.getString("CampaignForm.brands");
		salida[0][1] = Messages.getString(CampaignStatusEnum.ALL.getViewKey());
		MarcasDTO brandDTO = customMessageHeaderReportDTO.getBrand();
		if (brandDTO != null && brandDTO.getCodigo() != null && brandDTO.getNoCia() != null) {
			brandDTO = parametrizationService.getBrandById(brandDTO.getNoCia(), brandDTO.getCodigo(), null);
			salida[0][1] = brandDTO.getDescripcion();
		}

		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		salida[1][0] = Messages.getString("CampaignForm.description");
		salida[1][1] = customMessageHeaderReportDTO.getDescription();
		
		salida[2][0] = Messages.getString("CampaignForm.startDate");
		salida[2][1] = f.format(customMessageHeaderReportDTO.getStartDate());
		salida[3][0] = Messages.getString("CampaignForm.endDate");
		salida[3][1] = f.format(customMessageHeaderReportDTO.getEndDate());

		salida[4][0] = Messages.getString("Report.totalMessage");
		salida[4][1] = customMessageHeaderReportDTO.getAsignadas().toString();
		
		salida[5][0] = Messages.getString("Report.landingLink");
		salida[5][1] = customMessageHeaderReportDTO.getLandingLink();
		
		salida[6][0] = Messages.getString("CampaignForm.mailSubject");
		salida[6][1] = customMessageHeaderReportDTO.getMailSubject();
		
		salida[7][0] = Messages.getString("Report.sender");
		salida[7][1] = customMessageHeaderReportDTO.getDomain();
		
		return salida;
	}
	
	private String[][] buildEffectivityInformationHeader (final CustomMessageHeaderReportDTO customMessageHeaderReportDTO) throws BusinessException{
		String[][] salida = new String[3][2];
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		salida[0][0] = Messages.getString("CampaignForm.eventStartDate");
		salida[0][1] = f.format(customMessageHeaderReportDTO.getEventStartDate());
		
		salida[1][0] = Messages.getString("CampaignForm.eventEndDate");
		salida[1][1] = f.format(customMessageHeaderReportDTO.getEventEndDate());
		
		salida[2][0] = Messages.getString("CampaignForm.businessUnit");
		salida[2][1] = Messages.getString(CampaignStatusEnum.ALL.getViewKey());
		BusinessUnitDTO businessUnitDTO = customMessageHeaderReportDTO.getBusinessUnit();
		if (businessUnitDTO != null && businessUnitDTO.getCode() != null && businessUnitDTO.getNoCia() != null) {
			businessUnitDTO = parametrizationService.getBusinessUnitById(businessUnitDTO.getCode(), businessUnitDTO.getNoCia(),
					null);
			salida[2][1] =  businessUnitDTO.getName();
		}
		/*
		salida[3][0] = Messages.getString("CampaignForm.store");
		salida[3][1] = Messages.getString(CampaignStatusEnum.ALL.getViewKey());
		StoreDTO storeDTO = customMessageHeaderReportDTO.getStore();
		if (storeDTO != null && storeDTO.getCenter() != null && storeDTO.getNoCia() != null) {
			storeDTO = parametrizationService.getStoreById(storeDTO.getNoCia(), storeDTO.getCenter(), SessionUtil.getUserInfo());
			salida[3][1] = storeDTO.getName();
		}

		salida[4][0] = Messages.getString("CampaignForm.brands");
		salida[4][1] = Messages.getString(CampaignStatusEnum.ALL.getViewKey());
		ArticleDTO articleDTO = customMessageHeaderReportDTO.getArticle();
		if (articleDTO != null && articleDTO.getNoArti() != null && articleDTO.getMarca() != null && articleDTO.getNoCia() != null) {
			articleDTO = parametrizationService.getArticlesById(SessionUtil.getUserInfo(), articleDTO.getNoCia(), articleDTO.getMarca(),
					articleDTO.getNoArti());
			salida[14][1] = articleDTO.getNombre();
		}		
		salida[14][0] = Messages.getString("CampaignForm.articles");
		*/
		//salida[9][0] = Messages.getString("CampaignForm.approver");
		//salida[10][0] = Messages.getString("CampaignForm.creator");
		//salida[9][1] = customMessageHeaderReportDTO.getApprover();
		//salida[10][1] = customMessageHeaderReportDTO.getUserCreator();

		return salida;
	}
	
	private String[][] buildEffectivenessInformation (List<CustomMessageEffectivenessReportDTO> customMessageEffectivenessReportDTO){
		String[][] salida = null;
		if (customMessageEffectivenessReportDTO != null && customMessageEffectivenessReportDTO.get(0) != null){
			NumberFormat defaultFormat = NumberFormat.getPercentInstance();
			defaultFormat.setMinimumFractionDigits(2);
			NumberFormat defaultFormatCurrency = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
			defaultFormatCurrency.setMinimumFractionDigits(2);
			NumberFormat defaultFormatNumber = NumberFormat.getNumberInstance(new Locale("es","CO"));
			defaultFormatNumber.setMaximumFractionDigits(0);
			salida = new String[2][4];
			CustomMessageEffectivenessReportDTO c = customMessageEffectivenessReportDTO.get(0);
			Gson gson = new Gson();
			System.out.println (gson.toJson(c));
			salida[0][0] = "Unidades";
			salida[0][1] = defaultFormatNumber.format(c.getUnidadesTotales());
			salida[0][2] = defaultFormatNumber.format(c.getUnidadesCliente());
			salida[0][3] = defaultFormat.format(c.getPorcentajeUnidades());
			
			salida[1][0] = "Venta";
			salida[1][1] = defaultFormatCurrency.format(c.getVentasTotales());
			salida[1][2] = defaultFormatCurrency.format(c.getVentasCliente());
			salida[1][3] = defaultFormat.format(c.getPorcentajeVentas());
			
		}
		return salida;
	}
	
	private String[][] buildClicksInformation (List<CustomMessageClicksReportDTO> customMessageClicksReportList){
		String[][] salida = new String[7][3];
		if (customMessageClicksReportList != null && customMessageClicksReportList.size() > 0){
			NumberFormat defaultFormat = NumberFormat.getPercentInstance();
			defaultFormat.setMinimumFractionDigits(2);
			int cantidad = customMessageClicksReportList.size();
			String[] columnOne = {"30 min","1 hora", "6 horas", "12 horas", "24 horas", "48 horas", "> 48 horas"};
			int index = 0; 
			switch (customMessageClicksReportList.get(0).getTiempo()){
			case 30: index = 0; break;
			case 60: index = 1; break;
			case 360: index = 2; break;
			case 720: index = 3; break;
			case 1440: index = 4; break;
			case 2880: index = 5; break;	
			default: index = 6;
			}
			for (int i=0; i < cantidad; i++){
				salida[i][0] = columnOne[index];
				salida[i][1] = defaultFormat.format(customMessageClicksReportList.get(i).getAbsoluto());
				salida[i][2] = defaultFormat.format(customMessageClicksReportList.get(i).getRelativo());
				index++;
			}
		}
		
		return salida;
	}
	
	private String[][] fillTables(CustomMessageHeaderReportDTO customMessageHeaderReportDTO, List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportEffectiveCallsDTOList) {
		int rows = customPhoneHeaderReportEffectiveCallsDTOList.size();
		String[][] salida = new String[rows+4][3];
		NumberFormat defaultFormat = NumberFormat.getPercentInstance();
		defaultFormat.setMinimumFractionDigits(2);
		NumberFormat defaultFormatNumber = NumberFormat.getNumberInstance(new Locale("es","CO"));
		defaultFormatNumber.setMaximumFractionDigits(0);

		salida[0] = new String[] { Messages.getString("Report.assignedmessage"), defaultFormatNumber.format(customMessageHeaderReportDTO.getAsignadas()),
				defaultFormat.format(1) };
		salida[1] = new String[] { Messages.getString("Report.realmessage"), defaultFormatNumber.format(customMessageHeaderReportDTO.getRealizadas()),
				defaultFormat.format(customMessageHeaderReportDTO.getPorcentajeRealizadas()) };

		salida[2] = new String[] { Messages.getString("Report.effectivesmessage"), defaultFormatNumber.format(customMessageHeaderReportDTO.getEfectivas()),
				defaultFormat.format(customMessageHeaderReportDTO.getPorcentajeEfectivas()) };
		salida[3] = new String[] { Messages.getString("Report.notEffectivesmessage"), defaultFormatNumber.format(customMessageHeaderReportDTO.getNoEfectivas()),
						defaultFormat.format(customMessageHeaderReportDTO.getPorcentajeNoEfectivas()) };

		int i = 4;
		for (CustomPhoneHeaderReportEffectiveCallsDTO c : customPhoneHeaderReportEffectiveCallsDTOList) {
			salida[i] = new String[] { c.getNombre(), defaultFormatNumber.format(c.getCantidad()), defaultFormat.format(c.getPorcentaje()) };
		}
		
		return salida;
	}
	
	private String[][] buildGeneralPhoneInformation (CustomPhoneHeaderReportDTO customPhoneHeaderReportDTO)
		throws BusinessException {
		String[][] salida = new String[6][2];
		salida[0][0] = Messages.getString("CampaignForm.brands");
		salida[0][1] = Messages.getString(CampaignStatusEnum.ALL.getViewKey());
		MarcasDTO brandDTO = customPhoneHeaderReportDTO.getBrand();
		if (brandDTO != null && brandDTO.getCodigo() != null && brandDTO.getNoCia() != null) {
			brandDTO = parametrizationService.getBrandById(brandDTO.getNoCia(), brandDTO.getCodigo(), null);
			salida[0][1] = brandDTO.getDescripcion();
		}

		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		salida[1][0] = Messages.getString("CampaignForm.description");
		salida[1][1] = customPhoneHeaderReportDTO.getDescription();
		
		salida[2][0] = Messages.getString("CampaignForm.startDate");
		salida[2][1] = f.format(customPhoneHeaderReportDTO.getStartDate());
		salida[3][0] = Messages.getString("CampaignForm.endDate");
		salida[3][1] = f.format(customPhoneHeaderReportDTO.getEndDate());

		salida[4][0] = Messages.getString("Report.totalMessage");
		salida[4][1] = customPhoneHeaderReportDTO.getAsignadas().toString();
		
		salida[5][0] = Messages.getString("CampaignForm.mailSubject");
		salida[5][1] = customPhoneHeaderReportDTO.getMailSubject();
		
		return salida;
	}

	private String[][] fillPhoneTables (CustomPhoneHeaderReportDTO customPhoneHeaderReportDTO, List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportEffectiveCallsDTOList){
		int rows = customPhoneHeaderReportEffectiveCallsDTOList.size();
		String[][] salida = new String[rows+4][3];
		NumberFormat defaultFormat = NumberFormat.getPercentInstance();
		defaultFormat.setMinimumFractionDigits(2);
		NumberFormat defaultFormatNumber = NumberFormat.getNumberInstance(new Locale("es","CO"));
		defaultFormatNumber.setMaximumFractionDigits(0);

		salida[0] = new String[] { Messages.getString("Report.assigned"), defaultFormatNumber.format(customPhoneHeaderReportDTO.getAsignadas()),
				defaultFormat.format(1) };
		salida[1] = new String[] { Messages.getString("Report.realCalls"), defaultFormatNumber.format(customPhoneHeaderReportDTO.getRealizadas()),
				defaultFormat.format(customPhoneHeaderReportDTO.getPorcentajeRealizadas()) };

		salida[2] = new String[] { Messages.getString("Report.effectives"), defaultFormatNumber.format(customPhoneHeaderReportDTO.getEfectivas()),
				defaultFormat.format(customPhoneHeaderReportDTO.getPorcentajeEfectivas()) };
		salida[3] = new String[] { Messages.getString("Report.notEffectives"), defaultFormatNumber.format(customPhoneHeaderReportDTO.getNoEfectivas()),
						defaultFormat.format(customPhoneHeaderReportDTO.getPorcentajeNoEfectivas()) };

		int i = 4;
		for (CustomPhoneHeaderReportEffectiveCallsDTO c : customPhoneHeaderReportEffectiveCallsDTOList) {
			salida[i] = new String[] { c.getNombre(), defaultFormatNumber.format(c.getCantidad()), defaultFormat.format(c.getPorcentaje()) };
		}

		return salida;
	}
	
	private String[][] buildEffectivityPhoneInformationHeader (CustomPhoneHeaderReportDTO customPhoneHeaderReportDTO)
		throws BusinessException {
		String[][] salida = new String[3][2];
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		salida[0][0] = Messages.getString("CampaignForm.eventStartDate");
		salida[0][1] = f.format(customPhoneHeaderReportDTO.getEventStartDate());
		
		salida[1][0] = Messages.getString("CampaignForm.eventEndDate");
		salida[1][1] = f.format(customPhoneHeaderReportDTO.getEventEndDate());
		
		salida[2][0] = Messages.getString("CampaignForm.businessUnit");
		salida[2][1] = Messages.getString(CampaignStatusEnum.ALL.getViewKey());
		BusinessUnitDTO businessUnitDTO = customPhoneHeaderReportDTO.getBusinessUnit();
		if (businessUnitDTO != null && businessUnitDTO.getCode() != null && businessUnitDTO.getNoCia() != null) {
			businessUnitDTO = parametrizationService.getBusinessUnitById(businessUnitDTO.getCode(), businessUnitDTO.getNoCia(),
					null);
			salida[2][1] =  businessUnitDTO.getName();
		}
		return salida;
	}
}

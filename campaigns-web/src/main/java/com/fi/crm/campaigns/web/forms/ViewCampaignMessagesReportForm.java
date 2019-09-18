package com.fi.crm.campaigns.web.forms;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.addons.chart.js.ChartConfiguration;
import org.vaadin.addons.chart.js.ChartJS;
import org.vaadin.addons.chart.js.data.BarSeriesDataSet;

import com.fi.crm.campaigns.business.services.parametrization.ParametrizationServiceInterface;
import com.fi.crm.campaigns.business.services.report.ReportServiceInterface;
import com.fi.crm.campaigns.business.util.PdfReportGenerator;
import com.fi.crm.campaigns.common.dto.ArticleDTO;
import com.fi.crm.campaigns.common.dto.BusinessUnitDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageClicksReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageDetailReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageEffectivenessReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageHeaderReportDTO;
import com.fi.crm.campaigns.common.dto.CustomPhoneHeaderReportEffectiveCallsDTO;
import com.fi.crm.campaigns.common.dto.MarcasDTO;
import com.fi.crm.campaigns.common.dto.StoreDTO;
import com.fi.crm.campaigns.common.enums.CampaignStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.infrastructure.exception.ErrorCodesEnum;
import com.fi.crm.campaigns.web.graphics.ChartUtil;
import com.fi.crm.campaigns.web.util.CommonUtil;
import com.fi.crm.campaigns.web.util.ExcelExportSXSSF;
import com.fi.crm.campaigns.web.util.Messages;
import com.fi.crm.campaigns.web.util.SessionUtil;
import com.vaadin.addon.tableexport.DefaultTableHolder;
import com.vaadin.addon.tableexport.TemporaryFileDownloadResource;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class ViewCampaignMessagesReportForm extends BaseFormVertical {

	private static final long serialVersionUID = 4851822708780578484L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ViewCampaignMessagesReportForm.class);

	private ReportServiceInterface reportService;
	private ParametrizationServiceInterface parametrizationService;

	private VerticalLayout mainLayout;
	private HorizontalLayout innerDashOneLayout;
	// General Information
	private Label name;
	private Label contactType;
	private Label description;
	private Label campaignStartDate;
	private Label campaignEndDate;
	private Label eventStartDate;
	private Label eventEndDate;
	private Label subject;
	private Label totalCalls;
	private Label creator;
	private Label approver;
	private Label businessUnit;
	private Label store;
	private Label brand;
	private Label article;
	// General Statistics
	// Effective Call
	private Table effectiveCallsTable;
	// Call Protocol
	private Label message;
	private UI parent;

	public ViewCampaignMessagesReportForm(ReportServiceInterface reportService, ParametrizationServiceInterface parametrizationService, UI parent) {
		super();
		this.parent = parent;
		this.reportService = reportService;
		this.parametrizationService = parametrizationService;
		setImmediate(true);
		this.mainLayout = new VerticalLayout();
		this.mainLayout.setMargin(true);
		this.mainLayout.setSpacing(true);
		this.mainLayout.setSizeFull();
		this.setSizeFull();
		addComponent(this.mainLayout);
		addStyleName("campaigns_pop-up");

		this.init();
	}

	private void init() {
		try {
			// General Information
			name = new Label();
			contactType = new Label();
			description = new Label();
			campaignStartDate = new Label();
			campaignEndDate = new Label();
			eventStartDate = new Label();
			eventEndDate = new Label();
			subject = new Label();
			totalCalls = new Label();
			approver = new Label();
			creator = new Label();
			businessUnit = new Label();
			store = new Label();
			brand = new Label();
			article = new Label();
			// General Statistics
			// Effective Call
			effectiveCallsTable = new Table();
			effectiveCallsTable.setImmediate(false);
			effectiveCallsTable.setWidth("100%");
			effectiveCallsTable.setHeight("500px");

			// Call Protocol
			message = new Label("", ContentMode.HTML);

			Label nameLabel = new Label(Messages.getString("CampaignForm.name"));
			nameLabel.addStyleName("tinytitlefont");
			Label contactTypeLabel = new Label(Messages.getString("CampaignForm.contactType"));
			contactTypeLabel.addStyleName("tinytitlefont");
			Label descLabel = new Label(Messages.getString("CampaignForm.description"));
			descLabel.addStyleName("tinytitlefont");
			Label startDateLabel = new Label(Messages.getString("CampaignForm.startDate"));
			startDateLabel.addStyleName("tinytitlefont");
			Label endDateLabel = new Label(Messages.getString("CampaignForm.endDate"));
			endDateLabel.addStyleName("tinytitlefont");
			Label eventStartDateLabel = new Label(Messages.getString("CampaignForm.eventStartDate"));
			eventStartDateLabel.addStyleName("tinytitlefont");
			Label eventEndDateLabel = new Label(Messages.getString("CampaignForm.eventEndDate"));
			eventEndDateLabel.addStyleName("tinytitlefont");

			Label subjectLabel = new Label(Messages.getString("CampaignForm.mailSubject"));
			subjectLabel.addStyleName("tinytitlefont");
			Label totalCallsLabel = new Label(Messages.getString("Report.totalMessage"));
			totalCallsLabel.addStyleName("tinytitlefont");
			Label approverLabel = new Label(Messages.getString("CampaignForm.approver"));
			approverLabel.addStyleName("tinytitlefont");
			Label creatorLabel = new Label(Messages.getString("CampaignForm.creator"));
			creatorLabel.addStyleName("tinytitlefont");
			Label businessUnitLabel = new Label(Messages.getString("CampaignForm.businessUnit"));
			businessUnitLabel.addStyleName("tinytitlefont");
			Label storeLabel = new Label(Messages.getString("CampaignForm.store"));
			storeLabel.addStyleName("tinytitlefont");
			Label brandLabel = new Label(Messages.getString("CampaignForm.brands"));
			brandLabel.addStyleName("tinytitlefont");
			Label articleLabel = new Label(Messages.getString("CampaignForm.articles"));
			articleLabel.addStyleName("tinytitlefont");

			// General Statistics
			// Effective Call
			defineEffectiveCallsTable();

			VerticalLayout generalInformationLayout = new VerticalLayout();
			final Panel generalInformationPanel = new Panel(Messages.getString("Report.generalInformation"));
			generalInformationPanel.setHeight("500px");
			generalInformationPanel.setVisible(true);

			HorizontalLayout nameLayout = new HorizontalLayout();
			nameLayout.setSpacing(true);
			nameLayout.setSizeFull();
			nameLayout.addComponent(nameLabel);
			nameLayout.addComponent(this.name);
			generalInformationLayout.addComponent(nameLayout);

			HorizontalLayout typeLayout = new HorizontalLayout();
			typeLayout.setSpacing(true);
			typeLayout.setSizeFull();
			typeLayout.addComponent(contactTypeLabel);
			typeLayout.addComponent(this.contactType);
			generalInformationLayout.addComponent(typeLayout);

			HorizontalLayout descLayout = new HorizontalLayout();
			descLayout.setSpacing(true);
			descLayout.setSizeFull();
			descLayout.addComponent(descLabel);
			descLayout.addComponent(this.description);
			generalInformationLayout.addComponent(descLayout);

			HorizontalLayout startDateLayout = new HorizontalLayout();
			startDateLayout.setSpacing(true);
			startDateLayout.setSizeFull();
			startDateLayout.addComponent(startDateLabel);
			startDateLayout.addComponent(this.campaignStartDate);
			generalInformationLayout.addComponent(startDateLayout);

			HorizontalLayout endDateLayout = new HorizontalLayout();
			endDateLayout.setSpacing(true);
			endDateLayout.setSizeFull();
			endDateLayout.addComponent(endDateLabel);
			endDateLayout.addComponent(this.campaignEndDate);
			generalInformationLayout.addComponent(endDateLayout);

			HorizontalLayout eventStartDateLayout = new HorizontalLayout();
			eventStartDateLayout.setSpacing(true);
			eventStartDateLayout.setSizeFull();
			eventStartDateLayout.addComponent(eventStartDateLabel);
			eventStartDateLayout.addComponent(this.eventStartDate);
			generalInformationLayout.addComponent(eventStartDateLayout);

			HorizontalLayout eventEndDateLayout = new HorizontalLayout();
			eventEndDateLayout.setSpacing(true);
			eventEndDateLayout.setSizeFull();
			eventEndDateLayout.addComponent(eventEndDateLabel);
			eventEndDateLayout.addComponent(this.eventEndDate);
			generalInformationLayout.addComponent(eventEndDateLayout);

			HorizontalLayout subjectLayout = new HorizontalLayout();
			subjectLayout.setSpacing(true);
			subjectLayout.setSizeFull();
			subjectLayout.addComponent(subjectLabel);
			subjectLayout.addComponent(this.subject);
			generalInformationLayout.addComponent(subjectLayout);

			HorizontalLayout totalCallsLayout = new HorizontalLayout();
			totalCallsLayout.setSpacing(true);
			totalCallsLayout.setSizeFull();
			totalCallsLayout.addComponent(totalCallsLabel);
			totalCallsLayout.addComponent(this.totalCalls);
			generalInformationLayout.addComponent(totalCallsLayout);

			HorizontalLayout creatorLayout = new HorizontalLayout();
			creatorLayout.setSpacing(true);
			creatorLayout.setSizeFull();
			creatorLayout.addComponent(creatorLabel);
			creatorLayout.addComponent(this.creator);
			generalInformationLayout.addComponent(creatorLayout);

			HorizontalLayout approverLayout = new HorizontalLayout();
			approverLayout.setSpacing(true);
			approverLayout.setSizeFull();
			approverLayout.addComponent(approverLabel);
			approverLayout.addComponent(this.approver);
			generalInformationLayout.addComponent(approverLayout);

			HorizontalLayout businessUnitLayout = new HorizontalLayout();
			businessUnitLayout.setSpacing(true);
			businessUnitLayout.setSizeFull();
			businessUnitLayout.addComponent(businessUnitLabel);
			businessUnitLayout.addComponent(this.businessUnit);
			generalInformationLayout.addComponent(businessUnitLayout);

			HorizontalLayout storeLayout = new HorizontalLayout();
			storeLayout.setSpacing(true);
			storeLayout.setSizeFull();
			storeLayout.addComponent(storeLabel);
			storeLayout.addComponent(this.store);
			generalInformationLayout.addComponent(storeLayout);

			HorizontalLayout brandLayout = new HorizontalLayout();
			brandLayout.setSpacing(true);
			brandLayout.setSizeFull();
			brandLayout.addComponent(brandLabel);
			brandLayout.addComponent(this.brand);
			generalInformationLayout.addComponent(brandLayout);

			HorizontalLayout articleLayout = new HorizontalLayout();
			articleLayout.setSpacing(true);
			articleLayout.setSizeFull();
			articleLayout.addComponent(articleLabel);
			articleLayout.addComponent(this.article);
			generalInformationLayout.addComponent(articleLayout);

			generalInformationPanel.setContent(generalInformationLayout);
			mainLayout.addComponent(generalInformationPanel);

			VerticalLayout graphicLayout = new VerticalLayout();
			graphicLayout.setSizeFull();
			final Panel graphicPanel = new Panel(Messages.getString("Report.generalStatisticsMessage"));
			graphicPanel.setWidth(100,Unit.PERCENTAGE);
			graphicPanel.setHeight("600px");
			graphicPanel.setVisible(false);

			VerticalLayout dashBoard = new VerticalLayout();
			dashBoard.setSizeFull();
			dashBoard.setMargin(true);
			dashBoard.setImmediate(true);

			innerDashOneLayout = new HorizontalLayout();
			innerDashOneLayout.setSpacing(true);
			innerDashOneLayout.setSizeFull();

			dashBoard.addComponent(innerDashOneLayout);

			graphicPanel.setContent(dashBoard);

			Button showHideGraphicButton = new Button();
			showHideGraphicButton.setIcon(FontAwesome.DASHBOARD);
			showHideGraphicButton.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = -1749248287390847761L;

				@Override
				public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
					graphicPanel.setVisible(!graphicPanel.isVisible());
				}
			});

			HorizontalLayout showHideGraphicLayout = new HorizontalLayout();
			showHideGraphicLayout.setSpacing(true);

			Label showHideGraphic = new Label(Messages.getString("Report.generalStatisticsMessage"));
			showHideGraphicLayout.addComponent(showHideGraphicButton);
			showHideGraphicLayout.addComponent(showHideGraphic);
			graphicLayout.addComponent(showHideGraphicLayout);
			graphicLayout.addComponent(graphicPanel);

			mainLayout.addComponent(graphicLayout);

			VerticalLayout messageLayout = new VerticalLayout();
			final Panel messagePanel = new Panel(Messages.getString("Report.protocolMessage"));
			messagePanel.setWidth("100%");
			messagePanel.setVisible(false);
			messagePanel.setContent(this.message);

			Button showHideMesageButton = new Button();
			showHideMesageButton.setIcon(FontAwesome.COMMENTS_O);
			showHideMesageButton.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 8020921614119893578L;

				@Override
				public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
					messagePanel.setVisible(!messagePanel.isVisible());
				}
			});

			HorizontalLayout showHideMesageLayout = new HorizontalLayout();
			showHideMesageLayout.setSpacing(true);

			Label showHideMesageLabel = new Label(Messages.getString("Report.protocolMessage"));

			showHideMesageLayout.addComponent(showHideMesageButton);
			showHideMesageLayout.addComponent(showHideMesageLabel);

			messageLayout.addComponent(showHideMesageLayout);
			messageLayout.addComponent(messagePanel);
			mainLayout.addComponent(messageLayout);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public void loadFields(CustomMessageHeaderReportDTO customMessageHeaderReportDTO,
			List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportEffectiveCallsDTO) {
		try {
			name.setValue(customMessageHeaderReportDTO.getName());
			contactType.setValue(Messages.getString(customMessageHeaderReportDTO.getContactType().getViewKey()));
			description.setValue(customMessageHeaderReportDTO.getDescription());
			campaignStartDate.setValue(CommonUtil.formatDate(customMessageHeaderReportDTO.getStartDate(), "dd/MM/yyyy"));
			campaignEndDate.setValue(CommonUtil.formatDate(customMessageHeaderReportDTO.getEndDate(), "dd/MM/yyyy"));
			eventStartDate.setValue(CommonUtil.formatDate(customMessageHeaderReportDTO.getEventStartDate(), "dd/MM/yyyy"));
			eventEndDate.setValue(CommonUtil.formatDate(customMessageHeaderReportDTO.getEventEndDate(), "dd/MM/yyyy"));

			subject.setValue(customMessageHeaderReportDTO.getMailSubject());
			totalCalls.setValue(customMessageHeaderReportDTO.getAsignadas().toString());
			approver.setValue(customMessageHeaderReportDTO.getApprover());
			creator.setValue(customMessageHeaderReportDTO.getUserCreator());
			fillTables(customMessageHeaderReportDTO, customPhoneHeaderReportEffectiveCallsDTO);
			message.setValue(customMessageHeaderReportDTO.getMessage());
			businessUnit.setValue(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			BusinessUnitDTO businessUnitDTO = customMessageHeaderReportDTO.getBusinessUnit();
			if (businessUnitDTO != null && businessUnitDTO.getCode() != null && businessUnitDTO.getNoCia() != null) {
				businessUnitDTO = parametrizationService.getBusinessUnitById(businessUnitDTO.getCode(), businessUnitDTO.getNoCia(),
						SessionUtil.getUserInfo());
				businessUnit.setValue(businessUnitDTO.getName());
			}
			store.setValue(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			StoreDTO storeDTO = customMessageHeaderReportDTO.getStore();
			if (storeDTO != null && storeDTO.getCenter() != null && storeDTO.getNoCia() != null) {
				storeDTO = parametrizationService.getStoreById(storeDTO.getNoCia(), storeDTO.getCenter(), SessionUtil.getUserInfo());
				store.setValue(storeDTO.getName());
			}

			brand.setValue(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			MarcasDTO brandDTO = customMessageHeaderReportDTO.getBrand();
			if (brandDTO != null && brandDTO.getCodigo() != null && brandDTO.getNoCia() != null) {
				brandDTO = parametrizationService.getBrandById(brandDTO.getNoCia(), brandDTO.getCodigo(), SessionUtil.getUserInfo());
				brand.setValue(brandDTO.getDescripcion());
			}

			article.setValue(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			ArticleDTO articleDTO = customMessageHeaderReportDTO.getArticle();
			if (articleDTO != null && articleDTO.getNoArti() != null && articleDTO.getMarca() != null && articleDTO.getNoCia() != null) {
				articleDTO = parametrizationService.getArticlesById(SessionUtil.getUserInfo(), articleDTO.getNoCia(), articleDTO.getMarca(),
						articleDTO.getNoArti());
				article.setValue(articleDTO.getNombre());
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private void defineEffectiveCallsTable() {
		effectiveCallsTable.addContainerProperty("C", Label.class, null);
		effectiveCallsTable.setColumnWidth("C",10);
		effectiveCallsTable.addContainerProperty(Messages.getString("Report.effectivemessage.tableheader.one"), String.class, null);
		effectiveCallsTable.setColumnWidth(Messages.getString("Report.effectivemessage.tableheader.one"), 300);
		effectiveCallsTable.addContainerProperty(Messages.getString("Report.effectivemessage.tableheader.two"), String.class, null);
		effectiveCallsTable.setColumnWidth(Messages.getString("Report.effectivemessage.tableheader.two"), 100);
		effectiveCallsTable.addContainerProperty(Messages.getString("Report.effectivemessage.tableheader.three"), String.class, null);
		effectiveCallsTable.setColumnWidth(Messages.getString("Report.effectivemessage.tableheader.three"), 100);
		
	}

	private void fillTables(CustomMessageHeaderReportDTO customMessageHeaderReportDTO, List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportEffectiveCallsDTOList) {
		int i = 0;
		NumberFormat defaultFormat = NumberFormat.getPercentInstance();
		defaultFormat.setMinimumFractionDigits(2);
		List<String> labels = new ArrayList<String>();
		List<Float> data = new ArrayList<Float>();
		effectiveCallsTable.addItem(new Object[] { new Label("<span style='display: inline-block; background-color: "+ChartUtil.preferredBarColors[i++]+"; width: 10px; height: 10px;'></span>",ContentMode.HTML)
		, Messages.getString("Report.assignedmessage"), customMessageHeaderReportDTO.getAsignadas().toString(),
				defaultFormat.format(1) }, 1);
		effectiveCallsTable.addItem(new Object[] { new Label("<span style='display: inline-block; background-color: "+ChartUtil.preferredBarColors[i++]+"; width: 10px; height: 10px;'></span>",ContentMode.HTML)
		, Messages.getString("Report.realmessage"), customMessageHeaderReportDTO.getRealizadas().toString(),
				defaultFormat.format(customMessageHeaderReportDTO.getPorcentajeRealizadas()) }, 2);

		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMinimumFractionDigits(2);
		float asignadas = customMessageHeaderReportDTO.getAsignadas();
		float realizadas = customMessageHeaderReportDTO.getRealizadas();
		//labels.add(Messages.getString("Report.assignedmessage"));
		labels.add("");
		//labels.add(Messages.getString("Report.realmessage"));
		labels.add("");
		data.add(asignadas);
		data.add(realizadas);
		effectiveCallsTable.addItem(new Object[] { new Label("<span style='display: inline-block; background-color: "+ChartUtil.preferredBarColors[i++]+"; width: 10px; height: 10px;'></span>",ContentMode.HTML),
				Messages.getString("Report.effectivesmessage"), customMessageHeaderReportDTO.getEfectivas().toString(),
				defaultFormat.format(customMessageHeaderReportDTO.getPorcentajeEfectivas()) }, 3);
		effectiveCallsTable.addItem(
				new Object[] { new Label("<span style='display: inline-block; background-color: "+ChartUtil.preferredBarColors[i++]+"; width: 10px; height: 10px;'></span>",ContentMode.HTML),
						Messages.getString("Report.notEffectivesmessage"), customMessageHeaderReportDTO.getNoEfectivas().toString(),
						defaultFormat.format(customMessageHeaderReportDTO.getPorcentajeNoEfectivas()) }, 4);

		//labels.add(Messages.getString("Report.effectivesmessage"));
		labels.add("");
		//labels.add(Messages.getString("Report.notEffectivesmessage"));
		labels.add("");
		float efectivas = customMessageHeaderReportDTO.getEfectivas();
		float noEfectivas = customMessageHeaderReportDTO.getNoEfectivas();
		data.add(efectivas);
		data.add(noEfectivas);
		i = effectiveCallsTable.size();
		for (CustomPhoneHeaderReportEffectiveCallsDTO c : customPhoneHeaderReportEffectiveCallsDTOList) {
			effectiveCallsTable.addItem(new Object[] { new Label("<span style='display: inline-block; background-color: "+ChartUtil.preferredBarColors[i]+"; width: 10px; height: 10px;'></span>",ContentMode.HTML),
					c.getNombre(), c.getCantidad().toString(), defaultFormat.format(c.getPorcentaje()) }, i);
			//labels.add(c.getNombre());
			labels.add("");
			data.add(Float.valueOf(c.getCantidad()));
			i++;
		}

		ChartJS<BarSeriesDataSet> barChart = createBarChart(labels, data);
		barChart.setHeight(550, Unit.PIXELS);
		barChart.setWidth(400, Unit.PIXELS);
		innerDashOneLayout.addComponent(barChart);
		innerDashOneLayout.addComponent(effectiveCallsTable);
	}

	private ChartJS<BarSeriesDataSet> createBarChart(List<String> labels, List<Float> data) {
		
		List<BarSeriesDataSet> barSeries = new ArrayList<BarSeriesDataSet>();
		int i = 0;
		for (Float value : data){
			BarSeriesDataSet values = new BarSeriesDataSet();
			values.setFillColor(ChartUtil.preferredBarColors[i]);
			values.setHighlightFill(ChartUtil.preferredBarColors[i]);
			Float[] valuesToDraw = new Float[labels.size()];
			valuesToDraw[i] = value;
			values.setData(Arrays.asList(valuesToDraw));
			barSeries.add(values);
			i++;
		}
		
		ChartJS<BarSeriesDataSet> chartJS = new ChartJS<>(createChartConfiguration(true, false), labels, barSeries);
		return chartJS;
	}

	private ChartConfiguration createChartConfiguration(boolean datasetFill, boolean bezier) {
		ChartConfiguration chartConfiguration = new ChartConfiguration();
		chartConfiguration.animation = true;
		chartConfiguration.datasetFill = datasetFill;
		chartConfiguration.bezierCurve = bezier;
		chartConfiguration.scaleBeginAtZero = true;
		chartConfiguration.tooltipTemplate = "<%=datasetLabel%>: <%= value %>";
		chartConfiguration.multiTooltipTemplate = "<%=datasetLabel%>: <%= value %>";
		return chartConfiguration;
	}

	public String buildDownloadPdfButton (CustomMessageHeaderReportDTO customMessageHeaderReportDTO,
			List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportEffectiveCallsDTO,
			List<CustomMessageClicksReportDTO> customMessageClicksReportList,
			List<CustomMessageEffectivenessReportDTO> customMessageEffectivenessReportDTO) throws BusinessException{
		PdfReportGenerator report = new PdfReportGenerator(this.reportService, this.parametrizationService);
		return report.generateMessagesCampaignReport(customMessageHeaderReportDTO, customPhoneHeaderReportEffectiveCallsDTO, customMessageClicksReportList,customMessageEffectivenessReportDTO);
	}
	

	public void buildDownloadButton(final CustomMessageHeaderReportDTO customMessageHeaderReportDTO,
			final List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportEffectiveCallsDTOList,
			final List<CustomMessageClicksReportDTO> customMessageClicksReportList,
			final List<CustomMessageEffectivenessReportDTO> customMessageEffectivenessReportDTO) {
		final String reportTitle = Messages.getString("General.campaign") + customMessageHeaderReportDTO.getName();
		final Button reportButton = new Button();
		final Button reportButtonPdf = new Button();
		reportButtonPdf.setIcon(FontAwesome.FILE_PDF_O);
		reportButtonPdf.setIcon(FontAwesome.FILE_PDF_O);
		reportButtonPdf.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 9155187714631957200L;

			@SuppressWarnings("deprecation")
			public void buttonClick(ClickEvent event) {
				try {
					String pdfname = buildDownloadPdfButton(customMessageHeaderReportDTO, customPhoneHeaderReportEffectiveCallsDTOList, customMessageClicksReportList, customMessageEffectivenessReportDTO);
					 TemporaryFileDownloadResource resource;
				        try {
				        	String mimeType = "application/vnd.pdf";
				        	File fileToExport = new File(pdfname);
				            resource =
				                    new TemporaryFileDownloadResource(parent, pdfname, mimeType, fileToExport);
				            parent.getPage().open(resource, null, false);
				        } catch (final FileNotFoundException e) {
				            LOGGER.error("Sending file to user failed with FileNotFoundException " + e);
				            throw new BusinessException("Archivo no encontrado: " +pdfname , ErrorCodesEnum.GENERIC_BUSINESS_EXCEPTION);
				        }
				} catch (BusinessException e) {
					CommonUtil.showNotification(Messages.getString("General.err.download.report"), Type.ERROR_MESSAGE);
					LOGGER.error(e.getMessage(), e);
				}
			}
		});
		final UI parent = this.parent;
		reportButton.setIcon(FontAwesome.FILE_EXCEL_O);
		reportButton.setIcon(FontAwesome.FILE_EXCEL_O);
		reportButton.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -1165789436231907994L;

			@SuppressWarnings("unchecked")
			@Override
			public void buttonClick(ClickEvent event) {

				try {
					List<CustomMessageDetailReportDTO> customMessageDetailReportDTOList = reportService.getMessageDetailedReportByCampaingId(
							customMessageHeaderReportDTO.getCampaignId(), customMessageHeaderReportDTO.getContactType().getValue(),
							SessionUtil.getUserInfo());
					Table reportTable = buildTableResults(customMessageDetailReportDTOList);

					IndexedContainer cont = new IndexedContainer();
					Container source = reportTable.getContainerDataSource();
					for (Object prop : source.getContainerPropertyIds()) {
						cont.addContainerProperty(prop, source.getType(prop), null);
					}
					for (Object id : source.getItemIds()) {
						Item sourceItem = source.getItem(id);
						Item destItem = cont.addItem(id);
						for (Object prop : source.getContainerPropertyIds()) {
							Object value = sourceItem.getItemProperty(prop).getValue();
							destItem.getItemProperty(prop).setValue(value);
						}
					}

					DefaultTableHolder holder = new DefaultTableHolder(reportTable) {
						private static final long serialVersionUID = 1L;

						@Override
						public UI getUI() {
							return parent;
						}
					};
					ExcelExportSXSSF excelExport = new ExcelExportSXSSF(holder);
					excelExport.setDisplayTotals(false);
					excelExport.setReportTitle(reportTitle);
					excelExport.export();
				} catch (BusinessException e) {
					CommonUtil.showNotification(Messages.getString("General.err.download.report"), Type.ERROR_MESSAGE);
					LOGGER.error(e.getMessage(), e);
				}

			}
		});
		HorizontalLayout downloadReportLayout = new HorizontalLayout();
		downloadReportLayout.setSpacing(true);

		Label downloadReportLabel = new Label(Messages.getString("General.report.download"));

		downloadReportLayout.addComponent(reportButton);
		downloadReportLayout.addComponent(reportButtonPdf);
		downloadReportLayout.addComponent(downloadReportLabel);

		this.mainLayout.addComponent(downloadReportLayout);

	}

	private Table buildTableResults(List<CustomMessageDetailReportDTO> customPhoneDetailReportDTOList) {
		Table reportTable = new Table();
		reportTable.addContainerProperty("name", String.class, "", Messages.getString("ClientForm.firstName"), null, null);
		reportTable.addContainerProperty("telephone", String.class, "", Messages.getString("ClientForm.telephone"), null, null);
		reportTable.addContainerProperty("cellphone", String.class, "", Messages.getString("ClientForm.cellPhone"), null, null);
		reportTable.addContainerProperty("email", String.class, "", Messages.getString("ClientForm.email"), null, null);
		reportTable.addContainerProperty("status", String.class, "", Messages.getString("ClientForm.status"), null, null);
		reportTable.addContainerProperty("observations", String.class, "", Messages.getString("ClientForm.comment"), null, null);

		int i = 1;
		for (CustomMessageDetailReportDTO dtoObject : customPhoneDetailReportDTOList) {
			reportTable.addItem(
					new Object[] { dtoObject.getNombre(), dtoObject.getTelefono(), dtoObject.getCelular(), dtoObject.getCorreo(), dtoObject.getEstado(),
							dtoObject.getObservaciones() }, i);
			i++;
		}
		return reportTable;
	}
}

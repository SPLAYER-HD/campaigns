package com.fi.crm.campaigns.web.graphics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.vaadin.addons.chart.js.ChartConfiguration;
import org.vaadin.addons.chart.js.ChartJS;
import org.vaadin.addons.chart.js.data.BarSeriesDataSet;
import org.vaadin.addons.chart.js.data.PieSeriesDataSet;

public class ChartUtil {
	public static String[] preferredBarColors = {"#68A2D5","#5BC0DE","#5CB85C","#F2B661","#F2B661","#EB976E","#E1625E","#436888","#9897DC","#505688","#97DCC9"};

	public ChartJS<BarSeriesDataSet> createBarChart(List<String> labels,
			List<Float> data, String filColor, String highLightFill) {
		BarSeriesDataSet values = new BarSeriesDataSet();
		values.setFillColor(filColor);
		values.setHighlightFill(highLightFill);
		values.setData(data);
		ChartJS<BarSeriesDataSet> chartJS = new ChartJS<>(
				createChartConfiguration(true, false), labels,
				Arrays.asList(new BarSeriesDataSet[] { values }));
		return chartJS;
	}

	public ChartConfiguration createChartConfiguration(boolean datasetFill,
			boolean bezier) {
		ChartConfiguration chartConfiguration = new ChartConfiguration();
		chartConfiguration.animation = true;
		chartConfiguration.datasetFill = datasetFill;
		chartConfiguration.bezierCurve = bezier;
		chartConfiguration.scaleBeginAtZero = true;
		chartConfiguration.tooltipTemplate = "<%=datasetLabel%>: <%= value %>";
		chartConfiguration.multiTooltipTemplate = "<%=datasetLabel%>: <%= value %>";
		return chartConfiguration;
	}

	public ChartConfiguration createPieChartConfiguration() {
		ChartConfiguration chartConfiguration = new ChartConfiguration();
		chartConfiguration.animation = true;
		chartConfiguration.segmentShowStroke = true;
		chartConfiguration.segmentStrokeColor = "#fff";
		chartConfiguration.segmentStrokeWidth = 2;
		chartConfiguration.legendTemplate = "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>";
		return chartConfiguration;
	}

	public ChartJS<PieSeriesDataSet> createPieChart(List<String> series,
			List<Float> percentages, List<String> colors, List<String> highLight) {
		
		List<PieSeriesDataSet> pieSeriesDataSet = new ArrayList<PieSeriesDataSet>();
		int i=0;
		for (String serie : series) {
			PieSeriesDataSet serieData = new PieSeriesDataSet();
			serieData.label = serie;
			serieData.setColor(colors.get(i));
			serieData.setHighlight(highLight.get(i));
			serieData.setValue(percentages.get(i));
			pieSeriesDataSet.add(serieData);
		}
		
		ChartJS<PieSeriesDataSet> chartJS = new ChartJS<>(
				createPieChartConfiguration(),
				Arrays.asList(new String[] { "m" }),
				pieSeriesDataSet);

		return chartJS;
	}
}

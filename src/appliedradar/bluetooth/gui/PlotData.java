/*package appliedradar.bluetooth.gui;


import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;
import android.os.Bundle;
//import android.app.Activity;

public class PlotData extends Bluetooth_GUI {

//	private GraphicalView mChartView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        if(mChartView == null) {
//        	LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
//        	mChartView = ChartFactory.getLineChartView(this, getMyData(),getMyRenderer());
//        	layout.addView(mChartView);
//        }
//        else {
//        	mChartView.repaint();	// use this whenever data has changed and you want to redraw
//        }
    }
    
//    @Override
//    protected void onResume() {
//    	super.onResume();
//    	if(mChartView != null){
//    		mChartView.repaint();
//    	}
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
    
    public XYMultipleSeriesDataset getMyData() {
    	XYMultipleSeriesDataset myData = new XYMultipleSeriesDataset();
    	XYSeries dataSeries = new XYSeries("data");
    		dataSeries.add(0, 2);
    		dataSeries.add(1, 1);
    		dataSeries.add(2, 4);
    		dataSeries.add(3, 3);
    		dataSeries.add(4, 2);
    		dataSeries.add(5, 6);
    		myData.addSeries(dataSeries);
    		
    		XYSeries dataSeries2 = new XYSeries("data2");
    		dataSeries2.add(0,1);
    		dataSeries2.add(1,1);
    		dataSeries2.add(2,2);
    		dataSeries2.add(3,1);
    		dataSeries2.add(4,2);
    		dataSeries2.add(5,4);
    		myData.addSeries(dataSeries2);
    		return myData;
    }
    
    public XYMultipleSeriesRenderer getMyRenderer() {
    	XYSeriesRenderer r = new XYSeriesRenderer();
    	r.setColor(Color.BLUE);
    	r.setLineWidth(10);

    	r.setPointStyle(PointStyle.SQUARE); // CIRCLE, DIAMOND , POINT, TRIANGLE, X
    	r.setFillPoints(true); // not for point or x
    	// don't know how to set point size or point color

    	r.setFillBelowLine(true);
    	r.setFillBelowLineColor(Color.WHITE);

    	XYMultipleSeriesRenderer myRenderer = new XYMultipleSeriesRenderer();
    	myRenderer.addSeriesRenderer(r);
    	
    	XYSeriesRenderer r2 = new XYSeriesRenderer();
    	r2.setColor(Color.RED);
    	r2.setLineWidth(10);
    	r2.setPointStyle(PointStyle.DIAMOND);
    	r2.setFillPoints(true);
    	r2.setFillBelowLine(false);
    	myRenderer.addSeriesRenderer(r2);
    	return myRenderer;

    	}
}
*/
package appliedradar.bluetooth.gui;



import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;



public class Bluetooth_GUI extends Activity implements OnMenuItemClickListener {

	private GraphicalView mChartView;


@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if(mChartView == null) {
        	RelativeLayout layout = (RelativeLayout) findViewById(R.id.chart);
        	mChartView = ChartFactory.getLineChartView(this, getMyData(),getMyRenderer());
        	layout.addView(mChartView);
        }
        else {
        	mChartView.repaint();	// use this whenever data has changed and you want to redraw
        }
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	if(mChartView != null){
    		mChartView.repaint();
    	}
    }

    // Action Bar displays options when Menu item in Action bar is clicked
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }
    
    
    public void sendCollectSignal(View toast) {
    	Toast.makeText(this, "Selected Collect Data", Toast.LENGTH_SHORT).show();
    }
    
    public void openArchive(View toast2) {
    	Toast.makeText(this, "Selected Load Data", Toast.LENGTH_SHORT).show();
    }
    
    // Plotting pop-up menu
  	public void plotMenu(View view) {
  		PopupMenu popup = new PopupMenu(this, view);
  		popup.setOnMenuItemClickListener(this);
  		popup.inflate(R.menu.plotting_menu);
//  	MenuInflater inflater = getMenuInflater();
//  	inflater.inflate(R.menu.plotting_menu, popup.getMenu());
  		popup.show();
  	}

  	// Saving pop-up menu
  	public void saveMenu(View display) {
  		PopupMenu popup2 = new PopupMenu(this, display);
  		popup2.setOnMenuItemClickListener(this);
  		popup2.inflate(R.menu.saving_menu);
  		popup2.show();
  	}

  	// For testing button & popup menu purposes only!
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
        	case R.id.text_file:
                Toast.makeText(this, "Selected Text File", Toast.LENGTH_SHORT).show();
                break;
            case R.id.binary_file:
                Toast.makeText(this, "Selected Binary File", Toast.LENGTH_SHORT).show();
                break;
            case R.id.compressed_file:
                Toast.makeText(this, "Selected Compressed File", Toast.LENGTH_SHORT).show();
                break;
            case R.id.matlab_file:
                Toast.makeText(this, "Selected Matlab File", Toast.LENGTH_SHORT).show();
                break;
            case R.id.raw_plot:
                Toast.makeText(this, "Selected Raw Plot", Toast.LENGTH_SHORT).show();
                break;
            case R.id.range_plot:
                Toast.makeText(this, "Selected Range Plot", Toast.LENGTH_SHORT).show();
                break;
            case R.id.doppler_plot:
                Toast.makeText(this, "Selected Doppler File", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fft_plot:
                Toast.makeText(this, "Selected FFT File", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sar_plot:
                Toast.makeText(this, "Selected SAR File", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    public XYMultipleSeriesDataset getMyData() {
    	XYMultipleSeriesDataset myData = new XYMultipleSeriesDataset();
    	XYSeries dataSeries = new XYSeries("FMCW Radar- Simulated Data");
    		dataSeries.add(1, 8190);
    		dataSeries.add(2, 7615);
    		dataSeries.add(3, 5969);
    		dataSeries.add(4, 3483);
    		dataSeries.add(5, 505);
    		dataSeries.add(6, -2544);
    		dataSeries.add(7, -5236);
    		dataSeries.add(8, -7190);
    		dataSeries.add(9, -8130);
    		dataSeries.add(10, -7921);
    		dataSeries.add(11, -6593);
    		dataSeries.add(12, -4331);
    		dataSeries.add(13, -1456);
    		dataSeries.add(14, 1627);
    		dataSeries.add(15, 4480);
    		dataSeries.add(16, 6699);
    		dataSeries.add(17, 7967);
    		dataSeries.add(18, 8104);
    		dataSeries.add(19, 7088);
    		dataSeries.add(20, 5064);
    		dataSeries.add(21, 2318);
    		dataSeries.add(22, -759);
    		dataSeries.add(23, -3729);
    		dataSeries.add(24, -6168);
    		dataSeries.add(25, -7727);
    		myData.addSeries(dataSeries);
    		
//    		XYSeries dataSeries2 = new XYSeries("data2");
//    		dataSeries2.add(0,1);
//    		dataSeries2.add(1,1);
//    		dataSeries2.add(2,2);
//    		dataSeries2.add(3,1);
//    		dataSeries2.add(4,2);
//    		dataSeries2.add(5,4);
//    		myData.addSeries(dataSeries2);
    		return myData;
    }
    
    public XYMultipleSeriesRenderer getMyRenderer() {
    	XYSeriesRenderer r = new XYSeriesRenderer();
    	r.setColor(Color.BLUE);
    	r.setLineWidth(2);


    	r.setPointStyle(PointStyle.SQUARE); 	// CIRCLE, DIAMOND , POINT, TRIANGLE, X
    	r.setFillPoints(true); 					// not for point or x
    											// don't know how to set point size or point color

 //   	r.setFillBelowLine(true);				// shows area of curves
//    	r.setFillBelowLineColor(Color.TRANSPARENT);	//set color other than Default
    	
    	
    	XYMultipleSeriesRenderer myRenderer = new XYMultipleSeriesRenderer();
    	myRenderer.addSeriesRenderer(r);
		myRenderer.setPanEnabled(true, false);
    	
    	String title = "FMCW Radar Data Plot";
    	myRenderer.setChartTitle(title);
    	int textSize = 24;
		myRenderer.setChartTitleTextSize(textSize);
		
		myRenderer.setAxesColor(Color.BLACK);
		myRenderer.setXLabelsColor(Color.BLACK);
		myRenderer.setYLabelsColor(0, Color.BLACK);
		myRenderer.setShowAxes(true);
		myRenderer.setLabelsColor(Color.BLACK);

		// background color of the PLOT ONLY
		myRenderer.setApplyBackgroundColor(true);
		myRenderer.setBackgroundColor(Color.LTGRAY);	//Color.TRANSPARENT would show the background
															//of the app (Bluetooth_GUI)
		
		myRenderer.setMarginsColor(Color.WHITE); 	//sets the background area of the object itself
													//does not change the plots background
					
		myRenderer.setGridColor(Color.BLACK);
		myRenderer.setShowGrid(true);
    	
//    	XYSeriesRenderer r2 = new XYSeriesRenderer();
//    	r2.setColor(Color.RED);
//    	r2.setLineWidth(10);
//    	r2.setPointStyle(PointStyle.DIAMOND);
//    	r2.setFillPoints(true);
//    	r2.setFillBelowLine(false);
//    	myRenderer.addSeriesRenderer(r2);
    	return myRenderer;

    	}
	


  
}


  



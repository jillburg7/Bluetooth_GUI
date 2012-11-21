package appliedradar.bluetooth.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnMenuItemClickListener {

	private GraphicalView mChartView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		if (mChartView == null) {
			RelativeLayout layout = (RelativeLayout) findViewById(R.id.chart);
			mChartView = ChartFactory.getLineChartView(this, getMyData(),
					getMyRenderer());
			layout.addView(mChartView);
		} else {
			mChartView.repaint(); // use this whenever data has changed and you
									// want to redraw
		}
	}

	// @Override
	// protected void onStart() {
	//
	// }

	@Override
	protected void onResume() {
		super.onResume();
		if (mChartView != null) {
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
		Toast.makeText(this, "Selected Collect Data", Toast.LENGTH_SHORT)
				.show();
	}

	public void openArchive(View toast2) {
		Toast.makeText(this, "Selected Load Data", Toast.LENGTH_SHORT).show();
	}

	// Plotting pop-up menu
	public void plotMenu(View view) {
		PopupMenu popup = new PopupMenu(this, view);
	//	popup.setOnMenuItemClickListener(this);
		popup.inflate(R.menu.plotting_menu);
		popup.show();
	}

	// Saving pop-up menu
	public void saveMenu(View display) {
		PopupMenu popup2 = new PopupMenu(this, display);
//		popup2.setOnMenuItemClickListener(this);
		popup2.inflate(R.menu.saving_menu);
		popup2.show();
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.text_file:
	        	if (item.isChecked()) item.setChecked(false);
	            else item.setChecked(true);
	            return true;
	        case R.id.binary_file:
	            if (item.isChecked()) item.setChecked(false);
	            else item.setChecked(true);
	            return true;
	        case R.id.compressed_file:
	            if (item.isChecked()) item.setChecked(false);
	            else item.setChecked(true);
	            return true;
	        case R.id.matlab_file:
	            if (item.isChecked()) item.setChecked(false);
	            else item.setChecked(true);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
/*	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.text.file:
	            if (checked)
	                // Pirates are the best
	            break;
	        case R.id.binary_file:
	            if (checked)
	                // Ninjas rule
	            break;
	    }
	}*/
	
	
/*	 boolean checked = this.isChecked();
	 switch (display.getId()) {
	 case R.id.text_file:
	 if (checked)
	 break;
	 case R.id.binary_file:
	 if (checked)
	 break;
	 case R.id.compressed_file:
	 if (checked)
	 break;
	 case R.id.matlab_file:
	 if (checked)
	 break;
	 }*/

	/*
	 * public void onRadioButtonClicked(View view) { // Is the button now
	 * checked? boolean checked = ((MenuItem) view).isChecked();
	 * 
	 * // Check which radio button was clicked switch(view.getId()) { case
	 * R.id.text_file: if (checked) // do something break; case
	 * R.id.binary_file: if (checked) // do something break; } }
	 */

	// For testing button & popup menu purposes only!
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.text_file:
			// if (item.isChecked()) item.setChecked(false);
			// else item.setChecked(true);
			// return true;
			Toast.makeText(this, "Selected Text File", Toast.LENGTH_SHORT).show();
			break;
		case R.id.binary_file:
			Toast.makeText(this, "Selected Binary File", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.compressed_file:
			Toast.makeText(this, "Selected Compressed File", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.matlab_file:
			Toast.makeText(this, "Selected Matlab File", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.raw_plot:
			Toast.makeText(this, "Selected Raw Plot", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.range_plot:
			Toast.makeText(this, "Selected Range Plot", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.doppler_plot:
			Toast.makeText(this, "Selected Doppler Plot", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.fft_plot:
			Toast.makeText(this, "Selected FFT Plot", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.sar_plot:
			Toast.makeText(this, "Selected SAR Plot", Toast.LENGTH_SHORT)
					.show();
			break;
		}
		return false;
	}

	public XYMultipleSeriesDataset getMyData() {
		XYMultipleSeriesDataset myData = new XYMultipleSeriesDataset();

		XYSeries dataSeries = new XYSeries("FMCW Radar- Simulated Data");

		// Find the directory for the SD Card using the API
		File sdcard = Environment.getExternalStorageDirectory();

		// Get the text file
		File file = new File(sdcard, "simuData.txt");

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			int x = 0;

			while ((line = br.readLine()) != null & (x != 4000)) {
				x = x + 1;
				int y = Integer.parseInt(line);
				dataSeries.add(x, y);
			}
			br.close();
		} catch (IOException e) {
			Log.e("MainActivity", "IOError"); // You'll need to add proper error
												// handling here
		}
		myData.addSeries(dataSeries);

		return myData;
	}

	public XYMultipleSeriesRenderer getMyRenderer() {
		XYSeriesRenderer r = new XYSeriesRenderer();
		r.setColor(Color.BLUE);
		r.setLineWidth(2);

		r.setPointStyle(PointStyle.SQUARE); // CIRCLE, DIAMOND , POINT,
											// TRIANGLE, X
		r.setFillPoints(true); // not for point or x
								// don't know how to set point size or point
								// color

		// r.setFillBelowLine(true); // shows area of curves
		// r.setFillBelowLineColor(Color.TRANSPARENT); //set color other than
		// Default

		XYMultipleSeriesRenderer myRenderer = new XYMultipleSeriesRenderer();
		myRenderer.addSeriesRenderer(r);
		myRenderer.setPanEnabled(true, true);
		myRenderer.setZoomEnabled(true, false);
		myRenderer.setZoomButtonsVisible(true);

		String title = "FMCW Radar Data Plot";
		myRenderer.setChartTitle(title);
		int textSize = 24;
		myRenderer.setChartTitleTextSize(textSize);

		myRenderer.setZoomRate(10);

		myRenderer.setAxesColor(Color.BLACK);
		myRenderer.getXLabelsAlign();
		myRenderer.setXLabelsColor(Color.BLACK);
		myRenderer.setYLabelsColor(0, Color.BLACK);
		myRenderer.setShowAxes(true);
		myRenderer.setLabelsColor(Color.BLACK);

		myRenderer.setXTitle("Samples");
		myRenderer.setYTitle("Frequency");

		// background color of the PLOT ONLY
		myRenderer.setApplyBackgroundColor(true);
		myRenderer.setBackgroundColor(Color.LTGRAY); // Color.TRANSPARENT would
														// show the background
														// of the app
														// (MainActivity)

		myRenderer.setMarginsColor(Color.WHITE); // sets the background area of
													// the object itself
													// does not change the plots
													// background

		myRenderer.setGridColor(Color.DKGRAY);
		myRenderer.setXLabels(20);
		myRenderer.setYLabels(9);
		myRenderer.setShowGrid(true);
		return myRenderer;
	}
}

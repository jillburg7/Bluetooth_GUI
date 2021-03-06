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
import android.content.Intent;
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
import android.widget.ShareActionProvider;
import android.widget.Toast;


public class MainActivity extends Activity implements OnMenuItemClickListener {

	ShareActionProvider mShareActionProvider;
	private GraphicalView mChartView;
	protected XYMultipleSeriesDataset mDataset;
	protected XYMultipleSeriesRenderer mRenderer;
	double[] dataArray;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		if (mChartView == null) {
			RelativeLayout layout = (RelativeLayout) findViewById(R.id.chart);
			mChartView = ChartFactory.getLineChartView(this, getMyData(), getMyRenderer());
			layout.addView(mChartView);
		} else {
			mChartView.repaint(); // use this whenever data has changed and you
									// want to redraw
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mChartView != null) {
			mChartView.repaint();
		//	XYSeries seriesA = new XYSeries("new Series");
		//	mDataset.addSeries(seriesA);
		}
	}

	// Action Bar displays options when Menu item in Action bar is clicked
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);

		// Locate MenuItem with ShareActionProvider
		MenuItem menuItem = menu.findItem(R.id.menu_item_share);

		// Fetch and store ShareActionProvider
		mShareActionProvider = (ShareActionProvider) menuItem
				.getActionProvider();

		// Return true to display menu
		return true;
	}

	public void sendCollectSignal(View toast) {
		Toast.makeText(this, "Selected Collect Data", Toast.LENGTH_SHORT)
				.show();
	}

	public void openArchive(View newActivity) {
		//Log.d("openArchive", "error");
		Toast.makeText(this, "Selected Load Data", Toast.LENGTH_SHORT).show();
		Intent archiveData = new Intent(this, DisplayArchive.class);
		startActivity(archiveData);
	}

	boolean showNewSeries;
	
	public void plotFFT(View fftPlot) {
		Toast.makeText(this, "Selected Plot FFT", Toast.LENGTH_SHORT).show();
//		XYSeries seriesA = new XYSeries("new Series");
		
	//	mDataset.addSeries(seriesA);
//		double[] array2= getFftData();
//		int j=0;
//		for (j=0; j<4096; j++){
//			dataSeries2.add(j, array2[j]);
//		}
//		mDataset.addSeries(seriesA);
	}

	// Plotting pop-up menu
	public void plotMenu(View view) {
		PopupMenu popup = new PopupMenu(this, view);
		popup.setOnMenuItemClickListener(this);
		popup.inflate(R.menu.plotting_menu);
		popup.show();
	}

	// Saving pop-up menu
	public void saveMenu(View display) {
		PopupMenu popup2 = new PopupMenu(this, display);
		// popup2.setOnMenuItemClickListener(this);
		popup2.inflate(R.menu.saving_menu);
		popup2.show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			Intent settingsMenu = new Intent(this, NewActivity.class);
			startActivity(settingsMenu);
			return true;
/*		case R.id.text_file:
			if (item.isChecked()) {
				// item.setChecked(false);
				Toast.makeText(this, "Was true, set False", Toast.LENGTH_SHORT)
						.show();
			} else {
				item.setChecked(true);
				Toast.makeText(this, "Checked", Toast.LENGTH_SHORT).show();
			}
			return true;
		case R.id.binary_file:
			if (item.isChecked()) {
				item.setChecked(false);
				Toast.makeText(this, "Was true, set False", Toast.LENGTH_SHORT)
						.show();
			} else {
				item.setChecked(true);
				Toast.makeText(this, "Checked", Toast.LENGTH_SHORT).show();
			}
			return true;
		case R.id.compressed_file:
			if (item.isChecked()) {
				item.setChecked(false);
				Toast.makeText(this, "Was true, set False", Toast.LENGTH_SHORT)
						.show();
			} else {
				item.setChecked(true);
				Toast.makeText(this, "Checked", Toast.LENGTH_SHORT).show();
			}
			return true;
		case R.id.matlab_file:
			if (item.isChecked()) {
				item.setChecked(false);
				Toast.makeText(this, "Was true, set False", Toast.LENGTH_SHORT)
						.show();
			} else {
				item.setChecked(true);
				Toast.makeText(this, "Checked", Toast.LENGTH_SHORT).show();
			}
			return true;*/
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
	// For testing button & popup menu purposes only!
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
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

	
	// gets Data from a file in External Storage --> SD Card (?)
	// NEED TO UPDATE FOR UNIVERSAL DATA FILES 
	public double[] getDataFromFile() {
		File sdcard = Environment.getExternalStorageDirectory();

		// Get the text file
		// NEED TO SPECIFICALLY CHANGE THIS LINE OF CODE TO BE MORE UNIVERSAL!
		File file = new File(sdcard, "3kHz_44KHzFs.txt");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			int i = 0;
			dataArray = new double[512];	
//			dataArray = new double[5288];	//2048
			
			while ((line = br.readLine()) != null & (i != 512)) {	//5288
				dataArray[i] = Float.parseFloat(line);
				i++;
			}
			br.close();
		} 
		// You'll need to add proper error handling here
		catch (IOException e) {
			Log.e("MainActivity", "IOError");
		}
		System.out.println("returned dataArray from sdcard: " + dataArray);
		return dataArray;	
	}
	
	public XYMultipleSeriesDataset getMyData() {
		XYMultipleSeriesDataset myData = new XYMultipleSeriesDataset();
		
//		if (mChartView != null) 
			XYSeries dataSeries = new XYSeries("Simulated Data");	
			double[] array = new double[512];	//2048 //5288
			array = getDataFromFile();
			int i=0;
			for (i=0; i<512; i++){				//2048 //5288
				dataSeries.add(i, array[i]);
			}
			myData.addSeries(dataSeries);
			
			System.out.println("returned dataArray from getMyData: " + dataArray);
	//		if 	(showNewSeries = true) { }
			
			
			XYSeries dataSeries2 = new XYSeries("FFT data");
			double[] array2= getFftData();
			System.out.print("in getMyData, array2 =" + array2);
			int j=0;
			for (j=0; j<512; j++){
				dataSeries2.add(j, array2[j]);
			}
			myData.addSeries(dataSeries2);	
		
		return myData;
	}
	
	public double[] getFftData() {

//		double[] fftArray = new double[512];
		double[] realArray = dataArray;
		double[] imagArray = new double[512];
	
		FFTcalc fftData = new FFTcalc();
		double[] fftArray = fftData.fft(realArray,imagArray, true);
		int n = realArray.length;
		double[] imagFFT = new double[fftArray.length/2];
		double[] realFFT = new double[fftArray.length/2];
		double radice = 1 / Math.sqrt(n);
//		for(int i=0; i< fftArray.length; i+=2) {
//			int i2 = i/2;
//			realFFT[i] = fftArray[i2] / radice;
//			imagFFT[i] = fftArray[i2 + 1] / radice;
//		}
		
		for(int i=0; i< fftArray.length; i+=2) {
			int i2 = i/2;
			realFFT[i2] = fftArray[i] / radice;
			imagFFT[i2] = fftArray[i + 1] / radice;
		}
		
		double[] fftOutput = new double[n];
		for (int i=0; i<n; i++){
			fftOutput[i] = Math.sqrt(Math.pow(realFFT[i], 2) + Math.pow(imagFFT[i], 2)); 
		}
		
		//fftArray = fftData.realArray(dataArray);
		//calculateFft fftData = new calculateFft(512);
		//fftArray = fftData.realArray(realArray, imagArray);
		//System.out.println("returned data" + fftData);
		
		return fftOutput;
	}
	
	public XYMultipleSeriesRenderer getMyRenderer() {
		XYSeriesRenderer r1 = new XYSeriesRenderer();
		r1.setColor(Color.BLUE);
		r1.setLineWidth(2);
		r1.setPointStyle(PointStyle.SQUARE); // CIRCLE, DIAMOND , POINT, TRIANGLE, X									
		r1.setFillPoints(true); // not for point or x don't know how to set point size or point color
		
		XYSeriesRenderer r2 = new XYSeriesRenderer();
		r2.setColor(Color.RED);
		r2.setLineWidth(2);
		r2.setPointStyle(PointStyle.SQUARE);

		// r.setFillBelowLine(true); // shows area of curves
		// r.setFillBelowLineColor(Color.TRANSPARENT); //set color other than
		// Default

		XYMultipleSeriesRenderer myRenderer = new XYMultipleSeriesRenderer();
		myRenderer.addSeriesRenderer(r1);
		myRenderer.addSeriesRenderer(r2);
		myRenderer.setPanEnabled(true, true);
		myRenderer.setZoomEnabled(true, false);
		myRenderer.setZoomButtonsVisible(true);

		String title = "FMCW Radar Data Plot";
		myRenderer.setChartTitle(title);
	//	int textSize = 24;
		myRenderer.setChartTitleTextSize(30);
		
		myRenderer.setLegendTextSize(20);

		myRenderer.setZoomRate(10);

		myRenderer.setAxesColor(Color.BLACK);
		myRenderer.getXLabelsAlign();
		myRenderer.setXLabelsColor(Color.BLACK);
		myRenderer.setYLabelsColor(0, Color.BLACK);
		myRenderer.setShowAxes(true);
		myRenderer.setLabelsColor(Color.BLACK);

		myRenderer.setXTitle("Samples");
		myRenderer.setYTitle("Amplitude");
		myRenderer.setAxisTitleTextSize(20);

		// background color of the PLOT ONLY
		myRenderer.setApplyBackgroundColor(true);
		// Color.TRANSPARENT would show the background of the app (MainActivity)
		myRenderer.setBackgroundColor(Color.LTGRAY); 

		// sets the background area of the object itself
		// does not change the plots background
		myRenderer.setMarginsColor(Color.WHITE); 

		myRenderer.setGridColor(Color.DKGRAY);
		myRenderer.setXLabels(20);
		myRenderer.setYLabels(9);
		myRenderer.setShowGrid(true);
		return myRenderer;
	}
	
} //END OF MAINACTIVITY CODE!
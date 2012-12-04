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
		getMenuInflater().inflate(R.menu.main_menu, menu);

		// Locate MenuItem with ShareActionProvider
		MenuItem menuItem = menu.findItem(R.id.menu_item_share);

		// Fetch and store ShareActionProvider
		mShareActionProvider = (ShareActionProvider) menuItem
				.getActionProvider();

		// Return true to display menu
		return true;
	}

	/*
	 * public void doShare(Intent shareIntent) { List<Intent>
	 * targetedShareIntents = new ArrayList<Intent>(); shareIntent = new
	 * Intent(android.content.Intent.ACTION_SEND);
	 * shareIntent.setType("text/plain"); List<ResolveInfo> resInfo =
	 * getPackageManager().queryIntentActivities(shareIntent, 0); if
	 * (!resInfo.isEmpty()) { for (ResolveInfo resolveInfo : resInfo) { String
	 * packageName = resolveInfo.activityInfo.packageName; Intent
	 * targetedShareIntent = new Intent(android.content.Intent.ACTION_SEND);
	 * targetedShareIntent.setType("text/plain");
	 * targetedShareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
	 * "subject to be shared"); // if (StringUtils.equals(packageName,
	 * "com.facebook.katana")){ //
	 * targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
	 * "http://link-to-be-shared.com"); // }else{ //
	 * targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
	 * "text message to shared"); // }
	 * 
	 * targetedShareIntent.setPackage(packageName);
	 * targetedShareIntents.add(targetedShareIntent); } Intent chooserIntent =
	 * Intent.createChooser(targetedShareIntents.remove(0),
	 * "Select app to share");
	 * chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
	 * targetedShareIntents.toArray(new Parcelable[]{}));
	 * startActivity(chooserIntent); } }
	 */

	// Intent shareIntent = new Intent(Intent.ACTION_SEND);

	/*
	 * public void doShare(Intent shareIntent) { if (mShareActionProvider !=
	 * null) { mShareActionProvider.setShareIntent(shareIntent); } // Intent
	 * shareIntent = new Intent(); // shareIntent.setAction(Intent.ACTION_SEND);
	 * // shareIntent.setType("image/*"); ////
	 * shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage); //
	 * startActivity(Intent.createChooser(shareIntent,
	 * getResources().getText(R.string.send_to))); }
	 */

	/*
	 * // Call to update the share intent private void setShareIntent(Intent
	 * shareIntent) { if (mShareActionProvider != null) {
	 * mShareActionProvider.setShareIntent(shareIntent); } }
	 */

	public void sendCollectSignal(View toast) {
		Toast.makeText(this, "Selected Collect Data", Toast.LENGTH_SHORT)
				.show();
	}

	public void openArchive(View newActivity) {
		Toast.makeText(this, "Selected Load Data", Toast.LENGTH_SHORT).show();
		Intent archiveData = new Intent(this, DisplayArchive.class);
		startActivity(archiveData);
	}
	
	public void plotFFT(View fftPlot) {
	
		Toast.makeText(this, "Selected Plot FFT", Toast.LENGTH_SHORT).show();
		double[] print = getFftData();
		System.out.println("Clicking plotFFT button, print=" + print);
	//	mChartView.repaint(dataSeries2.getMyData(print));
		
		mChartView.repaint();
		
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
		case R.id.text_file:
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
			return true;
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
		/*
		 * case R.id.text_file: // if (item.isChecked()) item.setChecked(false);
		 * // else item.setChecked(true); // return true; Toast.makeText(this,
		 * "Selected Text File", Toast.LENGTH_SHORT).show(); break; case
		 * R.id.binary_file: Toast.makeText(this, "Selected Binary File",
		 * Toast.LENGTH_SHORT) .show(); break; case R.id.compressed_file:
		 * Toast.makeText(this, "Selected Compressed File", Toast.LENGTH_SHORT)
		 * .show(); break; case R.id.matlab_file: Toast.makeText(this,
		 * "Selected Matlab File", Toast.LENGTH_SHORT) .show(); break;
		 */
		}
		return false;
	}

	
	double[] dataArray;
	
	// gets Data from a file in External Storage --> SD Card (?)
	// NEED TO UPDATE FOR UNIVERSAL DATA FILES 
	public double[] getDataFromFile() {
		File sdcard = Environment.getExternalStorageDirectory();

		// Get the text file
		// NEED TO SPECIFICALLY CHANGE THIS LINE OF CODE TO BE MORE UNIVERSAL!
		File file = new File(sdcard, "simuData.txt");

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			int i = 0;
			dataArray = new double[2048];

			while ((line = br.readLine()) != null & (i != 2048)) {
				dataArray[i] = Float.parseFloat(line);
				i++;
			}
			br.close();
		} 
		// You'll need to add proper error handling here
		catch (IOException e) {
			Log.e("MainActivity", "IOError");
		}
		return dataArray;	
	}
	
	public XYMultipleSeriesDataset getMyData() {
		XYMultipleSeriesDataset myData = new XYMultipleSeriesDataset();

		XYSeries dataSeries = new XYSeries("Simulated Data");
		
		double[] array = new double[2048];
		array = getDataFromFile();
		//array = getFftData();
		int i=0;
		for (i=0; i<2048; i++){
			dataSeries.add(i, array[i]);
		}
		myData.addSeries(dataSeries);
		
		XYSeries dataSeries2 = new XYSeries("FFT data");
		double[] array2;		
		array2 = getFftData();
		System.out.print("in getMyData, array2 =" + array2);
		int j=0;
		for (j=0; j<2048; j++){
			dataSeries2.add(j, array2[j]);
		}
		myData.addSeries(dataSeries2);
		
		return myData;
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
		myRenderer.setYTitle("Amplitude");

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
	
	//public double[] getFftData(double[] dataArray2){
	public double[] getFftData(){
	//public calculateFft getFftData(double[] dataArray2){
		
		double[] fftArray = new double[2048];
		//double[] imagArray = new double[79];
		
		calculateFft fftData = new calculateFft(2048);
		//fftData.fft(fftArray, imagArray);
		fftArray = fftData.realArray(dataArray);
		System.out.println("returned data" + fftData);
		
		return fftArray;
	}

	
} //END OF MAINACTIVITY CODE!
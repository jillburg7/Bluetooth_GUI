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
	//	dataArray.calculateFft(dataArray);
		Toast.makeText(this, "Selected Plot FFT", Toast.LENGTH_SHORT).show();
		//dataArray = getFftData();
	//	dataArray = getDataFromFile();
		//mChartView.repaint();
		
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
		File file = new File(sdcard, "ffttesting.txt");

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			int i = 0;
			dataArray = new double[31];

			while ((line = br.readLine()) != null & (i != 31)) {
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

		XYSeries dataSeries = new XYSeries("FMCW Radar- Simulated Data");
		
		double[] array = new double[31];
		array = getDataFromFile();
		//array = getFftData();
		int x=0;
		for (x=0; x<31; x++){
			dataSeries.add(x, array[x]);
		}
		myData.addSeries(dataSeries);
		
//		XYSeries dataSeries2 = new XYSeries("FFT data");
//		double[] array2 = new double[31];
//		
//		array2 = getFftData();
//		int x=0;
//		for (x=0; x<31; x++){
//			dataSeries.add(x, array2[x]);
//		}
//		myData.addSeries(dataSeries2);
		
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
	
	public double[] getFftData(){
		//double[] fftData = new double[31];
		double[] imagData = new double[31];
		calculateFft fftData = new calculateFft(32);
		fftData.fft(dataArray, imagData);
		//int newDataArray = fftData;
		return dataArray;
	}

	
} //END OF MAINACTIVITY CODE!

	
	
	
	
	
	
	// public double[] fftData (double[] sampleData) {
	// double[] fftData = new double[4000];
	//
	// return fftData;
	// }

	// private double[] FFT(){
	// double[] fftData = new double[4000];
	//
	// int n, m;
	//
	// // Lookup tables. Only need to recompute when size of FFT changes.
	// double[] cos;
	// double[] sin;
	//
	// // public FFTcalc(int n) {
	//
	// // }
	//
	// return fftData;
	// }

	/*
	 * 
	 * public FFT(int n) { this.n = n; this.m = (int)(Math.log(n) /
	 * Math.log(2));
	 * 
	 * // Make sure n is a power of 2 if(n != (1<<m)) throw new
	 * RuntimeException("FFT length must be power of 2");
	 * 
	 * // precompute tables cos = new double[n]; sin = new double[n];
	 * 
	 * //Prints the value of 'n' to screen --> used to established // where the
	 * value of 'n' came from. // Value of 'n' is == to 'N' {declared below}
	 * System.out.println(n);
	 * 
	 * for(int i=0; i<n; i++) { cos[i] = Math.cos(-2*Math.PI*i); sin[i] =
	 * Math.sin(-2*Math.PI*i);
	 * 
	 * } }
	 * 
	 * public void fft(double[] x, double[] y) { int i,j,k,n1,n2,a; double
	 * c,s,t1,t2;
	 * 
	 * 
	 * // Bit-reverse j = 0; n2 = n/2; for (i=1; i < n - 1; i++) { n1 = n2;
	 * while ( j >= n1 ) { j = j - n1; n1 = n1/2; } j = j + n1;
	 * 
	 * if (i < j) { t1 = x[i]; x[i] = x[j]; x[j] = t1; t1 = y[i]; y[i] = y[j];
	 * y[j] = t1; } }
	 * 
	 * // FFT n1 = 0; n2 = 1;
	 * 
	 * for (i=0; i < m; i++) { n1 = n2; n2 = n2 + n2; a = 0;
	 * 
	 * for (j=0; j < n1; j++) { c = cos[a]; s = sin[a]; a += 1 << (m-i-1);
	 * 
	 * for (k=j; k < n; k=k+n2) { t1 = c*x[k+n1] - s*y[k+n1]; t2 = s*x[k+n1] +
	 * c*y[k+n1]; x[k+n1] = x[k] - t1; y[k+n1] = y[k] - t2; x[k] = x[k] + t1;
	 * y[k] = y[k] + t2; } } } }
	 * 
	 * // Test the FFT to make sure it's working public static void
	 * main(String[] args) { int N = 32;
	 * 
	 * FFT fft = new FFT(N);
	 * 
	 * double[] re = new double[N]; double[] im = new double[N];
	 * 
	 * System.out.println((0.5)*N); // Single sin for(int i=0; i<N; i++) { re[i]
	 * = 2*Math.cos(3*2*Math.PI*i/N); im[i] = 0; } beforeAfter(fft, re, im); }
	 * 
	 * protected static void beforeAfter(FFT fft, double[] re, double[] im) {
	 * System.out.println("Before: "); printReIm(re, im); fft.fft(re, im);
	 * System.out.println("After: "); printReIm(re, im); }
	 * 
	 * protected static void printReIm(double[] re, double[] im) {
	 * System.out.print("Re: ["); for(int i=0; i<re.length; i++)
	 * System.out.print(((int)(re[i]*1000)/1000.0) + " ");
	 * 
	 * System.out.print("]\nIm: ["); for(int i=0; i<im.length; i++)
	 * System.out.print(((int)(im[i]*1000)/1000.0) + " ");
	 * 
	 * System.out.println("]"); }
	 */
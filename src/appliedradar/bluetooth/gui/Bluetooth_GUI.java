package appliedradar.bluetooth.gui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
//import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
//import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
//import android.widget.Spinner;
import android.widget.Toast;

public class Bluetooth_GUI extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    // Action Bar displays options when Menu item in Action bar is clicked
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
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
  		popup2.inflate(R.menu.saving_menu);
  		popup2.show();
  	}

  	// For testing button purposes only!
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        }
        return super.onOptionsItemSelected(item);
    }}
                
//  	public void onRadioButtonClicked(View v) {
//  		boolean checked = ((RadioButton) v).isChecked();
//  		
//  	}

////Spinner- save data file formats
//	Spinner spinner = (Spinner) findViewById(R.id.save_spinner);
//	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, 
//			R.array.save_file_format, android.R.layout.simple_spinner_item);
//	adapter.setDropDownViewResource(this, android.R.layout.simple_spinner_dropdown_item);
//	spinner.setAdapter(adapter);


//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v,
//                                    ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.plotting_menu, menu);
//    }
 
    
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//    	switch (item.getItemId()) {
//    		case R.id.plotting_menu:
//    		case R.id.range_plot:
//    			if (item.isChecked()) item.setChecked(false);
//    			else item.setChecked(true);
//    			return true;
//    		default:
//    			return super.onOptionsItemSelected(item);
//    	}
//    }
    

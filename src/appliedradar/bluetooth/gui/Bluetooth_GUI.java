package appliedradar.bluetooth.gui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.Spinner;

public class Bluetooth_GUI extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth__gui);
    }

//Action Bar displays options when Menu item in Action bar is clicked
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }
    
//Plotting pop-up menu
  	public void plotMenu(View view) {
  		PopupMenu popup = new PopupMenu(this, view);
  		MenuInflater inflater = getMenuInflater();
  		inflater.inflate(R.menu.plotting_menu, popup.getMenu());
  		popup.show();
  	}
   
//  	public void onRadioButtonClicked(View v) {
//  		boolean checked = ((RadioButton) v).isChecked();
//  		
//  	}

////Spinner- save data file formats
//	Spinner spinner = (Spinner) findViewById(R.id.save_spinner);
//	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, 
//			R.array.save_file_format, android.R.layout.simple_spinner_item);
//	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//	spinner.setAdapter(adapter);
	
}

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
    

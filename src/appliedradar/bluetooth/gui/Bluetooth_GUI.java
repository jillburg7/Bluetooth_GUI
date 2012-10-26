package appliedradar.bluetooth.gui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Bluetooth_GUI extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth__gui);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_bluetooth__gui, menu);
        return true;
    }
}

package pl.tajchert.wearnotifications;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Button buttonStartNotifBasic;
        Button buttonStartNotifPages;
        Button buttonStartNotifAction;
        Button buttonStartNotifBackground;

        buttonStartNotifBasic = (Button) findViewById(R.id.buttonNotifyKittenzBasic);
        buttonStartNotifBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tutaj będziemy wysłać podstawowe powiadomienie
            }
        });
        buttonStartNotifBackground = (Button) findViewById(R.id.buttonNotifyKittenzBackground);
        buttonStartNotifBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tutaj będziemy wysłać powiadomienie z tłem
            }
        });
        buttonStartNotifAction = (Button) findViewById(R.id.buttonNotifyKittenzAction);
        buttonStartNotifAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tutaj będziemy wysłać powiadomienie z akcją
            }
        });
        buttonStartNotifPages = (Button) findViewById(R.id.buttonNotifyKittenzPages);
        buttonStartNotifPages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tutaj będziemy wysłać powiadomienie z stronami
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

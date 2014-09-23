package pl.tajchert.wearnotifications;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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
                showNotificationSimple();
            }
        });
        buttonStartNotifBackground = (Button) findViewById(R.id.buttonNotifyKittenzBackground);
        buttonStartNotifBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tutaj będziemy wysłać powiadomienie z tłem
                showNotificationWithBackground();
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

    private void showNotificationSimple(){
        Intent viewIntent = new Intent(MyActivity.this, MyActivity.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(MyActivity.this, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(MyActivity.this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Kittenz!")
                        .setContentText("Cute kittenz are cute!")
                        .setContentIntent(viewPendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MyActivity.this);
        notificationManager.notify(999, notificationBuilder.build());
    }

    private void showNotificationWithBackground(){
        Intent viewIntent = new Intent(MyActivity.this, MyActivity.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(MyActivity.this, 0, viewIntent, 0);

        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.bigText("Cute kittenz are cute! Cute kittenz are cute! Cute kittenz are cute! Cute kittenz are cute! Cute kittenz are cute!");

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(MyActivity.this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Kittenz!")
                        .setContentIntent(viewPendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.background))
                        .setStyle(bigStyle);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MyActivity.this);
        notificationManager.notify(999, notificationBuilder.build());
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

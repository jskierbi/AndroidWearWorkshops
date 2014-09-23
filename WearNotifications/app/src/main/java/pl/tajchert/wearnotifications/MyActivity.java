package pl.tajchert.wearnotifications;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MyActivity extends Activity {
    private final static String EXTRA_VOICE_REPLY = "extra_voice_reply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        CharSequence replay = getMessageText(getIntent()).toString();
        if(replay!= null && replay.toString().length() > 0){
            Toast.makeText(getApplicationContext(), replay.toString(), Toast.LENGTH_LONG).show();
        }

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
                //showNotificationAction();
                showNotificationVoiceInput();
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

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.background));

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(MyActivity.this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Kittenz!")
                        .setContentIntent(viewPendingIntent)
                        .extend(wearableExtender)
                        .setStyle(bigStyle);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MyActivity.this);
        notificationManager.notify(998, notificationBuilder.build());
    }

    private void showNotificationAction(){
        Intent actionIntent = new Intent(MyActivity.this, MyActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(MyActivity.this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_launcher, "Show our app", actionPendingIntent)
                        .build();
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(MyActivity.this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Meow!")
                        .addAction(action);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MyActivity.this);
        notificationManager.notify(997, notificationBuilder.build());
    }

    private void showNotificationVoiceInput(){
        Intent replyIntent = new Intent(MyActivity.this, MyActivity.class);
        PendingIntent replyPendingIntent =
                PendingIntent.getActivity(this, 0, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY)
                .setLabel("Meow once")
                .build();
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_launcher,
                        "Speak to our app", replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(MyActivity.this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Meow request!")
                        .addAction(action);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MyActivity.this);
        notificationManager.notify(996, notificationBuilder.build());
    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(EXTRA_VOICE_REPLY);
        }
        return "";
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

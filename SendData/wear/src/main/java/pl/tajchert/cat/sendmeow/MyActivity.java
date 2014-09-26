package pl.tajchert.cat.sendmeow;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

import pl.tajchert.cat.meow.Tools;

public class MyActivity extends Activity {
    private static final String TAG = "MyActivity";

    private BroadcastReceiver dataChangedReceiver;
    private IntentFilter dataChangedIntentFilter;
    private TextView mTextView;
    private String meowText = "Silence...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);

        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                mTextView.setText(meowText);
            }
        });
        Log.d(TAG, "Wear, meow constant is:" + Tools.MEOW);

        dataChangedIntentFilter = new IntentFilter(Tools.DATA_CHANGED_ACTION);
        dataChangedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (Tools.DATA_CHANGED_ACTION.equals(intent.getAction())) {
                    meowText = MyActivity.this.getSharedPreferences(Tools.PREFS, MODE_PRIVATE).getString(Tools.PREFS_KEY_MEOW_TEXT, "got null");
                    Log.d(TAG, "Received meow: " + meowText);
                    if(mTextView != null && meowText != null) {
                        mTextView.setText(meowText);
                    }
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.registerReceiver(dataChangedReceiver, dataChangedIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(dataChangedReceiver);
    }
}

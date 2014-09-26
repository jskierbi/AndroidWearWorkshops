package pl.tajchert.cat.sendmeow;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.Calendar;

import pl.tajchert.cat.meow.Tools;

public class UpdateService extends Service {
    private static final String TAG = UpdateService.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new SendWeatherTextTask().execute(Tools.MEOW);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    private class SendWeatherTextTask extends AsyncTask<String, Void, String> {
        private GoogleApiClient mGoogleAppiClient;
        @Override
        protected String doInBackground(String... params) {
            if(params != null && params.length > 0 ){
                String currentMeow = params[0];
                return currentMeow;
            }
            return "No meow :(";
        }
        @Override
        protected void onPostExecute(String result) {
            sendData(Tools.WEAR_KEY_MEOW_TEXT, result);

        }
        @Override
        protected void onPreExecute() {
            mGoogleAppiClient = new GoogleApiClient.Builder(UpdateService.this)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(Bundle connectionHint) {
                        }
                        @Override
                        public void onConnectionSuspended(int cause) {
                        }
                    }).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(ConnectionResult result) {
                        }
                    })
                    .addApi(Wearable.API)
                    .build();
            mGoogleAppiClient.connect();
        }

        @Override
        protected void onProgressUpdate(Void... values) {}

        private void sendData(String key, String value) {
            value = value +  Calendar.getInstance().getTimeInMillis();//Date to enforce update on Wear
            PutDataMapRequest dataMap = PutDataMapRequest.create(Tools.WEAR_PATH);
            dataMap.getDataMap().putString(key, value);
            PutDataRequest request = dataMap.asPutDataRequest();
            PendingResult<DataApi.DataItemResult> pendingResult = Wearable.DataApi.putDataItem(mGoogleAppiClient, request);
            pendingResult.setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
                @Override
                public void onResult(DataApi.DataItemResult dataItemResult) {
                    Log.d(TAG, "Sent: " + dataItemResult.toString());
                    mGoogleAppiClient.disconnect();
                    stopSelf();
                }
            });

        }
    }


}

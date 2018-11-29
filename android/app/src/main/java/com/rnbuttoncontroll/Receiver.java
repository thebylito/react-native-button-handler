package com.rnbuttoncontroll;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * Created by thebylito on 28/11/2018.
 */

public class Receiver extends BroadcastReceiver {
    public final static String SCREEN_TOGGLE_TAG = "SCREEN_TOGGLE_TAG";


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            Log.d(SCREEN_TOGGLE_TAG, "Screen is turn off.");
            BlockButtonModule.sendEvent("buttonDesliga", null, context);
        } else if (Intent.ACTION_SCREEN_ON.equals(action)) {
            Log.d(SCREEN_TOGGLE_TAG, "Screen is turn on.");
            BlockButtonModule.sendEvent("buttonLiga", null, context);
        } else if ("android.media.VOLUME_CHANGED_ACTION".equals(action)) {
            Log.d(SCREEN_TOGGLE_TAG, "Volume change");
            BlockButtonModule.sendEvent("volumeChange", null, context);
        }

    }

}
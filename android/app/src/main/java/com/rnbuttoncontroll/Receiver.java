package com.rnbuttoncontroll;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.List;

/**
 * Created by thebylito on 28/11/2018.
 */

public class Receiver extends BroadcastReceiver {
    public final static String SCREEN_TOGGLE_TAG = "SCREEN_TOGGLE_TAG";


    @Override
    public void onReceive(Context context, Intent intent) {
        /*
        * */
Log.d(SCREEN_TOGGLE_TAG, "onReceive");
        //if (!isAppOnForeground((context))) {
            Intent serviceIntent = new Intent(context, TestService.class);
            serviceIntent.putExtra("hasInternet", "testando");
            context.startForegroundService(serviceIntent);
            HeadlessJsTaskService.acquireWakeLockNow(context);


    /*    String action = intent.getAction();
        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            Log.d(SCREEN_TOGGLE_TAG, "Screen is turn off.");
            BlockButtonModule.sendEvent("buttonDesliga", null, context);
        } else if (Intent.ACTION_SCREEN_ON.equals(action)) {
            Log.d(SCREEN_TOGGLE_TAG, "Screen is turn on.");
            BlockButtonModule.sendEvent("buttonLiga", null, context);
        } else if ("android.media.VOLUME_CHANGED_ACTION".equals(action)) {
            Log.d(SCREEN_TOGGLE_TAG, "Volume change");
            BlockButtonModule.sendEvent("volumeChange", null, context);
        }*/

       // }
    }

    private boolean isAppOnForeground(Context context) {
        /**
         We need to check if app is in foreground otherwise the app will crash.
         http://stackoverflow.com/questions/8489993/check-android-application-is-in-foreground-or-not
         **/
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses =
                activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance ==
                    ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                    appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

}
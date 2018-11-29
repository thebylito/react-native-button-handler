package com.rnbuttoncontroll;

/**
 * Created by thebylito on 28/11/2018.
 */
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class ReceiverBackgroundService extends Service {

    private Receiver ButtonReceiver = null;
    private ReactContext reactContext = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final ReactInstanceManager reactInstanceManager =
                getReactNativeHost().getReactInstanceManager();
        reactContext = reactInstanceManager.getCurrentReactContext();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
        intentFilter.setPriority(100);
        ButtonReceiver = new Receiver();
        registerReceiver(ButtonReceiver, intentFilter);
        Log.d(ButtonReceiver.SCREEN_TOGGLE_TAG, "Service onCreate: buttonReceiver is registered.");
    }

    public void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }


    protected ReactNativeHost getReactNativeHost() {
        return ((ReactApplication) getApplication()).getReactNativeHost();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(ButtonReceiver!=null)
        {
            unregisterReceiver(ButtonReceiver);
            Log.d(ButtonReceiver.SCREEN_TOGGLE_TAG, "Service onDestroy: screenOnOffReceiver is unregistered.");
        }
    }
}
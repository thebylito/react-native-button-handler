package com.rnbuttoncontroll;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by thebylito on 28/11/2018.
 */

public class BlockButtonModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static ReactApplicationContext reactContext = null;

    public BlockButtonModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        reactContext.addActivityEventListener(this);

    }

    @Override
    public String getName() {
        return "BlockButton";
    }

    @ReactMethod
    public void start(Promise promise) {
        Intent service = new Intent(reactContext.getApplicationContext(), ReceiverBackgroundService.class);
        reactContext.startService(service);
        promise.resolve(null);
    }

    public static void sendEvent(String eventName, @Nullable WritableMap params, @Nullable Context context) {
        if (reactContext != null) {
            reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
        }else if(context != null){
            if(eventName.equals("volumeChange")){
            Toast.makeText(context, "Negado! Aumentando volume", Toast.LENGTH_SHORT).show();
                AudioManager am =  (AudioManager) context.getSystemService(AUDIO_SERVICE);
                am.setStreamVolume(AudioManager.STREAM_ALARM,10,0);
                am.setStreamVolume(AudioManager.STREAM_RING,10,0);
                am.setStreamVolume(AudioManager.STREAM_SYSTEM,10,0);
                am.setStreamVolume(AudioManager.STREAM_MUSIC,10,0);

                return;
            }
            Toast.makeText(context, "Fechado, mas escutando: Evento: "+ eventName, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }


}

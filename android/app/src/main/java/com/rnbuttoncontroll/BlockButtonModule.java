package com.rnbuttoncontroll;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Dimension;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
        Bundle bundle = new Bundle();
        Intent service2 = new Intent(reactContext.getApplicationContext(), TestService.class);
        service2.putExtras(bundle);
        bundle.putString("foo", "bar");
        reactContext.startService(service);
        reactContext.startService(service2);
        promise.resolve(null);
    }

    @ReactMethod
    public void start2(Promise promise) {
        final DisplayMetrics metrics = reactContext.getResources().getDisplayMetrics();

        View view = new View(reactContext);


view.setClickable(true);
        //View view = new View(reactContext);

        TextView textview = new TextView(reactContext);
        textview.setText("Testando");
        textview.setBackgroundColor(Color.CYAN);
        textview.setTextColor(Color.BLUE);
        textview.setHeight(200);
        textview.isClickable();
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewIn) {
                Log.e("TAG","Ooops GMAIL account selection problem");
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewIn) {
                    Log.e("TAG","Ooops GMAIL account selection problem");
            }
        });
        textview.setGravity(Gravity.CENTER);
        textview.setWidth(metrics.widthPixels - 20);
        textview.setPadding(10,10,10,10);

        view.addView(textview);
        Toast toast = new Toast(reactContext);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 30);
        toast.show();

    }
/*
    Bundle bundle = new Bundle();


    */

    public static void sendEvent(String eventName, @Nullable WritableMap params, @Nullable Context context) {
        if (reactContext != null) {
            reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
        } else if (context != null) {
            if (eventName.equals("volumeChange")) {
                Toast.makeText(context, "Negado! Aumentando volume", Toast.LENGTH_SHORT).show();
                AudioManager am = (AudioManager) context.getSystemService(AUDIO_SERVICE);
                am.setStreamVolume(AudioManager.STREAM_ALARM, 10, 0);
                am.setStreamVolume(AudioManager.STREAM_RING, 10, 0);
                am.setStreamVolume(AudioManager.STREAM_SYSTEM, 10, 0);
                am.setStreamVolume(AudioManager.STREAM_MUSIC, 10, 0);

                return;
            }
            Toast.makeText(context, "Fechado, mas escutando: Evento: " + eventName, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }


    public LayoutInflater getLayoutInflater() {
        Activity activity = getCurrentActivity();
        assert activity != null;
        return activity.getLayoutInflater();
    }
}

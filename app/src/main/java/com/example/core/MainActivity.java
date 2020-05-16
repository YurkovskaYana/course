package com.example.core;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public static void p(){}
    public static void g(){}
    public static void gr(){}

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        return super.dispatchGenericMotionEvent(ev);
    }

    @Override
    public boolean bindIsolatedService(Intent service, int flags, String instanceName, Executor executor, ServiceConnection conn) {
        return super.bindIsolatedService(service, flags, instanceName, executor, conn);
    }

    public static void gtr(){}

    @Override
    public void triggerSearch(String query, @Nullable Bundle appSearchData) {
        super.triggerSearch(query, appSearchData);
    }
}

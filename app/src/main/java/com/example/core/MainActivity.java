package com.example.core;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public static void p(){}
    public static void g(){}
    public static void gr(){}
    public static void gtr(){}

    @Override
    public void triggerSearch(String query, @Nullable Bundle appSearchData) {
        super.triggerSearch(query, appSearchData);
    }
}

package edu.utah.cs4710.rusty.base_ic;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



/**
 * Created by Rusty on 8/5/2017.
 */

public class ToggleActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

//    String PI_URL = "http://raspberrypi.local/api/v1/peripherals/";
    String PI_URL = "http://10.0.0.115/api/v1/peripherals/";
    Switch deadbolt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);

        deadbolt = new Switch(this);

        LinearLayout.LayoutParams deadboltLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 1);
        deadboltLayoutParams.gravity = Gravity.CENTER;
        rootLayout.addView(deadbolt, deadboltLayoutParams);

        setContentView(rootLayout);

        deadbolt.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean open) {
        if (open) {

        }
        else {
            //do something else obvi
        }
    }
}

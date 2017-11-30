package edu.utah.cs4710.rusty.base_ic;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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

public class ToggleActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, ListAdapter, View.OnClickListener{

//    String PI_URL_PERIPHERAL_LIST = "http://raspberrypi.local/api/v1/peripherals/";
    String PI_URL = "http://10.0.0.115/api/v1/peripherals/";
    Switch deadbolt;
    ListView _listView = null;
    Peripheral _peripheral = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);

        deadbolt = new Switch(this);

        Intent getPeripheralFromIntent = getIntent();
        Serializable serializableExtra = getPeripheralFromIntent.getSerializableExtra("peripheral");
        _peripheral = (Peripheral) serializableExtra;

        _listView = new ListView(this);
        _listView.setAdapter(this);
        _listView.setBackgroundColor(Color.RED);

//        LinearLayout.LayoutParams deadboltLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 1);
//        deadboltLayoutParams.gravity = Gravity.CENTER;
//        rootLayout.addView(deadbolt, deadboltLayoutParams);
//
        rootLayout.addView(_listView);

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

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return _peripheral.getInput_services().size();
    }

    @Override
    public Object getItem(int i) {
        return _peripheral.getInput_services().get(i).getService_name();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Button toggleButton = new Button(this);
        String buttonText = _peripheral.getInput_services().get(i).getService_name();
        toggleButton.setText(buttonText);
        toggleButton.setBackgroundColor(Color.DKGRAY);
        toggleButton.setTextColor(Color.YELLOW);
        toggleButton.setOnClickListener(this);
        return toggleButton;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        if (_peripheral.getInput_services().size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {

    }
}

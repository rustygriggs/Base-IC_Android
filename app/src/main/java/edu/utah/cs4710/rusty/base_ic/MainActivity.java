package edu.utah.cs4710.rusty.base_ic;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity implements PerLobbyService.PeripheralListReceivedListener, ListAdapter, View.OnClickListener {
    boolean hexButtonClicked = false;
    boolean toggleButtonClicked = false;
    boolean rangeButtonClicked = false;
    String PI_URL = "http://10.0.0.115/api/v1/peripherals/";


    PerLobbyService _perLobbyService = null;
    ListView _listView = null;
    List<Peripheral> _allPeripherals = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _perLobbyService = PerLobbyService.getInstance();
        _perLobbyService.setPeripheralListReceivedListener(this);

        _perLobbyService.getPeripheralsList();



        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(rootLayout);

        Button hexButton = new Button(this);
        hexButton.setText("hex");
        hexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showHexButton = new Intent();
                showHexButton.setClass(view.getContext(), HexActivity.class);
                view.getContext().startActivity(showHexButton);
            }
        });
        Button toggleButton = new Button(this);
        toggleButton.setText("toggle");
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showToggleButton = new Intent();
                showToggleButton.setClass(view.getContext(), ToggleActivity.class);
                view.getContext().startActivity(showToggleButton);
            }
        });

        Button rangeButton = new Button(this);
        rangeButton.setText("range");
        rangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showRangeButton = new Intent();
                showRangeButton.setClass(view.getContext(), RangeActivity.class);
                view.getContext().startActivity(showRangeButton);
                _listView.invalidateViews();
            }
        });

        _listView = new ListView(this);
        _listView.setAdapter(this);
        _listView.setBackgroundColor(Color.RED);
        //_listView.invalidateViews();

        //rootLayout.addView(hexButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        rootLayout.addView(toggleButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        rootLayout.addView(rangeButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        rootLayout.addView(_listView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 5));



    }

    @Override
    public void onPeripheralListReceived(boolean success, List<Peripheral> peripherals) {
        if (success) {
            _allPeripherals = peripherals;
            _listView.invalidateViews();
        }
        else {
            System.out.println("peripherals list was not received correctly");
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
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {    }

    @Override
    public int getCount() {
        return _allPeripherals.size();
    }

    @Override
    public Object getItem(int i) {
        return _allPeripherals.get(i);
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

        TextView textView = new TextView(this);
        textView.setBackgroundColor(Color.DKGRAY);
        textView.setTextColor(Color.YELLOW);
        textView.setId(_allPeripherals.get(i).id);
        textView.setOnClickListener(this);
        _perLobbyService = PerLobbyService.getInstance();

        textView.setText(_allPeripherals.get(i).name + "\n" + _allPeripherals.get(i).id);

        return textView;    }

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
        if (_allPeripherals.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        Peripheral per = _allPeripherals.get(view.getId() - 1);
        List<IOService> iosList = per.getInput_services();

        if (iosList.size() > 1) { //multiple toggles
            Intent showToggleButton = new Intent();
            showToggleButton.putExtra("peripheral", per);
            showToggleButton.setClass(view.getContext(), ToggleActivity.class);
            view.getContext().startActivity(showToggleButton);
        }
        else { //not necessarily toggle, check for other input types (Toggle, Hex, Range)
            // TODO: check type
            String serviceName = iosList.get(0).getService().getName();
            if (serviceName.equals("Toggle")) {
                Intent showToggleButton = new Intent();
                showToggleButton.putExtra("peripheral", per);
                showToggleButton.setClass(view.getContext(), ToggleActivity.class);
                view.getContext().startActivity(showToggleButton);
            }
            else if (serviceName.equals("Range")) {
                // TODO: go to range activity
            }
            else if (serviceName.equals("Hex")) {
                // TODO: go to hex activity
            }

        }
        //String type = _allPeripherals.get(perId).
    }


//    @Override
//    public void onClick(View view) {
////        if (hexButtonClicked) {
////            Intent showHexButton = new Intent();
////            showHexButton.setClass(this, HexActivity.class);
////            startActivity(showHexButton);
////        }
//        //startActivityForResult(showHexButton, PICK_PAINT_REQUEST);
//    }
}

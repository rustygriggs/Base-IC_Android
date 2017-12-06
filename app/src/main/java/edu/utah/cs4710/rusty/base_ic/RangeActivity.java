package edu.utah.cs4710.rusty.base_ic;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.marcinmoskala.arcseekbar.ProgressListener;

import java.io.Serializable;

/**
 * Created by Rusty on 9/6/2017.
 */

public class RangeActivity  extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, ProgressListener, View.OnClickListener{
    TextView progressText;
    private SendHTTPService _sendHTTPService;
    Peripheral _peripheral = null;
    TextView textRange = null;
    int _progress = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _sendHTTPService = SendHTTPService.getInstance();
        Intent getPeripheralFromIntent = getIntent();
        Serializable serializableExtra = getPeripheralFromIntent.getSerializableExtra("peripheral");
        _peripheral = (Peripheral) serializableExtra;

        ArcSeekBar arcSeekBar = new ArcSeekBar(this);
        arcSeekBar.setFadingEdgeLength(100);
        arcSeekBar.setProgressBackgroundWidth(70);
        arcSeekBar.setProgressWidth(70);
        arcSeekBar.setProgressGradient(Color.GRAY, Color.argb(255, 0, 82, 133));
        arcSeekBar.setBackgroundColor(Color.argb(255, 140, 63, 63));
        arcSeekBar.setOnStopTrackingTouch(this);

        arcSeekBar.setOnProgressChangedListener(this);

        textRange = new TextView(this);
        textRange.setText("Set Range");
        textRange.setTextColor(Color.LTGRAY);
        textRange.setBackgroundColor(Color.argb(255, 0, 82, 133));
        textRange.setGravity(Gravity.CENTER);
        textRange.setTextSize(50);

        Button sendRangeButton = new Button(this);
        sendRangeButton.setText("Press here to Send Range");
        sendRangeButton.setTextSize(30);
        sendRangeButton.setTextColor(Color.LTGRAY);
        sendRangeButton.setBackgroundColor(Color.argb(255, 0, 82, 133));
        sendRangeButton.setOnClickListener(this);


        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.addView(textRange, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 2));
        rootLayout.addView(arcSeekBar, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        rootLayout.addView(sendRangeButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 4));

//        seekBar.setOnSeekBarChangeListener(this);
        arcSeekBar.setOnProgressChangedListener(this);


//        ArcSeekBar arcSeekBar = new ArcSeekBar(this);
//        arcSeekBar.

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progressText.setText(String.valueOf(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        String address = _peripheral.address;
        Integer serviceId = _peripheral.getInput_services().get(0).getService().getId();
        Integer serviceNumber = _peripheral.getInput_services().get(0).getService_number();
        String value = String.valueOf(seekBar.getProgress());
        _sendHTTPService.sendHTTPPostRequest(address, serviceId.toString(), serviceNumber.toString(), value);
    }

    @Override
    public void invoke(int progress) {
        progressText.setText(String.valueOf(progress));
        _progress = progress;
    }

    @Override
    public void onClick(View view) {
        String address = _peripheral.address;
        Integer serviceId = _peripheral.getInput_services().get(0).getService().getId();
        Integer serviceNumber = _peripheral.getInput_services().get(0).getService_number();
        String value = String.valueOf(_progress);
        _sendHTTPService.sendHTTPPostRequest(address, serviceId.toString(), serviceNumber.toString(), value);
    }
}

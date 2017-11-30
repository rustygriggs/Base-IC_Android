package edu.utah.cs4710.rusty.base_ic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.marcinmoskala.arcseekbar.ArcSeekBar;

import java.io.Serializable;

/**
 * Created by Rusty on 9/6/2017.
 */

public class RangeActivity  extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    TextView progressText;
    private SendHTTPService _sendHTTPService;
    Peripheral _peripheral = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _sendHTTPService = SendHTTPService.getInstance();
        Intent getPeripheralFromIntent = getIntent();
        Serializable serializableExtra = getPeripheralFromIntent.getSerializableExtra("peripheral");
        _peripheral = (Peripheral) serializableExtra;

        LinearLayout rootLayout = new LinearLayout(this);
        progressText = new TextView(this);
        progressText.setText("test");
        rootLayout.addView(progressText, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));


        SeekBar seekBar = new SeekBar(this);
        //seekBar.setPointerIcon();
        rootLayout.addView(seekBar, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        setContentView(rootLayout);

        seekBar.setOnSeekBarChangeListener(this);


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
}

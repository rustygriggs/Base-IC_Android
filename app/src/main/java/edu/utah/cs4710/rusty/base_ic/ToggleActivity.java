package edu.utah.cs4710.rusty.base_ic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;

/**
 * Created by Rusty on 8/5/2017.
 */

public class ToggleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);

        Switch toggleSwitch = new Switch(this);

        rootLayout.addView(toggleSwitch, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        setContentView(rootLayout);

    }
}

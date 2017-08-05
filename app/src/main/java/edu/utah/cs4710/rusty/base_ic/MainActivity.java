package edu.utah.cs4710.rusty.base_ic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(rootLayout);

        Button hexButton = new Button(this);
        hexButton.setText("hex");

        Button toggleButton = new Button(this);
        toggleButton.setText("toggle");

        Button rangeButton = new Button(this);
        rangeButton.setText("range");

        rootLayout.addView(hexButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 1));
        rootLayout.addView(toggleButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 1));
        rootLayout.addView(rangeButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 1));


    }
}

package edu.utah.cs4710.rusty.base_ic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    boolean hexButtonClicked = false;
    boolean toggleButtonClicked = false;
    boolean rangeButtonClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            }
        });

        rootLayout.addView(hexButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        rootLayout.addView(toggleButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        rootLayout.addView(rangeButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));

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

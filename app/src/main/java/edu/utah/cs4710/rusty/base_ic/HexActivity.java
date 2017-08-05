package edu.utah.cs4710.rusty.base_ic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Rusty on 8/5/2017.
 */

public class HexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout leftSideLayout = new LinearLayout(this);
        leftSideLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout rightSideLayout = new LinearLayout(this);
        rightSideLayout.setOrientation(LinearLayout.VERTICAL);

        rootLayout.addView(leftSideLayout, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        rootLayout.addView(rightSideLayout, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        Button button0 = new Button(this);
        Button button1 = new Button(this);
        Button button2 = new Button(this);
        Button button3 = new Button(this);
        Button button4 = new Button(this);
        Button button5 = new Button(this);
        Button button6 = new Button(this);
        Button button7 = new Button(this);
        Button button8 = new Button(this);
        Button button9 = new Button(this);
        Button buttonA = new Button(this);
        Button buttonB = new Button(this);
        Button buttonC = new Button(this);
        Button buttonD = new Button(this);
        Button buttonE = new Button(this);
        Button buttonF = new Button(this);

        button0.setText("0");
        button1.setText("1");
        button2.setText("2");
        button3.setText("3");
        button4.setText("4");
        button5.setText("5");
        button6.setText("6");
        button7.setText("7");
        button8.setText("8");
        button9.setText("9");
        buttonA.setText("A");
        buttonB.setText("b");
        buttonC.setText("c");
        buttonD.setText("d");
        buttonE.setText("e");
        buttonF.setText("f");

        leftSideLayout.addView(button0, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        leftSideLayout.addView(button1, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        leftSideLayout.addView(button2, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        leftSideLayout.addView(button3, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        leftSideLayout.addView(button4, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        leftSideLayout.addView(button5, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        leftSideLayout.addView(button6, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        leftSideLayout.addView(button7, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));

        rightSideLayout.addView(button8, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        rightSideLayout.addView(button9, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        rightSideLayout.addView(buttonA, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        rightSideLayout.addView(buttonB, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        rightSideLayout.addView(buttonC, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        rightSideLayout.addView(buttonD, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        rightSideLayout.addView(buttonE, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        rightSideLayout.addView(buttonF, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));


        setContentView(rootLayout);
    }
}

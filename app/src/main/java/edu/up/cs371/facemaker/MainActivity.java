package edu.up.cs371.facemaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, OnItemSelectedListener, RadioGroup.OnCheckedChangeListener{

    private Face face;
    private Button randomButton;
    private Spinner hairSpinner;
    private Spinner eyeSpinner;
    private Spinner noseSpinner;
    private TextView redAmountText;
    private TextView greenAmountText;
    private TextView blueAmountText;
    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;
    private RadioButton hairColorButton;
    private RadioButton eyeColorButton;
    private RadioButton skinColorButton;
    private RadioGroup colorSetSelector;

    private static final int RED_MASK = 0xFF0000;
    private static final int GREEN_MASK = 0xFF00;
    private static final int BLUE_MASK = 0xFF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setListeners();
        /**
         External Reference
           Date: 11 Feb 2015
           Problem: I couldn't remember how to set spinner dropdown items with an adapter
           Resource:
               https://github.com/srvegdahl/srvegdahl-CS371GitLabStarter2Working/blob/master/app/src/main/java/edu/up/cs371/vegdahl/gitlab/GitLabActivity.java
           Solution: I look at Professor Vegdahl's example from the github lab
         */
        String[] hairStyleNames = getResources().getStringArray(R.array.HairStyles);
        ArrayAdapter<String> hairStyleAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, android.R.id.text1, hairStyleNames);
        hairStyleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairSpinner.setAdapter(hairStyleAdapter);

    }

    private void initializeViews() {
        face = (Face)findViewById(R.id.face);
        randomButton = (Button)findViewById(R.id.randomButton);
        hairSpinner = (Spinner)findViewById(R.id.hairSpinner);
        eyeSpinner = (Spinner)findViewById(R.id.eyeSpinner);
        noseSpinner = (Spinner)findViewById(R.id.noseSpinner);
        redAmountText = (TextView)findViewById(R.id.redAmountText);
        greenAmountText = (TextView)findViewById(R.id.greenAmountText);
        blueAmountText = (TextView)findViewById(R.id.blueAmountText);
        redSeekBar = (SeekBar)findViewById(R.id.redSeekBar);
        greenSeekBar = (SeekBar)findViewById(R.id.greenSeekBar);
        blueSeekBar = (SeekBar)findViewById(R.id.blueSeekBar);
        hairColorButton = (RadioButton)findViewById(R.id.hairColorButton);
        eyeColorButton = (RadioButton)findViewById(R.id.eyeColorButton);
        skinColorButton = (RadioButton)findViewById(R.id.skinColorButton);
        /**
         * External Citation
         *  Date: 11 Feb 2015
         *  Problem: I did not know how to get events from RadioButtons
         *  Resource: https://developer.android.com/reference/android/widget/RadioGroup.html
         *  Solution: I used a RadioGroup as a container and implemented the onCheckedChangeListener
         *      interface
         */
        colorSetSelector = (RadioGroup)findViewById(R.id.colorSetSelector);
    }

    private void setListeners(){
        randomButton.setOnClickListener(this);
        redSeekBar.setOnSeekBarChangeListener(this);
        greenSeekBar.setOnSeekBarChangeListener(this);
        blueSeekBar.setOnSeekBarChangeListener(this);
        colorSetSelector.setOnCheckedChangeListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.randomButton:
                face.randomize();
                face.invalidate();
                break;
            default:
                //do something
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == R.id.hairSpinner) {

        }
        if (view.getId() == R.id.eyeSpinner) {

        }
        if (view.getId() == R.id.noseSpinner) {

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do something
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.hairColorButton:
                int color = face.getHairColor();
                setColorSeekBars(color);
                break;
            case R.id.eyeColorButton:
                color = face.getEyeColor();
                setColorSeekBars(color);
                break;
            case R.id.skinColorButton:
                color = face.getSkinColor();
                setColorSeekBars(color);
                break;
        }
    }
    private void setColorSeekBars(int color){
        int red = (color & RED_MASK) >> 4*4;
        redSeekBar.setProgress(red);
        int green = (color & GREEN_MASK) >> 2*4;
        greenSeekBar.setProgress(green);
        int blue = (color & BLUE_MASK);
        blueSeekBar.setProgress(blue);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

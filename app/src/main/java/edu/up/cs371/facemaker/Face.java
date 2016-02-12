package edu.up.cs371.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;
import java.util.Random;

/**
 * Basic Face class that contains a face with styled eyes, hair, and nose that can be changed,
 * as well as variable color hair, eyes, and skin.
 *@author Kieran Losh
 *
 * Created by Kieran Losh on 2/9/2016.
 */
public class Face extends SurfaceView {
    //constants
    private static final int HAIR_STRAIGHT = 0;
    private static final int HAIR_CURLY = 1;
    private static final int HAIR_SPIKY = 2;



    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private Path[] hairStyles;
    private int hairStyleIndex;
    private int eyeStyle;
    private int noseStyle;
    private Random rand;

    public Face(Context context) {
        super(context);
        init();
    }

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        this.setWillNotDraw(false);
        hairStyles = new Path[3];
        rand = new Random();
        randomize();
    }

    public void randomize(){
        skinColor = rand.nextInt(0x1000000) + 0xFF000000; //generate a random color with no opacity
        eyeColor = rand.nextInt(0x1000000) + 0xFF000000;
        hairColor = rand.nextInt(0x1000000) + 0xFF000000;
        hairStyleIndex = rand.nextInt(hairStyles.length);
        eyeStyle = rand.nextInt(3);
        noseStyle = rand.nextInt(3);

    }

    public int getSkinColor(){ return skinColor; }

    public int getEyeColor(){
        return eyeColor;
    }

    public int getHairColor(){
        return hairColor;
    }

    public void setHairColor(int c){
        this.hairColor = c;
        this.invalidate();
    }

    public void setEyeColor(int c){
        this.eyeColor = c;
        this.invalidate();
    }

    public void setSkinColor(int c){
        this.skinColor = c;
        this.invalidate();
    }

    public void setHairStyle(int s){
        this.hairStyleIndex = s;
        this.invalidate();
    }

    public void setEyeStyle(int s){
        this.eyeStyle = s;
        this.invalidate();
    }

    public void setNoseStyle(int s){
        this.noseStyle = s;
        this.invalidate();
    }

    public int[] getStyles(){
        int returnVal[] = {hairStyleIndex, eyeStyle, noseStyle};
        return returnVal;
    }

    public void onDraw(Canvas c){
        //draw face
        drawHead(c);
        this.invalidate();
    }

    private void drawHead(Canvas canvas){
        Paint skinPaint = new Paint();
        skinPaint.setColor(skinColor);
        skinPaint.setStyle(Paint.Style.FILL);
        canvas.drawOval(300.0f, 50.0f, canvas.getWidth()-300.0f, canvas.getHeight()-50.0f, skinPaint);
    }

}

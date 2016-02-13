package edu.up.cs371.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
    private Path[] noseStyles;
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
        noseStyles = new Path[3];
        createNoseStyles();
        rand = new Random();
        randomize();

    }

    /**
     * creates the noseStyle paths.
     */
    private void createNoseStyles(){
        Path one = new Path();
        one.moveTo(650.0f, 400.0f);
        one.rLineTo(50.0f, 100.0f);
        one.rLineTo(-100.0f, 0.0f);
        one.rLineTo(50.0f, -100.0f);
        one.close();
        noseStyles[0] = one;

        Path two = new Path();
        two.moveTo(650.0f, 400.0f);
        two.rLineTo(25.0f, 150.0f);
        two.rLineTo(0.0f, 50.0f);
        two.rLineTo(-75.0f, 0.0f);
        two.rLineTo(25.0f, -50.0f);
        two.rLineTo(25.0f, -150.0f);
        two.close();
        noseStyles[1] = two;

        Path three = new Path();
        three.moveTo(650.0f, 400.0f);
        three.rLineTo(50.0f, 200.0f);
        three.rLineTo(-100.0f, 0.0f);
        three.rLineTo(50.0f, -200.0f);
        three.close();
        noseStyles[2] = three;
    }
    /**
     * creates the hair styles. straight hair is directly hard coded, and the other two types
     * have random elements.
     */
    private void createHairStyles(){
        /**
         * External Reference
         *  Date: 12 Feb 2016
         *  Problem: I couldn't remember the specifics on creating Paths
         *  Resource: https://developer.android.com/reference/android/graphics/Path.html
         *  Solution : Looked at API and used relevant functions
         */
        Path one = new Path();
        one.moveTo(375.0f, 200.0f);
        //one.addArc(275.0f, 50.f, 275.0f, 50.0f + 300.0f, -180.0f, 180.0f);
        one.lineTo(475.0f, 100.0f);
        one.lineTo(600.0f, 50.0f);
        one.lineTo(800.0f, 50.0f);
        one.lineTo(900.0f, 100.0f);
        one.lineTo(970.0f, 200.0f);
        one.lineTo(375.0f, 200.0f);
        one.close();
        hairStyles[0] = one;
        Path two = new Path();
        two.moveTo(350.0f, 200.0f);
        //randomly generate circles for curly hair
        for (int i = 0; i < 30; i++){
            two.addCircle(350.0f + (float)rand.nextInt(600), 50.0f + (float)rand.nextInt(150), 50.0f + (float)rand.nextInt(50), Path.Direction.CW);
        }
        two.close();
        hairStyles[1] = two;

        Path three = new Path();
        three.moveTo(375.0f, 200.0f);
        //randomly generate spikes
        for (int i = 400; i < 940; i += 20){
            three.lineTo((float)i + 5.0f, (float)rand.nextInt(100));
            three.lineTo((float)i + 10.0f, 50.0f);
        }
        three.lineTo(970.0f, 200.0f);
        three.lineTo(350.0f, 200.0f);
        three.close();
        hairStyles[2] = three;
    }

    /**
     * Randomizes the face properties
     */
    public void randomize(){
        skinColor = rand.nextInt(0x1000000) + 0xFF000000; //generate a random color with no opacity
        eyeColor = rand.nextInt(0x1000000) + 0xFF000000;
        hairColor = rand.nextInt(0x1000000) + 0xFF000000;
        hairStyleIndex = rand.nextInt(hairStyles.length);
        eyeStyle = rand.nextInt(3);
        noseStyle = rand.nextInt(3);
        createHairStyles();

    }

    /**
     * getters and setters
     */

    /**
     * gets skin color
     * @return skin color
     */
    public int getSkinColor(){ return skinColor; }
    /**
     * gets eye color
     * @return eye color
     */
    public int getEyeColor(){
        return eyeColor;
    }
    /**
     * gets hair color
     * @return hair color
     */
    public int getHairColor(){
        return hairColor;
    }

    /**
     * sets hair color
     * @param c - color to set to
     */
    public void setHairColor(int c){
        this.hairColor = c;
        this.invalidate();
    }
    /**
     * sets eye color
     * @param c - color to set to
     */
    public void setEyeColor(int c){
        this.eyeColor = c;
        this.invalidate();
    }
    /**
     * sets skin color
     * @param c - color to set to
     */
    public void setSkinColor(int c){
        this.skinColor = c;
        this.invalidate();
    }
    /**
     * sets hair style
     * @param s - style to set to
     */
    public void setHairStyle(int s){
        this.hairStyleIndex = s;
        this.invalidate();
    }
    /**
     * sets eye style
     * @param s - style to set to
     */
    public void setEyeStyle(int s){
        this.eyeStyle = s;
        this.invalidate();
    }
    /**
     * sets nose style
     * @param s - style to set to
     */
    public void setNoseStyle(int s){
        this.noseStyle = s;
        this.invalidate();
    }

    /**
     * gets current styles
     * @return array of styles in order hair, eye, nose
     * */
    public int[] getStyles(){
        int returnVal[] = {hairStyleIndex, eyeStyle, noseStyle};
        return returnVal;
    }

    /**
     * draws the face on the canvas
     * @param c - canvas to draw on
     */
    public void onDraw(Canvas c){
        //draw face
        drawHead(c);
        drawEyes(c);
        drawHair(c);
        drawNose(c);
        drawMouth(c);
        this.invalidate();
    }

    /**
     * Draws a basic mouth
     * @param c - canvas to draw on
     */
    private void drawMouth(Canvas c){
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.STROKE);
        blackPaint.setStrokeWidth(5.0f);
        c.drawArc(450.0f, 550.0f, 850.0f, 750.0f, -180.0f, -180.0f, false, blackPaint);
    }

    /**
     * Draws the hair paths depending on the current hairStyleIndex and colors it with hairColor
     * @param c - canvas to draw on
     */
    private void drawHair(Canvas c){
        Paint hairPaint = new Paint();
        hairPaint.setColor(hairColor);
        hairPaint.setPathEffect(null);
        hairPaint.setStyle(Paint.Style.FILL);
        c.drawPath(hairStyles[hairStyleIndex], hairPaint);
        this.invalidate();
    }

    /**
     * draws eyes
     * @param c - canvas to draw on
     */
    private void drawEyes(Canvas c){
        Paint eyePaint = new Paint();
        eyePaint.setColor(eyeColor);
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        Paint whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        whitePaint.setStyle(Paint.Style.FILL);
        Paint grayPaint = new Paint();
        grayPaint.setColor(Color.GRAY);
        if (eyeStyle == 2){//tired, set bags under eyes
            whitePaint.setShadowLayer(1.0f, 0.0f, 10.0f, Color.BLACK);
            c.drawOval(460.0f, 280.f, 540.0f, 340.0f, grayPaint);
            c.drawOval(760.0f, 280.f, 840.0f, 340.0f, grayPaint);
        }
        if (eyeStyle == 1){//excited
            c.drawOval(460.0f, 260.f, 540.0f, 340.0f, whitePaint);
            c.drawOval(760.0f, 260.f, 840.0f, 340.0f, whitePaint);
        }
        if (eyeStyle == 0 || eyeStyle == 2){//normal or tired
            c.drawOval(460.0f, 280.f, 540.0f, 320.0f, whitePaint);
            c.drawOval(760.0f, 280.f, 840.0f, 320.0f, whitePaint);
        }
        //always draw pupils and irises
        c.drawCircle(500.0f, 300.0f, 20.0f, eyePaint);
        c.drawCircle(800.0f, 300.0f, 20.0f, eyePaint);
        c.drawCircle(500.0f, 300.0f, 10.0f, blackPaint);
        c.drawCircle(800.0f, 300.0f, 10.0f, blackPaint);
    }

    /**
     * draws the nose using paths and the current nose style
     * @param c - canvas to draw on
     */
    private void drawNose(Canvas c){
        Paint nosePaint = new Paint();
        nosePaint.setColor(Color.BLACK);
        nosePaint.setStrokeWidth(5.0f);
        nosePaint.setPathEffect(null);
        nosePaint.setStyle(Paint.Style.STROKE);
        c.drawPath(noseStyles[noseStyle], nosePaint);
        this.invalidate();
    }

    /**
     * draws the overall head shape with skinColor
     * @param canvas - canvas to draw on
     */
    private void drawHead(Canvas canvas){
        Paint skinPaint = new Paint();
        skinPaint.setColor(skinColor);
        skinPaint.setStyle(Paint.Style.FILL);
        canvas.drawOval(300.0f, 50.0f, canvas.getWidth()-300.0f, canvas.getHeight()-50.0f, skinPaint);
    }

}

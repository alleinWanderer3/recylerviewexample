package com.example.mydraw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class MyView extends View {
    static final int EMPTY=1;
    static final int X=20;
    static final int O=300;
    Rect rect, crossRect;
    public static final int FIELD_SIZE = 3;
    private int[][] field;
    int cell_size;
    Drawable x_drawable, o_drawable;
    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    Paint paint;
    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;
    private boolean isXturn;
    private boolean isOver;

    public MyView(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyView, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.MyView_exampleString);
        mExampleColor = a.getColor(
                R.styleable.MyView_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.MyView_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.MyView_x_drawable)) {
            x_drawable = a.getDrawable(
                    R.styleable.MyView_x_drawable);

            x_drawable.setCallback(this);
        }
        if (a.hasValue(R.styleable.MyView_o_drawable)) {
            o_drawable = a.getDrawable(
                    R.styleable.MyView_o_drawable);
            o_drawable.setCallback(this);
        }
        a.recycle();
        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);


        cell_size = Math.min(this.getHeight()-1, this.getWidth()-1)/FIELD_SIZE;
        rect = new Rect();
        crossRect = new Rect();
        field = new int[FIELD_SIZE][FIELD_SIZE];
        startGame();

    }

    void startGame(){
        for(int i=0;i<FIELD_SIZE;i++)
            for(int j=0;j<FIELD_SIZE;j++)field[i][j]=EMPTY;
            isXturn = true;
            isOver = false;

    }

    public void drawGrid(Canvas c){
        paint.setColor(Color.BLACK);
        for(int i=0;i<=FIELD_SIZE;i++)for(int j=0;j<=FIELD_SIZE;j++){
            //линии сетки
            c.drawLine(1, j*cell_size,
                    FIELD_SIZE*cell_size, j*cell_size, paint);
            c.drawLine(i*cell_size, 1,
                    i*cell_size,FIELD_SIZE*cell_size, paint);
        }//ij
    }

    public void drawXO(Canvas c){
        for(int i=0;i<FIELD_SIZE;i++)for(int j=0;j<FIELD_SIZE;j++){
            //перобразуем индексы ячеек в координаты
            int tx=i*cell_size;
            int ty=j*cell_size;
            rect.set(tx, ty, tx+cell_size, ty+cell_size);
            //рисуем Х или О
            if(field[i][j]==X) {
                x_drawable.setBounds(rect);
                x_drawable.draw(c);
            }
            else if(field[i][j]==O) {
                o_drawable.setBounds(rect);
                o_drawable.draw(c);
            }
        }//ij
    }



    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(!isOver) {
                float x = event.getX();
                float y = event.getY();
                int i = (int) (x / cell_size);
                int j = (int) (y / cell_size);
                //выходим если индексы оказались вне поля
                if (i >= FIELD_SIZE || j >= FIELD_SIZE) return true;
                //выходим если клетка занята
                if (field[i][j] != EMPTY) return true;
                //isXturn - глобальный флаг хода Х или О
                if (isXturn) {
                    isXturn = false;
                    field[i][j] = X;
                } else {
                    isXturn = true;
                    field[i][j] = O;
                }
            }
        }
        //перерисовка View
        int tmp=checkState();
        if(tmp!=EMPTY){
            isOver=true;
            Toast.makeText
                    (getContext(), "game over", Toast.LENGTH_LONG).show();
        }

        invalidate();
        return super.onTouchEvent(event);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGrid(canvas);
        drawXO(canvas);
        drawCrossLine(canvas);

    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return mExampleString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {
        mExampleString = exampleString;

    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;

    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;

    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getXDrawable() {
        return x_drawable;
    }
    public Drawable getODrawable() {
        return o_drawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setXDrawable(Drawable exampleDrawable) {
        x_drawable = exampleDrawable;
    }
    public void setODrawable(Drawable exampleDrawable) {
        o_drawable = exampleDrawable;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cell_size=Math.min(this.getHeight()-1,
                this.getWidth()-1)/FIELD_SIZE;
    }
    /*
     * метод определяет размеры представления при создании
     */
    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measuredWidth =MeasureSpec.getSize(widthMeasureSpec);
        int g=Math.min(measuredHeight, measuredWidth);
        setMeasuredDimension(g, g);
    }

    public int checkState(){
        int check_row,check_cols,check_diag,check_diag2;
        //проверочные значения выигрыша
        int valueX=FIELD_SIZE*X;
        int valueO=FIELD_SIZE*O;
        check_diag=0;
        check_diag2=0;
        for(int i=0;i<FIELD_SIZE;i++){
            //блок проверки диагоналей
            check_diag+=field[i][i];
            check_diag2+=field[i][FIELD_SIZE-1-i];
            //блок проверки строк и стобцов
            check_row=0;
            check_cols=0;
            for(int j=0;j<FIELD_SIZE;j++){
                check_row+=field[i][j];
                check_cols+=field[j][i];
            }
            if(check_cols==valueO||check_cols==valueX){
                //прямоугольник для отрисовки линии
                crossRect=
                        new Rect(0+5,i*10+5,FIELD_SIZE*10-5,i*10+5);
                //возвращаем код символа, который выиграл
                return check_cols/FIELD_SIZE;
            }
            if(check_row==valueO||check_row==valueX){
                crossRect=new Rect(i*10+5,0+5,i*10+5,FIELD_SIZE*10-5);				return check_row/FIELD_SIZE;
            }
        }
        if(check_diag==valueO||check_diag==valueX){
            crossRect=
                    new Rect(0+5,0+5,FIELD_SIZE*10-5,FIELD_SIZE*10-5);
            return check_diag/FIELD_SIZE;
        }
        if(check_diag2==valueO||check_diag2==valueX){
            crossRect=
                    new Rect(0+5,FIELD_SIZE*10-5,FIELD_SIZE*10-5,0+5);
            return check_diag2/FIELD_SIZE;
        }
        //возвращаем 1 если нет зачеркиваний
        crossRect=null;
        return EMPTY;
    }
    private void drawCrossLine(Canvas c){
        Rect tmp;
        tmp=crossRect;
        paint.setColor(Color.RED);
        if(tmp!=null)
            c.drawLine(tmp.left*cell_size*0.1f,
                    tmp.top*cell_size*0.1f,
                    tmp.right*cell_size*0.1f,
                    tmp.bottom*cell_size*0.1f, paint);
    }
}

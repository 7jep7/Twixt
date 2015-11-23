package com.nyxxstudios.jonas.twixt;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.GridLayout;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity
        implements View.OnClickListener {

    Field[][] fields;

    GridLayout myGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myGridLayout = (GridLayout)findViewById(R.id.mygrid);

        int numOfCol = myGridLayout.getColumnCount();
        int numOfRow = myGridLayout.getRowCount();
        fields = new Field[numOfRow][numOfCol];
        for(int row=0; row<numOfRow; row++){
            for(int col=0; col<numOfCol; col++){
                Field f = new Field(this, col, row);
                f.setOnClickListener(this);
                fields[row][col] = f;
                myGridLayout.addView(f);
            }
        }

        //a comment for a test commit

        //set col and row of the fields?
        myGridLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new OnGlobalLayoutListener(){

                    @Override
                    public void onGlobalLayout() {

                        final int MARGIN = 5;

                        int pWidth = myGridLayout.getWidth();
                        int pHeight = myGridLayout.getHeight();
                        int numOfCol = myGridLayout.getColumnCount();
                        int numOfRow = myGridLayout.getRowCount();
                        int w = pWidth/numOfCol;
                        int h = pHeight/numOfRow;

                        for(int row=0; row<numOfRow; row++) {
                            for (int col = 0; col < numOfCol; col++) {
                                GridLayout.LayoutParams params =
                                        (GridLayout.LayoutParams) fields[row][col].getLayoutParams();
                                params.width = w - 2 * MARGIN;
                                params.height = h - 2 * MARGIN;
                                params.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);
                                fields[row][col].setLayoutParams(params);
                            }
                        }

                        //Is necessary to avoid calling the listener again
                        // and again in a endless loop
                        myGridLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                    }});
    }

    @Override
    public void onClick(View v) {

        Field f = (Field) v;
        //get the id string
        String idString = f.getIdX() + ":" + f.getIdY();

        Toast.makeText(MainActivity.this,
                "Toogled:\n" +
                        idString + "\n",
                Toast.LENGTH_SHORT).show();
    }
}
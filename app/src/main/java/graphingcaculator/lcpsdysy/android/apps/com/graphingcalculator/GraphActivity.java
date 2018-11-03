 package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.util.Log;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expression;

 public class GraphActivity extends AppCompatActivity {
    private ImageButton home;
    private ImageButton settings;
    private ImageButton origin;
    private GraphView graph;
    private EditText inputX;
    private EditText inputY;
    private EditText inputFunc;
    private Button updateInput;
    private LineGraphSeries<DataPoint> plotSeries;
    private int[] swapColors = {Color.GREEN, Color.BLUE, Color.RED, Color.GRAY, Color.CYAN, Color.MAGENTA, Color.LTGRAY, Color.YELLOW, Color.BLACK};
    private int colorInd = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        //Set up buttons + graph
        home = (ImageButton) findViewById(R.id.graphHomeButton);
        settings = (ImageButton) findViewById(R.id.graphSettingsButton);
        origin = (ImageButton) findViewById(R.id.graphOriginButton);
        inputX = (EditText) findViewById(R.id.graphInputX);
        inputY = (EditText) findViewById(R.id.graphInputY);
        inputFunc = (EditText) findViewById(R.id.enterFunc);
        updateInput = (Button) findViewById(R.id.click);
        graph = (GraphView) findViewById(R.id.graph);

        //Set up button OnClickListeners
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingsActivity();
            }
        });
        origin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOrigin();
            }
        });
        updateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readInput();
            }
        });

        //Set up basic series
        plotSeries = new LineGraphSeries<DataPoint>();
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(-1000, -1000),
        });
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(1000, 1000),
        });

        //Set up graph
        graph.getViewport().setMinY(-5);
        graph.getViewport().setMinX(-5);
        graph.getViewport().setMaxX(5);
        graph.getViewport().setMaxY(5);
        graph.getViewport().setMaxXAxisSize(10000);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
        graph.addSeries(series2);
        graph.addSeries(series3);
    }


    public void openHomeActivity()
    {
        Intent intent1 = new Intent(this, HomeActivity.class);
        startActivity(intent1);
    }
    public void openSettingsActivity()
    {
        Intent intent1 = new Intent(this, SettingsActivity.class);
        startActivity(intent1);
    }
    public void goToOrigin()
    {
        graph.getViewport().setMinY(-5);
        graph.getViewport().setMaxY(5);
        graph.getViewport().setMinX(-5);
        graph.getViewport().setMaxX(5);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
    }

    public void readInput()
    {
        try
        {
            if (!inputFunc.getText().toString().equals(""))
            {
                Expression exp = new Expression(new ArrayList<Character>(), new ArrayList<Double>());
                Log.d("readInput()", "reading Input, equation is " + inputFunc.getText().toString());
                LineGraphSeries<DataPoint> points = exp.graphSolve(inputFunc.getText().toString());
                //If returned series has no values, something went wrong
                if (points.isEmpty())
                    Toast.makeText(getApplicationContext(), "Please enter a valid equation", Toast.LENGTH_SHORT).show();
                else
                {
                    //Make graph a color
                    points.setColor(swapColors[colorInd++]);
                    if (colorInd > 8)
                        colorInd = 0;
                    graph.addSeries(points);
                }

            }
            else
            {
                graph.addSeries(plotSeries);
                //For fun, graphs sin function if all edittexts are empty
                if (inputX.getText().toString().equals("") && inputY.getText().toString().equals(""))
                {
                    testGraphing();
                }
                //Add plotted point
                else
                {
                    double x = Double.parseDouble(inputX.getText().toString());
                    double y = Double.parseDouble(inputY.getText().toString());
                    inputX.getText().clear();
                    inputY.getText().clear();
                    plotSeries.appendData(new DataPoint(x, y), false, 100000);
                }
            }
        }
        catch (Exception e)
        {
            Log.e("readInput()", "something went wrong in reading input");
            Toast.makeText(getApplicationContext(), "Invalid point value", Toast.LENGTH_SHORT).show();
        }
    }
    public void testGraphing()
     {
         try
         {
             LineGraphSeries<DataPoint> sinSeries = new LineGraphSeries<>();
             for (double i = -10; i < 100; i += 0.1)
             {
                 sinSeries.appendData(new DataPoint(i, Math.sin(i)),false,100000);
                 Log.d("testGraphing()", "RUNNING RUNNING RUNNING");
             }
             graph.addSeries(sinSeries);
         }
         catch (Exception e)
         {
             Log.e("testGraphing()", "something went wrong while graphing");
         }
     }
}

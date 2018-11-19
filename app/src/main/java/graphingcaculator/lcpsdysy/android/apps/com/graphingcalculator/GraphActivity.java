 package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expression;

 public class GraphActivity extends AppCompatActivity implements EquationEntryFragment.equationEntryFragmentListener {
     private Button addgraph;
    private ImageButton home;
    private ImageButton settings;
    private ImageButton originButton;
    private GraphView graph;
    private EditText inputX;
    private EditText inputY;
    private LineGraphSeries<DataPoint> plotSeries;
    private ArrayList<LineGraphSeries<DataPoint>> allSeries;
    private ArrayList<Integer> colorInds;
    private ArrayList<String> equations;
    private int[] swapColors = {Color.GREEN, Color.BLUE, Color.RED, Color.GRAY, Color.CYAN, Color.MAGENTA, Color.LTGRAY, Color.YELLOW, Color.BLACK};
    private int colorInd = 0;
    private ScrollView scroll;
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        //Initializers
        home = (ImageButton) findViewById(R.id.graphHomeButton);
        settings = (ImageButton) findViewById(R.id.graphSettingsButton);
        originButton = (ImageButton) findViewById(R.id.originButton);
        //inputX = (EditText) findViewById(R.id.graphInputX);
        //inputY = (EditText) findViewBy
        // Id(R.id.graphInputY);
        graph = (GraphView) findViewById(R.id.graph);
        addgraph = (Button)findViewById(R.id.addgraph);
        scroll = (ScrollView) findViewById(R.id.scroll);
        allSeries = new ArrayList<>();
        colorInds = new ArrayList<>();
        equations = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.frame) != null)
        {
            if (savedInstanceState != null)
                return;
            EquationEntryFragment eq = new EquationEntryFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(R.id.frame, eq, null);
            ft.commit();
        }

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
        addgraph.setOnClickListener(new View.OnClickListener() {
            @Override//
            public void onClick(View view) {
                if (findViewById(R.id.frame) != null)
                {
                    EquationEntryFragment eq = new EquationEntryFragment();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.add(R.id.frame, eq, null);
                    ft.commit();
                    scroll.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            scroll.fullScroll(View.FOCUS_DOWN);
                        }
                    },100);
                }
            }
        });
        originButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setYAxisBoundsManual(true);
                graph.getViewport().setMinX(-5);
                graph.getViewport().setMaxX(5);
                graph.getViewport().setMinY(-5);
                graph.getViewport().setMaxY(5);
                graph.onDataChanged(false, false);
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

        graph.setOnTouchListener(new GraphTouchListener(allSeries, graph, colorInds, equations));
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

    public void readInput(String func)
    {
        try
        {
            if (!func.equals(""))
            {
                Expression exp = new Expression(new ArrayList<Character>(), new ArrayList<Double>());
                Log.d("readInput()", "reading Input, equation is " + func);
                equations.add(func);
                double maxX = graph.getViewport().getMaxX(false);
                double minX = graph.getViewport().getMinX(false);
                double xChange = maxX - minX;
                LineGraphSeries<DataPoint> points = exp.graphSolve(func, minX - xChange, maxX + xChange, (maxX - minX) / 100);
                //If returned series has no values, something went wrong
                if (points.isEmpty())
                    Toast.makeText(getApplicationContext(), "Please enter a valid equation", Toast.LENGTH_SHORT).show();
                else
                {
                    //Make graph a color
                    colorInds.add(colorInd);
                    points.setColor(swapColors[colorInd++]);
                    if (colorInd > 8)
                        colorInd = 0;
                    allSeries.add(points);
                    graph.addSeries(points);
                }

            }
            else
            {
                graph.addSeries(plotSeries);
                colorInds.add(-1);
                equations.add("plot");
                //For fun, graphs sin function if all edittexts are empty
                if (inputX.getText().toString().equals("") && inputY.getText().toString().equals(""));
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

     @Override
     public void onEquationEntryFragmentRead(String message) {
         readInput(message);
     }

     @Override
     public void deleteEq(int code) {
         if (findViewById(R.id.frame) != null)
         {
             FragmentTransaction ft = fragmentManager.beginTransaction();
             List<Fragment> list = getSupportFragmentManager().getFragments();
             for (int i = 0; i < ((List) list).size() && i < allSeries.size(); i++)
             {
                 Fragment frag = list.get(i);
                 if (frag instanceof EquationEntryFragment)
                 {
                     if (((EquationEntryFragment) frag).code == code)
                     {
                         graph.removeSeries(allSeries.remove(i));
                         colorInds.remove(i);
                         equations.remove(i);
                         ft.remove(frag);
                     }

                 }
             }
             ft.commit();
             Log.d("delete eq", "done");
         }
     }

 }
 class GraphTouchListener implements View.OnTouchListener
 {
     private ArrayList<LineGraphSeries<DataPoint>> series;
     private GraphView graph;
     private double x;
     private ArrayList<Integer> colorInds;
     private ArrayList<String> equations;
     private double y;
     private double maxX;
     private double minX;
     private int[] swapColors = {Color.GREEN, Color.BLUE, Color.RED, Color.GRAY, Color.CYAN, Color.MAGENTA, Color.LTGRAY, Color.YELLOW, Color.BLACK};
     public GraphTouchListener(ArrayList<LineGraphSeries<DataPoint>> allSeries, GraphView graph, ArrayList<Integer> colorInds, ArrayList<String> equations)
     {
         this.equations = equations;
         series = allSeries;
         this.graph = graph;
         this.colorInds = colorInds;
         maxX = graph.getViewport().getMaxX(false);
         minX = graph.getViewport().getMinX(false);
         x = Math.abs(maxX) + Math.abs(minX);
         y = Math.abs(graph.getViewport().getMaxY(false)) + Math.abs(graph.getViewport().getMinY(false));
     }
     @Override
     public boolean onTouch(View v, MotionEvent event) {
         //Log.d("touch", x + " " + y);
         double newMaxX = graph.getViewport().getMaxX(false);
         double newMinX = graph.getViewport().getMinX(false);
         double newX = Math.abs(newMaxX + Math.abs(newMinX));
         double newY = Math.abs(graph.getViewport().getMaxY(false)) + Math.abs(graph.getViewport().getMinY(false));
         if ((newX > x && (newX - x) / x * 100 >= 100 )|| (newY > y && (newY - y) / y * 100 >= 100) || (x > newX && (x - newX) / x * 100 >= 50) || (y > newY && (y - newY) / y * 100 >= 50)
                 || Math.abs((newMaxX - maxX)) > 0.5 * x || Math.abs((newMinX - minX)) > 0.5 * x)
         {
             maxX = newMaxX;
             minX = newMinX;
             x = newX;
             y = newY;
             minX = graph.getViewport().getMinX(false);
             maxX = graph.getViewport().getMaxX(false);
             double xChange = maxX - minX;
             for (int i = 0; i < series.size(); i++)
             {
                 graph.removeSeries(series.get(i));
                 Expression exp = new Expression(new ArrayList<Character>(), new ArrayList<Double>());
                 series.set(i, exp.graphSolve(equations.get(i), minX - xChange, maxX + xChange, (maxX - minX) / 100));
                 if (!(swapColors[colorInds.get(i)] == -1))
                    series.get(i).setColor(swapColors[colorInds.get(i)]);
                 graph.addSeries(series.get(i));
             }
         }
//         if (series.size() > 0)
//         {
//             graph.removeSeries(series.get(0));
//             series.remove(0);
//         }
         return false;
     }
 }

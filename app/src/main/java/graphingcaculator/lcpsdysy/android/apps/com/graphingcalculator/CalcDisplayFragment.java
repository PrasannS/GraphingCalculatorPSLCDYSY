package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expression;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalcDisplayFragment.OnCalcDisplayReadListener} interface
 * to handle interaction events.
 * Use the {@link CalcDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalcDisplayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String STRING_REP = "param1";
    private static final String SOLUTION = "param2";
    OnCalcDisplayReadListener calcDisplayReadListener;
    // TODO: Rename and change types of parameters
    private String StringRep = "";
    private double Solution = 0;
    public EditText hdisplay;

    public CalcDisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalcDisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalcDisplayFragment newInstance(String param1, double param2) {
        CalcDisplayFragment fragment = new CalcDisplayFragment();
        Bundle args = new Bundle();
        args.putString(STRING_REP,param1);
        args.putDouble(SOLUTION, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void updateText(){
        hdisplay.setText(StringRep+" = "+Solution);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            StringRep = getArguments().getString(STRING_REP);
            Solution = getArguments().getDouble(SOLUTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_calc_display, container, false);
        hdisplay = (EditText)view.findViewById(R.id.hdisplay);
        hdisplay.setFocusable(false);
        hdisplay.setClickable(true);
        updateText();
        hdisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcDisplayReadListener.OnCalcDisplayRead(Solution);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try{
            calcDisplayReadListener = (CalcDisplayFragment.OnCalcDisplayReadListener)activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override onKeyboardOneRead");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCalcDisplayReadListener {
        // TODO: Update argument type and name
        void OnCalcDisplayRead(double b);
    }
}

package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expression;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KeyBoardOneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KeyBoardOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KeyBoardOneFragment extends Fragment {


    private Button openbracket;
    private Button second;
    private Button formula;
    private Button closedbracket;
    private Button clearbutton;
    private Button decimal;
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button enter;
    private Button add;
    private Button subtract;
    private Button squared;
    private Button divide;
    private Button multiply;
    private Button exponent;
    private Button E;
    private Button mod;
    private Button sqrt;
    private Button pi;
    String message;

    OnKeyboardOneReadListener KeyboardOneReadListener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public KeyBoardOneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KeyBoardOneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KeyBoardOneFragment newInstance(String param1, String param2) {
        KeyBoardOneFragment fragment = new KeyBoardOneFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public interface OnKeyboardOneReadListener{
        public void OnKeyboardOneRead(String message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_key_board_one, container, false);

        formula = (Button)view.findViewById(R.id.formulatest);
        formula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "quadratic";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });



        enter = (Button)view.findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "enter";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        second = (Button)view.findViewById(R.id.second);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcActivity.fragmentManager.beginTransaction().replace(R.id.fragmentcontainer,new KeyBoardTwoFragment(),null).commit();
            }
        });

        clearbutton = (Button)view.findViewById(R.id.clear);
        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "clear";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        pi = (Button)view.findViewById(R.id.pi);
        pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "π";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        add = (Button)view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "+";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        squared = (Button)view.findViewById(R.id.squared);
        squared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "^2";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        subtract = (Button)view.findViewById(R.id.subtract);
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "-";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        divide = (Button)view.findViewById(R.id.divide);
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "/";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        multiply = (Button)view.findViewById(R.id.multiply);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "*";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        exponent = (Button)view.findViewById(R.id.exponent);
        exponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "^";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        E = (Button)view.findViewById(R.id.E);
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "E";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        mod = (Button)view.findViewById(R.id.mod);
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "^";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });
        sqrt = (Button)view.findViewById(R.id.sqrt);
        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "√";
                KeyboardOneReadListener.OnKeyboardOneRead(message);

            }
        });


        decimal = (Button)view.findViewById(R.id.decimal);
        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = ".";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        openbracket = (Button)view.findViewById(R.id.openbracket);
        openbracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "(";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        closedbracket = (Button)view.findViewById(R.id.closedbracket);
        closedbracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = ")";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });




        one = (Button)view.findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "1";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        two = (Button)view.findViewById(R.id.two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "2";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        three = (Button)view.findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "3";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        four = (Button)view.findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "4";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        five = (Button)view.findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "5";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        six = (Button)view.findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "6";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        seven = (Button)view.findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "7";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        eight = (Button)view.findViewById(R.id.eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "8";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        nine = (Button)view.findViewById(R.id.nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "9";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });

        zero = (Button)view.findViewById(R.id.zero);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "0";
                KeyboardOneReadListener.OnKeyboardOneRead(message);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
        Activity activity = (Activity)context;
        try{
            KeyboardOneReadListener = (OnKeyboardOneReadListener)activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override onKeyboardOneRead");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

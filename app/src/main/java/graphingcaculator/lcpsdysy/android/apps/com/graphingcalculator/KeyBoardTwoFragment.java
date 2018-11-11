package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KeyBoardTwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KeyBoardTwoFragment extends Fragment {

    private Button openbracket;
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
    private Button divide;
    private Button multiply;
    private Button first;
    private String message;

    OnKeyBoardTwoReadListener KeyBoardTwoReadListener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public KeyBoardTwoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KeyBoardTwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KeyBoardTwoFragment newInstance(String param1, String param2) {
        KeyBoardTwoFragment fragment = new KeyBoardTwoFragment();
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

    public interface OnKeyBoardTwoReadListener{
        public void OnKeyBoardTwoRead(String message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_key_board_two, container, false);


        enter = (Button)view.findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "enter";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        clearbutton = (Button)view.findViewById(R.id.clear);
        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "clear";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        first = (Button)view.findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcActivity.fragmentManager.beginTransaction().replace(R.id.fragmentcontainer,new KeyBoardOneFragment(),null).commit();
            }
        });



        add = (Button)view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "+";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        subtract = (Button)view.findViewById(R.id.subtract);
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "-";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        divide = (Button)view.findViewById(R.id.divide);
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "/";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        multiply = (Button)view.findViewById(R.id.multiply);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "*";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });


        decimal = (Button)view.findViewById(R.id.decimal);
        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = ".";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        openbracket = (Button)view.findViewById(R.id.openbracket);
        openbracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "(";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        closedbracket = (Button)view.findViewById(R.id.closedbracket);
        closedbracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = ")";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });




        one = (Button)view.findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "1";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        two = (Button)view.findViewById(R.id.two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "2";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        three = (Button)view.findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "3";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        four = (Button)view.findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "4";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        five = (Button)view.findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "5";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        six = (Button)view.findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "6";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        seven = (Button)view.findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "7";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        eight = (Button)view.findViewById(R.id.eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "8";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        nine = (Button)view.findViewById(R.id.nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "9";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });

        zero = (Button)view.findViewById(R.id.zero);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "0";
                KeyBoardTwoReadListener.OnKeyBoardTwoRead(message);
            }
        });
        return view;
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
            KeyBoardTwoReadListener = (KeyBoardTwoFragment.OnKeyBoardTwoReadListener)activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override OnKeyBoardTwoRead");
        }
    }

}

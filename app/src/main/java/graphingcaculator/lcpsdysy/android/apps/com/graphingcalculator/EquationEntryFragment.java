package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EquationEntryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EquationEntryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EquationEntryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button enter;
    private String message;
    private EditText enterFunc;
    private equationEntryFragmentListener equationEntry;
    private Button deleteButton;
    public int code;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EquationEntryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EquationEntryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EquationEntryFragment newInstance(String param1, String param2) {
        EquationEntryFragment fragment = new EquationEntryFragment();
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

    public interface equationEntryFragmentListener
    {
        public void onEquationEntryFragmentRead(String message);
        public void deleteEq(int code);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_equation_entry, container, false);
        enterFunc = (EditText) view.findViewById(R.id.enterFunc);
        deleteButton = (Button) view.findViewById(R.id.deleteButton);
        enter = (Button) view.findViewById(R.id.enterButton);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override//
            public void onClick(View view) {
                message = enterFunc.getText().toString();
                equationEntry.onEquationEntryFragmentRead(message);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message == null)
                    return;
                code = (int) (Math.random() * 1000) + message.hashCode();
                equationEntry.deleteEq(code);
            }
        });
        return view;
    }
//
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
        Activity activity = (Activity)context;
        try{
            equationEntry = (equationEntryFragmentListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override equationEntry");
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

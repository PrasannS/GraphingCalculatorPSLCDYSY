package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EquationEntryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EquationEntryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EquationEntryFragment extends Fragment {

    private EditText enterFunc;
    private equationEntryFragmentListener equationEntry;
    private Button deleteButton;
    private int code;
    private boolean first;

    private OnFragmentInteractionListener mListener;

    public EquationEntryFragment() {

    }




    public static EquationEntryFragment newInstance(int code) {
        EquationEntryFragment fragment = new EquationEntryFragment();
        Bundle args = new Bundle();
        args.putInt("code", code);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }


    public interface equationEntryFragmentListener
    {
        public void deleteEq(int code);
        public void updatedEq(int code, String newEq);
        public void firstRead(String eq);
    }

    public int getCode()
    {
        return code;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_equation_entry, container, false);
        enterFunc = (EditText) view.findViewById(R.id.enterFunc);
        enterFunc.addTextChangedListener(changeWatch);
        first = true;
        if (getArguments() != null) {
            Bundle args = getArguments();
            code = (int) args.get("code");
        }
        deleteButton = (Button) view.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equationEntry.deleteEq(code);
            }
        });
        return view;
    }

    private TextWatcher changeWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (first)
            {
                equationEntry.firstRead(s.toString());
                first = false;
            }
            else
                equationEntry.updatedEq(code, s.toString());
        }
    };

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

package bitpot.aboutcanada;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.*;
import android.view.MotionEvent;
import android.widget.AdapterView.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TransBlankFragment extends android.support.v4.app.Fragment implements OnItemSelectedListener,View.OnTouchListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int diction;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutCitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransBlankFragment newInstance(String param1, String param2) {
        TransBlankFragment fragment = new TransBlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TransBlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TranslationsFragment.diction=R.raw.dictionaryenglish;
        diction = R.raw.dictionaryfrench;
        View view = inflater.inflate(R.layout.fragment_translation, null);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerLang);

        spinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Language_Array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        view.setOnTouchListener(this);
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        android.support.v4.app.Fragment newFragment = new TranslationsFragment();
        android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.langLang, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public boolean onTouchEvent(MotionEvent event) {
   /*     Log.d("hi","byedsasa");
        android.support.v4.app.Fragment newFragment = new CultureFragment();
        android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.langLang, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
        return false;
    }
    public boolean onTouch(View view,MotionEvent event) {
    /*    Log.d("hi","byedsasa");
        android.support.v4.app.Fragment newFragment = new CultureFragment();
        android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.langLang, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
        return false;
    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}


package bitpot.aboutcanada;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.*;
import android.widget.AdapterView.*;
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Pavi-MAC on 2015-11-28.
 */
public class TranslationsFragment extends android.support.v4.app.Fragment implements OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static int diction;

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
    public static TranslationsFragment newInstance(String param1, String param2) {
        TranslationsFragment fragment = new TranslationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TranslationsFragment() {
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
        DispFileText((LinearLayout) view.findViewById(R.id.transLinLay));
        return view;
    }



    public void DispFileText(LinearLayout ll){
        View lineDr = new View(getActivity());
        ViewGroup.LayoutParams linelay = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        lineDr.setLayoutParams(linelay);
        lineDr.setBackgroundColor(Color.parseColor(getResources().getString(R.string.line_header)));
        ll.addView(lineDr);
        InputStream is = getResources().openRawResource(diction);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        InputStream iss = getResources().openRawResource(R.raw.dictionaryenglish);
        BufferedReader brr = new BufferedReader(new InputStreamReader(iss));
        String line;
        String line2;
        String entireFile = "";

        try {
            while((line = br.readLine()) != null) { // <--------- place readLine() inside loop
                line2= brr.readLine();
                // entireFile += (line + "\n"); // <---------- add each line to entireFile
                TextView tv = new TextView(getActivity());

                ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                if (line.length() <= 0) {

                }
                else {



                    tv.setText(String.format("%-12s<---->%12s",line2,line));
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                    tv.setTextColor(Color.parseColor(getResources().getString(R.string.font_body)));
                    tv.setGravity(1);
                    tv.setPadding((int) (10 * getResources().getDisplayMetrics().density), 0, (int) (10 * getResources().getDisplayMetrics().density), 0);

                    ll.addView(tv);
                }
            }
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        switch (position) {
            case 1:
                diction = R.raw.dictionaryfrench;
                break;
            case 2:
                diction = R.raw.dictionaryarabic;
                break;
            case 3:
                diction = R.raw.dictionarychinesesimplified;
                break;
            case 4:
                diction = R.raw.dictionarychinesetraditional;
                break;
            case 5:
                diction = R.raw.dictionaryfilipino;
                break;
            case 6:
                diction = R.raw.dictionaryhindi;
                break;
            case 7:
                diction = R.raw.dictionarypunjabi;
                break;
            case 8:
                diction = R.raw.dictionarypersian;
                break;
            case 9:
                diction = R.raw.dictionarytamil;
                break;
            default:
                return;
        }

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
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


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onStart() {
        super.onStart();
        try {
            mListener = (OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            //throw new ClassCastException(getActivity().toString()
              //      + " must implement OnFragmentInteractionListener");
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


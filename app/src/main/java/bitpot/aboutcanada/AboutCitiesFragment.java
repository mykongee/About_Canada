package bitpot.aboutcanada;

import android.app.Activity;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import java.io.*;
import android.graphics.Color;
import java.lang.reflect.Type;

import android.util.TypedValue;
import android.view.ViewGroup.*;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AboutCitiesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AboutCitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutCitiesFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
    public static AboutCitiesFragment newInstance(String param1, String param2) {
        AboutCitiesFragment fragment = new AboutCitiesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AboutCitiesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_about_cities, null);
        DispFileText((LinearLayout) view.findViewById(R.id.fragCityLinLay));
        return view;
    }



    public void DispFileText(LinearLayout ll){
        InputStream is = getResources().openRawResource(R.raw.social);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        String entireFile = "";

        try {
            while((line = br.readLine()) != null) { // <--------- place readLine() inside loop
               // entireFile += (line + "\n"); // <---------- add each line to entireFile
                TextView tv = new TextView(getActivity());
                if (line.length() > 0) {
                    tv.setText(line.substring(3, line.length()));
                }
                LayoutParams layout = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                if (line.length() <= 0) {

                }
                else {

                if (line.substring(0,3).equals("H--")) {
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
                    tv.setTextColor(Color.parseColor(getResources().getString(R.string.font_header)));
                    tv.setPadding((int) (15 * getResources().getDisplayMetrics().density), 0, (int) (5 * getResources().getDisplayMetrics().density), (int) (5 * getResources().getDisplayMetrics().density));
                }else if (line.substring(0,3).equals("P--")) {
                    tv.setText("   • "+line.substring(3,line.length()));
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    tv.setTextColor(Color.parseColor(getResources().getString(R.string.font_body)));
                    tv.setPadding((int) (15 * getResources().getDisplayMetrics().density), 0, 0, 0);
                }
                else if (line.substring(0,3).equals("") || line.substring(0,3).equals("   ")){

                }else {
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    tv.setTextColor(Color.parseColor(getResources().getString(R.string.font_body)));
                    tv.setPadding((int) (15 * getResources().getDisplayMetrics().density), 0, 0, 0);
                }
                tv.setLayoutParams(layout);
                ll.addView(tv);
            }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

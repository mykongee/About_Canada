package bitpot.aboutcanada;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CultureFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CultureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinanceFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<String> currencyArray = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayList<String> financeArray;

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
     * @return A new instance of fragment CultureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CultureFragment newInstance(String param1, String param2) {
        CultureFragment fragment = new CultureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FinanceFragment() {
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

        View view = inflater.inflate(R.layout.fragment_finance, null);
        try {
            TextView headerView = (TextView) ((LayoutInflater) getActivity().getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_view_header, null, false);
            ListView lv = (ListView) view.findViewById(R.id.lv);
            headerView.setText("Currency Exchange");
            headerView.setPadding(12, 0,0,0);
            lv.addHeaderView(headerView);

            InputStream is = getResources().openRawResource(R.raw.finance);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            financeArray = new ArrayList<String>();

            while((line = br.readLine()) != null) { // <--------- place readLine() inside loop
                financeArray.add(line);
            }

            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, currencyArray);
            lv.setAdapter(adapter);
            CurrencyTask currencyTask = new CurrencyTask();
            String url = "http://api.fixer.io/latest?base=CAD";
            currencyTask.execute(url);
            return view;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return view;
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

    class CurrencyTask extends AsyncTask<String, Void, String> {
        HttpURLConnection urlConnection;
        @Override
        protected String doInBackground(String... params) {
            final String TAG = params[0];
            Log.d(TAG, "doInBackground: ");
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                return result.toString();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println(result);


            try {
                JSONObject obj = new JSONObject(result);
//                JSONObject date = obj.getJSONObject("date");
                JSONObject currencies = obj.getJSONObject("rates");
                System.out.println(currencies);
                for (int i = 0; i < currencies.names().length() ;i++) {

                    String y = (String) currencies.names().get(i);
                    Double x = (Double) currencies.get(currencies.names().getString(i));

                    String ex = y + " : " + x;
                    currencyArray.add(ex);
                    System.out.println(ex);
                }
                ArrayList<String> newList = new ArrayList<String>(currencyArray);
                newList.addAll(financeArray);
                System.out.println(newList.size());
                System.out.println("hello newList");

                System.out.println(adapter.getCount());
                System.out.println("hello");

//                adapter.clear();
                System.out.println(adapter.getCount());
                System.out.println("hello2");
                System.out.println(currencyArray.size());


                for (String x: financeArray) {
                        System.out.println("inloop?");
                        System.out.println(x);
                        System.out.println(adapter.getCount());
                        adapter.add(x);

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {}
    }

}

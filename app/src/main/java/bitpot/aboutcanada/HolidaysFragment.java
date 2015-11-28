package bitpot.aboutcanada;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackli on 2015-11-28.
 * <p/>
 * Fragment showing Canadian Holidays
 */
public class HolidaysFragment extends android.support.v4.app.Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Map of holiday dates (key) and holiday names (values)
     */
    private final Map<Calendar, String> holidayDatesMap = new HashMap<>();

    public static final Map<String, Integer> MONTHS_MAP = new HashMap<>();

    static
    {
        MONTHS_MAP.put("JANUARY", 0);
        MONTHS_MAP.put("FEBRUARY", 1);
        MONTHS_MAP.put("MARCH", 2);
        MONTHS_MAP.put("APRIL", 3);
        MONTHS_MAP.put("MAY", 4);
        MONTHS_MAP.put("JUNE", 5);
        MONTHS_MAP.put("JULY", 6);
        MONTHS_MAP.put("AUGUST", 7);
        MONTHS_MAP.put("SEPTEMBER", 8);
        MONTHS_MAP.put("OCTOBER", 9);
        MONTHS_MAP.put("NOVEMBER", 10);
        MONTHS_MAP.put("DECEMBER", 11);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CultureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HolidaysFragment newInstance(String param1, String param2)
    {
        HolidaysFragment fragment = new HolidaysFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HolidaysFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Populate holidayDatesMap
        // todo don't use hardcoded dates in the future
        Calendar date = Calendar.getInstance();
        date.set(2015, MONTHS_MAP.get("JANUARY"), 1);
        holidayDatesMap.put(date, getText(R.string.new_years).toString());

        date.set(2015, MONTHS_MAP.get("APRIL"), 3);
        holidayDatesMap.put(date, getText(R.string.good_friday).toString());

        date.set(2015, MONTHS_MAP.get("MAY"), 10);
        holidayDatesMap.put(date, getText(R.string.mothers_day).toString());

        date.set(2015, MONTHS_MAP.get("MAY"), 18);
        holidayDatesMap.put(date, getText(R.string.victoria_day).toString());

        date.set(2015, MONTHS_MAP.get("JUNE"), 21);
        holidayDatesMap.put(date, getText(R.string.fathers_day).toString());

        date.set(2015, MONTHS_MAP.get("JULY"), 1);
        holidayDatesMap.put(date, getText(R.string.canada_day).toString());

        date.set(2015, MONTHS_MAP.get("SEPTEMBER"), 7);
        holidayDatesMap.put(date, getText(R.string.labour_day).toString());

        date.set(2015, MONTHS_MAP.get("OCTOBER"), 12);
        holidayDatesMap.put(date, getText(R.string.thanksgiving_day).toString
                ());

        date.set(2015, MONTHS_MAP.get("OCTOBER"), 31);
        holidayDatesMap.put(date, getText(R.string.halloween).toString());

        date.set(2015, MONTHS_MAP.get("NOVEMBER"), 11);
        holidayDatesMap.put(date, getText(R.string.remembrance_day).toString());

        date.set(2015, MONTHS_MAP.get("DECEMBER"), 25);
        holidayDatesMap.put(date, getText(R.string.christmas).toString());

        Calendar currentDay = Calendar.getInstance();
        int currentYear = currentDay.get(Calendar.YEAR);
        int currentMonth = currentDay.get(Calendar.MONTH);
        int currentDate = currentDay.get(Calendar.DATE);

        String holiday;
        Activity parentActivity = getActivity();
        NotificationManager NM;
        Notification notifyHoliday;

        if ((holiday = holidayDatesMap.get(currentDay)) != null && (holiday =
                holidayDatesMap.get(currentDay)).isEmpty())
        {
            // todo push notification for holiday
            NM = (NotificationManager) parentActivity.getSystemService
                    (Context.NOTIFICATION_SERVICE);
//            notifyHoliday = new Notification.Builder(this.getContext())
//                    .setContentTitle("Today is" + holiday + "!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_about_cities, null);
        DispFileText((LinearLayout) view.findViewById(R.id.fragCityLinLay));
        return view;
    }

    // todo - pull holiday dates from online rather than hardcoded into a file
    public void DispFileText(LinearLayout ll)
    {
        InputStream is = getResources().openRawResource(R.raw.holidays);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        String entireFile = "";

        try
        {
            while ((line = br.readLine()) != null)
            { // <--------- place readLine() inside loop
                // entireFile += (line + "\n"); // <---------- add each line
                // to entireFile
                TextView tv = new TextView(getActivity());
                if (line.length() > 0)
                {
                    tv.setText(line.substring(3, line.length()));
                }
                ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                                .LayoutParams.MATCH_PARENT);
                if (line.length() <= 0)
                {

                }
                else
                {

                    if (line.substring(0, 3).equals("H--"))
                    {
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
                        tv.setTextColor(Color.parseColor(getResources()
                                .getString(R.string.font_header)));
                        tv.setPadding((int) (15 * getResources()
                                        .getDisplayMetrics().density), (int)
                                        (5 *
                                                getResources()
                                                        .getDisplayMetrics()
                                                        .density),
                                (int) (15 * getResources().getDisplayMetrics
                                        ().density), (int) (5 * getResources
                                        ().getDisplayMetrics().density));
                    }
                    else if (line.substring(0, 3).equals("P--"))
                    {
                        tv.setText("   • " + line.substring(3, line.length()));
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                        tv.setTextColor(Color.parseColor(getResources()
                                .getString(R.string.font_body)));
                        tv.setPadding((int) (10 * getResources()
                                .getDisplayMetrics().density), 0, (int) (10 *
                                getResources().getDisplayMetrics().density), 0);
                    }
                    else
                    {
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                        tv.setTextColor(Color.parseColor(getResources()
                                .getString(R.string.font_body)));
                        tv.setPadding((int) (10 * getResources()
                                .getDisplayMetrics().density), 0, (int) (10 *
                                getResources().getDisplayMetrics().density), 0);
                    }
                    tv.setLayoutParams(layout);
                    ll.addView(tv);
                    if (line.substring(0, 3).equals("H--"))
                    {
                        View lineDr = new View(getActivity());
                        ViewGroup.LayoutParams linelay = new ViewGroup
                                .LayoutParams(ViewGroup.LayoutParams
                                .MATCH_PARENT, 1);
                        lineDr.setLayoutParams(linelay);
                        lineDr.setBackgroundColor(Color.parseColor
                                (getResources().getString(R.string
                                        .line_header)));
                        ll.addView(lineDr);
                    }
                }
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mListener = (OnFragmentInteractionListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
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
     * "http://developer.android.com/training/basics/fragments/communicating
     * .html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}

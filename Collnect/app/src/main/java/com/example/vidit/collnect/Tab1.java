package com.example.vidit.collnect;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.vidit.collnect.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vidit on 02-10-2015.
 */
public class Tab1 extends Fragment {
    private static final String TAG = Tab1.class.getSimpleName();
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private ArrayList<Events> listEvents1 = new ArrayList<>();
    private AdapterKnuth adapterKnuth;
    private RecyclerView listEvents;
    private TextView textVolleyError;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String STATE_EVENTS = "state_events";
    private String mParam1;
    private String mParam2;
    //private VolleySingleton volleysingleton;
    public Tab1(){

    }
    public static Tab1 newInstance(String param1,String param2){
        Tab1 tab1 = new Tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        args.putString(ARG_PARAM2,param2);
        tab1.setArguments(args);
        return tab1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //volleySingleton = new VolleySingleton();
        requestQueue = VolleySingleton.getmInstance().getRequestQueue();
        //requestQueue = volleySingleton.getRequestQueue();
        String tag_string_req = "req_events";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_EVENTS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Event Response: " + response.toString());
                //hideDialog();
                    /*Intent i = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(i);
                    finish();*/



                listEvents1 = jsonparse(response);
                adapterKnuth.setEventList(listEvents1);
                    /*JSONArray jArr = new JSONArray(response);
                    parseJson(response);
                   boolean error = false;
                    //boolean error = jObj.getBoolean("error");
                    /*JSONObject jObj = jArr[0];
                     int id = jObj.getInt("id");
                    // Check for error node in json
                    if (id!=0) {
                        // user successfully logged in
                        // Create login session

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getActivity(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                }*/

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "EVENT Fetching Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                //hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("hub_id", "1");
                return params;
            }

        };
        requestQueue.add(strReq);
    }
    private ArrayList<Events> jsonparse(String rest){
        ArrayList<Events> tempList = new ArrayList<>();

        try {
            JSONArray response = new JSONArray(rest);
            if (response.length() == 0) {
                return tempList;
            }
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    String title = null;
                    if (jsonObject.has("name")) {
                        title = jsonObject.getString("name");
                    }
                    String created_at = null;
                    if (jsonObject.has("date")) {
                        created_at = jsonObject.getString("date");
                    }
                    String venue = null;
                    if (jsonObject.has("venue")) {
                        venue = jsonObject.getString("venue");
                    }
                    String description = null;
                    if (jsonObject.has("desc")) {
                        description = jsonObject.getString("desc");
                    }
                    Events events = new Events();
                    events.setTitle(title);
                    events.setCreated_at(created_at);
                    events.setDescription(description);
                    events.setVenue(venue);
                    tempList.add(events);
                } catch (JSONException e) {

                }
               // Toast.makeText(getActivity(), listEvents1.toString(), Toast.LENGTH_LONG).show();
            }
        }catch (JSONException e){

        }
        return tempList;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_1,container,false);
        listEvents = (RecyclerView) view.findViewById(R.id.listevents);
        listEvents.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterKnuth = new AdapterKnuth(getActivity());
        listEvents.setAdapter(adapterKnuth);
      /*  textVolleyError = (TextView) view.findViewById(R.id.textView);
        listEvents.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterGDG = new AdapterGDG(getActivity());
        listEvents.setAdapter(adapterGDG);*/
        /*if (savedInstanceState != null) {
            listEvents1 = savedInstanceState.getParcelableArrayList(STATE_EVENTS);

        } else {
            listMovies = MyApplication.getWritableDatabase().getAllMoviesBoxOffice();
            if (listMovies.isEmpty()) {
                L.t(getActivity(), "executing task from fragment");
                new TaskLoadMoviesBoxOffice(this).execute();
            }
        }*/

        // adapterGDG.setEvents(listEvents1);
        return view;
    }
}
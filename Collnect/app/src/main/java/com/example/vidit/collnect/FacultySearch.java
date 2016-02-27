package com.example.vidit.collnect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.example.vidit.collnect.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vidit on 01-12-2015.
 */
public class FacultySearch extends Fragment {
    private static final String TAG = Tab1.class.getSimpleName();
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private ArrayList<FacultyDetail> listEvents1 = new ArrayList<>();
    private AdapterFaculty adapterFaculty;
    private RecyclerView listEvents;
    private TextView textVolleyError;
    private Button submission;
    private EditText searchStr;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String STATE_EVENTS = "state_events";
    private String mParam1;
    private String mParam2;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //volleySingleton = new VolleySingleton();
        requestQueue = VolleySingleton.getmInstance().getRequestQueue();
    }
    public void sendJsonRequest(final String search_string){
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_FACULTY, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Event Response: " + response.toString());
                //hideDialog();
                    /*Intent i = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(i);
                    finish();*/



                listEvents1 = jsonparse(response);
                adapterFaculty.setEventList(listEvents1);
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
                params.put("query", search_string);
                return params;
            }

        };
        requestQueue.add(strReq);
    }
    private ArrayList<FacultyDetail> jsonparse(String rest){
        ArrayList<FacultyDetail> tempList = new ArrayList<>();

        try {
            JSONArray response = new JSONArray(rest);
            if (response.length() == 0) {
                return tempList;
            }
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    String name = null;
                    if (jsonObject.has("name")) {
                        name = jsonObject.getString("name");
                    }
                    String rank = null;
                    if (jsonObject.has("rank")) {
                        rank = jsonObject.getString("rank");
                    }
                    String department = null;
                    if (jsonObject.has("department")) {
                        department = jsonObject.getString("department");
                    }
                    String urlThumbnail = null;
                    if (jsonObject.has("pic_url")) {
                        urlThumbnail = jsonObject.getString("pic_url");
                    }
                    String urlProfile = null;
                    if (jsonObject.has("url")) {
                        urlProfile = jsonObject.getString("url");
                    }
                    FacultyDetail facultyDetail = new FacultyDetail();
                    facultyDetail.setName(name);
                    facultyDetail.setRank(rank);
                    facultyDetail.setDepartment(department);
                    facultyDetail.setUrlThumbnail(urlThumbnail);
                    facultyDetail.setProf_uri(urlProfile);
                    tempList.add(facultyDetail);
                } catch (JSONException e) {

                }
                // Toast.makeText(getActivity(), listEvents1.toString(), Toast.LENGTH_LONG).show();
            }
        }catch (JSONException e){

        }
        return tempList;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.search_faculty,container,false);
        submission = (Button) view.findViewById(R.id.button);
        submission.setOnClickListener(new View.OnClickListener() {
            private String search = null;
            @Override
            public void onClick(View v) {
                searchStr = (EditText) view.findViewById(R.id.search_string);
                search = searchStr.getText().toString();
                sendJsonRequest(search);
            }
        });
        listEvents = (RecyclerView) view.findViewById(R.id.listevents);
        listEvents.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterFaculty = new AdapterFaculty(getActivity());
        listEvents.setAdapter(adapterFaculty);
        sendJsonRequest("all");
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

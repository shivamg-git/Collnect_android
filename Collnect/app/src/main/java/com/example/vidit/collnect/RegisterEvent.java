package com.example.vidit.collnect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vidit.collnect.helper.SQliteEventHandler;
import com.example.vidit.collnect.network.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RegisterEvent extends ActionBarActivity {
    private static final String TAG = RegisterEvent.class.getSimpleName();
    private Spinner spinner1, spinner2;
    private Button btnSubmit;
    private EditText title;
    private EditText description;
    private EditText date;
    private EditText venue;
    private EditText duration;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_event);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        addItemsOnSpinner1();
       // addItemsOnSpinner2();
        addListenerOnButton();
       // addListenerOnSpinnerItemSelection();
    }
    public void  addItemsOnSpinner1(){
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        String[] hubs = {"Knuth","GDG","OSDC","Thespian","Robotics"};
        List<String> hubs_name = new ArrayList<>(Arrays.asList(hubs));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, hubs_name);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }
    /*public void  addItemsOnSpinner2(){
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        List<String> days_list = new ArrayList<>(Arrays.asList(days));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,days_list );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }*/

    public void addListenerOnButton(){
        btnSubmit = (Button) findViewById(R.id.btnRegister);
      //  final SQliteEventHandler sQliteEventHandler = new SQliteEventHandler(getApplicationContext());


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            String test;
            String title1;
            String description1;
            String date1;
            String venue1;
            String duration1;
            String hub;
            String enroll;
            @Override
            public void onClick(View v) {
                title = (EditText) findViewById(R.id.title);
                description = (EditText) findViewById(R.id.description);
                date = (EditText) findViewById(R.id.date);
                venue = (EditText) findViewById(R.id.venue_text);
                duration = (EditText) findViewById(R.id.duration);

                 title1 = title.getText().toString();
                 description1 = description.getText().toString();
                 date1 = date.getText().toString();
                 venue1 = venue.getText().toString();
                 duration1 = duration.getText().toString();
                  enroll = "13103544";
                if(String.valueOf(spinner1.getSelectedItem()).toString().equalsIgnoreCase("Knuth")){
                    hub = "1";
                }
                else if(String.valueOf(spinner1.getSelectedItem()).toString().equalsIgnoreCase("OSDC")){
                    hub = "2";
                }
                else if(String.valueOf(spinner1.getSelectedItem()).toString().equalsIgnoreCase("GDG")){
                    hub = "4";
                }
                else if(String.valueOf(spinner1.getSelectedItem()).toString().equalsIgnoreCase("Thespian")){
                    hub = "3";
                }
                else{
                    hub = "5";
                }
                registerEvent(title1,date1,description1,venue1,duration1,hub,enroll);
                /*if(String.valueOf(spinner1.getSelectedItem()).toString().equalsIgnoreCase("Knuth")) {
                    sQliteEventHandler.deleteUsers();
                    sQliteEventHandler.addEvent(title1,description1,String.valueOf(spinner2.getSelectedItem() ).toString(),date1);
                    HashMap<String, String> event = sQliteEventHandler.getEventsDetails();
                    test = event.get("title");
                    Log.d(TAG,test+date);
                }*/

                Toast.makeText(RegisterEvent.this,test + date1,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void registerEvent(final String title2, final String date2,final String description2, final String venue2, final String duration2, final String hub2, final String enroll) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Registering Event ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();
                    /*Intent i = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(i);
                    finish();*/


                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = false;
                    error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        //session.setLogin(true);
                        //enrollnumber = enroll;
                        // Launch main activity
                     /*   Intent intent = new Intent(RegisterEvent.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();*/
                        pDialog.setMessage("Event registered ...");
                        showDialog();
                        hideDialog();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("title", title2);
                params.put("description", description2);
                params.put("venue", venue2);
                params.put("hub",hub2);
                params.put("duration",duration2);
                params.put("date",date2);
                params.put("enroll",enroll);

                return params;
            }

        };

        // Adding request to request queue
        strReq.setTag(tag_string_req);
        // RequestQueue queue = Volley.newRequestQueue(this);
        RequestQueue queue = VolleySingleton.getmInstance().getRequestQueue();
        queue.add(strReq);
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

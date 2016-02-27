package com.example.vidit.collnect;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends ActionBarActivity {
    Events current_event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DetailFragment dt = new DetailFragment();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.conainer, new DetailFragment()).commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
    public static class DetailFragment extends Fragment{
        private static final String Log_Tag = DetailFragment.class.getSimpleName();
        private  String event_string;
        private String title;
        private String venue;
        private String date;
        private String description;
        private static final String sharing_tag = "#collnect";
        public DetailFragment(){

        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.detail_fragment,container,false);
            Intent intent = getActivity().getIntent();
            if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT)){
                event_string = intent.getStringExtra(Intent.EXTRA_TEXT);
                //list = new ArrayList<>();
                String[] array = event_string.split(",");
                title = array[0];
                description = array[1];
                date = array[2];
                venue = array[3];
                ((TextView)rootView.findViewById(R.id.title_text)).setText(title);
                ((TextView)rootView.findViewById(R.id.venue_text)).setText(venue);
                ((TextView)rootView.findViewById(R.id.date_text)).setText(date);
                ((TextView)rootView.findViewById(R.id.description_text)).setText(description);
            }
            return rootView;
        }
    }
}

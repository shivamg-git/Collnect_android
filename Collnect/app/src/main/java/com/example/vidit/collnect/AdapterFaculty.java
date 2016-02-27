package com.example.vidit.collnect;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.vidit.collnect.animation.Animutil;
import com.example.vidit.collnect.network.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by vidit on 01-12-2015.
 */
public class AdapterFaculty extends RecyclerView.Adapter<AdapterFaculty.ViewHolderEvents>{
    private LayoutInflater layoutInflater;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private ArrayList<FacultyDetail> listEvents = new ArrayList<>();
    static ArrayList<FacultyDetail> listEvents1 = new ArrayList<>();
    private int mPreviousPosition = 0;
    public AdapterFaculty(Context context){

        layoutInflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getmInstance();
        imageLoader = volleySingleton.getImageLoader();
    }
    public void setEventList(ArrayList<FacultyDetail> listEvents){
        this.listEvents = listEvents;
        this.listEvents1 = listEvents;
        notifyItemRangeChanged(0,listEvents.size());
    }
    @Override
    public ViewHolderEvents onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.custom_row_tab2,viewGroup,false);
        ViewHolderEvents viewHolderEvents = new ViewHolderEvents(view);
        return viewHolderEvents;
    }

    @Override
    public void onBindViewHolder(final ViewHolderEvents viewHolderEvents, int i) {
        FacultyDetail current_event = listEvents.get(i);
        viewHolderEvents.title_text.setText(current_event.getName());
        viewHolderEvents.rank.setText(current_event.getRank());
        String urlThumbnail = current_event.getUrlThumbnail();
        if(urlThumbnail != null){
            imageLoader.get(urlThumbnail,new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                    viewHolderEvents.thumbnail.setImageBitmap(imageContainer.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
        }
        if(i > mPreviousPosition){
            Animutil.animateSunblind(viewHolderEvents, true);
        }
        else{
            Animutil.animateSunblind(viewHolderEvents,false);
        }
        mPreviousPosition = i;
        Animutil.animate(viewHolderEvents);
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }

    public static class ViewHolderEvents extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title_text;
        TextView rank;
        ImageView thumbnail;
        public ViewHolderEvents(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            title_text = (TextView) itemView.findViewById(R.id.eventTitle);
            rank = (TextView) itemView.findViewById(R.id.eventDate);
            thumbnail = (ImageView) itemView.findViewById(R.id.eventThumbnail);
        }

        @Override
        public void onClick(View v) {
            Log.d(MyAdapter.ViewHolder.class.getSimpleName(), "position" + getPosition());
            String prof_uri = listEvents1.get(getPosition()).getProf_uri();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(prof_uri));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            v.getContext().startActivity(intent);
            //startActivity(intent);
            //Toast.makeText(v.getContext(), "position = " + getPosition(), Toast.LENGTH_LONG).show();
        }
    }
}

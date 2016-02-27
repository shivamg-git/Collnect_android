package com.example.vidit.collnect;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.example.vidit.collnect.animation.Animutil;
import com.example.vidit.collnect.network.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by vidit on 06-10-2015.
 */
public class AdapterGDG extends RecyclerView.Adapter<AdapterGDG.ViewHolderEvents> {
   private LayoutInflater layoutInflater;

    private ArrayList<Events> listEvents = new ArrayList<>();
    static ArrayList<Events> listEvents1 = new ArrayList<>();
    private int mPreviousPosition = 0;
   public AdapterGDG(Context context){
        layoutInflater = LayoutInflater.from(context);
   }
   public void setEventList(ArrayList<Events> listEvents){
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
    public void onBindViewHolder(ViewHolderEvents viewHolderEvents, int i) {
        Events current_event = listEvents.get(i);
        viewHolderEvents.title_text.setText(current_event.getTitle());
        viewHolderEvents.created_at_text.setText(current_event.getCreated_at());
        if(i > mPreviousPosition){
            Animutil.animateSunblind(viewHolderEvents,true);
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
         TextView created_at_text;

        public ViewHolderEvents(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            title_text = (TextView) itemView.findViewById(R.id.eventTitle);
            created_at_text = (TextView) itemView.findViewById(R.id.eventDate);

        }

        @Override
        public void onClick(View v) {
            Log.d(MyAdapter.ViewHolder.class.getSimpleName(),"position" + getPosition());
            String eventDetails = listEvents1.get(getPosition()).toString();
            Intent intent = new Intent(v.getContext(),DetailActivity.class).putExtra(Intent.EXTRA_TEXT,eventDetails);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            v.getContext().startActivity(intent);
            //startActivity(intent);
            //Toast.makeText(v.getContext(), "position = " + getPosition(), Toast.LENGTH_LONG).show();
        }
    }
}

package com.androidapk.diakonetapps.networkmarketing;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.androidapk.diakonetapps.networkmarketing.database.Notes;

import java.util.ArrayList;
import java.util.List;

public class TopicsAdapter extends BaseAdapter implements Filterable {

    Context context;
    private List<Notes> topicList;
    private List<Notes> orig;

    public TopicsAdapter(Context context, List<Notes> topicList) {
        this.context = context;
        this.topicList = topicList;
    }

    @Override
    public int getCount() {
        return topicList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             view = inflater.inflate(R.layout.topics_item, null, true);
        }

        TextView txtTopic;
        TextView txtDesc;

        txtTopic = view.findViewById(R.id.lesson_topic);
        txtDesc = view.findViewById(R.id.lesson_desc);

        txtTopic.setText(topicList.get(i).getTopic());
        txtDesc.setText(topicList.get(i).getNote());

        return view;
    }

    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<Notes> results = new ArrayList<>();

                if (orig == null){
                    orig = topicList;
                }

                if (constraint != null){
                    if (orig != null && orig.size() > 0){
                        for(final Notes n: orig){
                            if(n.getTopic().toLowerCase()
                            .contains(constraint.toString())
                                    || n.getNote().toLowerCase()
                            .contains(constraint.toString())){
                                results.add(n);
                            }
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                topicList = (List<Notes>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}

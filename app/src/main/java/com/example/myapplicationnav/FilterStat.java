package com.example.myapplicationnav;

import android.widget.Filter;

import java.util.ArrayList;

public class   FilterStat extends Filter {

    private Adapter adapter;
    public ArrayList<Model> filterList;

    public FilterStat(Adapter adapter, ArrayList<Model> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {

        FilterResults results = new FilterResults();
        ///cheak constraint validity
        if (charSequence != null && charSequence.length() > 0)
        {
            charSequence = charSequence.toString().toUpperCase();
            ArrayList<Model> filterModels = new ArrayList<>();

            for (int i=0; i<filterList.size(); i++)
            {
                if (filterModels.get(i).getCountry().toUpperCase().contains(charSequence)){
                    filterModels.add(filterList.get(i));
                }
            }
            results.count = filterModels.size();
            results.values = filterList;
        }
        else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults results) {

     adapter.modelArrayList = (ArrayList<Model>) results.values;

     adapter.notifyDataSetChanged();
    }
}

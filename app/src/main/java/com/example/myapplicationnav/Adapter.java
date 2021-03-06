package com.example.myapplicationnav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {

    private Context context;
    public ArrayList<Model>modelArrayList,filterList;
    private FilterStat filter;

    public Adapter(Context context, ArrayList<Model> modelList) {
        this.context = context;
        modelArrayList = modelList;
        this.filterList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_row,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //get data
        Model model = modelArrayList.get(position);

        String country = model.getCountry();
        String totalConfirmed = model.getTotalConfirmed();
        String totalDeaths = model.getTotalDeaths();
        String totalRecovered = model.getTotalRecovered();
        String newConfirmed = model.getNewConfirmed();
        String newDeaths = model.getNewDeaths();
        String newRecovered = model.getNewRecovered();

        //set data

        holder.countryT.setText(country);
        holder.todayRecovered.setText(newRecovered);
        holder.todayDeaths.setText(newDeaths);
        holder.todayCasesTv.setText(newConfirmed);
        holder.recoveredTv.setText(totalRecovered);
        holder.deathsTv.setText(totalDeaths);
        holder.casesTv.setText(totalConfirmed);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    @Override
    public Filter getFilter() {

        if (filter == null)
        {
            filter = new FilterStat(this,filterList);
        }
        return filter;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView countryT,casesTv,todayCasesTv,todayDeaths,deathsTv,recoveredTv,todayRecovered;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryT = itemView.findViewById(R.id.countyNameTv);
            casesTv = itemView.findViewById(R.id.casesTv);
            todayCasesTv = itemView.findViewById(R.id.todayCaseTv);
            deathsTv = itemView.findViewById(R.id.deathsTv);
             todayDeaths = itemView.findViewById(R.id.todayDeathsTv);
             recoveredTv = itemView.findViewById(R.id.recoveredTv);
             todayRecovered = itemView.findViewById(R.id.todayRecovered);

        }
    }
}

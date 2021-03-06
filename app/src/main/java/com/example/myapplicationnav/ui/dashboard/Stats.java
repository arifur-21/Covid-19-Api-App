package com.example.myapplicationnav.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplicationnav.Adapter;
import com.example.myapplicationnav.Model;
import com.example.myapplicationnav.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Stats extends Fragment {
    private static final String STATS_URL = "https://api.covid19api.com/summary";

private Context context;
private ProgressBar progressBar;
private EditText searchEt;
private ImageButton sortBtn;
private RecyclerView statsRv;

    ArrayList<Model> statArrayList;
    Adapter adapter;

    public Stats() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        progressBar = view.findViewById(R.id.progressBarId);
        searchEt = view.findViewById(R.id.searchEtId);
        sortBtn = view.findViewById(R.id.sortBtnId);
        statsRv = view.findViewById(R.id.tatsRvId);
        progressBar.setVisibility(View.GONE);

        loadStatsDate();
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //called as and when user type or remove letter
                try {

                    adapter.getFilter().filter(charSequence);
                }
                catch (Exception e)
                {
                    e.printStackTrace();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //popup menu
      final  PopupMenu popupMenu = new PopupMenu(context,sortBtn);
        popupMenu.getMenu().add(Menu.NONE, 0,0, "Ascending");
        popupMenu.getMenu().add(Menu.NONE, 1,1,"Descending");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == 0)
                {
                    Collections.sort(statArrayList, new sortStatCountryAsy());
                    adapter.notifyDataSetChanged();
                }
                else if (id == 1)
                {
                    Collections.sort(statArrayList, new sortStatCountryDes());
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        //sort
        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popupMenu.show();
            }
        });
        return view;
    }

    public void loadStatsDate(){
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, STATS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                handleRespons(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //failed message
                Toast.makeText(context, ""+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        // add request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private void handleRespons(String response) {

        statArrayList = new ArrayList<>();
        statArrayList.clear();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("Countries");

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("dd/MM/yyyy hh:mm a");
            Gson gson = gsonBuilder.create();

            //start getting data
            for (int i=0; i<jsonArray.length(); i++){
                Model model = gson.fromJson(jsonArray.getJSONObject(i).toString(), Model.class);
                statArrayList.add(model);
            }
            //setup adapter
            adapter = new Adapter(context, statArrayList);
            statsRv.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);



        }
        catch (Exception e)
        {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(context, ""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    //sort country as ascending order
    public class sortStatCountryAsy implements Comparator<Model>{

        @Override
        public int compare(Model letf, Model right) {
            return letf.getCountry().compareTo(right.getCountry());
        }
    }

    //sort country as descending order
    public class sortStatCountryDes implements Comparator<Model>{

        @Override
        public int compare(Model left, Model right) {
            return right.getCountry().compareTo(left.getCountry());
        }
    }

    /*@Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.searchId)
        {
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    adapter.getFilter().filter(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }*/
}
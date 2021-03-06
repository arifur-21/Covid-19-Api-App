package com.example.myapplicationnav.ui.home;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplicationnav.R;

import org.json.JSONObject;

public class HomeFragment extends Fragment {
    private static final String STATS_URL = "https://api.covid19api.com/summary";
    //Context for fragment
    Context context;

    private ProgressBar progressBar;
    private TextView totalCasesTv, newCasesTv, totalDesthsTv, totalRecoveredTv, newDeathstv, newRecovered;

    public HomeFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        totalCasesTv = view.findViewById(R.id.totalCasesTv);
        newCasesTv = view.findViewById(R.id.newCasesTv);
        totalDesthsTv = view.findViewById(R.id.totalDeathsTv);
        newDeathstv = view.findViewById(R.id.newDeathsTv);
        totalRecoveredTv = view.findViewById(R.id.totalRecoveredTv);
        newRecovered = view.findViewById(R.id.newRecoveredTv);

        progressBar.setVisibility(View.INVISIBLE);
        loadHomeData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadHomeData();
    }

    private void loadHomeData() {
        progressBar.setVisibility(View.VISIBLE);

//Json request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, STATS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //response received handle response

                handleResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //add requiest to queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private void handleResponse(String response) {

        try {

            ///since we know, our response is in Joson object os convert it to object
            JSONObject jsonObject = new JSONObject(response);
            JSONObject globalJo = jsonObject.getJSONObject("Global");
            //get data from it
            String newConfirmed = globalJo.getString("NewConfirmed");
            String totalConfirmed = globalJo.getString("TotalConfirmed");
            String newDeaths = globalJo.getString("NewDeaths");
            String totaldeaths = globalJo.getString("TotalDeaths");
            String newRecovereds = globalJo.getString("NewRecovered");
            String totalRecovered = globalJo.getString("TotalRecovered");

            //adding
            totalCasesTv.setText(totalConfirmed);
            newCasesTv.setText(newConfirmed);
            totalDesthsTv.setText(totaldeaths);
            newDeathstv.setText(newDeaths);
            totalRecoveredTv.setText(totalRecovered);
            newRecovered.setText(newRecovereds);

            progressBar.setVisibility(View.GONE);



        }
        catch (Exception e) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}

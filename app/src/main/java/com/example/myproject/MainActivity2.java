package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Mlm> refList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Employee list");
        prepairData();//default values
        getRewardData();
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),EmployeeAllDetails.class);
                startActivity(i);
            }
        });
    }
    public void prepairData() {
        Mlm je = new Mlm("", "");
        refList.add(je);


        recyclerView.setAdapter(new EmployeeListsAdapter(this, recyclerView, refList));
    }
    private void getRewardData() {
        HashMap<String,String> map = new HashMap<String,String>();

        VolleyLog.getResponse(this, Config.employeelist, map, new VolleyLog.VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                showRefJSON(response);
            }
        });
    }
    private void showRefJSON(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject data = jsonObject.getJSONObject("result");

            JSONArray result = null;
            if (data.getString("response").equals("201")) {
                result = data.getJSONArray("data");

                recyclerView.setVisibility(View.VISIBLE);
                refList.clear();

                for (int i = 0; i < result.length(); i++) {
                    JSONObject eve = result.getJSONObject(i);
                    Mlm mlm = new Mlm();

                    mlm.setName(eve.getString("name"));
                    mlm.setEmail(eve.getString("email"));



                    refList.add(mlm);

                }
                recyclerView.setAdapter(new EmployeeListsAdapter(this, recyclerView,refList));

            }
            else {
                recyclerView.setVisibility(View.GONE);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
}

}
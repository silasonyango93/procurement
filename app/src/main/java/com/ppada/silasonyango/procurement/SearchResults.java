package com.ppada.silasonyango.procurement;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchResults extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rView;
    DatabaseHelper myDb;
    public static Context context,mcontext;
    String UserId,BloodGroup;


    private GridLayoutManager lLayout;
    private ArrayList<String> FullNames;
    private ArrayList<String> Cities;
    private ArrayList<String> ids;
    private ArrayList<String> urls;
    private ArrayList<String> Periods;

    public static final String TAG_FULL_NAME = "Topic";
    public static final String TAG_CITY = "DatePosted";
    public static final String TAG_ID = "NewsId";
    public static final String TAG_URL = "NewsImageUrl";
    public static final String TAG_PERIOD = "Period";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        mcontext=SearchResults.this;



        myDb = new DatabaseHelper(getBaseContext());
      //  getSQLiteData();

        FullNames = new ArrayList<>();
        Cities = new ArrayList<>();
        ids = new ArrayList<>();
        urls = new ArrayList<>();
        Periods = new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        lLayout = new GridLayoutManager(getBaseContext(), 1);

        rView = (RecyclerView) findViewById(R.id.recycler_view1);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_container);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FullNames.clear();
                Cities.clear();
                ids.clear();
                urls.clear();
                Periods.clear();
                boolean net= isNetworkConnected();
                if(net!=true)
                {showMessage("No internet connectivity", "Check your internet connectivity and try again.");}
                else{
                    getData(BloodGroup);

                    // getData();
                    }
                swipeRefreshLayout.setRefreshing(false);

            }
        });


        boolean net= isNetworkConnected();
        if(net!=true)
        {showMessage("No internet connectivity", "Check your internet connectivity and try again.");}
        else{getData(BloodGroup);}




    }


    public void getSQLiteData() {

        Cursor res = myDb.getAllData();

        if (res.getCount() == 0) {
            //Show message
            showMessage("Error", "No data found Silas");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("dbID : " + res.getString(0) + "\n");
            buffer.append("id : " + res.getString(1) + "\n");
            buffer.append("Name : " + res.getString(2) + "\n");
            buffer.append("Email : " + res.getString(3) + "\n\n");

            UserId=res.getString(1);
        }

        //Show all data

        // showMessage("Data", buffer.toString());


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        // builder.setView(R.layout.activity_main);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();}


    private void getData(final String BloodGroup){
        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...","Fetching data...",false,false);
        loading.setCancelable(true);
        //Creating a json array request to get the json from our api

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Config.get_feeds, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                loading.dismiss();
                Log.d("yaya", s);


                //Displaying our grid
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray array= object.getJSONArray("result");
                    JSONObject ob = array.getJSONObject(0);

                    String data = ob.getString("data");
                    String zz="absent";
                    if(data.equals(zz))
                    {
                        showMessage("No news/Event","There are currently no news/events posted yet");
                    }
                    else {showGrid(array);}



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("ggg", volleyError.toString());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();

               // String BloodGroup="A-";
               // params.put("BloodGroup",BloodGroup);


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding our request to the queue
        requestQueue.add(stringRequest);
    }


    private void showGrid(JSONArray jsonArray){
        //Looping through all the elements of json array

        for(int i = 0; i<jsonArray.length(); i++){
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);


                //getting image url and title from json object
                FullNames.add(obj.getString(TAG_FULL_NAME));

                Cities.add(obj.getString(TAG_CITY));
                ids.add(obj.getString(TAG_ID));

                urls.add(obj.getString(TAG_URL));
                Periods.add(obj.getString(TAG_PERIOD));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // showMessage("Errorr", this.images.get(0));
        RecyclerviewAdapterSearch rcAdapter = new RecyclerviewAdapterSearch(getBaseContext(),FullNames,Cities,ids,urls,Periods);
        rView.setAdapter(rcAdapter);
        //Creating GridViewAdapter Object
      /*  GridViewAdapter gridViewAdapter = new GridViewAdapter(this,images,names);

        //Adding adapter to gridview
        gridView.setAdapter(gridViewAdapter);*/

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

}

package com.ppada.silasonyango.procurement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class News extends AppCompatActivity {
    String NewsId;

    AlertDialog.Builder builder;


    NetworkImageView largeImage;
    public String LargeImageUrl;
    public ImageLoader imageLoader, imageLoader1;


    TextView tvFullName,tvTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        NewsId=getIntent().getStringExtra("NewsId");

        largeImage = (NetworkImageView)findViewById(R.id.large_render);


        tvFullName=(TextView)findViewById(R.id.tv_full_names);
         tvTopic=(TextView)findViewById(R.id.tete);
        getLargeImage(largeImage,tvFullName,tvTopic);
    }

    public void getLargeImage(final NetworkImageView largeImage, final TextView tvFullName,final TextView tvTopic){
        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...","Fetching data...",false,false);
        loading.setCancelable(true);
        //Creating a json array request to get the json from our api

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Config.get_specific_feed, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                loading.dismiss();
                Log.d("responce", s);

                //Displaying our grid
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray array= object.getJSONArray("result");


                    inflateLargeImage(array,largeImage,tvFullName,tvTopic);


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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("NewsId",NewsId);

                return stringMap;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }



    public void inflateLargeImage(JSONArray jsonArray, NetworkImageView largeImage, TextView tvFullName, TextView tvTopic) {
        //Looping through all the elements of json array
        String ProductMake= new String();
        for(int i = 0; i<jsonArray.length(); i++){
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);


                LargeImageUrl= obj.getString("NewsImageUrl");
                tvFullName.setText(obj.getString("Content"));

                tvTopic.setText(obj.getString("Topic"));

                //renderLargeImage(context,v);



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // showMessage("url",ImageUrl);
        imageLoader = CustomVolleyRequest.getInstance(this).getImageLoader();
        imageLoader.get(LargeImageUrl, ImageLoader.getImageListener(largeImage, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        largeImage.setImageUrl(LargeImageUrl,imageLoader);





    }

}

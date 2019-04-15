package com.ppada.silasonyango.procurement;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
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

/**
 * Created by SilasOnyango on 9/9/2017.
 */
public class RecyclerViewHoldersSearch extends RecyclerView.ViewHolder implements View.OnClickListener {

    AlertDialog.Builder builder;

    public Context mContext;
    CheckBox checkBook, checkBook1;
    NetworkImageView largeImage;
    public String UserId,BookedId,LargeImageUrl;
    public ImageLoader imageLoader, imageLoader1;
    public Context bContext;
    public NetworkImageView ProfilePhoto;
    TextView tvName,tvCity,tvPostedDate;
    LayoutInflater inflater, inflater1;

    public RecyclerViewHoldersSearch(View itemView) {
        super(itemView);
        mContext = SearchResults.mcontext;
        inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        inflater1 = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

        itemView.setOnClickListener(this);

        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        tvCity = (TextView) itemView.findViewById(R.id.tv_city);
        tvPostedDate = (TextView) itemView.findViewById(R.id.tv_donation_date);
        ProfilePhoto = (NetworkImageView) itemView.findViewById(R.id.person_photo);
    }

    @Override
    public void onClick(View view) {
        View v = inflater.inflate(R.layout.donor_detailed_info,null);
        NetworkImageView largeImage = (NetworkImageView) v.findViewById(R.id.large_render);


        TextView tvFullName=(TextView)v.findViewById(R.id.tv_full_names);
        TextView tvTopic=(TextView)v.findViewById(R.id.tete);









        bContext=view.getContext();
        boolean net= isNetworkConnected();
        if(net!=true)
        {showMessage("No internet connectivity", "Check your internet connectivity and try again.");}
        else{getLargeImage(view.getContext(),largeImage,tvFullName,v,tvTopic);}


        //Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void renderLargeImage(final Context mContext, View v) {
        builder = new AlertDialog.Builder(mContext);

        builder.setView(v);
        final AlertDialog alertDialog = builder.create();


        builder.setCancelable(true);
        // builder.setTitle("Submit verification code");

        //editText.setText("test label");


        alertDialog.show();
    }



    public void getLargeImage(final Context context, final NetworkImageView largeImage, final TextView tvFullName, final View v, final TextView tvTopic){
        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(context, "Please wait...","Fetching data...",false,false);
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


                    inflateLargeImage(context,v,array,largeImage,tvFullName,tvTopic);


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
                stringMap.put("NewsId",BookedId);

                return stringMap;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
    }



    public void inflateLargeImage(Context context,View v,JSONArray jsonArray, NetworkImageView largeImage, TextView tvFullName, TextView tvTopic) {
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

                renderLargeImage(context,v);



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // showMessage("url",ImageUrl);
        imageLoader = CustomVolleyRequest.getInstance(mContext).getImageLoader();
        imageLoader.get(LargeImageUrl, ImageLoader.getImageListener(largeImage, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        largeImage.setImageUrl(LargeImageUrl,imageLoader);





    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bContext);
        builder.setCancelable(true);
        // builder.setView(R.layout.activity_main);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();}

    private void bookProduct(Context mContext){
        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(mContext, "Please wait...","Fetching data...",false,false);

        //Creating a json array request to get the json from our api

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Config.tag_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                loading.dismiss();
                Log.d("responce", s);

                //Displaying our grid

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
                stringMap.put("BookedId",BookedId);
                stringMap.put("id",UserId);

                //Log.d("pupu", "pupu:"+ProductId);

                return stringMap;
            }
        };
        Volley.newRequestQueue(mContext).add(stringRequest);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)bContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }



}
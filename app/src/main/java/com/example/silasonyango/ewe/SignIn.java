package com.example.silasonyango.ewe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2/20/2016.
 */
public class SignIn extends AppCompatActivity implements View.OnClickListener {
    EditText et_email, et_password;
    SessionManager sessionManager;
    ProgressDialog dialog;
    private String email;
    private String password;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(getBaseContext());
        setContentView(R.layout.login);
        myDb = new DatabaseHelper(this);
        findViewById(R.id.btn_submit_login).setOnClickListener(this);
        dialog = new ProgressDialog(this);
        dialog.setCancelable(true);
        et_email = (EditText) findViewById(R.id.et_emailLogin);
        et_password = (EditText) findViewById(R.id.et_passwordLogin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit_login:
                if (checkEmpty()) {
                    SignIn(email, password);
                }
                break;

            // startActivity(in);
        }
    }

    private boolean checkEmpty() {
        email = et_email.getText().toString();
        password = et_password.getText().toString();
        et_email.setError(null);
        et_password.setError(null);
        if (email.isEmpty()) {
            et_email.setError(getString(R.string.error_email_required));
            et_email.requestFocus();
            return false;
        } else if (password.isEmpty()) {
            et_password.setError(getString(R.string.error_password_required));
            et_password.requestFocus();
            return false;
        } else {
            return true;

        }

    }

    private void SignIn(final String email, final String password) {
        String tag_string_req = "req_login";
        dialog.setMessage("Signing in ...");
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jObj = null;
                try {
                    jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (error) {//When response returns error
                        String errorMessage = jObj.getString("error_msg");
                        Toast.makeText(getBaseContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        hideDialog();
                    } else {
                        String id = jObj.getString("id");
                        String name = jObj.getString("name");
                        String email = jObj.getString("email");
                        //String address = jObj.getString("Address");
                        String Key="User";
                        updateCredentials(id,name,email,Key);
                        Intent intent = new Intent(
                                getBaseContext(),MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("BookMark","signin");
                        intent.putExtra("Topic","");
                        intent.putExtra("StartIndex","");
                        intent.putExtra("EndIndex","");
                        intent.putExtra("Name","");
                        startActivity(intent);
                        sessionManager.setLogin(true);
                        hideDialog();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    hideDialog();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        Mimi.getInstance().addToRequestQueue(stringRequest, tag_string_req);
    }

    private void hideDialog() {
        if (dialog.isShowing())
            dialog.dismiss();
    }

    public void updateCredentials(String id,String name,String email,String Key) {

        Cursor res = myDb.getAllCredentials();

        if (res.getCount() == 0) {
            myDb.insertCredentials(id,name,email,Key);
            return;
        }else{myDb.updateCredentials(id,name,email,Key);}



        }


    }




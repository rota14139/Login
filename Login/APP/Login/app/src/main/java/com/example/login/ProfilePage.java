package com.example.login;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ProfilePage extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

        //the url where we need to send the request
        String url;

        //the parameters
        HashMap<String, String> params;

        //the request code to define whether it is a GET or POST
        int requestCode;

        //constructor to initialize values
        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //this method will give the response from the request
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                if (object.getString("error") == "false") {
                    textViewErrorMessage.setTextColor(Color.GREEN);
                    if (isDeleting)
                        finish();
                } else {
                    textViewErrorMessage.setTextColor(Color.RED);
                }
                textViewErrorMessage.setText(object.getString("message"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //the network operation will be performed in background
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }
    }

    //defining views
    EditText editTextOldPassowrd, editTextNewPassowrd;
    TextView textViewErrorMessage, textViewUsername, textViewSuccessMessage;
    Button button_changePassowrd, button_deleteUser, button_esci;
    boolean isDeleting = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        editTextOldPassowrd = (EditText) findViewById(R.id.editText_oldPassword);
        editTextNewPassowrd = (EditText) findViewById(R.id.editText_newPassword);
        textViewErrorMessage = (TextView) findViewById(R.id.textView_errorMessage);
        textViewUsername = (TextView) findViewById(R.id.textView_username);
        textViewSuccessMessage = (TextView) findViewById(R.id.textView_message);
        button_changePassowrd = (Button) findViewById(R.id.button_changePassword);
        button_deleteUser = (Button) findViewById(R.id.button_deleteUser);
        button_esci = (Button) findViewById(R.id.button_esci);

        textViewSuccessMessage.setText(GlobalVariable.responseMessage);
        textViewUsername.setText(GlobalVariable.username);
        button_changePassowrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = textViewUsername.getText().toString().trim();
                String oldPassword = editTextOldPassowrd.getText().toString().trim();
                String newPassword = editTextNewPassowrd.getText().toString().trim();
                isDeleting = false;

                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("oldPassword", oldPassword);
                params.put("newPassword", newPassword);
                PerformNetworkRequest request = new PerformNetworkRequest(GlobalVariable.URL_CHANGEPASSWORD, params, CODE_POST_REQUEST);
                request.execute();
            }
        });

        button_deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = textViewUsername.getText().toString().trim();
                String password = editTextOldPassowrd.getText().toString().trim();
                isDeleting = true;

                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                PerformNetworkRequest request = new PerformNetworkRequest(GlobalVariable.URL_DELETEUSER, params, CODE_POST_REQUEST);
                request.execute();
            }
        });
        button_esci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
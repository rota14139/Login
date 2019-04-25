package com.example.login;

import android.content.DialogInterface;
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

import static com.example.login.GlobalVariable.username;


public class LoginPage extends AppCompatActivity {

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
                GlobalVariable.responseMessage = object.getString("message");
                if (object.getString("error") == "false") {
                    GlobalVariable.username = editTextUsername.getText().toString().trim();
                    startActivity(new Intent(LoginPage.this, ProfilePage.class));
                } else {
                    textViewErrorMessage.setText(GlobalVariable.responseMessage);
                    GlobalVariable.responseError = false;
                }
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
    EditText editTextUsername, editTextPassowrd;
    TextView textViewErrorMessage;
    Button button_login, button_signUp;
    boolean isUpdating = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.editText_username);
        editTextPassowrd = (EditText) findViewById(R.id.editText_password);
        textViewErrorMessage = (TextView) findViewById(R.id.textView_errorMessage);
        button_login = (Button) findViewById(R.id.button_login);
        button_signUp = (Button) findViewById(R.id.button_signUp);


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassowrd.getText().toString().trim();
                 editTextPassowrd.setText("");

                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                PerformNetworkRequest request = new PerformNetworkRequest(GlobalVariable.URL_LOGIN, params, CODE_POST_REQUEST);
                request.execute();
            }
        });

        button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassowrd.getText().toString().trim();
                editTextPassowrd.setText("");

                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                PerformNetworkRequest request = new PerformNetworkRequest(GlobalVariable.URL_SIGNUP, params, CODE_POST_REQUEST);
                request.execute();
            }
        });
    }
}
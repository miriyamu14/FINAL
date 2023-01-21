package com.example.carmonitor;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    Button button;
    EditText Name , Pass;
    String uname,cna,cnu,pa;
    DbHelper dbHelper;
    private static final String URL_PRODUCTS = "http://172.31.184.213/app/fetch.php";
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

            button = (Button) findViewById(R.id.login);
            Name = (EditText) findViewById(R.id.username);
            Pass = (EditText) findViewById(R.id.passt);
            builder = new AlertDialog.Builder(login.this);
        dbHelper = new DbHelper(this);
        Cursor  cursor = dbHelper.getData();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String name , pass ;
                    name =Name.getText().toString();
                    pass=Pass.getText().toString();
                    if(cursor.getCount() == 0){

                        if(name.equals(uname)&& pass.equals(pa)){
                            Intent intent= new Intent(login.this,controls.class);
                            intent.putExtra("carname",name);
                            intent.putExtra("carnumber",pass);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(login.this,MapsActivity.class);
                            intent.putExtra("carname",name);
                            intent.putExtra("carnumber",pass);
                            Name.setText("");
                            Pass.setText("");
                            startActivity(intent);
                        }
                        //Toast.makeText(login.this,"No entries Exists",Toast.LENGTH_LONG).show();
                    }
                    if (loginCheck(cursor,name,pass)) {
                        Intent intent = new Intent(login.this,MapsActivity.class);
                        intent.putExtra("carname",name);
                        intent.putExtra("carnumber",pass);
                        Name.setText("");
                        Pass.setText("");
                        startActivity(intent);
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                        Intent intent = new Intent(login.this,MapsActivity.class);
                        intent.putExtra("carname",name);
                        intent.putExtra("carnumber",pass);
                        Name.setText("");
                        Pass.setText("");
                        startActivity(intent);
                    }
                    dbHelper.close();
                   if(name.equals(uname)&& pass.equals(pa)){
                       Intent intent= new Intent(login.this,MapsActivity.class);
                       intent.putExtra("carname",name);
                       intent.putExtra("carnumber",pass);
                       Name.setText("");
                       Pass.setText("");
                       startActivity(intent);
                   }
                   else {
                       Intent intent = new Intent(login.this,MapsActivity.class);
                       intent.putExtra("carname",name);
                       intent.putExtra("carnumber",pass);
                       Name.setText("");
                       Pass.setText("");
                       startActivity(intent);
                   }
                }
            });

    }
    public boolean loginCheck(Cursor cursor, String emailCheck, String passCheck) {
        while (cursor.moveToNext()){
            if (cursor.getString(1).equals(emailCheck)) {
                if (cursor.getString(2).equals(passCheck)) {
                    uname= cursor.getString(1);
                    cna=cursor.getString(2);
                    cnu= cursor.getString(3);
                    pa=cursor.getString(4);
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    private void loadinfo() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                        uname= product.getString("name");
                                        cna=product.getString("carname");
                                       cnu= product.getString("carsim");
                                        pa=product.getString("pass");
                            }
                            ///coditionssss.
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
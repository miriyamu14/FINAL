package com.example.carmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class setup extends AppCompatActivity {

    Button button;
    EditText Name , Pass,Cname,Cnumber,Cpass;
    DbHelper dbHelper;
    String server_url = "http://172.31.184.213/app/dbconfig.php";
    AlertDialog.Builder builder;
    String name , pass,cname,cnumber,cpass ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        button = (Button) findViewById(R.id.submit);
        Name = (EditText) findViewById(R.id.username);
        Cname = (EditText) findViewById(R.id.carname);
        Cnumber = (EditText) findViewById(R.id.phone);
        Pass = (EditText) findViewById(R.id.passt);
        Cpass = (EditText) findViewById(R.id.passc);
        dbHelper = new DbHelper(this);
        builder = new AlertDialog.Builder(setup.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name =Name.getText().toString();
                cname =Cname.getText().toString();
                cnumber =Cnumber.getText().toString();
                pass=Pass.getText().toString();
                cpass=Cpass.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        insertlocal();
                        builder.setTitle("Server Response");
                        builder.setMessage("Response :"+response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(setup.this,login.class);
                                clear();
                                startActivity(i);

                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }
                }

                        , error -> {
                    insertlocal();
                    Toast.makeText(setup.this,"some error found .....",Toast.LENGTH_SHORT).show();
                    error.printStackTrace();


                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map <String,String> Params = new HashMap<String, String>();
                        Params.put("name",name);
                        Params.put("carname",cname);
                        Params.put("simnumber",cnumber);
                        Params.put("pass",pass);
                        return Params;

                    }
                };
                Mysingleton.getInstance(setup.this).addTorequestque(stringRequest);
            }
        });

    }

    private void clear() {
        Name.setText("");
        Pass.setText("");
        Cname.setText("");
        Cnumber.setText("");
        Cpass.setText("");
    }

    private void insertlocal() {
        boolean b =dbHelper.insetUserData(name,cname,cnumber,pass);
        if (b){
            Toast.makeText(setup.this,"Data inserted",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(setup.this,login.class);
            clear();
            startActivity(i);
       }else {
            Toast.makeText(setup.this,"Failed To insert Data",Toast.LENGTH_SHORT).show();
        }
    }
}
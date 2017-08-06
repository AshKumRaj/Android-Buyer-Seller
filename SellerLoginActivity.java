package com.example.ashish.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SellerLoginActivity extends AppCompatActivity {
    DatabaseHelper dHelp = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        final EditText etUsername= (EditText) findViewById(R.id.etUsername);
        final EditText etPassword= (EditText) findViewById(R.id.etPassword);

        final Button bLogin= (Button) findViewById(R.id.bLogin);
        final Button bRegister= (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerSellerIntent = new Intent(SellerLoginActivity.this,SellerRegisterActivity.class);
                SellerLoginActivity.this.startActivity(registerSellerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(etUsername.getText().toString().isEmpty()){
                    etUsername.setError("UserName should not be blank!");
                }
                if(etPassword.getText().toString().isEmpty()){
                    etPassword.setError("Password should not be blank!");
                }
                if(!(etUsername.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty())) {
                    if(dHelp.verifyThisUser(etUsername.getText().toString(),etPassword.getText().toString(),"Seller")){
                        Intent productSellerIntent = new Intent(SellerLoginActivity.this, SellerProductsActivity.class);
                        SellerLoginActivity.this.startActivity(productSellerIntent);
                    }
                    else{
                        Toast succ = Toast.makeText(SellerLoginActivity.this,"Login not successful. Please try again.", Toast.LENGTH_SHORT);
                        succ.show();
                    }

                }
            }
        });

    }
}

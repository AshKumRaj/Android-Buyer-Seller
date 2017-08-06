package com.example.ashish.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyerLoginActivity extends AppCompatActivity {
    DatabaseHelper dHelp = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_login);

        final EditText etUserName= (EditText) findViewById(R.id.etUsernameBuy);
        final EditText etPwd= (EditText) findViewById(R.id.etPasswordBuy);

        final Button bLoginBuy= (Button) findViewById(R.id.bLoginBuy);
        final Button bRegisterBuy= (Button) findViewById(R.id.bRegisterBuy);

        bRegisterBuy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerBuyerIntent = new Intent(BuyerLoginActivity.this,BuyerRegisterActivity.class);
                BuyerLoginActivity.this.startActivity(registerBuyerIntent);
            }
        });

        bLoginBuy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(etUserName.getText().toString().isEmpty()){
                    etUserName.setError("UserName should not be blank!");
                }
                if(etPwd.getText().toString().isEmpty()){
                    etPwd.setError("Password should not be blank!");
                }
                if(!(etUserName.getText().toString().isEmpty() || etPwd.getText().toString().isEmpty())) {
                    if(dHelp.verifyThisUser(etUserName.getText().toString(),etPwd.getText().toString(),"Buyer")){
                        Intent productBuyerIntent = new Intent(BuyerLoginActivity.this, BuyerProductsActivity.class);
                        BuyerLoginActivity.this.startActivity(productBuyerIntent);
                    }
                    else{
                        Toast succ = Toast.makeText(BuyerLoginActivity.this,"Login not successful. Please try again.", Toast.LENGTH_SHORT);
                        succ.show();
                    }

                }
            }
        });

    }


}

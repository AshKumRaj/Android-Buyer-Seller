package com.example.ashish.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyerRegisterActivity extends AppCompatActivity {
    DatabaseHelper dHelp = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_register);

        final EditText etUsernameBuy1= (EditText) findViewById(R.id.etUsernameBuy1);
        final EditText etPasswordBuy1= (EditText) findViewById(R.id.etPasswordBuy1);

        final Button bRegisterBuy1= (Button) findViewById(R.id.bRegisterBuy1);

        bRegisterBuy1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(etUsernameBuy1.getText().toString().isEmpty()){
                    etUsernameBuy1.setError("UserName should not be blank!");
                }
                if(etPasswordBuy1.getText().toString().isEmpty()){
                    etPasswordBuy1.setError("Password should not be blank!");
                }
                if(!(etUsernameBuy1.getText().toString().isEmpty() || etPasswordBuy1.getText().toString().isEmpty())) {
                    UsersAndProducts usr = new UsersAndProducts();
                    usr.setUserName(etUsernameBuy1.getText().toString());
                    usr.setUserPassword(etPasswordBuy1.getText().toString());
                    usr.setUserType("Buyer");
                    if(dHelp.insertThisRecord(usr)){
                        Toast succ = Toast.makeText(BuyerRegisterActivity.this,"You are now added as buyer!!", Toast.LENGTH_SHORT);
                        succ.show();
                    }
                    else{
                        Toast succ = Toast.makeText(BuyerRegisterActivity.this,"Buyer Registration not successful! Try different" +
                                " username", Toast.LENGTH_SHORT);
                        succ.show();
                    }


                    Intent mainIntent = new Intent(BuyerRegisterActivity.this, MainActivity.class);
                    BuyerRegisterActivity.this.startActivity(mainIntent);
                }
            }
        });

    }
}

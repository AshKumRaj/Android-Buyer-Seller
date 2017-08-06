package com.example.ashish.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SellerRegisterActivity extends AppCompatActivity {
    DatabaseHelper dHelp = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_register);

        final EditText etUsernameSell1= (EditText) findViewById(R.id.etUsernameSell1);
        final EditText etPasswordSell1= (EditText) findViewById(R.id.etPasswordSell1);

        final Button bRegisterSell1= (Button) findViewById(R.id.bRegisterSell1);

        bRegisterSell1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(etUsernameSell1.getText().toString().isEmpty()){
                    etUsernameSell1.setError("UserName should not be blank!");
                }
                if(etPasswordSell1.getText().toString().isEmpty()){
                    etPasswordSell1.setError("Password should not be blank!");
                }
                if(!(etUsernameSell1.getText().toString().isEmpty() || etPasswordSell1.getText().toString().isEmpty())) {
                    UsersAndProducts usr = new UsersAndProducts();
                    usr.setUserName(etUsernameSell1.getText().toString());
                    usr.setUserPassword(etPasswordSell1.getText().toString());
                    usr.setUserType("Seller");
                    if(dHelp.insertThisRecord(usr)){
                        Toast succ = Toast.makeText(SellerRegisterActivity.this,"You are now added as seller!!", Toast.LENGTH_SHORT);
                        succ.show();
                    }
                    else{
                        Toast succ = Toast.makeText(SellerRegisterActivity.this,"Seller Registration not successful! Try different" +
                                " username", Toast.LENGTH_SHORT);
                        succ.show();
                    }


                    Intent mainIntent = new Intent(SellerRegisterActivity.this, MainActivity.class);
                    SellerRegisterActivity.this.startActivity(mainIntent);
                }
            }
        });

    }
}

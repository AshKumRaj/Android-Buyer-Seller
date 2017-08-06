package com.example.ashish.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dHelp = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bSeller= (Button) findViewById(R.id.bSeller);
        final Button bBuyer= (Button) findViewById(R.id.bBuyer);

        bBuyer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //dHelp.clearCurrentUser();
                Intent loginBuyerIntent = new Intent(MainActivity.this,BuyerLoginActivity.class);
                MainActivity.this.startActivity(loginBuyerIntent);
            }
        });

        bSeller.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent loginSellerIntent = new Intent(MainActivity.this,SellerLoginActivity.class);
                MainActivity.this.startActivity(loginSellerIntent);
            }
        });
    }


}

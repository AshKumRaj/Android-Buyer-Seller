package com.example.ashish.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNumberofProductsActivity extends AppCompatActivity {
    DatabaseHelper dHelp = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_numberof_products);
        final EditText etnumProds= (EditText) findViewById(R.id.etnumProds);

        final Button bAddProd= (Button) findViewById(R.id.bAddProd);
        final Button bBackSell= (Button) findViewById(R.id.bBackSell);

        bAddProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numProd = etnumProds.getText().length();
                if(numProd!=0) {
                    Intent addProd = new Intent(AddNumberofProductsActivity.this, AddProductsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("stuff", etnumProds.getText().toString());
                    addProd.putExtras(bundle);
                    AddNumberofProductsActivity.this.startActivity(addProd);
                }
                else
                {
                    etnumProds.setError("Enter number of products you want to add:");
                }

            }
        });

        bBackSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backProd = new Intent(AddNumberofProductsActivity.this, SellerProductsActivity.class);
                AddNumberofProductsActivity.this.startActivity(backProd);
            }
        });


    }
}

package com.example.ashish.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddProductsActivity extends AppCompatActivity {
    DatabaseHelper dHelp = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        Bundle bundle = getIntent().getExtras();
        String a = bundle.getString("stuff");
        final int numProds = Integer.parseInt(a);
        initTable(numProds);

        final Button bAdd= (Button) findViewById(R.id.bAdd);
        final Button bBack= (Button) findViewById(R.id.bBack);


        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add products in products table, display toast message that
                // numProds many products have been added and then redirect to seller product
                ArrayList<EditText> allEds = new ArrayList<EditText>();

                for (int i = 0; i < numProds; i++) {

                    EditText et = (EditText) findViewById(i);
                    allEds.add(et);
                }
                //Toast succ = Toast.makeText(AddProductsActivity.this,user, Toast.LENGTH_SHORT);
                //succ.show();
                boolean added = false;
                for( EditText e : allEds){
                    added = dHelp.addThisProduct(e.getText().toString());

                }
                if(added){
                    Toast succ = Toast.makeText(AddProductsActivity.this,numProds+" products added successfully!", Toast.LENGTH_SHORT);
                    succ.show();
                }

                Intent sellerHome = new Intent(AddProductsActivity.this, SellerProductsActivity.class);
                AddProductsActivity.this.startActivity(sellerHome);
            }
        });

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addProd = new Intent(AddProductsActivity.this, AddNumberofProductsActivity.class);
                AddProductsActivity.this.startActivity(addProd);
            }
        });

    }

    private void initTable(int numProds) {

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        stk.setOrientation(LinearLayout.HORIZONTAL);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText("Sl.No");
        tv0.setTextSize(15);
        tv0.setGravity(Gravity.LEFT);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText("Product");
        tv1.setTextSize(15);
        tv1.setGravity(Gravity.RIGHT);
        tbrow0.addView(tv1);
        stk.addView(tbrow0,new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        for(int i=0;i<numProds;i++){
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(""+(i+1));
            t1v.setGravity(Gravity.LEFT);
            tbrow.addView(t1v);
            EditText et1 = new EditText(this);
            et1.setHint("Product"+(i+1));
            et1.setGravity(Gravity.RIGHT);
            et1.setId(i);
            tbrow.addView(et1);
            stk.addView(tbrow,new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

        }
    }
}

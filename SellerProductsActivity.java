package com.example.ashish.loginregister;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SellerProductsActivity extends AppCompatActivity {
    DatabaseHelper dHelp = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_products);
        final int numP = 0;
        initTable();

        final Button bAddProd = (Button) findViewById(R.id.bAddProd);
        final Button bUpdate = (Button) findViewById(R.id.bUpdate);
        final Button bLogout = (Button) findViewById(R.id.bLogout);

        bAddProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddProdIntent = new Intent(SellerProductsActivity.this, AddNumberofProductsActivity.class);
                SellerProductsActivity.this.startActivity(AddProdIntent);
            }
        });

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dHelp.clearCurrentUser();
                Intent mainIntent = new Intent(SellerProductsActivity.this, MainActivity.class);
                SellerProductsActivity.this.startActivity(mainIntent);
            }
        });

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<EditText> allEds = new ArrayList<EditText>();
                int numProds = dHelp.getRowCountProdForSeller();

                for (int i = 0; i < numProds; i++) {

                    EditText et = (EditText) findViewById(i);
                    allEds.add(et);
                }
                //Toast succ = Toast.makeText(AddProductsActivity.this,user, Toast.LENGTH_SHORT);
                //succ.show();
                boolean added = false;
                dHelp.deleteAllProd();
                for( EditText e : allEds){

                    added = dHelp.addThisProduct(e.getText().toString());

                }
                if(added){
                    Toast succ = Toast.makeText(SellerProductsActivity.this,"Product(s) updated successfully!", Toast.LENGTH_SHORT);
                    succ.show();
                }
            }
        });
    }

    private void initTable() {
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
        tv1.setGravity(Gravity.CENTER);
        tbrow0.addView(tv1);
        stk.addView(tbrow0,new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        int numProds = dHelp.getRowCountProdForSeller();
        ArrayList<String> prods = dHelp.getAllRowsForForSeller("product");
        if(numProds !=0) {
            for (int i = 0; i < numProds; i++) {
                TableRow tbrow = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(String.valueOf(i+1));
                t1v.setGravity(Gravity.LEFT);
                tbrow.addView(t1v);
                EditText t2v = new EditText(this);
                t2v.setText(prods.get(i));
                t2v.setGravity(Gravity.RIGHT);
                t2v.setId(i);
                tbrow.addView(t2v);
                stk.addView(tbrow, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

            }
        }
        else
        {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("No products to show for now!");
            t1v.setGravity(Gravity.LEFT);
            tbrow.addView(t1v);
        }
    }

}

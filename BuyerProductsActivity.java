package com.example.ashish.loginregister;

import android.content.Intent;
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

import java.util.ArrayList;

public class BuyerProductsActivity extends AppCompatActivity {
    DatabaseHelper dHelp = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_products);
        initTable();

        final Button etLogOut = (Button) findViewById(R.id.logout);

        etLogOut.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dHelp.clearCurrentUser();
                Intent mainActivity = new Intent(BuyerProductsActivity.this,MainActivity.class);
                BuyerProductsActivity.this.startActivity(mainActivity);
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
        TextView tv2 = new TextView(this);
        tv2.setText("Seller");
        tv2.setTextSize(15);
        tv2.setGravity(Gravity.RIGHT);
        tbrow0.addView(tv2);
        stk.addView(tbrow0,new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        int numProds = dHelp.getRowCountProd();
        ArrayList<String> prods = dHelp.getAllRowsFor("product");
        ArrayList<String> sellers = dHelp.getAllRowsFor("seller");
        if(numProds !=0) {
            for (int i = 0; i < numProds; i++) {
                TableRow tbrow = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(String.valueOf(i+1));
                t1v.setGravity(Gravity.LEFT);
                tbrow.addView(t1v);
                TextView t2v = new TextView(this);
                t2v.setText(prods.get(i));
                t2v.setGravity(Gravity.LEFT);
                tbrow.addView(t2v);
                TextView t3v = new TextView(this);
                t3v.setText(sellers.get(i));
                t3v.setGravity(Gravity.RIGHT);
                tbrow.addView(t3v);
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

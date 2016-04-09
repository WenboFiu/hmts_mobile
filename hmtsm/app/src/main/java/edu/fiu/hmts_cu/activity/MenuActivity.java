package edu.fiu.hmts_cu.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import edu.fiu.hmts_cu.R;
import edu.fiu.hmts_cu.controller.MobileController;
import edu.fiu.hmts_cu.customcontrol.ProductListAdapter;

/**
 * Class for MenuActivity
 */
public class MenuActivity extends Activity {

    String userId = "";
    String phone = "";
    JSONArray products = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_menu);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbar_custom);

        TextView header = (TextView) findViewById(R.id.header_text);
        header.setText(R.string.ttile_menu);

        Button left = (Button) findViewById(R.id.header_left_btn);
        left.setBackgroundResource(R.drawable.back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });

        Button right = (Button) findViewById(R.id.header_right_btn);
        right.setBackgroundResource(R.drawable.cart);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewCart(v);
            }
        });

        products = getProducts();
        ListView productList = (ListView)findViewById(R.id.productList);
        productList.setAdapter(new ProductListAdapter(this, products));

        userId = getIntent().getStringExtra("userId");
        phone = getIntent().getStringExtra("phone");
    }

    private JSONArray getProducts() {
        JSONArray products = null;
        try {
            products = new Service().execute().get();
            for (int i = 0; i < products.length(); i++){
                products.getJSONObject(i).put("quantity", "0");
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Class for async task.
     */
    class Service extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected void onPreExecute() {

        }

        protected JSONArray doInBackground(Void... params) {
            return MobileController.displayMenu();
        }

        protected void onPostExecute() {

        }
    }

    /**
     * Logout.
     */
    public void logout(View view){
        MenuActivity.this.finish();
    }

    /**
     * Go to shopping cart.
     */
    public void viewCart(View view){
        JSONArray cartList = getProductinCart();

        if (cartList.length() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sorry! Shopping cart is empty");
            builder.setMessage("Please choose products you like.");
            builder.setCancelable(true);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return;
        }

        Intent cartIntent = new Intent(MenuActivity.this, CartActivity.class);
        cartIntent.putExtra("userId", userId);
        cartIntent.putExtra("phone", phone);
        cartIntent.putExtra("cartList", cartList.toString());
        startActivity(cartIntent);
    }

    /**
     * Get products in shopping cart.
     */
    public JSONArray getProductinCart(){
        JSONArray cartList = new JSONArray();
        ListView prodList = (ListView)findViewById(R.id.productList);

        for (int i = 0; i < prodList.getCount(); i++){
            View view = prodList.getAdapter().getView(i, null, prodList);
            String qty = ((TextView)view.findViewById(R.id.prodquatity)).getText().toString();

            if (!"0".equals(qty)){
                JSONObject cartItem = new JSONObject();
                try {
                    cartItem.put("productId", prodList.getItemIdAtPosition(i));
                    cartItem.put("name", ((TextView)view.findViewById(R.id.productname)).getText().toString());
                    cartItem.put("type", ((TextView)view.findViewById(R.id.producttype)).getText().toString());
                    cartItem.put("price", ((TextView)view.findViewById(R.id.productprice)).getText().toString());
                    cartItem.put("quantity", ((TextView)view.findViewById(R.id.prodquatity)).getText().toString());
                    cartItem.put("brief", ((TextView)view.findViewById(R.id.productbrief)).getText().toString());
                    cartList.put(cartItem);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return cartList;
    }
}

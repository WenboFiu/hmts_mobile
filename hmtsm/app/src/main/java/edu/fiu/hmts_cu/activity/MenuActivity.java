package edu.fiu.hmts_cu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import edu.fiu.hmts_cu.R;
import edu.fiu.hmts_cu.controller.MobileController;
import edu.fiu.hmts_cu.customcontrol.ProductListAdapter;

/**
 * Class for MenuActivity
 */
public class MenuActivity extends Activity {

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
                goCart(v);
            }
        });

        JSONArray products = getProducts();
        ListView productList = (ListView)findViewById(R.id.productList);
        productList.setAdapter(new ProductListAdapter(this, products));

        //ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.quantity, R.layout.spinner_item);
        //((Spinner)findViewById(R.id.productquatity)).setAdapter(adapter);
    }

    private JSONArray getProducts() {
        JSONArray products = null;
        try {
            products = new Service().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
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
     * Add a product into shopping cart.
     */
    public void AddProductCart(){

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
    public void goCart(View view){
        Intent cartIntent = new Intent(MenuActivity.this, CartActivity.class);
        startActivity(cartIntent);
    }
}

package edu.fiu.hmts_cu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.Object;

import edu.fiu.hmts_cu.R;
import edu.fiu.hmts_cu.customcontrol.CartListAdapter;

/**
 * Class for CartActivity
 */
public class CartActivity extends Activity {

    /**
     * The User id.
     */
    String userId = "";
    /**
     * The Phone.
     */
    String phone = "";
    /**
     * The Source.
     */
    String source = "";
    /**
     * The Ship addr.
     */
    String shipAddr = "";
    /**
     * The Ship city.
     */
    String shipCity = "";
    /**
     * The Notes.
     */
    String notes = "";
    /**
     * The Payment.
     */
    String payment = "";
    /**
     * The Cart array.
     */
    JSONArray cartArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_cart);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbar_custom);

        receiveDataFromLastActivity();

        configActionBar();

        getTotalAmount();
    }

    /**
     * Receive data from last activity.
     */
    private void receiveDataFromLastActivity() {
        userId = getIntent().getStringExtra("userId");
        phone = getIntent().getStringExtra("phone");
        source = getIntent().getStringExtra("source");

        try {
            String object = getIntent().getStringExtra("cartArray");
            if (object == null || "".equals(object))
                object = "[]";
            cartArray = new JSONArray(object);
            ListView cartList = (ListView) findViewById(R.id.cartList);
            cartList.setAdapter(new CartListAdapter(this, cartArray));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Config action bar.
     */
    private void configActionBar() {
        TextView header = (TextView) findViewById(R.id.header_text);
        header.setText("Shopping Cart");

        Button left = (Button) findViewById(R.id.header_left_btn);
        left.setBackgroundResource(R.drawable.back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMenu(v);
            }
        });

        Button right = (Button) findViewById(R.id.header_right_btn);
        right.setBackgroundResource(R.drawable.order);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewOrderInfo(v);
            }
        });
        if ("cardactivity".equals(source))
            right.setVisibility(View.INVISIBLE);
    }

    /**
     * Display menu.
     *
     * @param v the v
     */
    public void viewMenu(View v) {
        this.finish();
    }

    /**
     * Display a view to enter order info.
     *
     * @param v the v
     */
    public void viewOrderInfo(View v) {
        Intent orderIntent = new Intent(CartActivity.this, OrderInfoActivity.class);
        orderIntent.putExtra("userId", userId);
        orderIntent.putExtra("phone", phone);
        orderIntent.putExtra("shipaddr", shipAddr);
        orderIntent.putExtra("shipcity", shipCity);
        orderIntent.putExtra("notes", notes);
        orderIntent.putExtra("payment", payment);
        orderIntent.putExtra("cartList", cartArray.toString());
        startActivityForResult(orderIntent, 1);
    }

    /**
     * Get total amount.
     */
    private void getTotalAmount(){
        ListView cartList = (ListView)findViewById(R.id.cartList);
        TextView total = (TextView)findViewById(R.id.total);
        double amount = 0;
        for (int i = 0; i < cartArray.length(); i++) {
            View view = cartList.getAdapter().getView(i, null, cartList);
            String subTotalTmp = ((TextView) view.findViewById(R.id.carttotal)).getText().toString();
            double subTotal = Double.parseDouble(subTotalTmp);
            try {
                cartArray.getJSONObject(i).put("total", subTotal);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            amount += subTotal;
        }
        total.setText("TOTAL: $" + String.format("%.2f", amount) + " USD");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                shipAddr = data.getStringExtra("shipaddr");
                shipCity = data.getStringExtra("shipcity");
                notes = data.getStringExtra("notes");
                phone = data.getStringExtra("phone");
                payment = data.getStringExtra("payment");
            }
        }
    }
}

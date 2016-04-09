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

    String userId = "";
    String phone = "";
    JSONArray cartArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_cart);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbar_custom);

        userId = getIntent().getExtras().getString("userId");
        phone = getIntent().getExtras().getString("phone");

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

        try {
            Object object = getIntent().getExtras().get("cartList");
            if (object != null){
                cartArray = new JSONArray(object.toString());
                ListView cartList = (ListView)findViewById(R.id.cartList);
                cartList.setAdapter(new CartListAdapter(this, cartArray));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        getTotal();
    }

    /**
     * Display menu.
     */
    private void viewMenu(View v) {
        this.finish();
    }

    /**
     * Display a view to enter order info.
     */
    private void viewOrderInfo(View v) {
        Intent orderIntent = new Intent(CartActivity.this, OrderInfoActivity.class);
        orderIntent.putExtra("userId", userId);
        orderIntent.putExtra("phone", phone);
        orderIntent.putExtra("cartList", orderIntent.toString());
        startActivity(orderIntent);
    }

    private void getTotal(){
        ListView cartList = (ListView)findViewById(R.id.cartList);
        TextView total = (TextView)findViewById(R.id.total);
        double amount = 0;
        for (int i = 0; i < cartArray.length(); i++){
            View view = cartList.getAdapter().getView(i, null, cartList);
            String subTotalTmp = ((TextView)view.findViewById(R.id.carttotal)).getText().toString();
            double subTotal = Double.parseDouble(subTotalTmp);
            try {
                cartArray.getJSONObject(i).put("total", subTotal);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            amount += subTotal;
        }
        total.setText("TOTAL: $" + String.valueOf(amount) + " USD");
    }
}

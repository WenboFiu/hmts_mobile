package edu.fiu.hmts_cu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.fiu.hmts_cu.R;

/**
 * Class for FillOrderInfoActivity
 */
public class OrderInfoActivity extends Activity {

    String userId = "";
    String phone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_orderinfo);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbar_custom);

        userId = getIntent().getExtras().getString("userId");
        phone = getIntent().getExtras().getString("phone");

        EditText phoneNum = (EditText)findViewById(R.id.phonenum);
        phoneNum.setText(phone);

        TextView header = (TextView) findViewById(R.id.header_text);
        header.setText("Order Information");

        Button left = (Button) findViewById(R.id.header_left_btn);
        left.setBackgroundResource(R.drawable.back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewCart(v);
            }
        });

        Button right = (Button) findViewById(R.id.header_right_btn);
        right.setBackgroundResource(R.drawable.pay);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newOrder(v);
            }
        });
        right.setVisibility(View.INVISIBLE);
    }

    /**
     * Create an order.
     */
    public void newOrder(View view){

    }

    /**
     * Display shopping cart.
     */
    public void viewCart(View view) {
        this.finish();
    }
}

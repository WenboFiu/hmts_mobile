package edu.fiu.hmts_cu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import edu.fiu.hmts_cu.R;

/**
 * Class for CardActivity
 */
public class CardActivity extends Activity {

    String userId = "";
    String phone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_card);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbar_custom);

        userId = getIntent().getExtras().getString("userId");
        phone = getIntent().getExtras().getString("phone");

        TextView header = (TextView) findViewById(R.id.header_text);
        header.setText("Debit/Credit Card Information");

        Button left = (Button) findViewById(R.id.header_left_btn);
        left.setBackgroundResource(R.drawable.back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewOrderInfo(v);
            }
        });

        Button right = (Button) findViewById(R.id.header_right_btn);
        right.setBackgroundResource(R.drawable.order);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newOrderbyCard(v);
            }
        });
    }

    /**
     * Create an order with card.
     */
    public void newOrderbyCard(View view){
    }

    /**
     * View order information.
     */
    public void viewOrderInfo(View view){

    }
}

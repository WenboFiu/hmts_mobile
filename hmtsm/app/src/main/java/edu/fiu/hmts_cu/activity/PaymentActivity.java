package edu.fiu.hmts_cu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import edu.fiu.hmts_cu.R;

/**
 * Class for PaymentActivity
 */
public class PaymentActivity extends Activity {

    /**
     * The User id.
     */
    String userId = "";
    /**
     * The Phone.
     */
    String phone = "";
    /**
     * The Result.
     */
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_payment);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbar_custom);

        TextView header = (TextView) findViewById(R.id.header_text);
        header.setText("Confirmation");

        receiveDataFromLastActivity();
    }

    /**
     * Receive data from last activity.
     */
    private void receiveDataFromLastActivity() {
        try {
            result = getIntent().getStringExtra("result");
            userId = getIntent().getStringExtra("userId");
            phone = getIntent().getStringExtra("phone");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Open order info activity for cash.
     */
    public void ByCash(){

    }

    /**
     * Open order info activity for card.
     */
    public void ByCard(){

    }

    /**
     * Display shopping cart.
     */
    public void ViewCart(){

    }

    /**
     * Display menu.
     */
    public void continueToPurchase(View view){
        Intent menuIntent = new Intent(PaymentActivity.this, MenuActivity.class);
        menuIntent.putExtra("userId", userId);
        menuIntent.putExtra("phone", phone);
        startActivity(menuIntent);
    }
}

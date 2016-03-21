package edu.fiu.hmts_cu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.fiu.hmts_cu.R;

/**
 * Class for PaymentActivity
 */
public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
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
}

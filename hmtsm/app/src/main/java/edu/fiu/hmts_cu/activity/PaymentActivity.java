package edu.fiu.hmts_cu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import edu.fiu.hmts_cu.R;

/**
 * Class for PaymentActivity
 */
public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_payment);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbar_custom);
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

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
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import edu.fiu.hmts_cu.R;
import edu.fiu.hmts_cu.controller.MobileController;

/**
 * Class for CardActivity
 */
public class CardActivity extends Activity {

    /**
     * The User id.
     */
    String userId = "";
    /**
     * The Phone.
     */
    String phone = "";
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
     * The Total.
     */
    String total = "";
    /**
     * The Cart array.
     */
    JSONArray cartArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_card);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbar_custom);

        configActionBar();

        receiveDataFromLastActivity();

        configFields();
    }

    /**
     * Config fields.
     */
    private void configFields() {
        EditText cardNum = (EditText)findViewById(R.id.cardnum);
        cardNum.setText(getIntent().getStringExtra("cardnum"));

        EditText nameOnCard = (EditText)findViewById(R.id.nameoncard);
        nameOnCard.setText(getIntent().getStringExtra("nameoncard"));

        EditText expiration = (EditText)findViewById(R.id.expiration);
        expiration.setText(getIntent().getStringExtra("expiration"));

        EditText secCode = (EditText)findViewById(R.id.seccode);
        secCode.setText(getIntent().getStringExtra("seccode"));

        EditText billAddr = (EditText)findViewById(R.id.billaddr);
        if ("".equals(getIntent().getStringExtra("billaddr")))
            billAddr.setText(shipAddr);
        else
            billAddr.setText(getIntent().getStringExtra("billaddr"));

        EditText billCity = (EditText)findViewById(R.id.billcity);
        if ("".equals(getIntent().getStringExtra("billcity")))
            billCity.setText(shipCity);
        else
            billCity.setText(getIntent().getStringExtra("billcity"));
    }

    /**
     * Receive data from last activity
     */
    private void receiveDataFromLastActivity() {
        try {
            userId = getIntent().getStringExtra("userId");
            phone = getIntent().getStringExtra("phone");
            shipAddr = getIntent().getStringExtra("shipaddr");
            shipCity = getIntent().getStringExtra("shipcity");
            notes = getIntent().getStringExtra("notes");
            total = getIntent().getStringExtra("amount");
            String object = getIntent().getStringExtra("cartArray");
            if (object == null || "".equals(object))
                object = "[]";
            cartArray = new JSONArray(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Config action bar.
     */
    private void configActionBar() {
        TextView header = (TextView) findViewById(R.id.header_text);
        header.setText("Debit/Credit Card Info");

        Button left = (Button) findViewById(R.id.header_left_btn);
        left.setBackgroundResource(R.drawable.back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewOrderInfo(v);
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
    }

    /**
     * Create an order with card.
     *
     * @param view the view
     */
    public void placeOrderCard(View view){
        if (checkFields()) return;
        if (checkAddresses()) return;
        placeOrder();
    }

    /**
     * Place order.
     */
    private void placeOrder(){
        JSONObject object = new JSONObject();
        JSONObject order = new JSONObject();
        JSONObject payment = new JSONObject();
        JSONObject card = new JSONObject();
        JSONObject result = new JSONObject();
        try {
            order.put("shipAddress", ((EditText)findViewById(R.id.shipaddr)).getText().toString()
                    + ", " + ((EditText)findViewById(R.id.shipcity)).getText().toString());
            order.put("phone", ((EditText)findViewById(R.id.phonenum)).getText().toString());
            order.put("note", ((EditText)findViewById(R.id.deliverynote)).getText().toString());
            order.put("status", "Processing");
            order.put("userId", userId);

            payment.put("method", "0");
            payment.put("amount", total.split("$")[1].split(" ")[0]);

            card.put("cardNum", ((EditText)findViewById(R.id.cardnum)).getText().toString());
            card.put("cardOwner", ((EditText)findViewById(R.id.nameoncard)).getText().toString());
            card.put("expDate", ((EditText)findViewById(R.id.expiration)).getText().toString());
            card.put("secCode", ((EditText)findViewById(R.id.seccode)).getText().toString());
            card.put("billAddress", ((EditText)findViewById(R.id.billaddr)).getText().toString()
                    + ", " + ((EditText)findViewById(R.id.billcity)).getText().toString());

            object.put("order", order);
            object.put("payment", payment);
            object.put("card", card);
            object.put("cartArray", cartArray);

            result = new Service().execute(object).get();
            Intent resIntent = new Intent(CardActivity.this, PaymentActivity.class);
            resIntent.putExtra("result", result.toString());
            startActivity(resIntent);
            finish();
        } catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Class for async task.
     */
    class Service extends AsyncTask<JSONObject, Void, JSONObject> {
        @Override
        protected void onPreExecute() {
        }

        protected JSONObject doInBackground(JSONObject... params) {
            return MobileController.newOrder(params[0]);
        }

        /**
         * On post execute.
         *
         * @param result the result
         */
        protected void onPostExecute(Void result) {
        }
    }

    /**
     * View order information.
     *
     * @param view the view
     */
    public void viewOrderInfo(View view){
        Intent returnIntent = new Intent();
        try {
            returnIntent.putExtra("cardnum", ((EditText) findViewById(R.id.cardnum)).getText().toString());
            returnIntent.putExtra("nameoncard", ((EditText) findViewById(R.id.nameoncard)).getText().toString());
            returnIntent.putExtra("expiration", ((EditText) findViewById(R.id.expiration)).getText().toString());
            returnIntent.putExtra("seccode", ((EditText) findViewById(R.id.seccode)).getText().toString());
            returnIntent.putExtra("billaddr", ((EditText) findViewById(R.id.billaddr)).getText().toString());
            returnIntent.putExtra("billcity", ((EditText) findViewById(R.id.billcity)).getText().toString());
            setResult(RESULT_OK, returnIntent);
            finish();
        }catch (Exception e){
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    /**
     * View cart.
     *
     * @param view the view
     */
    public void viewCart(View view){
        Intent cartIntent = new Intent(CardActivity.this, CartActivity.class);
        cartIntent.putExtra("userId", userId);
        cartIntent.putExtra("phone", phone);
        cartIntent.putExtra("source", "cardactivity");
        if (cartArray != null)
            cartIntent.putExtra("cartArray", cartArray.toString());
        else
            cartIntent.putExtra("cartArray", "[]");
        startActivity(cartIntent);
    }

    /**
     * Check fields.
     *
     * @return the boolean
     */
    private boolean checkFields() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        if ("".equals(((EditText)findViewById(R.id.cardnum)).getText().toString())
                || "".equals(((EditText)findViewById(R.id.nameoncard)).getText().toString())
                || "".equals(((EditText)findViewById(R.id.expiration)).getText().toString())
                || "".equals(((EditText)findViewById(R.id.seccode)).getText().toString())
                || "".equals(((EditText)findViewById(R.id.billaddr)).getText().toString())
                || "".equals(((EditText)findViewById(R.id.billcity)).getText().toString())){
            builder.setTitle("Warning");
            builder.setMessage("Sorry, card information is incomplete.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return true;
        }
        return false;
    }

    /**
     * Check addresses boolean.
     *
     * @return the boolean
     */
    private boolean checkAddresses() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (!shipAddr.equals(((EditText)findViewById(R.id.billaddr)).getText().toString())
                || !shipCity.equals(((EditText) findViewById(R.id.billcity)).getText().toString())){
            builder.setTitle("Address Validation");
            builder.setMessage("Your shipping address and billing address are different.\nDo you want to keep it?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    placeOrder();
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return true;
        }
        return false;
    }
}

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
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import edu.fiu.hmts_cu.R;
import edu.fiu.hmts_cu.controller.MobileController;

/**
 * Class for FillOrderInfoActivity
 */
public class OrderInfoActivity extends Activity {

    /**
     * The User id.
     */
    String userId = "";
    /**
     * The Phone.
     */
    String phone = "";
    /**
     * The Card num.
     */
    String cardNum = "";
    /**
     * The Name on card.
     */
    String nameOnCard = "";
    /**
     * The Expiration.
     */
    String expiration = "";
    /**
     * The Sec code.
     */
    String secCode = "";
    /**
     * The Bill addr.
     */
    String billAddr = "";
    /**
     * The Bill city.
     */
    String billCity = "";
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
        setContentView(R.layout.activity_orderinfo);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbar_custom);

        configActionBar();

        receiveDataFromLastActivity();

        initialization();
    }

    /**
     * Config fields.
     */
    private void initialization() {
        EditText shipAddr = (EditText)findViewById(R.id.shipaddr);
        shipAddr.setText(getIntent().getStringExtra("shipaddr"));

        EditText shipCity = (EditText)findViewById(R.id.shipcity);
        shipCity.setText(getIntent().getStringExtra("shipcity"));

        EditText notes = (EditText)findViewById(R.id.deliverynote);
        notes.setText(getIntent().getStringExtra("notes"));

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        if (getIntent().getStringExtra("payment") != null
                && !"".equals(getIntent().getStringExtra("payment"))){
            int id = Integer.parseInt(getIntent().getStringExtra("payment"));
            radioGroup.check(id);
        }

        EditText phoneNum = (EditText)findViewById(R.id.phonenum);
        phoneNum.setText(phone);
    }

    /**
     * Receive data from last activity.
     */
    private void receiveDataFromLastActivity() {
        try {
            userId = getIntent().getStringExtra("userId");
            phone = getIntent().getStringExtra("phone");
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
                viewCardInfo(v);
            }
        });
        right.setVisibility(View.INVISIBLE);
    }

    /**
     * View card information.
     *
     * @param view the view
     */
    public void viewCardInfo(View view){
        if (checkFields()) return;
        try{
            Intent cardIntent = new Intent(OrderInfoActivity.this, CardActivity.class);
            cardIntent.putExtra("userId", userId);
            cardIntent.putExtra("shipaddr", ((EditText)findViewById(R.id.shipaddr)).getText().toString());
            cardIntent.putExtra("shipcity", ((EditText)findViewById(R.id.shipcity)).getText().toString());
            cardIntent.putExtra("phone", ((EditText)findViewById(R.id.phonenum)).getText().toString());
            cardIntent.putExtra("notes", ((EditText)findViewById(R.id.deliverynote)).getText().toString());
            cardIntent.putExtra("cardnum", cardNum);
            cardIntent.putExtra("nameoncard", nameOnCard);
            cardIntent.putExtra("expiration", expiration);
            cardIntent.putExtra("seccode", secCode);
            cardIntent.putExtra("billaddr", billAddr);
            cardIntent.putExtra("billcity", billCity);
            cardIntent.putExtra("amount", total);
            if (cartArray != null)
                cardIntent.putExtra("cartArray", cartArray.toString());
            else
                cardIntent.putExtra("cartArray", "[]");
            startActivityForResult(cardIntent, 1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Check fields.
     *
     * @return the boolean
     */
    private boolean checkFields() {
        if ("".equals(((EditText)findViewById(R.id.shipaddr)).getText().toString())
                || "".equals(((EditText)findViewById(R.id.shipcity)).getText().toString())
                || "".equals(((EditText)findViewById(R.id.phonenum)).getText().toString())){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Warning");
            builder.setMessage("Sorry, order information is incomplete.");
            builder.setCancelable(true);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return true;
        } return false;
    }

    /**
     * Place an order.
     *
     * @param view the view
     */
    public void placeOrderCash(View view) {
        if (checkFields()) return;
        JSONObject object = new JSONObject();
        JSONObject order = new JSONObject();
        JSONObject payment = new JSONObject();
        JSONObject result = new JSONObject();
        try {
            order.put("shipAddress", ((EditText)findViewById(R.id.shipaddr)).getText().toString()
                + ", " + ((EditText)findViewById(R.id.shipcity)).getText().toString());
            order.put("phone", ((EditText)findViewById(R.id.phonenum)).getText().toString());
            order.put("note", ((EditText)findViewById(R.id.deliverynote)).getText().toString());
            order.put("status", "Processing");
            order.put("userId", userId);

            payment.put("method", "0");
            payment.put("amount", total == null ? 0.0 : Double.parseDouble(total));

            object.put("order", order);
            object.put("payment", payment);
            object.put("cartArray", cartArray);

            result = new Service().execute(object).get();
            Intent resIntent = new Intent(OrderInfoActivity.this, PaymentActivity.class);
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
     * Display shopping cart.
     *
     * @param view the view
     */
    public void viewCart(View view) {
        Intent returnIntent = new Intent();
        try {
            returnIntent.putExtra("shipaddr", ((EditText) findViewById(R.id.shipaddr)).getText().toString());
            returnIntent.putExtra("shipcity", ((EditText) findViewById(R.id.shipcity)).getText().toString());
            returnIntent.putExtra("phone", ((EditText) findViewById(R.id.phonenum)).getText().toString());
            returnIntent.putExtra("notes", ((EditText) findViewById(R.id.deliverynote)).getText().toString());
            returnIntent.putExtra("payment", String.valueOf(((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId()));
            setResult(RESULT_OK, returnIntent);
            finish();
        }catch (Exception e){
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    /**
     * Select card as payment method.
     *
     * @param view the view
     */
    public void selectCard(View view) {
        Button placeOrder = (Button)findViewById(R.id.placeorder);
        Button right = (Button) findViewById(R.id.header_right_btn);
        placeOrder.setVisibility(View.GONE);
        right.setVisibility(View.VISIBLE);
    }

    /**
     * Select cash as payment method.
     *
     * @param view the view
     */
    public void selectCash(View view) {
        Button placeOrder = (Button)findViewById(R.id.placeorder);
        Button right = (Button) findViewById(R.id.header_right_btn);
        placeOrder.setVisibility(View.VISIBLE);
        right.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                cardNum = data.getStringExtra("cardnum");
                nameOnCard = data.getStringExtra("nameoncard");
                expiration = data.getStringExtra("expiration");
                secCode = data.getStringExtra("seccode");
                billAddr = data.getStringExtra("billaddr");
                billCity = data.getStringExtra("billcity");
            }
        }
    }
}

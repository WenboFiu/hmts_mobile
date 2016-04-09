package edu.fiu.hmts_cu.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.fiu.hmts_cu.R;
import edu.fiu.hmts_cu.controller.MobileController;

/**
 * Class for RegisterActivity
 */
public class RegisterActivity extends Activity {

    List<String> quesitems;
    List<String> quesIditems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_register);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbar_custom);

        TextView header = (TextView)findViewById(R.id.header_text);
        header.setText(R.string.title_register);

        Button left = (Button)findViewById(R.id.header_left_btn);
        left.setBackgroundResource(R.drawable.back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

        Button right = (Button)findViewById(R.id.header_left_btn);
        right.setWidth(25);
        right.setVisibility(View.INVISIBLE);

        Spinner question = (Spinner)findViewById(R.id.question);
        getQuestionItems();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,quesitems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        question.setAdapter(adapter);
    }

    private void getQuestionItems() {
        quesIditems = new ArrayList<>();
        quesitems = new ArrayList<>();
        try {
            JSONObject param = new JSONObject();
            param.put("service", "getquestions");
            JSONArray questions = new Service().execute(param).get().getJSONArray("data");
            for (int i = 0; i < questions.length(); i++) {
                quesIditems.add(questions.getJSONObject(i).get("secQuestionId").toString());
                quesitems.add(questions.getJSONObject(i).get("content").toString());
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
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
            try {
                if (params[0].get("service").equals("register")){
                    params[0].remove("service");
                    return MobileController.register(params[0]);
                }
                else if (params[0].get("service").equals("getquestions")){
                    return MobileController.getQuestions();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new JSONObject();
        }

        protected void onPostExecute() {

        }
    }

    /**
     * Create a user.
     */
    public void register(View view) throws Exception{
        if (checkFields()) return;

        JSONObject regData = new JSONObject();
        regData.put("username", ((EditText)findViewById(R.id.email)).getText().toString());
        regData.put("password", ((EditText)findViewById(R.id.passwd)).getText().toString());
        regData.put("firstName", ((EditText)findViewById(R.id.firstnm)).getText().toString());
        regData.put("lastName", ((EditText)findViewById(R.id.lastnm)).getText().toString());
        regData.put("phone", ((EditText)findViewById(R.id.phone)).getText().toString());
        regData.put("role", "4");
        regData.put("secQuestionId", quesIditems.get(((Spinner) findViewById(R.id.question)).getSelectedItemPosition()));
        regData.put("secAnswer", ((EditText)findViewById(R.id.answer)).getText().toString());
        regData.put("service", "register");

        JSONObject regRes = new Service().execute(regData).get();
        if ("successful".equals(regRes.get("result").toString())
                && !"-1".equals(regRes.getJSONObject("data").get("userId").toString())) {
            Intent menuIntent = new Intent(this, MenuActivity.class);
            menuIntent.putExtra("UserId", regRes.getJSONObject("data").getString("userId"));
            menuIntent.putExtra("Phone", regRes.getJSONObject("data").getString("phone"));
            startActivity(menuIntent);
            this.finish();
        }
    }

    private boolean checkFields() {
        if ("".equals(((EditText)findViewById(R.id.email)).getText().toString())
                || "".equals(((EditText)findViewById(R.id.passwd)).getText().toString())
                || "".equals(((EditText)findViewById(R.id.firstnm)).getText().toString())
                || "".equals(((EditText)findViewById(R.id.lastnm)).getText().toString())
                || "".equals(((EditText)findViewById(R.id.phone)).getText().toString())
                || ((Spinner)findViewById(R.id.question)).getSelectedItemPosition() < 0
                || "".equals(((EditText)findViewById(R.id.answer)).getText().toString())){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Warning");
            builder.setMessage("Registration information is incomplete.");
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
        }
        return false;
    }
}

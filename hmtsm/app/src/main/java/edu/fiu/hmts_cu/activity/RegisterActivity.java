package edu.fiu.hmts_cu.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        Spinner question = (Spinner)findViewById(R.id.question);
        List<String> items = getQuestionItems();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        question.setAdapter(adapter);
    }

    private List<String> getQuestionItems() {
        List<String> items = new ArrayList<String>();
        try {
            JSONArray questions = new Service().execute().get();
            for (int i = 0; i < questions.length(); i++) {
                items.add(questions.getJSONObject(i).get("content").toString());
            }
            return items;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Class for async task.
     */
    class Service extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected void onPreExecute() {

        }

        protected JSONArray doInBackground(Void... params) {
            return MobileController.getQuestions();
        }

        protected void onPostExecute() {

        }
    }

    /**
     * Create a user.
     */
    public void register(View view) throws Exception{
        JSONObject regData = new JSONObject();

    }
}

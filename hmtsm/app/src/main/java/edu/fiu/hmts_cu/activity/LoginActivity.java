package edu.fiu.hmts_cu.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONObject;

import edu.fiu.hmts_cu.R;
import edu.fiu.hmts_cu.controller.MobileController;

/**
 * Class for LoginActivity
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * The Load.
     */
    private ProgressBar load;

    /**
     * Initialize activity.
     * @param savedInstanceState Instance status
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        load = (ProgressBar)LoginActivity.this.findViewById(R.id.load);
    }

    /**
     * Class for async task.
     */
    class service extends AsyncTask<JSONObject, Void, JSONObject> {
        @Override
        protected void onPreExecute() {
            //load.setIndeterminate(true);
            //load.setVisibility(View.VISIBLE);
        }

        protected JSONObject doInBackground(JSONObject... params) {
            return MobileController.login(params[0]);
        }

        /**
         * On post execute.
         *
         * @param result the result
         */
        protected void onPostExecute(Void result) {
            load.setVisibility(View.GONE);
        }
    }

    /**
     * Login event.
     *
     * @param view current view
     * @throws Exception the exception
     */
    public void Login(View view) throws Exception {
        JSONObject input = new JSONObject();
        EditText usernm = (EditText)LoginActivity.this.findViewById(R.id.usernm);
        EditText passwd = (EditText)LoginActivity.this.findViewById(R.id.passwd);
        input.put("username", usernm.getText());
        input.put("password", passwd.getText());
        JSONObject loginRes = new service().execute(input).get();

        if (loginRes.length() > 0) {
            Intent menuIntent = new Intent(this, MenuActivity.class);
            menuIntent.putExtra("userId", loginRes.getString("userId"));
            menuIntent.putExtra("phone", loginRes.getString("phone"));
            startActivity(menuIntent);
        }
    }

    /**
     * Open registration activity event.
     *
     * @param view the view
     * @throws Exception the exception
     */
    public void SignUp(View view) throws Exception{
        Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(regIntent);
    }
}

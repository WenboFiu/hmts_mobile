package edu.fiu.hmts_cu.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import edu.fiu.hmts_cu.R;
import edu.fiu.hmts_cu.model.HttpService;

/**
 * Class for LoginActivity
 */
public class LoginActivity extends AppCompatActivity {

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
    class service extends AsyncTask<JSONObject, Void, String> {
        @Override
        protected void onPreExecute() {
            load.setIndeterminate(true);
            load.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(JSONObject... params) {
            return HttpService.requestService(params[0]);
        }

        protected void onPostExecute(Void result) {
            load.setVisibility(View.GONE);
        }
    }

    /**
     * Login event.
     * @param view current view
     * @throws Exception
     */
    public void openMenuActivity(View view) throws Exception {
        Login();
    }

    /**
     * Login.
     * @throws Exception
     */
    public void Login() throws Exception {
        JSONObject input = new JSONObject();
        EditText usernm = (EditText)LoginActivity.this.findViewById(R.id.usernm);
        EditText passwd = (EditText)LoginActivity.this.findViewById(R.id.passwd);
        input.put("login", "/userservice/login");
        input.put("username", usernm.getText());
        input.put("password", passwd.getText());
        String res = new service().execute(input).get();

        if (!"Not Found".equals(res)) {
            JSONObject resJson = new JSONObject(res);
            Intent menuIntent = new Intent(this, MenuActivity.class);
            menuIntent.putExtra("UserId", resJson.getString("userId"));
            menuIntent.putExtra("Phone", resJson.getString("phone"));
            startActivity(menuIntent);
        }
    }

    /**
     * Open registration activity.
     */
    public void NewUser() {

    }
}

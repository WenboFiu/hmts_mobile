package edu.fiu.hmts_cu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openMenuActivity(View view) {
        Intent menuIntent = new Intent(this, MenuActivity.class);
        startActivity(menuIntent);
    }
}

package edu.fiu.hmts_cu;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import edu.fiu.hmts_cu.activity.LoginActivity;
import edu.fiu.hmts_cu.activity.MenuActivity;
import edu.fiu.hmts_cu.activity.SplashActivity;


public class testLogout_S1 extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;

  	public testLogout_S1() {
		super(SplashActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'edu.fiu.hmts_cu.activity.SplashActivity'
		solo.waitForActivity(SplashActivity.class, 2000);
        //Wait for activity: 'edu.fiu.hmts_cu.activity.LoginActivity'
		solo.waitForActivity(edu.fiu.hmts_cu.activity.LoginActivity.class);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.usernm));
        //Enter the text: 'bob@fiu.edu'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.usernm));
		solo.enterText((android.widget.EditText) solo.getView(R.id.usernm), "bob@fiu.edu");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.passwd));
        //Enter the text: 'bb1234'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.passwd));
		solo.enterText((android.widget.EditText) solo.getView(R.id.passwd), "bb1234");
        //Click on Log In
		solo.clickOnView(solo.getView(R.id.login));
		//Click on Empty Text View
		solo.clickOnView(solo.getView(edu.fiu.hmts_cu.R.id.header_left_btn));
		assertTrue("edu.fiu.hmts_cu.activity.LoginActivity is not found!", solo.waitForActivity(LoginActivity.class));
		solo.assertCurrentActivity("LoginActivity", LoginActivity.class);
		assertTrue(solo.searchText("Happy Meal"));
	}
}

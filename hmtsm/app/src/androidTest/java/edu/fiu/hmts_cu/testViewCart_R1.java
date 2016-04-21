package edu.fiu.hmts_cu;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;

import edu.fiu.hmts_cu.activity.LoginActivity;
import edu.fiu.hmts_cu.activity.MenuActivity;
import edu.fiu.hmts_cu.activity.SplashActivity;


public class testViewCart_R1 extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;

  	public testViewCart_R1() {
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
		solo.waitForActivity(LoginActivity.class);
        //Enter the text: 'jack@fiu.edu'
		solo.clearEditText((EditText) solo.getView(R.id.usernm));
		solo.enterText((EditText) solo.getView(R.id.usernm), "jack@fiu.edu");
        //Enter the text: 'jk1234'
		solo.clearEditText((EditText) solo.getView(R.id.passwd));
		solo.enterText((EditText) solo.getView(R.id.passwd), "jk1234");
        //Click on Log In
		solo.clickOnView(solo.getView(R.id.login));
        //Wait for activity: 'edu.fiu.hmts_cu.activity.MenuActivity'
		solo.waitForActivity(MenuActivity.class);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.header_right_btn));
		assertTrue("Could not find the dialog!", solo.searchText("Sorry! Shopping cart is empty"));
	}
}

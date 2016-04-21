package edu.fiu.hmts_cu;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import edu.fiu.hmts_cu.activity.MenuActivity;
import edu.fiu.hmts_cu.activity.SplashActivity;


public class testRegister_R1 extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;

  	public testRegister_R1() {
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
		//Click on Sign up
		solo.clickOnView(solo.getView(R.id.newuser));
		//Wait for activity: 'edu.fiu.hmts_cu.activity.RegisterActivity'
		assertTrue("edu.fiu.hmts_cu.activity.RegisterActivity is not found!", solo.waitForActivity(edu.fiu.hmts_cu.activity.RegisterActivity.class));
		//Enter the text: 'jn1234'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.passwd));
		solo.enterText((android.widget.EditText) solo.getView(R.id.passwd), "jn1234");
		//Enter the text: 'Jackson'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.firstnm));
		solo.enterText((android.widget.EditText) solo.getView(R.id.firstnm), "Jackson");
		//Enter the text: 'Bill'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.lastnm));
		solo.enterText((android.widget.EditText) solo.getView(R.id.lastnm), "Bill");
		//Click on Create account
		solo.clickOnView(solo.getView(R.id.signup));
		//Wait for activity: 'edu.fiu.hmts_cu.activity.MenuActivity'
		assertTrue("Text is not found!", solo.searchText("Sorry! Registration information is incomplete."));
	}
}

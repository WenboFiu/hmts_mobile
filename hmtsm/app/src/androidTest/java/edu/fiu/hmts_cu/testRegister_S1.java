package edu.fiu.hmts_cu;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import edu.fiu.hmts_cu.activity.MenuActivity;
import edu.fiu.hmts_cu.activity.SplashActivity;


public class testRegister_S1 extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;

  	public testRegister_S1() {
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
		solo.clickOnView(solo.getView(edu.fiu.hmts_cu.R.id.newuser));
		//Wait for activity: 'edu.fiu.hmts_cu.activity.RegisterActivity'
		assertTrue("edu.fiu.hmts_cu.activity.RegisterActivity is not found!", solo.waitForActivity(edu.fiu.hmts_cu.activity.RegisterActivity.class));
		//Enter the text: 'jackson@gmail.com'
		solo.clearEditText((android.widget.EditText) solo.getView(edu.fiu.hmts_cu.R.id.email));
		solo.enterText((android.widget.EditText) solo.getView(edu.fiu.hmts_cu.R.id.email), "jackson@gmail.com");
		//Enter the text: 'jn1234'
		solo.clearEditText((android.widget.EditText) solo.getView(edu.fiu.hmts_cu.R.id.passwd));
		solo.enterText((android.widget.EditText) solo.getView(edu.fiu.hmts_cu.R.id.passwd), "jn1234");
		//Enter the text: 'Jackson'
		solo.clearEditText((android.widget.EditText) solo.getView(edu.fiu.hmts_cu.R.id.firstnm));
		solo.enterText((android.widget.EditText) solo.getView(edu.fiu.hmts_cu.R.id.firstnm), "Jackson");
		//Enter the text: 'Bill'
		solo.clearEditText((android.widget.EditText) solo.getView(edu.fiu.hmts_cu.R.id.lastnm));
		solo.enterText((android.widget.EditText) solo.getView(edu.fiu.hmts_cu.R.id.lastnm), "Bill");
		//Enter the text: '(305)473-8737'
		solo.clearEditText((android.widget.EditText) solo.getView(edu.fiu.hmts_cu.R.id.phone));
		solo.enterText((android.widget.EditText) solo.getView(edu.fiu.hmts_cu.R.id.phone), "(305)473-8737");
		solo.clickOnView(solo.getView(edu.fiu.hmts_cu.R.id.question));
		//Click on What's your favorite sport?
		solo.clickOnView(solo.getView(android.R.id.text1, 2));
		//Click on Empty Text View
		solo.clickOnView(solo.getView(edu.fiu.hmts_cu.R.id.answer));
		//Enter the text: 'Running'
		solo.clearEditText((android.widget.EditText) solo.getView(edu.fiu.hmts_cu.R.id.answer));
		solo.enterText((android.widget.EditText) solo.getView(edu.fiu.hmts_cu.R.id.answer), "Running");
		//Click on Create account
		solo.clickOnView(solo.getView(edu.fiu.hmts_cu.R.id.signup));
		//Wait for activity: 'edu.fiu.hmts_cu.activity.MenuActivity'
		assertTrue("edu.fiu.hmts_cu.activity.MenuActivity is not found!", solo.waitForActivity(edu.fiu.hmts_cu.activity.MenuActivity.class));
	}
}

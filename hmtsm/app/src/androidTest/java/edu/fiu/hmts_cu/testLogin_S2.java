package edu.fiu.hmts_cu;

import edu.fiu.hmts_cu.activity.MenuActivity;
import edu.fiu.hmts_cu.activity.SplashActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.Assert;


public class testLogin_S2 extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;
  	
  	public testLogin_S2() {
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
		assertTrue("edu.fiu.hmts_cu.activity.LoginActivity is not found!", solo.waitForActivity(edu.fiu.hmts_cu.activity.LoginActivity.class));
        //Enter the text: 'jack@fiu.edu'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.usernm));
		solo.enterText((android.widget.EditText) solo.getView(R.id.usernm), "jack@fiu.edu");
        //Enter the text: 'jk1234'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.passwd));
		solo.enterText((android.widget.EditText) solo.getView(R.id.passwd), "jk1234");
        //Click on Log In
		solo.clickOnView(solo.getView(R.id.login));
        assertTrue("edu.fiu.hmts_cu.activity.MenuActivity is not found!", solo.waitForActivity(MenuActivity.class));
        solo.assertCurrentActivity("MenuActivity", MenuActivity.class);
		assertTrue(solo.searchText("Turkey Breast"));
        assertTrue(solo.searchText("8.99"));
        assertTrue(solo.searchText("Vegetables"));
        assertTrue(solo.searchText("2.99"));
        assertTrue(solo.searchText("Lemonade"));
        assertTrue(solo.searchText("1.99"));
	}
}

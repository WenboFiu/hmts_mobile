package edu.fiu.hmts_cu;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.robotium.solo.Solo;

import edu.fiu.hmts_cu.activity.SplashActivity;


public class testRemoveProduct_S2 extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;

  	public testRemoveProduct_S2() {
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
        //Enter the text: 'jack@fiu.edu'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.usernm));
		solo.enterText((android.widget.EditText) solo.getView(R.id.usernm), "jack@fiu.edu");
        //Enter the text: 'jk1234'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.passwd));
		solo.enterText((android.widget.EditText) solo.getView(R.id.passwd), "jk1234");
        //Click on Log In
		solo.clickOnView(solo.getView(R.id.login));
        //Wait for activity: 'edu.fiu.hmts_cu.activity.MenuActivity'
		assertTrue("edu.fiu.hmts_cu.activity.MenuActivity is not found!", solo.waitForActivity(edu.fiu.hmts_cu.activity.MenuActivity.class));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.prodadd, 1));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.prodminus, 1));
		assertEquals("0", ((TextView)solo.getView(R.id.prodquatity)).getText());
	}
}

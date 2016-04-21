package edu.fiu.hmts_cu;

import edu.fiu.hmts_cu.activity.SplashActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;


public class testSelectProduct_S1 extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;
  	
  	public testSelectProduct_S1() {
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
		solo.clickOnView(solo.getView(R.id.prodadd));
		assertEquals("1", ((TextView)solo.getView(R.id.prodquatity)).getText());
	}
}

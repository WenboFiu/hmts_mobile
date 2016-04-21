package edu.fiu.hmts_cu;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.robotium.solo.Solo;

import edu.fiu.hmts_cu.activity.SplashActivity;


public class testViewCart_S1 extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;

  	public testViewCart_S1() {
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
		solo.waitForActivity(edu.fiu.hmts_cu.activity.MenuActivity.class);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.prodadd));
		//Click on Empty Text View
		solo.clickOnView(solo.getView(edu.fiu.hmts_cu.R.id.header_right_btn));
		//Wait for activity: 'edu.fiu.hmts_cu.activity.CartActivity'
		assertTrue("edu.fiu.hmts_cu.activity.CartActivity is not found!", solo.waitForActivity(edu.fiu.hmts_cu.activity.CartActivity.class));
		assertEquals("Turkey Breast", ((TextView)solo.getView(R.id.cartname,1)).getText());
		assertEquals("Dish", ((TextView)solo.getView(R.id.carttype,1)).getText());
		assertEquals("8.99", ((TextView)solo.getView(R.id.cartprice,1)).getText());
		assertEquals("1", ((TextView)solo.getView(R.id.cartquatity,1)).getText());
		assertEquals("8.99", ((TextView)solo.getView(R.id.carttotal,1)).getText());
		assertEquals("TOTAL: $8.99 USD", ((TextView)solo.getView(R.id.total)).getText());
	}
}

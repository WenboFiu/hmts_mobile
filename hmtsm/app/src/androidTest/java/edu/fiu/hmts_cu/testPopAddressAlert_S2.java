package edu.fiu.hmts_cu;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import edu.fiu.hmts_cu.activity.SplashActivity;


public class testPopAddressAlert_S2 extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;

  	public testPopAddressAlert_S2() {
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
		solo.enterText((android.widget.EditText) solo.getView(R.id.usernm), "bob@fiu.edu");
        //Enter the text: 'jk1234'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.passwd));
		solo.enterText((android.widget.EditText) solo.getView(R.id.passwd), "bb1234");
        //Click on Log In
		solo.clickOnView(solo.getView(R.id.login));
        //Wait for activity: 'edu.fiu.hmts_cu.activity.MenuActivity'
		solo.waitForActivity(edu.fiu.hmts_cu.activity.MenuActivity.class);

		//Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.prodadd));
		//Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.header_right_btn));
		//Wait for activity: 'edu.fiu.hmts_cu.activity.CartActivity'
		solo.waitForActivity(edu.fiu.hmts_cu.activity.CartActivity.class);
		//Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.header_right_btn));

		//Wait for activity: 'edu.fiu.hmts_cu.activity.OrderInfoActivity'
		assertTrue("edu.fiu.hmts_cu.activity.OrderInfoActivity is not found!", solo.waitForActivity(edu.fiu.hmts_cu.activity.OrderInfoActivity.class));
		//Enter the text: '120 SW 7th St'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.shipaddr));
		solo.enterText((android.widget.EditText) solo.getView(R.id.shipaddr), "220 NW 15th St");
		//Enter the text: 'Miami, FL 33155'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.shipcity));
		solo.enterText((android.widget.EditText) solo.getView(R.id.shipcity), "Miami, FL 33366");
		//Click on Pay by Debit/Credit Card
		solo.clickOnView(solo.getView(R.id.card));
		//Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.header_right_btn));
		//Wait for activity: 'edu.fiu.hmts_cu.activity.CardActivity'
		assertTrue("edu.fiu.hmts_cu.activity.CardActivity is not found!", solo.waitForActivity(edu.fiu.hmts_cu.activity.CardActivity.class));

		//Enter the text: '8756546526590677'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.cardnum));
		solo.enterText((android.widget.EditText) solo.getView(R.id.cardnum), "8756546526590677");
		//Enter the text: 'Bob'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.nameoncard));
		solo.enterText((android.widget.EditText) solo.getView(R.id.nameoncard), "Bob");
		//Enter the text: '05/2020'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.expiration));
		solo.enterText((android.widget.EditText) solo.getView(R.id.expiration), "12/2023");
		//Enter the text: '343'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.seccode));
		solo.enterText((android.widget.EditText) solo.getView(R.id.seccode), "765");
		//Enter the text: '1120 NW 7th St'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.billaddr));
		solo.enterText((android.widget.EditText) solo.getView(R.id.billaddr), "222 SW 87th St");
		//Enter the text: 'mMiami, FL 33155'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.billcity));
		solo.enterText((android.widget.EditText) solo.getView(R.id.billcity), "Miami, FL 33178");
		//Click on Place Order
		solo.clickOnView(solo.getView(R.id.placeordercard));
		assertTrue("Text is not found!", solo.searchText("Your shipping address and billing address are different."));
		//Click on NO
		solo.clickOnView(solo.getView(android.R.id.button2));
	}
}

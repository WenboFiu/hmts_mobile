package edu.fiu.hmts_cu;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import edu.fiu.hmts_cu.activity.SplashActivity;


public class testFillOutOrderInfo_Card_S2 extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;

  	public testFillOutOrderInfo_Card_S2() {
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
        //Wait for activity: 'edu.fiu.hmts_cu.activity.MenuActivity'
		solo.waitForActivity(edu.fiu.hmts_cu.activity.MenuActivity.class);

		//Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.prodadd));
		solo.clickOnView(solo.getView(R.id.prodadd, 1));
		solo.clickOnView(solo.getView(R.id.prodadd, 2));
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
		solo.enterText((android.widget.EditText) solo.getView(R.id.shipaddr), "110 NW 22th Ave");
		//Enter the text: 'Miami, FL 33155'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.shipcity));
		solo.enterText((android.widget.EditText) solo.getView(R.id.shipcity), "Miami, FL 33109");
		//Enter the text: 'Please fast'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.deliverynote));
		solo.enterText((android.widget.EditText) solo.getView(R.id.deliverynote), "Thank you.");
		//Click on Pay by Debit/Credit Card
		solo.clickOnView(solo.getView(R.id.card));
		//Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.header_right_btn));
		//Wait for activity: 'edu.fiu.hmts_cu.activity.CardActivity'
		assertTrue("edu.fiu.hmts_cu.activity.CardActivity is not found!", solo.waitForActivity(edu.fiu.hmts_cu.activity.CardActivity.class));

		//Enter the text: '8756546526590677'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.cardnum));
		solo.enterText((android.widget.EditText) solo.getView(R.id.cardnum), "8457874367235542");
		//Enter the text: 'Bob'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.nameoncard));
		solo.enterText((android.widget.EditText) solo.getView(R.id.nameoncard), "Jack");
		//Enter the text: '05/2020'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.expiration));
		solo.enterText((android.widget.EditText) solo.getView(R.id.expiration), "09/2025");
		//Enter the text: '343'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.seccode));
		solo.enterText((android.widget.EditText) solo.getView(R.id.seccode), "456");
		//Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.header_right_btn));
		//Wait for activity: 'edu.fiu.hmts_cu.activity.CartActivity'
		assertTrue("edu.fiu.hmts_cu.activity.CartActivity is not found!", solo.waitForActivity(edu.fiu.hmts_cu.activity.CartActivity.class));
		//Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.header_left_btn));
		//Click on Place Order
		solo.clickOnView(solo.getView(R.id.placeordercard));
		//Wait for activity: 'edu.fiu.hmts_cu.activity.PaymentActivity'
		assertTrue("edu.fiu.hmts_cu.activity.PaymentActivity is not found!", solo.waitForActivity(edu.fiu.hmts_cu.activity.PaymentActivity.class));
		assertTrue("Confirmation is not found!", solo.searchText("We will process your order recently."));
	}
}

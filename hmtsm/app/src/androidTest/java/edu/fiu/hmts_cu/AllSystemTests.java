package edu.fiu.hmts_cu;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllSystemTests {
    public static Test suite() {
        TestSuite suite = new TestSuite(AllSystemTests.class.getName());
        suite.addTest(TestSuite.createTest(testLogin_S1.class, "testRun"));
        suite.addTest(TestSuite.createTest(testLogin_S2.class, "testRun"));
        suite.addTest(TestSuite.createTest(testLogin_R1.class, "testRun"));

        suite.addTest(TestSuite.createTest(testLogout_S1.class, "testRun"));
        suite.addTest(TestSuite.createTest(testLogout_S2.class, "testRun"));

        suite.addTest(TestSuite.createTest(testRegister_S1.class, "testRun"));
        suite.addTest(TestSuite.createTest(testRegister_S2.class, "testRun"));
        suite.addTest(TestSuite.createTest(testRegister_R1.class, "testRun"));

        suite.addTest(TestSuite.createTest(testSelectProduct_S1.class, "testRun"));
        suite.addTest(TestSuite.createTest(testSelectProduct_S2.class, "testRun"));
        suite.addTest(TestSuite.createTest(testSelectProduct_R1.class, "testRun"));

        suite.addTest(TestSuite.createTest(testRemoveProduct_S1.class, "testRun"));
        suite.addTest(TestSuite.createTest(testRemoveProduct_S2.class, "testRun"));
        suite.addTest(TestSuite.createTest(testRemoveProduct_R1.class, "testRun"));

        suite.addTest(TestSuite.createTest(testViewCart_S1.class, "testRun"));
        suite.addTest(TestSuite.createTest(testViewCart_S2.class, "testRun"));
        suite.addTest(TestSuite.createTest(testViewCart_R1.class, "testRun"));

        suite.addTest(TestSuite.createTest(testFillOutOrderInfo_Cash_S1.class, "testRun"));
        suite.addTest(TestSuite.createTest(testFillOutOrderInfo_Cash_S2.class, "testRun"));
        suite.addTest(TestSuite.createTest(testFillOutOrderInfo_Cash_R1.class, "testRun"));

        suite.addTest(TestSuite.createTest(testFillOutOrderInfo_Card_S1.class, "testRun"));
        suite.addTest(TestSuite.createTest(testFillOutOrderInfo_Card_S2.class, "testRun"));
        suite.addTest(TestSuite.createTest(testFillOutOrderInfo_Card_R1.class, "testRun"));

        suite.addTest(TestSuite.createTest(testPopAddressAlert_S1.class, "testRun"));
        suite.addTest(TestSuite.createTest(testPopAddressAlert_S2.class, "testRun"));
        suite.addTest(TestSuite.createTest(testPopAddressAlert_R1.class, "testRun"));

        return suite;
    }
}
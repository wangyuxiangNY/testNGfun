package com.buxie.selenium.verificationLibrary;



import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;


public interface Verify {
	
	 static Assertion hardAssert = new Assertion();
     static SoftAssert softAssert = new SoftAssert();
     
    
}

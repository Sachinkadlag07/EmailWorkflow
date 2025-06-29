package Testcase;

import BaseTest.Basetest;
import PageObject.SignInPage;
import org.testng.annotations.Test;


public class T001_Login extends Basetest {

    @Test(groups ="sanity")
    public void logintoEmail() throws InterruptedException {
        SignInPage sp=new SignInPage(driver);
        //logger.info("Entering email id");
        sp.enterEmailId(p.getProperty("Emailid"));
        sp.clickNext();
        Thread.sleep(10000);
        sp.enterPassword("miyamo123");
       // sp.clickNext();





    }


}

package Testcase;

import BaseTest.Basetest;
import PageObject.SignInPage;
import org.testng.annotations.Test;


public class T001_Login extends Basetest {

    @Test(groups ="sanity")
    public void logintoEmail() throws InterruptedException {

        logger.info("**** Starting T001 ****");
        SignInPage sp=new SignInPage(driver);
        logger.info("Entering Gmail id in to text filed");
        sp.enterEmailId(p.getProperty("Emailid"));
        logger.info(("clicking on Next CTA"));
        sp.clickNext();
        //Thread.sleep(10000);
        sp.enterPassword("miyamo123");
       // sp.clickNext();





    }


}

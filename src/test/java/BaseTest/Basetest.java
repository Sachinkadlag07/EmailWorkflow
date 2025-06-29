package BaseTest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class Basetest {

public static WebDriver driver;

public static Properties p;

public Logger logger;

   @BeforeClass(groups = {"Sanity","Regression"}) // adding grouping to base class
    @Parameters({"os","browser"})
    public void setup(String os ,String br) throws IOException {

        //for loading property file

        FileReader file = new FileReader("C:\\Users\\skadla01\\IdeaProjects\\EmailWorkflow\\src\\test\\resources\\Config\\Properties");
        p = new Properties();
        p.load(file);

        logger= LogManager.getLogger(this.getClass());  // log initiation for all method

       //Passing  os and browser  value  from xml file for execution

       // for remote machine execution - value passing from properties file

       if(p.getProperty("Execution_env").equalsIgnoreCase("remote"))
       {
           DesiredCapabilities capabilities=new DesiredCapabilities();

           //os
           if(os.equalsIgnoreCase("windows"))
           {
               capabilities.setPlatform(Platform.WIN11);
           }
           else if (os.equalsIgnoreCase("mac"))
           {
               capabilities.setPlatform(Platform.MAC);
           }
           else if (os.equalsIgnoreCase("Linx"))
           {
               capabilities.setPlatform(Platform.LINUX);
           }
           else
           {
               System.out.println("No matching os");
               return;
           }

           //browser
           switch(br.toLowerCase())
           {
               case "chrome": capabilities.setBrowserName("chrome"); break;
               case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
               default: System.out.println("No matching browser"); return;
           }

           driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
       }

        //for local machine execution
        if(p.getProperty("Execution_env").equalsIgnoreCase("local"))
        {

            switch(br.toLowerCase())
            {
                case "chrome" : driver=new ChromeDriver(); break;
                case "edge" : driver=new EdgeDriver(); break;
                case "firefox": driver=new FirefoxDriver(); break;
                case "safari" : driver= new SafariDriver(); break;
                default : System.out.println("Invalid browser name.."); return;
            }
        }

        driver.manage().deleteAllCookies();  // deleting cookies
        driver.manage().deleteAllCookies();  // deleting cookies
        driver.manage().window().maximize(); // Maximaizing browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // implimenting implicate wait
        driver.get(p.getProperty("TestURL")); //reading url value from property file

    }

    @AfterClass(groups = {"Sanity","Regression"})

    public void teardown(){

        driver.quit();

        System.out.println("Quiting Browser successfully");

    }

    public String randomeString() {
        String generatedString = randomAlphabetic(5);
        return (generatedString);
    }

    public String randomeNumber() {
        String generatedString2 = RandomStringUtils.randomNumeric(10);
        return (generatedString2);
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\Screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }





}

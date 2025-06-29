package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage extends BasePage {

    public SignInPage(WebDriver driver) {
        super(driver);

    }

    @FindBy(xpath = "//input[@type='email']")
    WebElement EmailTextBox;

    @FindBy(xpath = "//span[text()='Next']")
    WebElement next;

    @FindBy(xpath = "//span[text()='Next']")
    WebElement Tryagain;




    public void enterEmailId(String emailId) {
        EmailTextBox.sendKeys(emailId);
    }

    public void clickNext(){
        next.click();
    }



    public void tryAgain(){
        Tryagain.click();
    }

    public void enterPassword(String password) {

        WebDriverWait wp = new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement Password= wp.until(ExpectedConditions.visibilityOfElementLocated(By.name("Passwd")));


        Password.sendKeys(password);
    }



}

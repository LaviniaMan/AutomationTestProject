package comenzi.bebetei;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class WrongLoginTest {
    WebDriver driver;
    String url = "https://comenzi.bebetei.ro/login";

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Parameters({"email1", "newaccount"})
    @Test
    public void loginWrongEmail(String email1, String newaccount) throws InterruptedException {

        WebElement emailInput = driver.findElement(By.id("auth-email"));
        emailInput.sendKeys(email1);
        WebElement continuaButton = driver.findElement(By.id("auth-next-btn"));
        continuaButton.click();
        sleep(2000);

        WebElement createNewAccount = driver.findElement(By.id("step-register-txt"));
        String createNewAccountContent = newaccount;

        Assert.assertTrue(createNewAccount.getText().contains(createNewAccountContent));

        WebElement returnButton = driver.findElement(By.className("link"));
        returnButton.click();
        sleep(2000);

    }

    @Parameters({"email2","password","error"})
    @Test
    public void loginWrongPassword (String email2, String password, String error) throws InterruptedException {

        WebElement emailInput = driver.findElement(By.id("auth-email"));
        emailInput.sendKeys(email2);
        WebElement continuaButton = driver.findElement(By.id("auth-next-btn"));
        continuaButton.click();
        sleep(2000);

        WebElement passwordimput = driver.findElement(By.id("auth-login-password"));
        passwordimput.sendKeys(password);
        WebElement conecteazaButton =driver.findElement(By.id("auth-next-btn"));
        conecteazaButton.click();
        sleep(2000);

        WebElement errorMessage= driver.findElement(By.id("auth-login-password-error"));
        String errorContent = "Parola nu este validă";
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorContent,errorMessage.getText());

    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}

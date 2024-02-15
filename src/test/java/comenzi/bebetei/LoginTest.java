package comenzi.bebetei;

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

public class LoginTest {
    WebDriver driver;
    String url = "https://comenzi.bebetei.ro/login";

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }
    @Parameters({"email", "password", "successMessage"})
    @Test
    public void login(String email, String password, String message) throws InterruptedException {
        WebElement emailInput = driver.findElement(By.id("auth-email"));
        emailInput.sendKeys(email);
        WebElement continuaButton = driver.findElement(By.id("auth-next-btn"));
        continuaButton.click();
        sleep(1000);

        WebElement passwordimput = driver.findElement(By.id("auth-login-password"));
        passwordimput.sendKeys(password);
        WebElement conecteazaButton =driver.findElement(By.id("auth-next-btn"));
        conecteazaButton.click();
        sleep(1000);

        WebElement wellcomeMessage= driver.findElement(By.id("header-links-left"));
        String headerContent = "Bun venit, Petran Cecilia!";
        Assert.assertTrue(wellcomeMessage.isDisplayed());
        Assert.assertEquals(headerContent,wellcomeMessage.getText());

        String dashboardUrl = "https://comenzi.bebetei.ro/dashboard";
        Assert.assertEquals(driver.getCurrentUrl(),dashboardUrl);

    }
    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}

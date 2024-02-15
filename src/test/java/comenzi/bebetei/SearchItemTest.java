package comenzi.bebetei;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static comenzi.bebetei.AddAdressTest.sleep;

public class SearchItemTest {
    WebDriver driver;
    String url = "https://comenzi.bebetei.ro/login";

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        WebElement emailInput = driver.findElement(By.id("auth-email"));
        emailInput.sendKeys("dummy199006@yahoo.com");
        WebElement continuaButton = driver.findElement(By.id("auth-next-btn"));
        continuaButton.click();
        sleep(1000);

        WebElement passwordimput = driver.findElement(By.id("auth-login-password"));
        passwordimput.sendKeys("Dummyisdummy24.");
        WebElement conecteazaButton = driver.findElement(By.id("auth-next-btn"));
        conecteazaButton.click();
        sleep(1000);
    }

    @Test
    public void SearchTest(){
        String itemName = "vitamina b12";

        WebElement selectsearch = driver.findElement(By.xpath("//input[@id='desktop-search']"));
        selectsearch.click();
        sleep(1000);

        selectsearch.sendKeys(itemName,"\n");
        sleep(1000);

        WebElement items = driver.findElement(By.xpath("//h1[normalize-space()='Rezultate cautare VITAMINA B12']"));
        Assert.assertTrue(items.getText().toLowerCase().contains(itemName));
    }
    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }
}

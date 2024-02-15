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

public class ModifyFavoriteListTest {
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
        WebElement conecteazaButton =driver.findElement(By.id("auth-next-btn"));
        conecteazaButton.click();
        sleep(1000);

    }
    @Test
    public void ModifyFavoriteList(){
        WebElement favorite = driver.findElement(By.xpath("//div[@class='container mt-mobile-inner']//li[3]//a[1]"));
        favorite.click();
        sleep(1000);

        WebElement selectList = driver.findElement(By.xpath("//div[@class='col-12 col-md-8 col-lg-9 col-sm-80']//a[1]//span[1]"));
        selectList.click();
        sleep(1000);

        WebElement modifylist = driver.findElement(By.xpath("//button[normalize-space()='Modifica lista']"));
        modifylist.click();
        sleep(1000);

        WebElement entername = driver.findElement(By.xpath("//input[@id='exampleFormControlInput1']"));
        entername.sendKeys("Pastilute","\n");
        sleep(1000);

        WebElement save = driver.findElement(By.xpath("//button[contains(text(),'Salvează')]"));
        save.click();
        sleep(1000);

        WebElement favoritelist = driver.findElement(By.xpath("//div[contains(text(),'Lista a fost actualizata.')]"));
        String expectedMessage = "Lista a fost actualizata.";
        Assert.assertTrue(favoritelist.getText().contains(expectedMessage));
    }
    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }
}

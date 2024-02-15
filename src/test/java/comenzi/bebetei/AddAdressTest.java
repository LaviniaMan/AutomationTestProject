package comenzi.bebetei;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Stack;

import static java.lang.Thread.sleep;

public class AddAdressTest {
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

    @Parameters({"name", "firstname", "phone", "email", "street", "streetnr", "zipcode"})
    @Test
    public void AddAdress(String name, String firstname, String phone, String email, String street, String streetnr, String zipcode) {

        WebElement adressimput = driver.findElement(By.xpath("//a[@href='https://comenzi.bebetei.ro/dashboard/address_book/shipping']"));
        adressimput.click();
        sleep(1000);

        WebElement adressButton = driver.findElement(By.xpath("//a[@class='btn btn-sm btn-primary me-2']"));
        adressButton.click();
        sleep(1000);

        WebElement nume = driver.findElement(By.id("lastname"));
        nume.sendKeys(name);
        sleep(1000);

        WebElement prenume = driver.findElement(By.id("firstname"));
        prenume.sendKeys(firstname);
        sleep(1000);

        WebElement telefon = driver.findElement(By.id("phone"));
        telefon.sendKeys(phone);
        sleep(1000);

        WebElement email2 = driver.findElement(By.id("email"));
        email2.sendKeys(email);
        sleep(1000);

        WebElement tara = driver.findElement(By.id("select2-country-container"));
        tara.click();
        sleep(1000);

        WebElement romania = driver.findElement(By.xpath("//input[@role='textbox']"));
        romania.sendKeys("Romania", "\n");
        sleep(1000);

        WebElement judet = driver.findElement(By.id("select2-county-container"));
        judet.click();
        sleep(1000);

        WebElement cluj = driver.findElement(By.xpath("//input[@role='textbox']"));
        cluj.sendKeys("Cluj", "\n");
        sleep(1000);

        WebElement oras = driver.findElement(By.id("select2-city-container"));
        oras.click();
        sleep(1000);

        WebElement clujoras = driver.findElement(By.xpath("//input[@role='textbox']"));
        clujoras.sendKeys("Cluj-Napoca");
        sleep(1000);
        clujoras.sendKeys(Keys.ENTER);
        sleep(1000);

        WebElement strada = driver.findElement(By.id("street"));
        strada.sendKeys(street);
        sleep(1000);

        WebElement numar = driver.findElement(By.id("streetnr"));
        numar.sendKeys(streetnr);
        sleep(1000);

        WebElement codPostal = driver.findElement(By.id("zipcode"));
        codPostal.sendKeys(zipcode);
        sleep(1000);

        WebElement salvare = driver.findElement(By.id("confirm-add-address"));
        salvare.click();
        sleep(3000);

        WebElement successmessage = driver.findElement(By.xpath("//div[contains(text(),'Adresa a fost adaugata.')]"));
        String expectedMessage = "Adresa a fost adaugata.";
        Assert.assertTrue(successmessage.getText().contains(expectedMessage));

        WebElement clickok = driver.findElement(By.xpath("//button[normalize-space()='ok']"));
        clickok.click();
        sleep(1000);

        String dashboardUrl = "https://comenzi.bebetei.ro/dashboard/address_book/shipping";
        Assert.assertEquals(driver.getCurrentUrl(),dashboardUrl);
    }

    public static void sleep(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

}

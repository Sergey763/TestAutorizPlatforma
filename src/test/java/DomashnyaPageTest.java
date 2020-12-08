import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DomashnyaPageTest {

    private WebDriver driver;
    private DomashnyaPage domashnyaPage;
    private String DOM_STR = "http://platform.dev.techranch.ru/";


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(DOM_STR);
        domashnyaPage = new DomashnyaPage(driver);
    }

    @Test(description = "PLAT-985 Пользователь при клике на кнопку вход в шапке происходит переход на страницу авторизации")
    public void plat985() {
        AutorizationPage autorizationPage = domashnyaPage.clickVhod();
        Assert.assertTrue(domashnyaPage.checkDisplayed());
    }

    @AfterMethod
    public void testDown() {
        driver.quit();
    }


}

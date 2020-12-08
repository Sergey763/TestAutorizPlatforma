import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AutorizationPageTest {

    private WebDriver driver;
    private AutorizationPage autorizationPage;
    private String AUTORIZ_STR = "http://platform.dev.techranch.ru/accounts/sign_in";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(AUTORIZ_STR);
        autorizationPage = new AutorizationPage(driver);
    }

    @Test(description = "PLAT-928 Пользователь, на страницу авторизации успешно авторизуется вводя валидные данные. Система переходит на страницу профиля")
    public void plat928() {
        ProfilePage profilePage = autorizationPage.autorization("test@techranch.ru", "test1234");
        profilePage.clickProfil();
        Assert.assertTrue(profilePage.exitDisplayed());
    }

    @Test(description = "PLAT-930 Пользователь, на страницу авторизации не может авторизоваться вводя не валидные данные")
    public void plat930() {
        ProfilePage profilePage = autorizationPage.autorization("faled@techranch.ru","faled1234");
        Assert.assertTrue(autorizationPage.textDisplayed());
    }

    @Test(description = "PLAT-931 Пользователь, на страницу авторизации не авторизуется вводя валидный email и не валидный пароль. Появляется сообщение под полем для ввода пароля: Неверный адрес email или пароль.")
    public void plat931() {
        ProfilePage profilePage = autorizationPage.autorization("test@techranch.ru", "faled1234");
        Assert.assertTrue(autorizationPage.textDisplayed());
    }

    @Test(description = "PLAT-951 Пользователь, на страницу авторизации не авторизуется оставляя поля Почта Пароль пустыми. Появляется сообщение под полем для ввода пароля: Неверный адрес email или пароль.")
    public void plat951() {
        ProfilePage profilePage = autorizationPage.autorization("", "");
        Assert.assertTrue(autorizationPage.textDisplayed());
    }

    @Test(description = "PLAT-953 Пользователь, на страницу авторизации не авторизуется вводя валидную почту и оставив поле пароль пустым. Появляется сообщение под полем для ввода пароля: Неверный адрес email или пароль.")
    public void plat953() {
        ProfilePage profilePage = autorizationPage.autorization("test@techranch.ru", "");
        Assert.assertTrue(autorizationPage.textDisplayed());
    }

    @AfterMethod
    public void testDown() {
        driver.quit();
    }


}

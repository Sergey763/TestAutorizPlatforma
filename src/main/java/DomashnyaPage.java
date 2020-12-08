import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DomashnyaPage {

    private WebDriver driver;

    public DomashnyaPage(WebDriver driver) {

        this.driver = driver;
    }

    private By buttonVhod = By.xpath("//div[@class=\"_1kFMl _1HmiM\"]//a");
    private By emailField = By.xpath("//input[@name=\"account[email]\"]");
    private By passwordField = By.xpath("//input[@id='account_password']");

    public AutorizationPage clickVhod() {
        driver.findElement(buttonVhod).click();
        return new AutorizationPage(driver);
    }

    public boolean checkDisplayed() {
        if (driver.findElement(emailField).isDisplayed() && driver.findElement(passwordField).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

}

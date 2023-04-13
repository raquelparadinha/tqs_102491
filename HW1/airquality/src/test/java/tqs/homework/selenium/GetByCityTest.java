package tqs.homework.selenium;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class GetByCityTest {
  @FindBy(id="city")
  private WebElement city;

  @FindBy(css=".h-5:nth-child(1)")
  private WebElement btn;

  @Test
  @Disabled
  public void getByCity() {
    System.setProperty("webdriver.gecko.driver", "/home/raquel/Downloads/geckodriver");
    WebDriver driver = new FirefoxDriver();
    driver.get("http://localhost:8000/");
    PageFactory.initElements(driver, this);

    city.click();
    city.sendKeys("London");
    btn.click();
  }
}

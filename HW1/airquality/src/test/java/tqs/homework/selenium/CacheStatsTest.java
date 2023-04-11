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
public class CacheStatsTest {
  @FindBy(linkText="Stats")
  private WebElement stats;

  @FindBy(linkText="Current Air Quality")
  private WebElement page;

  @FindBy(css=".py-12")
  private WebElement btn1;

  @FindBy(id="city")
  private WebElement city;
  
  @FindBy(css=".flex:nth-child(2) > .inline-block")
  private WebElement btn2;

  @FindBy(css="h3")
  private WebElement btn3;

  @Test
  public void cacheStats() {
    System.setProperty("webdriver.gecko.driver", "/home/raquel/Downloads/geckodriver");
    WebDriver driver = new FirefoxDriver();
    driver.get("http://localhost:8000/");
    PageFactory.initElements(driver, this);

    stats.click();
    page.click();
    btn1.click();
    city.click();
    city.sendKeys("London");
    btn2.click();
    btn3.click();
    stats.click();
  }
}

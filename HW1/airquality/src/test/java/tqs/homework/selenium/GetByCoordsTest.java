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
public class GetByCoordsTest {
  @FindBy(id="latitude")
  private WebElement lat;

  @FindBy(id="longitude")
  private WebElement lon;

  @FindBy(css=".flex:nth-child(1) > .inline-block")
  private WebElement btn;

  @Test
  public void getByCoords() {
    System.setProperty("webdriver.gecko.driver", "/home/raquel/Downloads/geckodriver");
    WebDriver driver = new FirefoxDriver();
    driver.get("http://localhost:8000/");
    PageFactory.initElements(driver, this);
    
    lat.click();
    lat.sendKeys("41.80631748656699");
    lon.click();
    lon.sendKeys("-6.756790700125621");
    btn.click();
  }
}

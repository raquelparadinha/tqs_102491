package tqs.lab4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class Lab42Test {

  private WebDriver driver;

  @FindBy(name="fromPort")
  private WebElement fromPort;

  @FindBy(xpath = "//option[. = 'Philadelphia']")
  private WebElement fromPortOption;

  @FindBy(name="toPort")
  private WebElement toPort;

  @FindBy(xpath = "//option[. = 'Rome']")
  private WebElement toPortOption;

  @FindBy(css=".btn-primary")
  private WebElement btn1;

  @FindBy(css="tr:nth-child(3) .btn")
  private WebElement btn2;

  @FindBy(name="inputName")
  private WebElement inputName;

  @FindBy(name="address")
  private WebElement address;

  @FindBy(name="city")
  private WebElement city;

  @FindBy(name="state")
  private WebElement state;

  @FindBy(name="zipCode")
  private WebElement zipCode;

  @FindBy(name="cardType")
  private WebElement cardType;

  @FindBy(css="option:nth-child(2)")
  private WebElement btn3;

  @FindBy(xpath = "//option[. = 'American Express']")
  private WebElement cardTypeOption;

  @FindBy(name="creditCardNumber")
  private WebElement creditCardNumber;

  @FindBy(name="nameOnCard")
  private WebElement nameOnCard;

  @FindBy(css=".checkbox")
  private WebElement btn4;

  @Test
  public void lab42(FirefoxDriver driver) {
    driver.get("https://blazedemo.com/");
    PageFactory.initElements(driver, this);
    
    fromPortOption.click();
    toPortOption.click();
    btn1.click();
    btn2.click();
    inputName.sendKeys("maria");
    address.sendKeys("praça de cima");
    city.sendKeys("amadora");
    state.sendKeys("amadora");
    zipCode.sendKeys("1234");
    cardTypeOption.click();
    btn3.click();
    creditCardNumber.sendKeys("123456");
    nameOnCard.sendKeys("maria");
    btn4.click();
    btn1.click();
    assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
  }
}

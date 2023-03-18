package tqs.lab5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

public class ReserveFlightSteps {
    
    private WebDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver = WebDriverManager.firefoxdriver().create();
        driver.get(url);
    }

    @And("I choose a ticket from {string} to {string}")
    public void flight(String departure, String destination) {
        driver.findElement(By.name("fromPort")).sendKeys(departure);
        driver.findElement(By.name("toPort")).sendKeys(destination);
    } 

    @And("I click Find Flights")
    public void iPressEnter() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I see {string}")
    public void iShouldSeeFlights(String results) {
        assertThat(driver.findElement(By.cssSelector("h3")).getText(), containsString(results));
    }

    @And("I choose a flight")
    public void iPressFlight() {
        driver.findElement(By.cssSelector("tr:nth-child(2) .btn")).click();

    }

    @Then("I see the message that the flight was reserved")
    public void iShouldSeeTheReservation() {
        assertThat(driver.findElement(By.cssSelector("h2")).getText(), containsString("has been reserved"));
    }
}

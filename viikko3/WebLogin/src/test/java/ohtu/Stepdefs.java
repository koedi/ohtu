package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Random;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    

    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void properNewUserCredentialsAreGiven(String username, String password) {
        Random r = new Random();
        username += r.nextInt(100000);
        createNewUser(username, password, password);
    }

    @When("a valid username {string} and a too short password {string} and matching password confimation are entered")
    public void validUsernameAndShortPasswordAreGiven(String username, String password) {
        Random r = new Random();
        username += r.nextInt(100000);
        createNewUser(username, password, password);
    }

    @When("a too short username {string} and password {string} and matching password confirmation are entered")
    public void tooShortUsernameAndValidPasswordAreGiven(String username, String password) {
        createNewUser(username, password, password);
    }

    @When("a valid username {string} and password {string} and non-matching password confirmation are entered")
    public void validUsernameAndValidPasswordButNonmatchingConfirmationAreGiven(String username, String password) {
        Random r = new Random();
        username += r.nextInt(100000);
        String password2 = password + r.nextInt(100000);
        createNewUser(username, password, password2);
    }

    @Then("a new user is created")
    public void newUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @Then("user is not created and error {string} is reported")
    public void invalidCredential(String message) {
        if (message.equals("username should have at least 3 characters"))
            pageHasContent("username should have at least 3 characters");

        if (message.equals("password should have at least 8 characters"))
            pageHasContent("password should have at least 8 characters");

        if (message.equals("password and password confirmation do not match"))
            pageHasContent("password and password confirmation do not match");
    }
    


    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    

    @When("incorrect username {string} and incorrect password {string} are given")
    public void incorrectUsernameAndIncorrectPasswordAreGive(String username, String password) {
        logInWith(username, password);
    }
    
    
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }
    
    private void createNewUser(String username, String password, String confirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);

      
        element.submit();  
        //try{ Thread.sleep(120000); } catch(Exception e){}
  
    }


}

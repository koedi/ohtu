package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        Random r = new Random();
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        

        /*
        //Original test
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        */

        /*
        //Correct username, incorrect password
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        */

        /*
        //New user creation
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        element = driver.findElement(By.name("username"));
        element.sendKeys("aku"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("ankka");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("ankka");

        sleep(2);
        element.submit();

        */

        //New user creation and logout
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        element = driver.findElement(By.name("username"));
        element.sendKeys("aku"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("ankka");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("ankka");

        sleep(2);
        element.submit();

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        sleep(3);

        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(2);

        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}

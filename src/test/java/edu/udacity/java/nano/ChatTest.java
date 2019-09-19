/*
* Tests for the chat application
* */

package edu.udacity.java.nano;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChatTest {

    private String userName = "jerry"; //username to sign in
    private String chat = "Hello"; //chat message to be sent

    private static WebDriver driver;

    @BeforeClass
    public static void navigate(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/edu/udacity/java/nano/chromedriver"); //setting the system properties to the path of the driver for the required browser
        driver = new ChromeDriver(); //using chrome driver
    }

    //login test
    //user should navigate to login page and login with username
    @Test
    public void test1() {
        driver.manage().window().maximize(); //setting up the browser in maximize mode
        driver.get("http://localhost:8080/"); //navigating to the login page
        WebElement username=driver.findElement(By.id("username"));
        WebElement login=driver.findElement(By.id("login"));
        //perform the action on the html element
        username.sendKeys(userName);
        login.click();
    }

    //userjoin test
    //confirms user is on the chat page and username is displayed
    @Test
    public void test2() {
        String actualUrl = "http://localhost:8080/index?username=jerry";
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        String actualUsername = driver.findElement(By.id("username")).getText();
        String expectedUsername = userName;
        Assert.assertEquals(actualUsername, expectedUsername);
    }

    //chat test
    //user should compose message and click on send and sent message should be seen in content
    @Test
    public void test3(){
        WebElement message =driver.findElement(By.id("msg"));
        WebElement send=driver.findElement(By.id("send"));
        //perform the action on the html elements
        message.sendKeys(chat);
        send.click();

        String actualMessage = driver.findElement(By.className("message-content")).getText();
        String expectedMessage = userName + ": " + chat;
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    //leave test
    //on click of the exit button user should be returned back to the login page
    @Test
    public void test4(){
        WebElement exit=driver.findElement(By.id("exit"));
        exit.click();
        String actualUrl = "http://localhost:8080/";
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        driver.quit();
    }
}

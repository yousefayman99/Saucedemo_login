package java_project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void ID_fields() {
        try {
            test = extent.createTest("ID_fields", "Check if login fields are present on the main screen");
            
            WebElement usernameField = driver.findElement(By.id("user-name"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));
            
            Assert.assertNotNull(usernameField, "Username field is not present");
            Assert.assertNotNull(passwordField, "Password field is not present");
            Assert.assertNotNull(loginButton, "Login button is not present");
            //System.out.println("passed.");
            test.pass("Username, Password fields and Login button are present on the main screen");
        } catch (Exception e) {
        	
            e.printStackTrace();
            test.fail(e.getMessage());
        }
    }

    @Test
    public void Valid_login() {
        try {
            test = extent.createTest("Valid_login", "Check if valid credentials work");

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            WebElement logoDiv = driver.findElement(By.cssSelector(".app_logo"));
            String logoText = logoDiv.getText();
            
            Assert.assertEquals(logoText, "Swag Labs", "Login failed with valid credentials");
            
            test.pass("Login successful with valid credentials");
        } catch (Exception e) {
        	
            e.printStackTrace();
            test.fail(e.getMessage());
        }
    }

    @Test
    public void InValid_login() {
        try {
            test = extent.createTest("InValid_login", "Check if invalid credentials are handled properly");

            driver.findElement(By.id("user-name")).sendKeys("standard_user1");
            driver.findElement(By.id("password")).sendKeys("secret_sauce1");
            driver.findElement(By.id("login-button")).click();

            WebElement errorMessage = driver.findElement(By.cssSelector(".error-message-container.error"));
            String errorText = errorMessage.getText();
            
            Assert.assertTrue(errorText.contains("Epic sadface: Username and password do not match any user in this service"), "Incorrect error message for invalid credentials");
            
            test.pass("Error message is displayed correctly for invalid credentials");
        } catch (Exception e) {
        	
            e.printStackTrace();
            test.fail(e.getMessage());
        }
    }

    @Test
    public void testEmptyUsername() {
        try {
            test = extent.createTest("testEmptyUsername", "Check if empty username is handled properly");

            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            WebElement errorMessage = driver.findElement(By.cssSelector(".error-message-container.error"));
            String errorText = errorMessage.getText();
            
            Assert.assertTrue(errorText.contains("Epic sadface: Username is required"), "Incorrect error message for empty username");
            
            test.pass("Error message is displayed correctly for empty username");
        } catch (Exception e) {
        	
            e.printStackTrace();
            test.fail(e.getMessage());
        }
    }

    @Test
    public void testEmptyPassword() {
        try {
            test = extent.createTest("testEmptyPassword", "Check if empty password is handled properly");

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("login-button")).click();

            WebElement errorMessage = driver.findElement(By.cssSelector(".error-message-container.error"));
            String errorText = errorMessage.getText();
            
            Assert.assertTrue(errorText.contains("Epic sadface: Password is required"), "Incorrect error message for empty password");
            
            test.pass("Error message is displayed correctly for empty password");
        } catch (Exception e) {
        	
            e.printStackTrace();
            test.fail(e.getMessage());
        }
    }
}

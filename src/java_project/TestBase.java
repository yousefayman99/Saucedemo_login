package java_project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestBase {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeClass
    public void setUpExtent() {
        try {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html"); // Specify report file name if needed
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            System.out.println("Extent report setup completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUp() {
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com"); // Replace with your actual URL
            System.out.println("WebDriver setup completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
            System.out.println("WebDriver tear down completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDownExtent() {
        try {
            if (extent != null) {
                extent.flush();
            }
            System.out.println("Extent report tear down completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

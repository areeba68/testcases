import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class WebAppTest {
    private WebDriver driver;
    @Before
    public void setUp() {
        // Set up Chrome options for headless mode
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200");
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver(chromeOptions);
    }
    @Test
    public void testUserRegistration() {
        // Navigate to the registration page
        driver.get("http://localhost:8080/register");

        // Fill in registration form
        driver.findElement(By.id("name")).sendKeys("John Doe");
        driver.findElement(By.id("email")).sendKeys("john@example.com");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("confirmPassword")).sendKeys("password123");
        driver.findElement(By.id("registerBtn")).click();
        // Verify successful registration
        assertTrue(driver.getCurrentUrl().contains("success"));
    }
    @Test
    public void testUserLogin() {
        // Navigate to the login page
         driver.get("http://localhost:8080/login");

        // Fill in login form
        driver.findElement(By.id("email")).sendKeys("john@example.com");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("loginBtn")).click();
        // Verify successful login
        assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }
    @Test
    public void testInvalidUserLogin() {
        // Navigate to the login page
        driver.get("http://localhost:8080/login");
        // Fill in login form with invalid credentials
        driver.findElement(By.id("email")).sendKeys("nonexistent@example.com");
        driver.findElement(By.id("password")).sendKeys("invalidpassword");
        driver.findElement(By.id("loginBtn")).click();
        // Verify error message for invalid login
        assertTrue(driver.getPageSource().contains("Invalid credentials"));
    }
    @Test
    public void testLogout() {
        // Assume user is already logged in
        driver.get("http://localhost:8080/dashboard");
        // Perform logout
        driver.findElement(By.id("logoutBtn")).click();
        // Verify redirection to the login page after logout
        assertTrue(driver.getCurrentUrl().contains("login"));
    }
    @After
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}

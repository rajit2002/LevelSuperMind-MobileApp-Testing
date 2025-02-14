package LevelSuperMind;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import LevelSuperMind_cap.cap_LevelSuperMind;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginFunctionalityTest extends cap_LevelSuperMind {

    public AndroidDriver<AndroidElement> driver;

    // Setup method to initialize the Appium driver before tests
    @BeforeTest
    public void setup() throws MalformedURLException {
        driver = cap();  // Call the method to set up Appium capabilities
        // Set an implicit wait for driver to manage timeouts
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    // Test case for positive login functionality
    @Test
    public void testPositiveLogin() throws InterruptedException {
        // Step 1: Confirm that the Level Super Mind app has launched
        System.out.println("Level Super Mind app launched...");

        // Step 2: Wait explicitly for the app to load (adjust wait time as necessary)
        Thread.sleep(30000);

        // Step 3: Click the "Sign" button to initiate the login process
        driver.findElement(MobileBy.className("android.widget.Button")).click();
        System.out.println("Clicked on the 'Sign' button...");

        // Step 4: Select the "Email" option for logging in
        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Email']")).click();
        System.out.println("Clicked on the 'Email' option...");

        // Step 5: Enter a valid email address for login
        driver.findElement(MobileBy.className("android.widget.EditText")).sendKeys("rajitgautam2002@gmail.com");
        System.out.println("Entered the email address...");

        // Step 6: Click the "Proceed" button to continue to the next step
        driver.findElement(MobileBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]")).click();
        System.out.println("Clicked on the 'Proceed' button...");

        // Step 7: Switch to the gmail app to retrieve the OTP sent for verification
        System.out.println("Switching to Gmail to retrieve OTP...");
        driver.activateApp("com.google.android.gm");  // Activate the gmail app
        Thread.sleep(3000);

        // Step 8: Locate and click on the verification email
        driver.findElement(MobileBy.xpath("//android.widget.TextView[@resource-id='com.google.android.gm:id/subject' and @text='Verification E-mail for Level App']")).click();
        System.out.println("Clicked on the email with subject 'Verification E-mail for Level App'");
        Thread.sleep(4000);

        // Step 9: Extract the OTP from the email content
        String otpText = driver.findElement(MobileBy.xpath("//android.widget.TextView[contains(@text, 'Hey, Please use the verification code')]")).getText();
        
        // Use regex to find the 6-digit OTP in the text
        Pattern otpPattern = Pattern.compile("\\b\\d{6}\\b");
        Matcher matcher = otpPattern.matcher(otpText);

        String otpCode = "";
        if (matcher.find()) {
            otpCode = matcher.group(0); // Capture the OTP
            System.out.println("Extracted OTP: " + otpCode);
        }
        
        // Step 10: Switch back to the Level Super Mind app without resetting its state
        driver.activateApp("level.game");  // Replace with your app's package name

        // Step 11: Locate the OTP input field and enter the retrieved OTP
        driver.findElement(MobileBy.className("android.widget.EditText")).sendKeys(otpCode);
        System.out.println("OTP entered in the app.");
        
        // Step 12: Click the 'Proceed' button to log in with the OTP
        driver.findElement(MobileBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]")).click();
        System.out.println("Clicked on the 'Proceed' button...");

        // Final confirmation of successful login
        System.out.println("Login successful!");
    }
    
    
    
 // Test case for negative login functionality with an invalid email (unable to click "Proceed" button)
    @Test
    public void testNegativeLoginProceedButtonDisabledWithInvalidEmail() throws InterruptedException {
        // Step 1: Confirm that the Level Super Mind app has launched
        System.out.println("Level Super Mind app launched...");

        // Step 2: Wait explicitly for the app to load (adjust wait time as necessary)
        Thread.sleep(30000);

        // Step 3: Click the "Sign" button to initiate the login process
        driver.findElement(MobileBy.className("android.widget.Button")).click();
        System.out.println("Clicked on the 'Sign' button...");

        // Step 4: Select the "Email" option for logging in
        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Email']")).click();
        System.out.println("Clicked on the 'Email' option...");

        // Step 5: Enter an invalid email address to test the button's disabled state
        driver.findElement(MobileBy.className("android.widget.EditText")).sendKeys("rajitgautam2002gmail.com");  // Missing "@"
        System.out.println("Entered an invalid email address...");

        // Step 6: Attempt to click the "Proceed" button and check if it's enabled
        AndroidElement proceedButton = driver.findElement(MobileBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]"));
        
        // Check if the "Proceed" button is enabled
        if (!proceedButton.isEnabled()) {
            System.out.println("Proceed button is disabled as expected for an invalid email.");
        } else {
            System.out.println("Unexpected behavior - Proceed button is enabled for an invalid email.");
        }

        // Final confirmation for the negative test case result
        System.out.println("Negative test case completed - Proceed button disabled check with invalid email completed.");
    }

    }

    


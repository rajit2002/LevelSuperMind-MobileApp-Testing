package LevelSuperMind_cap;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class cap_LevelSuperMind {

    // This method sets up the desired capabilities for Appium
    @SuppressWarnings("deprecation")
	public static AndroidDriver<AndroidElement> cap() throws MalformedURLException {
        
        DesiredCapabilities dc = new DesiredCapabilities();
        
        // Set the device and app capabilities
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "CWdevice");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "level.game");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "level.game.utils.HiltPreviewActivity");
        
        // Set timeouts to handle device latency
        dc.setCapability("newCommandTimeout", 900);
        dc.setCapability("adbExecTimeout", 60000);  // 60 seconds ADB timeout
        
        // Prevent app from resetting between sessions
        dc.setCapability(MobileCapabilityType.NO_RESET, true);
        dc.setCapability(MobileCapabilityType.FULL_RESET, false);
        
        // Initialize the AndroidDriver and connect to Appium server
        return new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
    }
}

package common.drivers;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Objects;

public class SingletonDriver {

    private SingletonDriver() {
    }

    private static WebDriver driver;

    private static WebDriver getWebDriver(BrowserType type, boolean proxyEnabled) {
        if (driver == null) {
            Proxy proxy = null;
            if (proxyEnabled) {
                System.out.println("PROXY ENABLED!!");
                proxy = getProxySettings();
            }
            switch (type) {
                case FIREFOX: {
                    System.setProperty("webdriver.gecko.driver", "gecko/geckodriver_x64.exe");
                    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                    if(Objects.nonNull(proxy)) {
                        capabilities.setCapability(CapabilityType.PROXY, proxy);
                    }
                    FirefoxOptions options = new FirefoxOptions();
                    options.setAcceptInsecureCerts(true);
                    //Below line is for zap proxy
                    options.merge(capabilities);
                    driver = new FirefoxDriver(options);
                    break;
                }
                case CHROME: {
                    System.setProperty("webdriver.chrome.driver", "chrome/chromedriver.exe");
                    ChromeOptions co = new ChromeOptions();
                    driver = new ChromeDriver(co);
                    break;
                }
            }
        }
        return driver;
    }

    public static WebDriver getDriver() {
        String browserName = System.getProperty("browser", "firefox");
        //assuming name is coming as correct
        BrowserType type = BrowserType.fromName(browserName);
        boolean proxyEnabled = Boolean.valueOf(System.getProperty("proxy.enabled", "FALSE"));
        return getWebDriver(type, proxyEnabled);
    }

    private static Proxy getProxySettings() {
        String proxyPath = System.getProperty("proxy.path", "localhost:11111");
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyPath);
        proxy.setNoProxy(null);
        return proxy;
    }

}

package com.eudonet.hooks;

import com.eudonet.constants.DriverType;
import com.eudonet.context.TestContext;
import com.eudonet.factory.DriverManagerFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private WebDriver driver;
    private TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void before() {
        String browser = System.getProperty("browser", String.valueOf(DriverType.CHROME));
        driver = DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver();
        context.driver = driver;
    }

    @After
    public void after() {
        driver.quit();
    }
}

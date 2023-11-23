package com.eudonet.context;

import com.eudonet.objects.ChartDetails;
import com.eudonet.objects.Cookies;
import com.eudonet.objects.LoginDetails;
import com.eudonet.objects.User;
import org.openqa.selenium.WebDriver;

public class TestContext {
    public WebDriver driver;
    public Cookies cookies;

    public LoginDetails loginDetails;

    public User user;

    public ChartDetails chartDetails;

    public TestContext() {
        cookies = new Cookies();
        cookies.setCookies(new io.restassured.http.Cookies());
    }
}

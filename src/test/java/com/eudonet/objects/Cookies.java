package com.eudonet.objects;

import com.eudonet.utils.CookieUtils;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.List;

@Getter
@Setter
public class Cookies {
    private io.restassured.http.Cookies cookies;

    public void injectCookiesToBrowser(WebDriver driver) {
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for (Cookie cookie : seleniumCookies) {
            driver.manage().addCookie(cookie);
        }
        driver.navigate().refresh();
    }
}

package com.eudonet.api;

import com.eudonet.constants.EndPoint;
import com.eudonet.utils.ConfigLoader;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoginApi {

    private Cookies cookies;
    private Map<String, String> tags;

    public LoginApi(Cookies cookies) {
        this.cookies = cookies;
    }

    public Cookies getCookies() {
        return cookies;
    }

    public String fetchValues(Response response, String tagName) {
        String elem = null;
        Document document = Jsoup.parse(response.body().prettyPrint(), "", Parser.xmlParser());
        for (Element e : document.select(tagName)) {
            elem = e.text();
        }
        return elem;
    }

    public Response subscribe() {
        Cookies cookies = new Cookies();

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("action", "authsubscriber");

        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        Map<String, Object> formParams = new HashMap<>();
        formParams.put("SubscriberLogin", "eudonet");
        formParams.put("SubscriberPassword", "eudonet");
        formParams.put("rememberme", 1);
        formParams.put("_processid", "undefined");

        Response response = ApiRequest.post(EndPoint.APILOGIN.url, queryParams, headers, formParams, cookies);
        if (response.getStatusCode() != 200) {
            throw  new RuntimeException("Failed to login...");
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }

    public Response login() {
        Cookies cookies = new Cookies();
        Response subscribeResponse = subscribe();
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("action", "authuser");
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("UserLogin", "ADMINISTRATEUR");
        formParams.put("UserPassword", "EudonetPentalog2023");
        formParams.put("dbt", fetchValues(subscribeResponse, "dbtoken"));
        formParams.put("rememberme", "1");
        formParams.put("st", fetchValues(subscribeResponse, "subscribertoken"));
        formParams.put("LogUserListEnabled", "0");
        formParams.put("Height", "1032");
        formParams.put("Width", "1920");
        formParams.put("forceactivelang", "1");
        formParams.put("_processid", "undefined");


        Response response =
                given()
                        .baseUri(ConfigLoader.getInstance().getBaseUrl()).log().all()
                        .headers(headers)
                        .formParams(formParams)
                        .queryParams(queryParams)
                        .cookies(cookies)
                        .when()
                        .post(EndPoint.APILOGIN.url)
                        .then()
                        .log().all()
                        .extract().response();
        if (response.getStatusCode() != 200) {
            throw  new RuntimeException("Failed to login...");
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}

package com.eudonet.customtypes;

import com.eudonet.objects.ChartDetails;
import com.eudonet.objects.LoginDetails;
import com.eudonet.objects.User;
import io.cucumber.java.DataTableType;

import java.util.Map;


public class CustomDataTableType {

    @DataTableType
    public LoginDetails loginDetailsLabelsText(Map<String, String> entry) {
        return new LoginDetails(entry.get("logo"), entry.get("client_area"), entry.get("client_password"),
                entry.get("your_database"), entry.get("username"), entry.get("password"),
                entry.get("forgot_password"), entry.get("loginBtn"));
    }

    @DataTableType
    public User userEntry(Map<String, String> entry) {
        return new User(entry.get("client_area"), entry.get("client_password"), entry.get("your_database"),
                        entry.get("username"), entry.get("password"));
    }

    @DataTableType
    public ChartDetails chartEntry(Map<String, String> entry) {
        return new ChartDetails(entry.get("label1_X"), entry.get("label2_X"), entry.get("label3_X"),
                                entry.get("field1_X"), entry.get("field2_X"),
                                entry.get("value1_Y"), entry.get("value2_Y"));
    }
}

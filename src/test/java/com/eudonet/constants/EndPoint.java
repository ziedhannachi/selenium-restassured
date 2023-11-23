package com.eudonet.constants;

public enum EndPoint {

    LOGIN("/elogin.aspx"),
    APILOGIN("/mgr/eLoginMgr.ashx"),
    HOME("/eMain.aspx");


    public final String url;

    EndPoint(String url) {
        this.url = url;
    }
}

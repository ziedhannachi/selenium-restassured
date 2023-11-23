package com.eudonet.constants;

public enum OfficeVersion {
    Office_2019_64("Office 2019 64 bits"),
    Office_2019_32("Office 2019 32 bits"),
    Office_2016_64("Office 2016 64 bits"),
    Office_2016_32("Office 2016 32 bits"),
    Office_2013_64("Office 2013 64 bits"),
    Office_2013_32("Office 2013 32 bits"),
    Office_2010_64("Office 2010 64 bits"),
    Office_2007_2010("Office 2007, 2010"),
    Office_for_MAC("Microsoft Office for Mac 2011"),
    Libre_Office("LibreOffice 5, 6 and 7"),
    Open_Office("Open Office 3 & 4");

    public final String name;

    OfficeVersion(String name) {
        this.name = name;
    }
}

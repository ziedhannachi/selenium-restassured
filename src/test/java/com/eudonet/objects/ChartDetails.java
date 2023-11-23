package com.eudonet.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChartDetails {

    private String label1_X;
    private String label2_X;
    private String label3_X;
    private String field1_X;
    private String field2_X;
    private String value1_Y;
    private String value2_Y;

    public ChartDetails(String label1_X, String label2_X, String label3_X, String field1_X, String field2_X,
                        String value1_Y, String value2_Y) {
        this.label1_X = label1_X;
        this.label2_X = label2_X;
        this.label3_X = label3_X;
        this.field1_X = field1_X;
        this.field2_X = field2_X;
        this.value1_Y = value1_Y;
        this.value2_Y = value2_Y;
    }
}

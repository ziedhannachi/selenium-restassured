package com.eudonet.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Business {
    private String name;
    private String product;
    private String type;
    private String source;
    private String decision;

    public Business(String name, String products, String type, String source, String decision) {
        this.name = name;
        this.product = products;
        this.type = type;
        this.source = source;
        this.decision = decision;
    }
}

package com.eudonet.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDetails {
    private String logoLabel;
    private String clientAreaLabel;
    private String clientPasswordLabel;
    private String yourDataBaseLabel;
    private String yourUsernameLabel;
    private String passwordLabel;
    private String forgotYourPasswordLink;
    private String loginBtnText;

    public LoginDetails(String logoLabel, String clientAreaLabel, String clientPasswordLabel,
                        String yourDataBaseLabel, String yourUsernameLabel, String passwordLabel,
                        String forgotYourPasswordLink, String loginBtnText) {
        this.logoLabel = logoLabel;
        this.clientAreaLabel = clientAreaLabel;
        this.clientPasswordLabel = clientPasswordLabel;
        this.yourDataBaseLabel = yourDataBaseLabel;
        this.yourUsernameLabel = yourUsernameLabel;
        this.passwordLabel = passwordLabel;
        this.forgotYourPasswordLink = forgotYourPasswordLink;
        this.loginBtnText = loginBtnText;
    }
}

package ie.tcd.paintsplat.painsplatapp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude()
public class loginCredentials {
    private String userId;
    private String password;

    public loginCredentials(String userId, String password) {
        this.password = password;
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

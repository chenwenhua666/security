package com.plm.security.core.properties;

/**
 * @author : cwh
 * 2019/8/28 0028
 * description ：
 */
public class Oanth2Properties {

    private String jwtSigningKey = "plm";

    private Oauth2ClientProperties[] clients = {};

    public Oauth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(Oauth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}

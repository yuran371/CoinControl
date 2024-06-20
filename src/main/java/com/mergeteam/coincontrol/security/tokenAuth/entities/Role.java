package com.mergeteam.coincontrol.security.tokenAuth.entities;

public enum Role {
    ROLE_USER("ROLE_USER"),

    ROLE_BANNED("ROLE_BANNED"),
    ROLE_UNACTIVATED("ROLE_UNACTIVATED"),

    ROLE_LOGOUT("ROLE_LOGOUT");
//    ROLE_JWT_REFRESH("ROLE_JWT_REFRESH");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
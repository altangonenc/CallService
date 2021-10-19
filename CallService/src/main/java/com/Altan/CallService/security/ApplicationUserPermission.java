package com.Altan.CallService.security;

public enum ApplicationUserPermission {
    CALL_READ("call:read"),
    CALL_WRITE("call:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");



    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission=permission;
    }

    public String getPermission(){
        return permission;
    }


}

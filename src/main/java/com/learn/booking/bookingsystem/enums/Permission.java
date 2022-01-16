package com.learn.booking.bookingsystem.enums;

public enum Permission {

    CUSTOMER_READ("customer_read"),
    CUSTOMER_WRITE("customer_write"),
    EMPLOYEE_READ("employee_read"),
    EMPLOYEE_WRITE("employee_write"),
    ADMIN_WRITE("admin_write");

    public String getPermission() {
        return permission;
    }

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}

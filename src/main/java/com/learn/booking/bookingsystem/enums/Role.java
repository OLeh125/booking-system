package com.learn.booking.bookingsystem.enums;

import java.util.Set;
import java.util.stream.Collectors;


public enum Role {

    CLIENT(Set.of(Permission.CUSTOMER_READ, Permission.CUSTOMER_WRITE)),
    MANAGER(Set.of(Permission.CUSTOMER_READ, Permission.CUSTOMER_WRITE, Permission.EMPLOYEE_WRITE, Permission.EMPLOYEE_READ)),
    ADMIN(Set.of(Permission.CUSTOMER_READ, Permission.CUSTOMER_WRITE, Permission.EMPLOYEE_WRITE, Permission.EMPLOYEE_READ, Permission.ADMIN_WRITE));

    public Set<Permission> getPermissions() {
        return permissions;
    }

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

//    public Set<SimpleGrantedAuthority> getAuthorities(){
//        return getPermissions().stream()
//            .map(p-> new SimpleGrantedAuthority(p.getPermission()))
//            .collect(Collectors.toSet());
//    }

}

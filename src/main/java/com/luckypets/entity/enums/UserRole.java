package com.luckypets.entity.enums;


public enum UserRole {

    ROLE_ADMIN,
    ROLE_USER,
    ROLE_ANONYMOUS;
    UserRole() {
    }

    /**
     * @return String value of enum without prefix "ROLE_"
     * spring java config automatically adds it, so this method
     * removes prefix to use IDE support
     * Example:
     * UserRole.ROLE_ADMIN.configValue() instead of "ADMIN",
     * where mistake can be made
     */
    public String configValue() {
        return name().substring(5);
    }
}

package com.basejava.webapp.model;

/**
 * Initial resume class
 */
public class Resume {

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    // Unique identifier
    private String uuid;

    @Override
    public String toString() {
        return uuid;
    }
}

package org.aleks4ay.jms.model;

import org.aleks4ay.jms.util.Constants;

public class User {

    private final int userId;
    private final String name;

    public User(String name) {
        this.userId = Constants.getNextUserId();
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}

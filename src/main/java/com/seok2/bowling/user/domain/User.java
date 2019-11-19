package com.seok2.bowling.user.domain;

import java.util.Objects;

public class User {

    private static final int MAX_SIZE = 3;

    private final String name;

    private User(String name) {
        this.name = name;
    }

    public static User of(String name) {
        validate(name);
        return new User(name);
    }

    private static void validate(String name) {
        if (name == null || name.length() != MAX_SIZE) {
            throw new IllegalArgumentException("사용자 이름은 3글자로 지정되어야 합니다.");
        }
    }

    String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

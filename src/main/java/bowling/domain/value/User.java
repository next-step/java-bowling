package bowling.domain.value;

import java.util.Objects;

public class User {
    private static final String NAME_LENGTH_ERROR_MSG = "이름의 길으는 3글자를 초과 할 수 없습니다!!!";
    private static final int NAME_MAX_LENGTH = 3;

    private final String name;

    public User(String name) {

        if(name == null && name.trim().isEmpty()) {
            throw new NullPointerException("빈 값과 null값은 입력 하실수 없습니다!!!");
        }

        if(name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(NAME_LENGTH_ERROR_MSG);
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

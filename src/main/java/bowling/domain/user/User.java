package bowling.domain.user;

import java.util.Objects;

public class User {

    private final Name name;

    private User(Name name) {
        this.name = name;
    }

    public static User of(String name) {
        return new User(Name.of(name));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
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

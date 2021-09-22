package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {

    private static final String NAME_PATTERNS = "^[a-zA-Z]*$";
    public static final int MAX_NAME_LENGTH = 3;
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player from(String name) {
        nameValidate(name);

        return new Player(name);
    }

    private static void nameValidate(String name) {
        if (name.length() > MAX_NAME_LENGTH || !Pattern.matches(NAME_PATTERNS, name)) {
            throw new IllegalArgumentException("3 english letters");
        }
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player person = (Player) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}

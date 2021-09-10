package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Person {

    private static final String NAME_PATTERNS = "^[a-zA-Z]*$";
    private final String name;

    private Person(String name) {
        this.name = name;
    }

    public static Person from(String name) {
        nameValidate(name);

        return new Person(name);
    }

    private static void nameValidate(String name) {
        if (name.length() > 3 || !Pattern.matches(NAME_PATTERNS, name)) {
            throw new IllegalArgumentException("3 english letters");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String name() {
        return name;
    }
}

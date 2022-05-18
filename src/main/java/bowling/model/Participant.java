package bowling.model;

import bowling.utility.Assert;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Participant {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{3}$");

    private final String name;

    private Participant(String name) {
        Assert.hasText(name, "name must not be blank");
        Assert.isTrue(NAME_PATTERN.matcher(name).matches(), String.format("name(%s) must be 3 english letters", name));
        this.name = name;
    }

    public static Participant from(String name) {
        return new Participant(name);
    }

    public String name() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                '}';
    }
}

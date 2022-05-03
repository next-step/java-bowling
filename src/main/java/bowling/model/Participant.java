package bowling.model;

import bowling.utility.Assert;

import java.util.regex.Pattern;

public final class Participant {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{3}$");

    private final String name;

    private Participant(String name) {
        Assert.hasText(name, "name must not be blank");
        validatePattern(name);
        this.name = name;
    }

    public static Participant from(String name) {
        return new Participant(name);
    }

    public String name() {
        return name;
    }

    private void validatePattern(String value) {
        if (!NAME_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException(String.format("name(%s) must be 3 english letters", value));
        }
    }
}

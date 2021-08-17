package bowling.domain.common;

import bowling.domain.common.exception.NameCreateException;
import java.util.regex.Pattern;

public class Name {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{3}$");

    private final String name;

    private Name(final String name) {
        nameValidation(name);
        this.name = name;
    }

    public static Name of(final String name) {
        return new Name(name);
    }

    private void nameValidation(final String name) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new NameCreateException();
        }
    }
}

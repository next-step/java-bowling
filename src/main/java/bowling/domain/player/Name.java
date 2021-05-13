package bowling.domain.player;

import bowling.exception.InvalidNameSizeException;
import bowling.exception.NameIncludeVariableLanguageException;
import bowling.exception.StringNullPointerException;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public final class Name {

    private static final String REGEX = "[^a-zA-Z]";
    private static final int MAXIMUM_NAME_SIZE = 3;

    private final String name;

    public Name(final String name) {
        validateNull(name);
        validateSize(name);
        validateAlphabet(name);
        this.name = name;
    }

    private final void validateNull(final String name) {
        if (Objects.isNull(name)) {
            throw new StringNullPointerException();
        }
    }

    private final void validateSize(final String name) {
        if (name.isEmpty() || name.length() > MAXIMUM_NAME_SIZE) {
            throw new InvalidNameSizeException();
        }
    }

    private final void validateAlphabet(final String name) {
        if (!replaceSpecialCharacters(name).equals(name)) {
            throw new NameIncludeVariableLanguageException();
        }
    }

    private final String replaceSpecialCharacters(final String name) {
        return name.replaceAll(REGEX, Strings.EMPTY);
    }


    public final String name() {
        return name;
    }
}

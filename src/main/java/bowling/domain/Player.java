package bowling.domain;

import bowling.exception.NameIncludeOtherLanguagesException;
import bowling.exception.NameSizeMissMatchException;

public final class Player {

    private static final String EMPTY = "";
    private static final String REGEX = "[^a-zA-Z]";
    private static final int STANDARD_SIZE = 3;

    private final String name;

    public static final Player from(final String name) {
        return new Player(name);
    }

    private Player(final String name) {
        validateSize(name);
        validateAlphabet(name);
        this.name = name.toUpperCase();
    }

    private final void validateSize(final String name) {
        if (name.length() > STANDARD_SIZE) {
            throw new NameSizeMissMatchException(name);
        }
    }

    private final void validateAlphabet(final String name) {
        if (!replaceSpecialCharacters(name).equals(name)) {
            throw new NameIncludeOtherLanguagesException(name);
        }
    }

    private final String replaceSpecialCharacters(final String name) {
        return name.replaceAll(REGEX, EMPTY);
    }

    public final String name() {
        return name;
    }
}

package bowling.domain;

import bowling.exception.NameSizeMissMatchException;

public final class Player {

    private static final int STANDARD_SIZE = 3;

    private final String name;

    public static final Player from(final String name) {
        return new Player(name);
    }

    private Player(final String name) {
        validateName(name);
        this.name = name.toUpperCase();
    }

    private final void validateName(final String name) {
        if(name.length() > STANDARD_SIZE) {
            throw new NameSizeMissMatchException(name);
        }
    }

}

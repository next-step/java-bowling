package bowling.domain;

import bowling.exception.NameSizeMissMatchException;

public final class Player {

    private static final int NAME_SIZE_MAXIMUM = 3;

    private final String name;

    public static final Player from(final String name) {
        return new Player(name);
    }

    private Player(final String name) {
        validateSize(name);
        this.name = name.toUpperCase();
    }

    private final void validateSize(final String name) {
        if (name.isEmpty() || name.length() > NAME_SIZE_MAXIMUM) {
            throw new NameSizeMissMatchException(name);
        }
    }


}

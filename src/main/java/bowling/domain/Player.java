package bowling.domain;

import bowling.exception.BadNameException;

public class Player {
    private final String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!name.matches("^[a-zA-Z]{3}$")) {
            throw BadNameException.getInstance();
        }
    }
}

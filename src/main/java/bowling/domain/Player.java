package bowling.domain;

import bowling.exception.BadNameException;

public class Player {
    private final String name;
    private final Rolls rolls = new Rolls();

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    void play(Roll roll) {
        rolls.add(roll);
    }

    Board board() {
        return Board.of(
                rolls.toIntegers()
        );
    }

    private void validate(String name) {
        if (!name.matches("^[a-zA-Z]{3}$")) {
            throw BadNameException.getInstance();
        }
    }
}

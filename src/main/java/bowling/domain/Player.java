package bowling.domain;

import bowling.exception.PlayerLengthOutOfBoundException;
import bowling.exception.PlayerNameNullPointerException;

public class Player {

    private final String name;

    public Player(String name) throws PlayerLengthOutOfBoundException {
        valid(name);
        this.name = name;
    }

    public String player() {
        return name;
    }

    private void valid(String name) throws PlayerLengthOutOfBoundException {
        if (name == null || name.isEmpty()) {
            throw new PlayerNameNullPointerException();
        }

        if (name.length() != 3) {
            throw new PlayerLengthOutOfBoundException(name.length());
        }
    }

}

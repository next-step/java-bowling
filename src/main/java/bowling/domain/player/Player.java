package bowling.domain.player;

import bowling.domain.player.exception.NameEngException;
import bowling.domain.player.exception.NameLengthException;

import java.util.Optional;

public class Player {

    private final String name;

    private Player(String name) {
        checkNameLength(name);
        checkLowerCase(name);

        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name);
    }

    private static void checkNameLength(String name) {
        Optional.of(name)
                .map(String::length)
                .filter(length -> 0 < length)
                .filter(length -> length <= 3)
                .orElseThrow(NameLengthException::new);

    }

    private static void checkLowerCase(String name) {
        name.chars()
                .filter(c -> (65 <= c && c <= 90) || (97 <= c && c <= 122))
                .reduce(Integer::sum)
                .orElseThrow(NameEngException::new);
    }

    public String name() {
        return this.name;
    }
}

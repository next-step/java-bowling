package bowling.domain;

import bowling.exception.InvalidNameException;
import bowling.util.LanguageUtil;

import java.util.Objects;

public class Player {

    private static final int NAME_LENGTH = 3;

    private final String name;

    public Player(String name) {
        if (validateName(name)) {
            throw new InvalidNameException();
        }
        this.name = name;
    }

    private boolean validateName(String name) {
        return name.length() != NAME_LENGTH || !LanguageUtil.isEnglish(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

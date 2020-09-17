package bowling.player;

import bowling.frame.Frames;
import bowling.global.utils.ExceptionMessage;
import bowling.global.exception.NotMatchingPlayerNameException;

import java.util.Objects;

import static bowling.global.utils.CommonConstant.NUMBER_THREE;

public class Player {

    private String name;
    private Frames frames;

    public Player(String name, Frames frames) {
        validatePlayerNameisNull(name);
        validatePlayerNameLength(name);
        this.name = name.toUpperCase();
        this.frames = frames;
    }

    public String getName() {
        return name;
    }

    private void validatePlayerNameisNull(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NotMatchingPlayerNameException(ExceptionMessage.INVALID_PLAYER_NAME_IS_NULL);
        }
    }

    private void validatePlayerNameLength(String name) {
        if (name.length() > NUMBER_THREE) {
            throw new NotMatchingPlayerNameException(ExceptionMessage.INVALID_PLAYER_NAME_LENGTH);
        }
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
        return "Player{" +
                "name='" + name + '\'' +
                ", frames=" + frames +
                '}';
    }
}

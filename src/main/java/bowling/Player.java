package bowling;

import bowling.exception.InvalidNameException;
import bowling.frame.Frame;
import java.util.regex.Pattern;

public class Player {

    private static final String NAME_REGX = "^[a-zA-Z]{3}$";

    private final String name;
    public Player(String name) {
        validateName(name);
        this.name = name.toUpperCase();
    }

    public String getName() {
        return this.name;
    }

    public Frame bowlBall(Frame frame, FallenPins fallenPins) {
        return frame.update(fallenPins);
    }

    private void validateName(String name) {
        if (!Pattern.matches(NAME_REGX, name)) {
            throw new InvalidNameException();
        }
    }
}

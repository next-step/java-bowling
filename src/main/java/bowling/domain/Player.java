package bowling.domain;

import bowling.domain.exception.InvalidNameException;
import bowling.domain.frame.Frame;
import bowling.domain.pin.FallenPins;
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
        return frame.updateFrameState(fallenPins);
    }

    private void validateName(String name) {
        if (!Pattern.matches(NAME_REGX, name)) {
            throw new InvalidNameException();
        }
    }
}

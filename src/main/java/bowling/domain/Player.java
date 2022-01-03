package bowling.domain;

import bowling.exception.PlayerLengthOutOfBoundException;
import bowling.exception.PlayerNameNullPointerException;
import java.util.List;

public class Player {

    private static final int NAME_LENGTH = 3;

    private final String name;
    private final Frames frames;

    public Player(String name) throws PlayerLengthOutOfBoundException {
        this(name, Frames.init());
    }

    private Player(String name, Frames frames) throws PlayerLengthOutOfBoundException {
        valid(name);
        this.name = name;
        this.frames = frames;
    }

    public void bowl(int fallenPins) {
        frames.bowl(Pins.of(fallenPins));
    }

    public boolean isFinished() {
        return frames.isFinished();
    }

    public String player() {
        return name;
    }

    public List<Frame> frames() {
        return frames.frames();
    }

    public int currentRound() {
        return frames.currentRound().round();
    }

    private void valid(String name) throws PlayerLengthOutOfBoundException {
        if (name == null || name.isEmpty()) {
            throw new PlayerNameNullPointerException();
        }

        if (name.length() != NAME_LENGTH) {
            throw new PlayerLengthOutOfBoundException(NAME_LENGTH);
        }
    }

}

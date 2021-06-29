package bowling.domain;

import bowling.domain.frame.Frame;

import java.util.List;

public class Player {
    private static final int MAX_NAME_LENGTH = 3;
    private static final String OVER_MAX_NAME_LENGTH_MESSAGE =
            String.format("이름은 %d글자까지 가능합니다.", MAX_NAME_LENGTH);

    private final String name;
    private final Game game;

    public Player(String name) {
        this(name, Game.init());
    }

    private Player(String name, Game game) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(OVER_MAX_NAME_LENGTH_MESSAGE);
        }

        this.name = name;
        this.game = game;
    }

    public String name() {
        return name;
    }

    public boolean playing() {
        return game.playing();
    }

    public Player play(int knockedPinsCount) {
        return new Player(name, game.play(knockedPinsCount));
    }

    public boolean played(int frameNumber) {
        if (game.currentFrameNumber() < frameNumber) {
            return false;
        }

        return !frames().get(frameNumber - 1).playing();
    }

    public List<Frame> frames() {
        return game.frames();
    }
}
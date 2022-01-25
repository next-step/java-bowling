package bowling.domain;

import bowling.domain.frame.DefaultFrame;
import bowling.domain.frame.Frames;

import static bowling.domain.frame.Frames.BOARD_MAX_SIZE;

public class Game {
    private final Frames frames;

    public Game(Frames frames) {
        this.frames = frames;
    }

    public Frames getBoard() {
        return frames;
    }

    public void playGame(KnockedPins knockedPins) {
        frames.play(knockedPins);
    }

    public void init() {
        frames.init();
    }
}



package view;

import board.Name;
import frame.Frame;
import frame.Frames;
import frame.LastFrame;

import java.util.ArrayList;

import static view.OutputView.showFrames;

public class BowlingGame {

    private final Frames frames = new Frames(new ArrayList<>());
    private final Name name;

    public static BowlingGame newGame() {
        String name = InputView.inputName();
        return new BowlingGame(new Name(name));
    }

    private BowlingGame(Name name) {
        this.name = name;
    }

    public void start() {
        while (isNotEnd()) {
            Frames frames = rolling();
            showFrames(name, frames);
        }
    }

    private boolean isNotEnd() {
        return !frames.isFull();
    }

    private Frames rolling() {
        if (frames.isFull()) {
            return frames;
        }
        return roll();
    }

    private Frames roll() {
        Frame nowFrame = frames.getNowFrame();

        if (frames.reachLast()) {
            return rollingLast();
        }

        int score = InputView.inputScore(frames.getNowFrameNumber());
        nowFrame.bowling(score);
        return frames;
    }

    private Frames rollingLast() {
        LastFrame lastFrame = frames.getLastFrame();
        int score = InputView.inputScore(frames.getNowFrameNumber());
        lastFrame.bowling(score);

        return frames;
    }
}

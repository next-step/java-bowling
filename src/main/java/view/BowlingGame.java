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

    private BowlingGame(Name name) {
        this.name = name;
    }

    public static BowlingGame newGame() {
        String name = InputView.inputName();
        return new BowlingGame(new Name(name));
    }

    public void start() {
        while (isNotEnd()) {
            rolling();
            showFrames(name, frames);
        }
    }

    private boolean isNotEnd() {
        return !frames.isFull();
    }

    private void rolling() {
        if (frames.isFull()) {
            return;
        }
        roll();
    }

    private void roll() {
        Frame nowFrame = frames.getNowFrame();

        if (frames.reachLast()) {
            rollingLast();
            return;
        }

        int score = InputView.inputScore(frames.getNowFrameNumber());
        nowFrame.bowling(score);
    }

    private void rollingLast() {
        LastFrame lastFrame = frames.getLastFrame();
        int score = InputView.inputScore(frames.getNowFrameNumber());
        lastFrame.bowling(score);
    }
}

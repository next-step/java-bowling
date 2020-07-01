package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Frame> frames;

    public Game() {
        this.frames = new ArrayList<>();
    }

    public void startGame() {
        for (int i = 0; i < 9; i++) {
            frames.add(new NormalFrame(i));
        }
        frames.add(new FinalFrame());
    }

    public List<Frame> getFrames() {
        return new ArrayList<>(frames);
    }
}

package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.state.last.LastFrameSecondBowl;
import bowling.exception.CannotCalculateException;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Bowling {

    private final List<Frame> frames;

    public Bowling() {
        List<Frame> frames = new ArrayList<>();
        frames.add(new NormalFrame(1));
        this.frames = frames;
    }

    public void bowl(int pinCount) {
        bowl(PinCount.of(pinCount));
    }

    public void bowl(PinCount pinCount) {
        Frame frame = getLastFrame();
        Frame next = frame.bowl(pinCount);
        if (frame != next) {
            frames.add(next);
        }
    }

    private Frame getLastFrame() {
        return frames.get(getLastIndex());
    }

    private int getLastIndex() {
        return frames.size() - 1;
    }

    public List<Result> createResults() {
        if (!isGameEnd()) {
            throw new IllegalStateException("게임이 종료되지 않았습니다.");
        }

        List<Result> results = new ArrayList<>();
        try {
            for (Frame frame : frames) {
                results.add(frame.createResult());
            }
        } catch (CannotCalculateException ex) {
        }

        return results;
    }

    private boolean isGameEnd() {
        Frame frame = getLastFrame();
        return frame.getNo() == 10 && frame.isFinish() ;
    }

    @Override
    public String toString() {
        return "Bowling{" +
                "frames=" + frames +
                '}';
    }
}

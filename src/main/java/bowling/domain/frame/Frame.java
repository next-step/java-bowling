package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frame {

    private static final int FIRST_FRAME_NUMBER = 0;
    private static final int FINAL_FRAME_NUMBER = 9;

    private final FrameNumber frameNumber;
    private final Pins frameDownPins;

    private Frame nextFrame;

    private Frame(FrameNumber frameNumber, Pins frameDownPins) {
        this.frameNumber = frameNumber;
        this.frameDownPins = frameDownPins;
    }

    public static Frame first() {
        return new Frame(FrameNumber.create(FIRST_FRAME_NUMBER), new NormalPins());
    }

    public Frame last() {
        this.nextFrame = new Frame(FrameNumber.create(FINAL_FRAME_NUMBER), FinalFramePins.create());
        return this.nextFrame;
    }

    public Frame next() {
        this.nextFrame = new Frame(FrameNumber.create(this.frameNumber.getValue() + 1), new NormalPins());
        return this.nextFrame;
    }

    public void roll(int numberOfDownPin) {
        this.frameDownPins.down(numberOfDownPin);
    }

    public boolean hasTurn() {
        return this.frameDownPins.hasTurn();
    }

    public FrameResult getFrameResult() {
        return new FrameResult(this.frameDownPins.getDownPins(), this.frameDownPins.getScoreType(), frameScore());
    }

    public Score frameScore() {
        if (!isFinished()) {
            return Score.create(0, ScoreType.READY);
        }

        if (isLast() && isFinished()) {
            return Score.create(this.frameDownPins.sum(), ScoreType.NORMAL);
        }

        int nextBowlCount = this.frameDownPins.getScoreType().getBonusBowlCount();
        List<Integer> nextDownPins = getNextDownPins(nextBowlCount);
        if (nextDownPins.size() < nextBowlCount) {
            return Score.create(0, ScoreType.READY);
        }

        int score = this.frameDownPins.sum() + nextDownPins.stream().reduce(0, Integer::sum);
        return Score.create(score, ScoreType.NORMAL);
    }

    private boolean isLast() {
        return this.frameNumber.getValue() == FINAL_FRAME_NUMBER;
    }

    private boolean isFinished() {
        return !this.frameDownPins.hasTurn();
    }

    private List<Integer> getNextDownPins(int count) {
        if (this.nextFrame == null) {
            return Collections.EMPTY_LIST;
        }

        List<Integer> nextDownPins = this.nextFrame.getDownPins();
        if (nextDownPins.isEmpty()) {
            return new ArrayList<>();
        }

        if (nextDownPins.size() >= count) {
            return nextDownPins.subList(0, count);
        }

        nextDownPins.addAll(this.nextFrame.getNextDownPins(count - nextDownPins.size()));
        return nextDownPins;
    }

    private List<Integer> getDownPins() {
        return this.frameDownPins.getDownPins();
    }
}

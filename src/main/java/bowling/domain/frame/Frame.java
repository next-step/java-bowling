package bowling.domain.frame;

import bowling.domain.ScoreType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Frame {

    private final int position;
    private final Pins pins;

    private Frame nextFrame;

    private Frame(int position, Pins pins) {
        this.position = position;
        this.pins = pins;
    }

    static Frame first() {
        return new Frame(0, new NormalPins());
    }

    Frame last() {
        this.nextFrame = new Frame(9, FinalPins.newInstance());
        return this.nextFrame;
    }

    Frame next() {
        this.nextFrame = new Frame(this.position + 1, new NormalPins());
        return this.nextFrame;
    }

    public void play(int numberOfDownPin) {
        this.pins.down(numberOfDownPin);
    }

    public boolean hasTurn() {
        return this.pins.hasTurn();
    }

    public Optional<Integer> getScore() {
        if (!isFinished()) {
            return Optional.empty();
        }

        if (isLast() && isFinished()) {
            return Optional.ofNullable(this.pins.sum());
        }

        int nextBowlCount = this.pins.getScoreType().map(ScoreType::getBonusBowlCount).orElse(0);
        List<Integer> downPins = getNextDownPins(nextBowlCount);
        if (downPins.size() < nextBowlCount) {
            return Optional.empty();
        }

        int score = this.pins.sum() + downPins.stream().reduce(0, Integer::sum);
        return Optional.of(score);
    }

    public FrameResult getFrameResult() {
        return new FrameResult(this.pins.getDownPins(), this.pins.getScoreType(), getScore());
    }

    private boolean isLast() {
        return this.position == 9;
    }

    private boolean isFinished() {
        return !this.pins.hasTurn();
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
        return this.pins.getDownPins();
    }
}

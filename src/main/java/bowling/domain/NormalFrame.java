package bowling.domain;

import java.util.Optional;

public class NormalFrame extends AbstractFrame {

    public static final int MAX_BOWLCOUNT = 1;
    public static final int MAX_FRAMENUMBER = 9;

    private Frame next;

    NormalFrame(int maxBowlCount, int frameNumber) {
        super(maxBowlCount, frameNumber);
    }

    public static Frame init(int frameNumber) {
        validate(frameNumber);
        return new NormalFrame(MAX_BOWLCOUNT, frameNumber);
    }

    private static void validate(int frameNumber) {
        if (frameNumber < Frames.MIN_FRAMENUMBER) {
            throw new IllegalArgumentException("Frame은 1번부터 시작합니다.");
        }
        if (frameNumber > MAX_FRAMENUMBER) {
            throw new IllegalArgumentException("NormalFrame은 9번까지만 존재합니다.");
        }
    }

    @Override
    public void bowl(Pin pin) {
        assertFinished();

        super.bowl(pin);
    }

    @Override
    public Optional<Integer> calculateScore() {
        if (!isFinished()) {
            return Optional.empty();
        }

        Score score = currentStatus().score();
        if (score.canCalculateScore()) {
            return Optional.of(score.score());
        }

        if (next == null) {
            return Optional.empty();
        }
        return next.calculateLastFrameScore(score);
    }

    public Optional<Integer> calculateLastFrameScore(Score lastScore) {
        Score newScore = currentStatus().calculateScore(lastScore);
        if (newScore.canCalculateScore()) {
            return Optional.of(newScore.score());
        }

        if (!isFinished()) {
            return Optional.empty();
        }

        if (next == null) {
            return Optional.empty();
        }
        return next.calculateLastFrameScore(newScore);
    }

    @Override
    public boolean isFinished() {
        return currentStatus().isFinished();
    }

    @Override
    public Frame nextFrame() {
        if (!isFinished()) {
            return this;
        }
        if (frameNumber < MAX_FRAMENUMBER) {
            Frame next = NormalFrame.init(frameNumber + 1);
            this.next = next;
            return next;
        }
        Frame next = FinalFrame.init();
        this.next = next;
        return next;
    }

    @Override
    public String toString() {
        return currentStatus().toString();
    }



}

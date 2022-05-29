package bowling.domain.frame;

import bowling.domain.pitch.Pitches;

public class NormalFrame implements Frame {

    private static final int ONE = 1;
    private static final int SEMI_FINAL_INDEX = 9;
    private static final int MAX_PITCHES_SIZE = 2;

    private final int round;
    private final Pitches pitches;

    private NormalFrame(int round, int pins) {
        this.round = round;
        this.pitches = Pitches.first(pins);
    }

    public static NormalFrame bowling(int round, int pins) {
        return new NormalFrame(round, pins);
    }

    @Override
    public Frame bowling(int pins) {
        this.pitches.next(pins);
        return this;
    }

    @Override
    public Frame next(int pins) {
        int nextIndex = this.increment();
        if (isSemiFinal()) {
            return FinalFrame.lastBowling(pins);
        }
        return bowling(nextIndex, pins);
    }

    @Override
    public int round() {
        return this.round;
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public boolean isFinishBowling() {
        if (this.pitches.isStrikeOrSpare()) {
            return true;
        }

        return this.pitches.size() == MAX_PITCHES_SIZE;
    }

    @Override
    public String partitionPins() {
        return this.pitches.toString();
    }

    private boolean isSemiFinal() {
        return this.round == SEMI_FINAL_INDEX;
    }

    private int increment() {
        return Math.addExact(this.round, ONE);
    }
}

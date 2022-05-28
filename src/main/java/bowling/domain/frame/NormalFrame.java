package bowling.domain.frame;

import bowling.domain.pitch.Pitches;

public class NormalFrame implements Frame {

    private static final int ONE = 1;
    private static final int SEMI_FINAL_INDEX = 9;
    private static final int MAX_PITCHES_SIZE = 2;

    private final int round;
    private final Pitches pitches;

    private NormalFrame(int round, int count) {
        this.round = round;
        this.pitches = Pitches.first(count);
    }

    public static NormalFrame bowling(int round, int count) {
        return new NormalFrame(round, count);
    }

    @Override
    public Frame bowling(int count) {
        this.pitches.next(count);
        return this;
    }

    @Override
    public Frame next(int count) {
        int nextIndex = this.increment();
        if (isSemiFinal()) {
            return FinalFrame.lastBowling(count);
        }
        return bowling(nextIndex, count);
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
    public String partitionCount() {
        return this.pitches.toString();
    }

    private boolean isSemiFinal() {
        return this.round == SEMI_FINAL_INDEX;
    }

    private int increment() {
        return Math.addExact(this.round, ONE);
    }
}

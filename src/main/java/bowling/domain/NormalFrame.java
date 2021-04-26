package bowling.domain;

import java.util.Objects;

public class NormalFrame implements Frame {
    private static final int NORMAL_FRAME_INDEX_MAX = 8;
    private static final int FIRST_FRAME_INDEX = 0;
    private static final int PIN_COUNT_SIZE_MAX = 2;
    private static final String CANNOT_THROW_MORE_THAN_TWO = "2번을 초과하여 던질 수 없습니다.";

    private final int index;
    private final PinCounts pinCounts;

    public NormalFrame(int index) {
        this(index, new NormalPinCounts());
    }

    public NormalFrame(int index, PinCounts FrameBowls) {
        this.index = index;
        this.pinCounts = FrameBowls;
    }

    @Override
    public Frame throwBowl(String pinCount) {
        if (pinCounts.pinCounts().size() == PIN_COUNT_SIZE_MAX) {
            throw new IllegalArgumentException(CANNOT_THROW_MORE_THAN_TWO);
        }

        return addBowl(pinCount);
    }

    private Frame addBowl(int pinCount) {
        pinCounts.knockDown(pinCount);
        return new NormalFrame(index, pinCounts);
    }

    private Frame addBowl(String pinCount) {
        return addBowl(Integer.parseInt(pinCount));
    }

    @Override
    public Frame next() {
        if (!isFinished()) {
            return this;
        }

        if (index < NORMAL_FRAME_INDEX_MAX) {
            return new NormalFrame(index + 1, new NormalPinCounts());
        }

        return new FinalFrame();
    }

    @Override
    public Score score() {
        if (!isFinished()) {
            return Score.unCountableScore();
        }

        if (pinCounts.isFirstPinCountStrike()) {
            return Score.Strike(pinCounts.totalPinCount());
        }

        if (pinCounts.isSecondPinCountSpare()) {
            return Score.Spare(pinCounts.totalPinCount());
        }

        return Score.Miss(pinCounts.totalPinCount());
    }

    @Override
    public Score add(Score previousScore) {
        int previousScoreChance = previousScore.leftOpportunity();
        int previousScoreValue = previousScore.value();

        if (pinCounts().pinCounts().isEmpty()
                || (previousScoreChance == 2 && pinCounts.pinCounts().size() == 1 && !pinCounts().isFirstPinCountStrike())) {
            return Score.unCountableScore();
        }

        if (previousScoreChance == 1) {
            return Score.Miss(pinCounts.pinCounts().get(FIRST_FRAME_INDEX).value() + previousScoreValue);
        }

        if (pinCounts().isFirstPinCountStrike()) {
            return Score.Spare(pinCounts.totalPinCount() + previousScoreValue);
        }
        System.out.println("abc");
        return Score.Miss(pinCounts.totalPinCount() + previousScoreValue);
    }

    @Override
    public boolean isFinished() {
        return pinCounts.isFinished();
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public PinCounts pinCounts() {
        return pinCounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index &&
                Objects.equals(pinCounts, that.pinCounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, pinCounts);
    }
}

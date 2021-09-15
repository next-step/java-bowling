package bowling.domain;

import bowling.exception.BusinessException;

import java.util.Objects;

public class FinalFrame implements Frame {

    private static final int MAXIMUM_FINAL_FRAME_PITCH = 3;

    private final FrameNumber frameNumber;
    private final Pitches pitches;

    public FinalFrame() {
        frameNumber = new FrameNumber(BowlingGame.MAX_FRAME_SIZE);
        pitches = new Pitches();
    }

    @Override
    public Frame pitch(final int countOfPins) {
        if (isEnd()) {
            throw new BusinessException("현재 프레임에서 투구할 수 있는 갯수를 초과했습니다.");
        }
        addNextPitch(countOfPins);
        return this;
    }

    private void addNextPitch(final int countOfPins) {
        if (pitches.isEmpty()) {
            pitches.add(Pitch.firstPitch(countOfPins));
            return;
        }
        Pitch next = pitches.get(pitches.size() - 1).pitch(countOfPins);
        pitches.add(next);
    }

    @Override
    public boolean isEnd() {
        return isNormalEnd() || pitches.equalsToSize(MAXIMUM_FINAL_FRAME_PITCH);
    }

    public boolean isNormalEnd() {
        boolean isMaximumSize = pitches.equalsToSize(NormalFrame.MAXIMUM_NORMAL_FRAME_PITCH);
        boolean isNormalPitch = pitches.sum() < Pitch.MAXIMUM_COUNT_OF_PINS;
        return isMaximumSize && isNormalPitch;
    }

    @Override
    public Frame next() {
        if (!isEnd()) {
            return this;
        }
        throw new BusinessException("마지막 프레임입니다.");
    }

    @Override
    public boolean isNormal() {
        return false;
    }

    @Override
    public Pitches pitches() {
        return pitches;
    }

    @Override
    public Score addScore(final Score beforeScore) {
        if (pitches.isEmpty()) {
            return Score.cantCalculate();
        }

        pitchScore(beforeScore);

        if (beforeScore.canCalculateScore()) {
            return beforeScore;
        }
        return Score.cantCalculate();
    }

    private void pitchScore(final Score beforeScore) {
        int range = Math.min(beforeScore.leftPitch(), pitches.size());
        for (int i = 0; i < range; i++) {
            beforeScore.pitch(pitches.get(i).intValue());
        }
    }

    @Override
    public Score score() {
        if (!isEnd()) {
            return Score.cantCalculate();
        }
        return Score.of(pitches.sum());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber) &&
                Objects.equals(pitches, that.pitches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, pitches);
    }
}

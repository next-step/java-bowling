package bowling.domain;

import bowling.exception.BusinessException;

import java.util.Objects;

public class NormalFrame implements Frame {

    public static final int MAXIMUM_NORMAL_FRAME_PITCH = 2;

    private final FrameNumber frameNumber;
    private Pitches pitches;
    private Frame nextFrame;

    public NormalFrame(final int frameNumber) {
        this.frameNumber = new FrameNumber(frameNumber);
        pitches = new Pitches();
    }

    @Override
    public Frame pitch(final int countOfPins) {
        if (isEnd()) {
            throw new BusinessException("현재 프레임에서 투구할 수 있는 갯수를 초과했습니다.");
        }
        if (pitches.size() == 1) {
            pitches.validateNormalSecondPitch(countOfPins);
        }
        addNextPitch(countOfPins);
        return this;
    }

    @Override
    public Frame next() {
        if (!isEnd()) {
            return this;
        }
        if (frameNumber.isLastNormalNumber()) {
            nextFrame = new FinalFrame();
            return nextFrame;
        }
        nextFrame = new NormalFrame(frameNumber.nextNumber());
        return nextFrame;
    }

    @Override
    public boolean isNormal() {
        return true;
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

        pitchScoreUntilPossible(beforeScore);

        if (beforeScore.canCalculateScore()) {
            return beforeScore;
        }
        if (nextFrame == null) {
            return Score.cantCalculate();
        }
        return nextFrame.addScore(beforeScore);
    }

    private void pitchScoreUntilPossible(final Score beforeScore) {
        int range = Math.min(beforeScore.leftPitch(), pitches.size());
        for (int i = 0; i < range; i++) {
            beforeScore.pitch(pitches.get(i).intValue());
        }
    }

    @Override
    public Score score() {
        if (!isEnd() || nextFrame == null) {
            return Score.cantCalculate();
        }
        if (pitches.isLastPitchStatus(Status.SPARE)) {
            return nextFrame.addScore(Score.ofSpare());
        }
        if (pitches.isLastPitchStatus(Status.STRIKE)) {
            return nextFrame.addScore(Score.ofStrike());
        }

        return Score.of(pitches.sum());
    }

    private void addNextPitch(int countOfPins) {
        if (pitches.isEmpty()) {
            pitches.add(Pitch.firstPitch(countOfPins));
            return;
        }
        Pitch next = pitches.get(pitches.size() - 1).pitch(countOfPins);
        pitches.add(next);
    }

    @Override
    public boolean isEnd() {
        return pitches.firstStrike() || pitches.isMaxSize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber) &&
                Objects.equals(pitches, that.pitches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, pitches);
    }
}

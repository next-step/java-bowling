package bowling.domain;

import bowling.exception.BusinessException;

import java.util.Objects;
import java.util.stream.Collectors;

public class NormalFrame implements Frame {

    public static final int MAXIMUM_NORMAL_FRAME_PITCH = 2;

    private final FrameNumber frameNumber;
    private Pitches pitches;

    public NormalFrame(final int frameNumber) {
        this.frameNumber = new FrameNumber(frameNumber);
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

    @Override
    public Frame next() {
        if (!isEnd()) {
            return this;
        }
        if (frameNumber.isLastNormalNumber()) {
            return new FinalFrame();
        }
        return new NormalFrame(frameNumber.nextNumber());
    }

    @Override
    public boolean isNormal() {
        return true;
    }

    @Override
    public Pitches pitches() {
        return pitches;
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

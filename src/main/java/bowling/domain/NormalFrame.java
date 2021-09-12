package bowling.domain;

import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.Strike;
import bowling.exception.BusinessException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NormalFrame implements Frame {

    private static final int FIRST_PITCH_INDEX = 0;
    private static final int MAXIMUM_NORMAL_FRAME_PITCH = 2;
    private static final String SEPARATOR = "|";

    private final FrameNumber frameNumber;
    private List<Pitch> pitches = new LinkedList<>();

    public NormalFrame(final int frameNumber) {
        this.frameNumber = new FrameNumber(frameNumber);
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
        return pitches.size() == 1 && pitches.get(FIRST_PITCH_INDEX) instanceof Strike
                || pitches.size() == MAXIMUM_NORMAL_FRAME_PITCH;
    }

    @Override
    public String result() {
        return pitches.stream()
                .map(Pitch::value)
                .collect(Collectors.joining(SEPARATOR));
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

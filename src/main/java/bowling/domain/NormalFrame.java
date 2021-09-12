package bowling.domain;

import bowling.domain.pitch.Pitch;
import bowling.exception.BusinessException;

import java.util.LinkedList;
import java.util.List;

public class NormalFrame implements Frame {

    private static final int MAXIMUM_NORMAL_FRAME_PITCH = 2;

    private final FrameNumber frameNumber;
    private List<Pitch> pitches = new LinkedList<>();

    public NormalFrame(final int firstFrameIndex) {
        frameNumber = new FrameNumber(firstFrameIndex);
    }

    @Override
    public Frame pitch(final int countOfPins) {
        validate();

        addNextPitch(countOfPins);

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

    private void validate() {
        if (pitches.size() >= MAXIMUM_NORMAL_FRAME_PITCH) {
            throw new BusinessException("현재 프레임에서 투구할 수 있는 갯수를 초과했습니다.");
        }
    }

    @Override
    public boolean isEnd() {
        // 첫번째 투구가 스트라이크 이거나
        // 투구를 두번 했으면
        if (pitches.size() == MAXIMUM_NORMAL_FRAME_PITCH) {

        }
        return false;
    }

    @Override
    public int sumPitches() {
        return 0;
    }
}

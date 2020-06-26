package bowling.game;

import java.util.LinkedList;

public class FinalFrame extends Frame {
    private static final int PITCH_COUNT_MAX = 2;
    private static final int BONUS_COUNT_MAX = 3;

    private final FrameNumber frameNumber;
    private final LinkedList<Pitch> pitches;

    public FinalFrame(final int frameNumber) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.pitches = new LinkedList<>();
    }

    @Override
    public int bowl(final int pinCount) {
        if (pitches.isEmpty() || isBonusPitch()) {
            pitches.add(Pitch.firstPitch(pinCount));

            return FrameNumber.FRAME_NUMBER_MAX - pinCount;
        }

        Pitch lastPitch = pitches.getLast();
        pitches.add(lastPitch.nextPitch(pinCount));

        return FrameNumber.FRAME_NUMBER_MAX - pitches.stream()
                .mapToInt(Pitch::getPinCount)
                .sum();
    }

    @Override
    public boolean hasRemainChance() {
        if (!isBonusPitch() && pitches.size() == PITCH_COUNT_MAX) {
            return false;
        }

        if (pitches.size() == BONUS_COUNT_MAX) {
            return false;
        }

        return true;
    }

    @Override
    protected Frame createNextFrame() {
        throw new IllegalStateException("마지막 프레임은 다음 프레임을 만들 수 없습니다.");
    }

    private boolean isBonusPitch() {
        if (pitches.size() == 1 && pitches.getFirst().getState() == State.STRIKE) {
            return true;
        }

        if (pitches.size() == 2 && pitches.getLast().getState() == State.SPARE) {
            return true;
        }

        return false;
    }

    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    @Override
    boolean isLastFrame() {
        return true;
    }
}

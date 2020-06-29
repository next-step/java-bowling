package bowling.game;

import java.util.LinkedList;

import static java.util.stream.Collectors.joining;

public class FinalFrame implements Frame {
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
        if (pitches.size() == PITCH_COUNT_MAX && !isBonusPitch()) {
            return false;
        }

        if (pitches.size() == BONUS_COUNT_MAX) {
            return false;
        }

        return true;
    }

    @Override
    public Frame createNextFrame() {
        throw new IllegalStateException("마지막 프레임은 다음 프레임을 만들 수 없습니다.");
    }

    private boolean isBonusPitch() {
        return pitches.getFirst().isStrikePitch() || pitches.getLast().isSparePitch();
    }

    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }

    @Override
    public String getStates() {
        return pitches.stream()
                .map(Pitch::stateToString)
                .collect(joining(STATE_DELIMITER));
    }
}

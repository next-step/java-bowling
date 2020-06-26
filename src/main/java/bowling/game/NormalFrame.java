package bowling.game;

import java.util.LinkedList;

public class NormalFrame extends Frame {
    private static final int PITCH_COUNT_MAX = 2;

    private final FrameNumber frameNumber;
    private final LinkedList<Pitch> pitches;

    public NormalFrame(final int frameNumber) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.pitches = new LinkedList<>();
    }

    @Override
    public int bowl(final int pinCount) {
        if (pitches.isEmpty()) {
            pitches.add(Pitch.firstPitch(pinCount));

            return FrameNumber.FRAME_NUMBER_MAX - pitches.stream()
                    .mapToInt(Pitch::getPinCount)
                    .sum();
        }

        Pitch lastPitch = pitches.getLast();
        pitches.add(lastPitch.nextPitch(pinCount));

        return FrameNumber.FRAME_NUMBER_MAX - pitches.stream()
                .mapToInt(Pitch::getPinCount)
                .sum();
    }

    @Override
    public boolean hasRemainChance() {
        Pitch firstPitch = pitches.getFirst();

        if (firstPitch.getState().equals(State.STRIKE)) {
            return false;
        }

        if (pitches.size() == PITCH_COUNT_MAX) {
            return false;
        }

        return true;
    }

    @Override
    public Frame createNextFrame() {
        if (frameNumber.getNumber() == 9) {
            return new FinalFrame(frameNumber.getNumber() + 1);
        }

        return new NormalFrame(frameNumber.getNumber() + 1);
    }

    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    @Override
    boolean isLastFrame() {
        return false;
    }
}

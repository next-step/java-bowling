package bowling.game;

import java.util.LinkedList;

import static java.util.stream.Collectors.joining;

public class NormalFrame implements Frame {
    private static final int PITCH_COUNT_MAX = 2;
    private static final int FINAL_NORMAL_FRAME_NUMBER = 9;

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
        if (pitches.isEmpty()) {
            return true;
        }

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
        if (frameNumber.getNumber() == FINAL_NORMAL_FRAME_NUMBER) {
            return new FinalFrame(frameNumber.getNumber() + 1);
        }

        return new NormalFrame(frameNumber.getNumber() + 1);
    }

    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    @Override
    public boolean isLastFrame() {
        return false;
    }

    @Override
    public String getStates() {
        return pitches.stream()
                .map(Pitch::stateToString)
                .collect(joining(STATE_DELIMITER));
    }
}

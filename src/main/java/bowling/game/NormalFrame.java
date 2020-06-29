package bowling.game;

public class NormalFrame implements Frame {
    private static final int FINAL_NORMAL_FRAME_NUMBER = 9;

    private final FrameNumber frameNumber;
    private final Pitches pitches;

    public NormalFrame(final int frameNumber) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.pitches = new NormalPitches();
    }

    @Override
    public int bowl(final int pinCount) {
        return pitches.throwBall(pinCount);
    }

    @Override
    public boolean hasRemainChance() {
        return pitches.hasChance();
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
        return pitches.getPitchesStates();
    }
}

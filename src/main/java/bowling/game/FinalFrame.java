package bowling.game;

public class FinalFrame implements Frame {

    private final FrameNumber frameNumber;
    private final Pitches pitches;

    public FinalFrame(final int frameNumber) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.pitches = new FinalPitches();
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
        throw new IllegalStateException("마지막 프레임은 다음 프레임을 만들 수 없습니다.");
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
        return pitches.getPitchesStates();
    }
}

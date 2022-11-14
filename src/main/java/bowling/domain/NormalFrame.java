package bowling.domain;

public class NormalFrame extends Frame {

    public NormalFrame() {
        this(START_FRAME_NUMBER);
    }

    public NormalFrame(int round) {
        this(round, new Balls());
    }

    public NormalFrame(int round, Balls balls) {
        this.round = round;
        this.balls = balls;
    }

    @Override
    public void play(int knockedDownPinCount) {
        balls.pitch(knockedDownPinCount);
    }

    @Override
    public String scoringText() {
        return scoringTextNormalFrame();
    }

    public Frame createNextFrame() {
        if (round + 1 == FINAL_FRAME_NUMBER) {
            return new FinalFrame();
        }

        return new NormalFrame(round + 1);
    }

}

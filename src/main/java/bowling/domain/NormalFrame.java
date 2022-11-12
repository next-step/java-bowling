package bowling.domain;

import java.util.List;

public class NormalFrame extends Frame{
    public NormalFrame(int round) {
        this(round, new Balls(0, 0));
    }

    public NormalFrame(int round, Balls balls) {
        this.round = round;
        this.balls = balls;
    }

    @Override
    public void play() {
        balls.pitch();
    }

    @Override
    public String scoringText() {
        return scoringTextNormalFrame();
    }

}

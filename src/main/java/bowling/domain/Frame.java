package bowling.domain;

public class Frame {

    public static Frame from(Round round) {
        return new Frame();
    }

    public boolean isLastRound() {
        return false;
    }

    public void throwBall(int hitCount) {
    }

    public boolean roundEnded() {
        return false;
    }
}

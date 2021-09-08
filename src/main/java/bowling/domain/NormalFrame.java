package bowling.domain;

public class NormalFrame extends Frame {
    public NormalFrame(int score) {
        super(score);
    }

    @Override
    public boolean isNext() {
        return scores.size() == 2 || sumOfScore() == 10;
    }
}

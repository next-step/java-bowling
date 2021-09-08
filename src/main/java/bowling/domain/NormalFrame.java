package bowling.domain;

public class NormalFrame extends Frame {
    private static int MAX_COUNT = 2;

    public NormalFrame(int score) {
        super(score);
    }

    @Override
    public boolean isNext() {
        return scores.size() == MAX_COUNT || sumOfScore() == TEN_SCORE;
    }
}

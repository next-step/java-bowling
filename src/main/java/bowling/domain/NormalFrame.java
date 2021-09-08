package bowling.domain;

public class NormalFrame extends Frame {
    public NormalFrame(int score) {
        super(score);
    }

    @Override
    public boolean isNext() {
        return super.scores.size() == 2 || scores.stream().mapToInt(Integer::intValue).sum() >= 10;
    }
}

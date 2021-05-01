package bowling.domain;

public class NormalFrame extends Frame {
    public NormalFrame() {
        super();
        availability = 2;
    }

    public NormalFrame(Score score) {
        super(score);
        availability = 2;
    }

    @Override
    public String addScore(int inputScore) {
        if (isAvailable()) {
            --availability;
            return getFormattedScore(score.updateScore(inputScore));
        }
        throw new IllegalStateException("점수를 더 이상 입력할 수 없습니다.");
    }
}

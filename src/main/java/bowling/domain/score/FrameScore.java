package bowling.domain.score;

public class FrameScore {
    public static final int MAX_SCORE_PER_FRAME = 10;

    private final Score firstScore;
    private final Score secondScore;

    private FrameScore(Score firstScore) {
        this(firstScore, Score.of(0));
        validateStrike(firstScore);
    }

    private void validateStrike(Score firstScore) {
        if (!firstScore.equals(Score.of(MAX_SCORE_PER_FRAME))) {
            throw new IllegalArgumentException("스트라이크의 점수는 반드시 " + MAX_SCORE_PER_FRAME + " 점 입니다.");
        }
    }

    private FrameScore(Score firstScore, Score secondScore) {
        validateSum(firstScore, secondScore);
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    private void validateSum(Score firstScore, Score secondScore) {
        if (Score.sum(firstScore, secondScore) > MAX_SCORE_PER_FRAME) {
            throw new IllegalArgumentException("한 프레임 당 점수의 합은 " + MAX_SCORE_PER_FRAME + "점을 초과할 수 없습니다.");
        }
    }

    public static FrameScore of(Score firstScore, Score secondScore) {
        return new FrameScore(firstScore, secondScore);
    }

    public static FrameScore strike(Score firstScore) {
        return new FrameScore(firstScore);
    }
}

package bowling.domain.score;

public class Scores {

    private final Score first;
    private Score second;

    private Scores(Score first) {
        this.first = first;

        if (first.equals(Score.MAX_SCORE)) {
            inputSecondScore(Score.MIN_SCORE);
        }
    }

    public static Scores from(Score first) {
        return new Scores(first);
    }

    public void inputSecondScore(Score secondScore) {
        Score scoreTotal = first.add(secondScore);

        if (scoreTotal.isGreaterThan(Score.MAX_SCORE)) {
            throw new IllegalArgumentException("두 점수의 합이 최대값을 초과하였습니다 : " + scoreTotal);
        }

        second = secondScore;
    }

    public Result checkResult() {
        if (second == null) {
            throw new IllegalStateException("두번째 투구의 점수가 입력되지 않았습니다");
        }

        return Result.STRIKE;
    }

    public Score getFirst() {
        return Score.of(first.getContent());
    }

    public Score getSecond() {
        return Score.of(second.getContent());
    }
}

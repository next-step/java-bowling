package bowling.domain.score;

public class Scores {

    private final Score first;
    private Score second;

    private Scores(Score first) {
        this.first = first;
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
}

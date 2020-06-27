package bowling.domain.score;

public class Scores {

    private Score first;
    private Score second;

    public static Scores create() {
        return new Scores();
    }

    public void add(Score score) {
        if (first == null) {
            inputFirst(score);
            return;
        }

        if (second == null) {
            inputSecond(score);
            return;
        }

        throw new IllegalStateException("더 이상 점수를 입력할 수 없습니다.");
    }

    private void inputFirst(Score score) {
        this.first = score;

        if (score.equals(Score.MAX_SCORE)) {
            inputSecond(Score.MIN_SCORE);
        }
    }

    private void inputSecond(Score score) {
        Score scoreTotal = first.add(score);

        if (scoreTotal.isGreaterThan(Score.MAX_SCORE)) {
            throw new IllegalArgumentException("두 점수의 합이 최대값을 초과하였습니다 : " + scoreTotal);
        }

        this.second = score;
    }

    public Result checkResult() {
        if (second == null) {
            throw new IllegalStateException("두번째 투구의 점수가 입력되지 않았습니다");
        }

        return Result.findByScores(first, second);
    }

    public boolean canAddMore() {
        return !first.equals(Score.MAX_SCORE) &&  second == null;
    }
}

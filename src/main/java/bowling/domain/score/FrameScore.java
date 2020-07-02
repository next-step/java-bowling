package bowling.domain.score;

import java.util.Optional;

public class FrameScore {

    private Score first;
    private Score second;
    private Score bonus;

    private FrameScore() {}

    public static FrameScore create() {
        return new FrameScore();
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

        if (bonus == null) {
            inputBonus(score);
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

    private void inputBonus(Score score) {
        this.bonus = score;
    }

    public Result checkResult() {
        if (second == null) {
            throw new IllegalStateException("두번째 투구의 점수가 입력되지 않았습니다");
        }

        return Result.findByFrameScore(first, second);
    }

    public Optional<Score> getFirst() {
        return Optional.ofNullable(first);
    }

    public Optional<Score> getSecond() {
        return Optional.ofNullable(second);
    }

    public Optional<Score> getBonus() {
        return Optional.ofNullable(bonus);
    }

    public Score calculateTotalScore() {
        return second == null ? first
                : bonus == null ? first.add(second)
                : first.add(second).add(bonus);
    }
}

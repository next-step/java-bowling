package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Score {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int SCORES_SECOND_SIZE = 2;

    private List<Integer> scores = new ArrayList<>();

    private TotalScore totalScore;

    public Score(int firstBall) {
        validateNumberOfPin(firstBall);

        scores.add(firstBall);
    }

    public void secondBall(int second) {
        scores.add(second);
    }

    public void finalBall(int hitNumberOfPin) {
        validateNumberOfPin(hitNumberOfPin);
        scores.add(hitNumberOfPin);
    }

    public int firstScore() {
        return scores.get(FIRST_INDEX);
    }

    public int secondScore() {
        validateExistSecondScore();

        return scores.get(SECOND_INDEX);
    }

    public int scoresSum() {
        return scores
                .stream()
                .mapToInt(score -> score)
                .sum();
    }

    private void validateNumberOfPin(int hitNumberOfPin) {
        if (hitNumberOfPin > Pin.MAX.getValue()) {
            throw new IllegalArgumentException("핀의 최고 갯수는 10개 입니다.");
        }
    }

    public void validateExistSecondScore() {
        if (scores.size() < SCORES_SECOND_SIZE) {
            throw new IllegalArgumentException("두번째 점수 입력해주세요.");
        }
    }

    public List<Integer> scores() {
        return scores;
    }

    public Status frameStatus() {
        if (scores.get(FIRST_INDEX) == Pin.MAX.getValue()) {
            return Status.STRIKE;
        }

        validateExistSecondScore();

        if (scores.get(FIRST_INDEX) + scores.get(SECOND_INDEX) == Pin.MAX.getValue()) {
            return Status.SPARE;
        }

        return Status.MISS;
    }

    public TotalScore createTotalScore(Status status) {
        int score = scoresSum();

        if (status.equals(Status.STRIKE)) {
            totalScore = TotalScore.strikeTotalScore(score);
            return totalScore;
        }
        if (status.equals(Status.SPARE)) {
            totalScore = TotalScore.spareTotalScore(score);
            return totalScore;
        }

        totalScore = new TotalScore(score);
        return totalScore;
    }

    public TotalScore createFinalFrameTotalScore() {
        totalScore = new TotalScore(scoresSum());

        return totalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Objects.equals(scores, score.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }
}

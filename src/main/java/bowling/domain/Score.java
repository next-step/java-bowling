package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Score {
    private static final int MAX_PIN_NUMBER = 10;
    private static final int GUTTER_PIN_NUMBER = 0;
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int FINAL_INDEX = 2;
    private static final int SCORES_SECOND_SIZE = 2;

    private List<Integer> scores = new ArrayList<>();

    public Score(int firstBall) {
        validateNumberOfPin(firstBall);

        scores.add(firstBall);
    }

    public void secondBall(int second) {
        if (scores.get(FIRST_INDEX) + second > MAX_PIN_NUMBER) {
            throw new IllegalArgumentException("핀의 최고 갯수는 10개 입니다.");
        }

        scores.add(SECOND_INDEX, second);
    }

    public int firstScore() {
        return scores.get(FIRST_INDEX);
    }

    public int secondScore() {
        validateExistSecondScore();

        return scores.get(SECOND_INDEX);
    }

    public void finalScore(int hitNumberOfPin) {
        validateNumberOfPin(hitNumberOfPin);
        scores.add(FINAL_INDEX, hitNumberOfPin);
    }

    private void validateNumberOfPin(int hitNumberOfPin) {
        if (hitNumberOfPin > MAX_PIN_NUMBER) {
            throw new IllegalArgumentException("핀의 최고 갯수는 10개 입니다.");
        }
    }

    private void validateExistSecondScore() {
        if (scores.size() < SCORES_SECOND_SIZE) {
            throw new IllegalArgumentException("두번째 점수 입력해주세요.");
        }
    }

    public List<Integer> scores() {
        return scores;
    }

    public Status frameStatus() {
        if (scores.get(FIRST_INDEX) == MAX_PIN_NUMBER) {
            return Status.STRIKE;
        }

        validateExistSecondScore();

        if (scores.get(FIRST_INDEX) + scores.get(SECOND_INDEX) == MAX_PIN_NUMBER) {
            return Status.SPARE;
        }

        if (scores.get(FIRST_INDEX) == GUTTER_PIN_NUMBER || scores.get(SECOND_INDEX) == GUTTER_PIN_NUMBER) {
            return Status.GUTTER;
        }

        return Status.MISS;
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

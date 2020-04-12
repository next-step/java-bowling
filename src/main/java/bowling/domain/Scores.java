package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private static final int FRAME_MAX_SCORE = 10;
    private static final int FINAL_MAX_SCORE = 20;
    private static final int NORMAL_TRY_NUMBER = 2;
    private static final int FINAL_TRY_NUMBER = 3;
    private List<Score> scores = new ArrayList<>();

    public Scores(Score score) {
        this.scores.add(score);
    }

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public void add(Score score) {
        this.scores.add(score);
    }

    public int size() {
        return scores.size();
    }

    public int sum() {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    public int numberOfTry() {
        return (int) scores.stream()
                .count();
    }

    public boolean nextFrame() {
        return isStrike();
    }

    private boolean isStrike() {
        return sum() == FRAME_MAX_SCORE;
    }

    private boolean isMiss() {
        return sum() < FRAME_MAX_SCORE;
    }

    @Override
    public String toString() {
        return "Scores{" +
                "scores=" + scores +
                '}';
    }

    public void checkNormalSum(int numberOfPin) {
        if (sumUntilThisValue(numberOfPin) > FRAME_MAX_SCORE) {
            throw new IllegalArgumentException(FRAME_MAX_SCORE + "을 넘으면 안됩니다.");
        }
    }

    private int sumUntilThisValue(int numberOfPin) {
        return sum() + numberOfPin;
    }

    public boolean isEndFinalFrame() {
        if (numberOfTry() == NORMAL_TRY_NUMBER && isMiss()) {
            return true;
        }
        if (numberOfTry() == FINAL_TRY_NUMBER) {
            return true;
        }
        return false;
    }


    public void checkFinalSum(int numberOfPin) {
        //처음에스트라이크가 아닌경우
        if (numberOfTry() == 1 && !isStrike() && sumUntilThisValue(numberOfPin) > FRAME_MAX_SCORE) {
            throw new IllegalArgumentException(FRAME_MAX_SCORE + "을 넘으면 안됩니다.");
        }
        //첫번째 스트라이크, 두번째
        if (numberOfTry() == 2 && sum() < FINAL_MAX_SCORE && sumUntilThisValue(numberOfPin) > FINAL_MAX_SCORE) {
            throw new IllegalArgumentException(FINAL_MAX_SCORE + "을 넘으면 안됩니다.");
        }
    }
}

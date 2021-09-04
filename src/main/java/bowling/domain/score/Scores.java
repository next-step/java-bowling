package bowling.domain.score;

import bowling.domain.frame.Frame;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Scores {
    private final List<Score> scores;

    private Scores(List<Score> scores) {
        this.scores = scores;
    }

    public static Scores of(Score... scores) {
        return new Scores(Arrays.stream(scores)
            .map(Score::from)
            .collect(Collectors.toList())
        );
    }

    public static Scores of(int... scores) {
        return new Scores(Arrays.stream(scores)
            .mapToObj(Score::from)
            .collect(Collectors.toList())
        );
    }

    public void addScore(Score score) {
        scores.add(score);
    }

    public boolean isOverTenInNormalFrame() {
        if (scores.size() < 2) {
            return false;
        }

        return scores.get(0)
            .isOverTenAfterAdd(
                scores.get(1)
            );
    }

    public boolean isOverTenInFinalFrame() {
        if (scores.size() < 2) {
            return false;
        }

        Score firstScore = scores.get(0);
        Score secondScore = scores.get(1);
        if (scores.size() == 2 && checkFirstScoreAndSecondScore()) {
            return false;
        }

        if (scores.size() == 2 && firstScore.isOverTenAfterAdd(secondScore)) {
            return true;
        }
        return checkSecondScoreAndThirdScore();
    }

    private boolean checkFirstScoreAndSecondScore() {
        Score firstScore = scores.get(0);
        Score secondScore = scores.get(1);

        boolean isFirstScoreEqualToTen = firstScore.equals(Frame.TEN_SCORE);
        boolean isFirstAndSecondEqualToTen = firstScore.isEqualTenAfterAdd(secondScore);

        return isFirstScoreEqualToTen || isFirstAndSecondEqualToTen;
    }

    private boolean checkSecondScoreAndThirdScore() {
        if (scores.size() < 3) {
            return false;
        }

        Score firstScore  = scores.get(0);
        Score secondScore = scores.get(1);
        Score thirdScore  = scores.get(2);
        if (firstScore.isEqualTenAfterAdd(secondScore)) {
            return false;
        }
        return !secondScore.equals(Frame.TEN_SCORE) && secondScore.isOverTenAfterAdd(thirdScore);
    }

    public Score firstTryScore() {
        if (scores.size() < 1) {
            return null;
        }
        return Score.from(scores.get(0));
    }

    public Score secondTryScore() {
        if (scores.size() < 2) {
            return null;
        }
        return Score.from(scores.get(1));
    }

    public Score thirdTryScore() {
        if (scores.size() < 3) {
            return null;
        }
        return Score.from(scores.get(2));
    }

    public int numberOfTurnInFrame() {
        return scores.size();
    }
}

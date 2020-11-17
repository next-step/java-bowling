package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.point.Point;
import bowling.domain.score.Score2;
import bowling.domain.score.ScoreGenerator;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame2 implements Frame2 {
    private static final String ROLL_COUNT_ERRORS = "더 이상 던질 수 없습니다";
    private static final String ROLL_SIZE_ERROR = "첫 투구가 있어야합니다";
    private static final String BLOCK = "|";
    private List<Score2> scores;
    private int rollCount = 0;

    public FinalFrame2(List<Score2> scores, int rollCount) {
        validate(rollCount);
        this.scores = scores;
        this.rollCount = rollCount;
    }

    private void validate(int rollCount) {
        if (rollCount > MAX_FINAL_FRAME_CAN_ROLL) {
            throw new IllegalArgumentException(ROLL_COUNT_ERRORS);
        }
    }

    @Override
    public Frame2 roll(Point point) {
        if (rollCount == 0) {
            return firstRoll(point);
        }
        return nextRoll(point);
    }

    private Frame2 firstRoll(Point point) {
        Score2 score = ScoreGenerator.of(point);
        scores.add(score);
        if (score.isStrike()) {
            return FinalFrame2.of(scores, rollCount + 1);
        }
        return FinalFrame2.of(scores, rollCount + 1);
    }

    private Frame2 nextRoll(Point point) {
        rollCheck();
        Score2 score = scores.get(rollCount - 1);
        Score2 nextScore = score.nextScore(point);
        scores.add(nextScore);

        if (nextScore.isSpare()) {
            return FinalFrame2.of(scores, rollCount + 1);
        }
        if (score.isStrike()) {
            return FinalFrame2.of(scores, rollCount + 1);
        }
        return FinalFrame2.of(scores, MAX_FINAL_FRAME_CAN_ROLL);
    }

    private void rollCheck() {
        if (scores.size() == 0) {
            throw new IllegalArgumentException(ROLL_SIZE_ERROR);
        }
    }

    public boolean isLastRoll() {
        return rollCount == MAX_FINAL_FRAME_CAN_ROLL;
    }

    public static Frame2 of() {
        List<Score2> scores = new ArrayList<>();
        return new FinalFrame2(scores, 0);
    }

    public static Frame2 of(List<Score2> scores, int rollCount) {
        return new FinalFrame2(scores, rollCount);
    }

    @Override
    public String getScores() {
        String result = "";
        if (scores.size() == FIRST_ROLL) {
            return scores.get(0).getScore();
        }
        if (scores.size() == SECOND_ROLL) {
            return scores.get(0).getScore() + BLOCK + scores.get(1).getScore();
        }
        if (scores.size() == BONUS_ROLL) {
            return scores.get(0).getScore() + BLOCK + scores.get(1).getScore() + BLOCK + scores.get(2).getScore();
        }
        return result;
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public int getPoint() {
        if (rollCount == MAX_FINAL_FRAME_CAN_ROLL) {
            return scores.stream()
                    .map(Score2::getPoint)
                    .mapToInt(Integer::intValue)
                    .sum();
        }
        return 0;
    }
}

package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.score.Score2;
import bowling.domain.score.ScoreGenerator;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame2 implements Frame2 {
    private static final String ROLL_COUNT_ERRORS = "일반 게임에서는 두번만 던질 수 있습니다.";
    private static final String ROLL_SIZE_ERROR = "첫 투구가 있어야합니다";
    private static final String BLOCK = "|";

    private List<Score2> scores;
    private int rollCount = 0;
    private int point = 0;

    private NormalFrame2(List<Score2> scores, int point, int rollCount) {
        validate(rollCount);
        this.scores = scores;
        this.rollCount = rollCount;
        this.point = point;
    }

    private void validate(int rollCount) {
        if (rollCount > MAX_NORMAL_FRAME_CAN_ROLL) {
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

    public Frame2 firstRoll(Point point) {
        Score2 score = ScoreGenerator.of(point);
        scores.add(score);

        if (score.isStrike()) {
            return NormalFrame2.of(scores, score.getPoint(), MAX_NORMAL_FRAME_CAN_ROLL);
        }
        return NormalFrame2.of(scores, score.getPoint(), MAX_NORMAL_FRAME_CAN_ROLL);
    }

    private Frame2 nextRoll(Point point) {
        rollCheck();
        Score2 firstScore = scores.get(rollCount - 1);
        Score2 nextScore = firstScore.nextScore(point);
        scores.add(nextScore);

        if (firstScore.isSpare()) {
            NormalFrame2.of(scores, firstScore.getPoint(), MAX_NORMAL_FRAME_CAN_ROLL);
        }
        return NormalFrame2.of(scores, firstScore.getPoint(), MAX_NORMAL_FRAME_CAN_ROLL);
    }

    private void rollCheck() {
        if (scores.size() == 0) {
            throw new IllegalArgumentException(ROLL_SIZE_ERROR);
        }
    }

    public boolean isLastRoll() {
        return rollCount == MAX_NORMAL_FRAME_CAN_ROLL;
    }

    @Override
    public boolean isFinalFrame() {
        return false;
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
        return result;
    }

    @Override
    public int getPoint() {
        if (rollCount == MAX_FINAL_FRAME_CAN_ROLL) {
            return this.point;
        }
        return 0;
    }

    public static Frame2 of() {
        List<Score2> scores = new ArrayList<>();
        return new NormalFrame2(scores, 0, 0);
    }

    public static Frame2 of(List<Score2> scores, int point, int rollCount) {
        return new NormalFrame2(scores, point, rollCount);
    }
}

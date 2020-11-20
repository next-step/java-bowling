package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.score.Score2;
import bowling.domain.score.ScoreGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame2 implements Frame2 {
    private static final String ROLL_COUNT_ERRORS = "더 이상 던질 수 없습니다";
    private static final String ROLL_SIZE_ERROR = "첫 투구가 있어야합니다";
    private static final String BLOCK = "|";
    private static final String BLANK = "";
    private List<Score2> scores;
    private int rollCount = 0;
    private int point = 0;

    public FinalFrame2(List<Score2> scores, int point, int rollCount) {
        validate(rollCount);
        this.scores = scores;
        this.rollCount = rollCount;
        this.point = point;
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
        int totalPoint = this.point + point.getPoint();
        Score2 score = ScoreGenerator.of(point);
        this.scores.add(score);
        if (score.isStrike()) {
            return FinalFrame2.of(this.scores, point.getPoint(), rollCount + 1);
        }
        return FinalFrame2.of(this.scores, totalPoint, rollCount + 1);
    }

    private Frame2 nextRoll(Point point) {
        rollCheck();
        Score2 score = this.scores.get(rollCount - 1);
        Score2 nextScore = score.nextScore(point);
        this.scores.add(nextScore);
        int totalPoint = this.point + point.getPoint();

        if (nextScore.isSpare()) {
            return FinalFrame2.of(this.scores, totalPoint, rollCount + 1);
        }
        if (score.isStrike()) {
            return FinalFrame2.of(this.scores, totalPoint, rollCount + 1);
        }
        return FinalFrame2.of(this.scores, totalPoint, MAX_FINAL_FRAME_CAN_ROLL);
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
        return new FinalFrame2(scores, 0, 0);
    }

    public static Frame2 of(List<Score2> scores, int point, int rollCount) {
        return new FinalFrame2(scores, point, rollCount);
    }

    @Override
    public String getScores() {
        return scores.stream()
                .map(Score2::getScore)
                .collect(Collectors.joining(BLOCK));
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public int getPoint() {
        if (rollCount == MAX_FINAL_FRAME_CAN_ROLL) {
            return this.point;
        }
        return 0;
    }
}

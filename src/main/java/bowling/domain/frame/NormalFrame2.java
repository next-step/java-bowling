package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.score.ScoreGenerator;
import bowling.domain.score.Score2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NormalFrame2 implements Frame2 {
    private static final String ROLL_COUNT_ERRORS = "일반 게임에서는 두번만 던질 수 있습니다.";
    private static final String ROLL_SIZE_ERROR = "첫 투구가 있어야합니다";
    private static final String BLOCK = "|";
    private static final String NONE = "";

    private List<Score2> score;
    private int rollCount = 0;
    private int point = 0;

    private NormalFrame2(List<Score2> score, int point, int rollCount) {
        validate(rollCount);
        this.score = score;
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
        Score2 score2 = ScoreGenerator.of(point);
        score.add(score2);

        if (score2.isStrike()) {
            return NormalFrame2.of(score, score2.getPoint(), MAX_NORMAL_FRAME_CAN_ROLL);
        }
        return NormalFrame2.of(score, score2.getPoint(), rollCount + 1);
    }

    private Frame2 nextRoll(Point point) {
        rollCheck();
        Score2 firstScore2 = score.get(rollCount - 1);
        Score2 nextScore2 = firstScore2.nextScore(point);
        score.add(nextScore2);
        int totalPoint = this.point + point.getPoint();

        if (firstScore2.isSpare()) {
            NormalFrame2.of(score, totalPoint, MAX_NORMAL_FRAME_CAN_ROLL);
        }
        return NormalFrame2.of(score, totalPoint, MAX_NORMAL_FRAME_CAN_ROLL);
    }

    private void rollCheck() {
        if (score.size() == 0) {
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
        return score.stream()
                .map(Score2::getScore)
                .collect(Collectors.joining(BLOCK));
    }

    @Override
    public int getPoint() {
        if (rollCount == MAX_NORMAL_FRAME_CAN_ROLL) {
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

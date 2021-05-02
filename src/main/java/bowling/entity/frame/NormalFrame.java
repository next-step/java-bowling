package bowling.entity.frame;

import bowling.entity.BowlingBoard;
import bowling.entity.Pin;
import bowling.entity.Score;
import bowling.entity.score.CalculateImPossibleException;
import bowling.entity.score.ScoreType;

import java.util.Objects;

public class NormalFrame implements Frame {
    public static final int NOT_CALCULATE_VALUE = -1;
    private static final String EMPTY_STRING = "";

    private final NormalFrameInfo normalFrameInfo;
    private Frame nextFrame;

    public NormalFrame(int frameNo) {
        this.normalFrameInfo = new NormalFrameInfo(frameNo);
    }

    public NormalFrame(int frameNo, ScoreType scoreType) {
        this.normalFrameInfo = new NormalFrameInfo(frameNo, scoreType);
    }

    public Frame bowl(Pin fallenPin) {

        if (normalFrameInfo.bowl(fallenPin).isFrameEnd()) {
            nextFrame = nextFrame();
            return nextFrame;
        }

        return this;
    }


    private Frame nextFrame() {
        if (normalFrameInfo.nextFrameIsLastFrame()) {
            return new LastFrame();
        }
        return new NormalFrame(normalFrameInfo.frameNo() + 1);
    }

    private Score score() {
        Score score = normalFrameInfo.score();

        if (score.calculatePossible()) {
            return score;
        }

        return nextFrame.calculate(score);
    }

    @Override
    public Score calculate(Score score) {

        Score calculateScore = normalFrameInfo.calculate(score);

        if (calculateScore.calculatePossible()) {
            return calculateScore;
        }

        return nextFrame.calculate(calculateScore);
    }

    public BowlingBoard bowlingBoard() {
        BowlingBoard bowlingBoard = new BowlingBoard();
        addFrameResult(bowlingBoard, 0);
        return bowlingBoard;
    }

    private NormalFrameResult frameResult(String scoreResult, int totalScore) {

        if (!normalFrameInfo.isFrameEnd()) {
            return new NormalFrameResult(scoreResult, NOT_CALCULATE_VALUE);
        }

        try {
            totalScore += score().score();
            return new NormalFrameResult(scoreResult, totalScore);
        } catch (CalculateImPossibleException e) {
            return new NormalFrameResult(scoreResult, NOT_CALCULATE_VALUE);
        }
    }

    public void addFrameResult(BowlingBoard bowlingBoard, int totalScore) {
        String scoreResult = normalFrameInfo.scoreResult();

        if (bowlResultIsNotNone(scoreResult)) {
            NormalFrameResult frameResult = frameResult(scoreResult, totalScore);
            totalScore = frameResult.totalScore();
            bowlingBoard.addResult(frameResult);
        }

        if (nextFrame != null) {
            nextFrame.addFrameResult(bowlingBoard, totalScore);
        }
    }

    private boolean bowlResultIsNotNone(String scoreResult) {
        return !(scoreResult.equals(EMPTY_STRING));
    }

    @Override
    public boolean bowlingGameEnd() {
        return false;
    }

    @Override
    public int frameNo() {
        return normalFrameInfo.frameNo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(normalFrameInfo, that.normalFrameInfo) && Objects.equals(nextFrame, that.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalFrameInfo, nextFrame);
    }
}

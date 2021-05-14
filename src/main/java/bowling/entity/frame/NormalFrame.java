package bowling.entity.frame;

import bowling.entity.BowlingBoard;
import bowling.entity.Pin;
import bowling.entity.TotalScore;
import bowling.entity.score.CalculateImPossibleException;
import bowling.entity.score.ScoreType;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {
    public static final int NOT_CALCULATE_VALUE = -1;
    private static final String EMPTY_STRING = "";

    private final NormalFrameInfo normalFrameInfo;
    private Frame nextFrame;

    public NormalFrame(int frameNo) {
        this.normalFrameInfo = new NormalFrameInfo(frameNo);
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

    public BowlingBoard bowlingBoard() {
        TotalScore totalScore = new TotalScore();
        addScore(totalScore);

        BowlingBoard bowlingBoard = new BowlingBoard();
        addFrameResult(bowlingBoard, totalScore);

        return bowlingBoard;
    }

    @Override
    public void addScore(TotalScore totalScore){

        List<ScoreType> scoreTypes = normalFrameInfo.scoreTypes();
        if (!CollectionUtils.isEmpty(scoreTypes)) {
            totalScore.addScore(scoreTypes);
        }

        if (nextFrame != null) {
            nextFrame.addScore(totalScore);
        }
    }

    private NormalFrameResult frameResult(String scoreResult, int totalScore) {

        if (!normalFrameInfo.isFrameEnd()) {
            return new NormalFrameResult(scoreResult, NOT_CALCULATE_VALUE);
        }

        try {
            return new NormalFrameResult(scoreResult, totalScore);
        } catch (CalculateImPossibleException e) {
            return new NormalFrameResult(scoreResult, NOT_CALCULATE_VALUE);
        }
    }

    @Override
    public void addFrameResult(BowlingBoard bowlingBoard, TotalScore totalScore) {

        String scoreResult = normalFrameInfo.scoreResult();
        int totalScoreResult;

        try {
            totalScoreResult = totalScore.getFrameScoreResults(frameNo());
        } catch (CalculateImPossibleException e) {
            totalScoreResult = NOT_CALCULATE_VALUE;
        }

        if (bowlResultIsNotNone(scoreResult)) {
            NormalFrameResult frameResult = frameResult(scoreResult, totalScoreResult);
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

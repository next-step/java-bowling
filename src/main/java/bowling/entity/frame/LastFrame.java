package bowling.entity.frame;

import bowling.entity.BowlingBoard;
import bowling.entity.Pin;
import bowling.entity.TotalScore;
import bowling.entity.score.CalculateImPossibleException;
import bowling.entity.score.ScoreType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static bowling.controller.BowlingController.END_FRAME;
import static bowling.entity.Pin.SCORE_ASSOCIATION_SYMBOL;
import static bowling.entity.frame.NormalFrame.NOT_CALCULATE_VALUE;

public class LastFrame implements Frame {
    private static final int EMPTY = 0;

    private final LastFrameInfo lastFrameInfo;
    private final List<ScoreType> scoreTypes;

    public LastFrame() {
        lastFrameInfo = new LastFrameInfo();
        this.scoreTypes = new ArrayList<>();
    }

    @Override
    public Frame bowl(Pin fallenPin) {
        ScoreType scoreType = lastFrameInfo.bowl(fallenPin);
        scoreTypes.add(scoreType);
        return this;
    }

    @Override
    public void addScore(TotalScore totalScore) {
        if (!CollectionUtils.isEmpty(scoreTypes)) {
            totalScore.addScore(scoreTypes);
        }
    }

    @Override
    public void addFrameResult(BowlingBoard bowlingBoard, TotalScore totalScore) {
        if (scoreTypes.size() != EMPTY) {
            int totalScoreResult;

            try {
                totalScoreResult = totalScore.getFrameScoreResults(frameNo());
            } catch (CalculateImPossibleException e) {
                totalScoreResult = NOT_CALCULATE_VALUE;
            }

            LastFrameResult lastFrameResult = lastFrameResult(totalScoreResult);
            bowlingBoard.addResult(lastFrameResult);
        }
    }

    private LastFrameResult lastFrameResult(int totalScore) {

        if (!lastFrameInfo.bowlingGameEnd()) {
            return new LastFrameResult(scoreTypesResult(), NOT_CALCULATE_VALUE);
        }

        try {
            return new LastFrameResult(scoreTypesResult(), totalScore);
        } catch (CalculateImPossibleException e) {
            return new LastFrameResult(scoreTypesResult(), NOT_CALCULATE_VALUE);
        }
    }

    private String scoreTypesResult() {

        String scoreTypesResult = this.scoreTypes.stream()
                .filter(ScoreType::isFrameEnd)
                .map(ScoreType::scoreResult)
                .collect(Collectors.joining(SCORE_ASSOCIATION_SYMBOL));

        if (!StringUtils.hasLength(scoreTypesResult)) {
            return lastFrameInfo.scoreResult();
        }

        if (StringUtils.hasLength(scoreTypesResult) && (!lastFrameInfo.isFrameEnd())) {
            return scoreTypesResult + SCORE_ASSOCIATION_SYMBOL + lastFrameInfo.scoreResult();
        }

        return scoreTypesResult;
    }

    @Override
    public BowlingBoard bowlingBoard() {
        TotalScore totalScore = new TotalScore();
        addScore(totalScore);

        BowlingBoard bowlingBoard = new BowlingBoard();
        addFrameResult(bowlingBoard, totalScore);
        return bowlingBoard;
    }

    @Override
    public int frameNo() {
        return END_FRAME;
    }

    @Override
    public boolean bowlingGameEnd() {
        return lastFrameInfo.bowlingGameEnd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastFrame lastFrame = (LastFrame) o;
        return Objects.equals(lastFrameInfo, lastFrame.lastFrameInfo) && Objects.equals(scoreTypes, lastFrame.scoreTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastFrameInfo, scoreTypes);
    }
}

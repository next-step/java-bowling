package bowling.entity.frame;

import bowling.entity.BowlingBoard;
import bowling.entity.Pin;
import bowling.entity.score.None;
import bowling.entity.score.ScoreType;
import bowling.entity.score.Spare;
import bowling.entity.score.Strike;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.controller.BowlingController.END_FRAME;
import static bowling.entity.Pin.SCORE_ASSOCIATION_SYMBOL;

public class LastFrame implements Frame {
    private static final int DEFAULT_BOWL_COUNT = 2;
    private static final int NOT_START_BOWL_COUNT = 0;
    private static final int MAX_BOWL_COUNT = 3;
    private static final int EMPTY = 0;

    private ScoreType scoreType;
    private int maxBowlCount;
    private int currentBowlCount;
    private final List<ScoreType> scoreTypes;

    public LastFrame() {
        this.scoreType = new None();
        this.maxBowlCount = DEFAULT_BOWL_COUNT;
        this.currentBowlCount = NOT_START_BOWL_COUNT;
        this.scoreTypes = new ArrayList<>();
    }

    @Override
    public Frame pinResult(Pin fallenPin) {
        scoreType = scoreType.pinResult(fallenPin);
        scoreTypes.add(scoreType);

        if (scoreType instanceof Strike || scoreType instanceof Spare) {
            maxBowlCount = MAX_BOWL_COUNT;
        }

        currentBowlCount++;
        return this;
    }

    @Override
    public void addFrameResult(BowlingBoard bowlingBoard) {
        normalFrameResult(bowlingBoard);
        lastFrameResult(bowlingBoard);
    }

    private void normalFrameResult(BowlingBoard bowlingBoard) {
        if ((!(scoreType instanceof None)) && (frameNo() != END_FRAME)) {
            bowlingBoard.addResult(new NormalFrameResult(scoreType.scoreResult()));
        }
    }

    private void lastFrameResult(BowlingBoard bowlingBoard) {
        if (scoreTypes.size() != EMPTY) {
            bowlingBoard.addResult(new LastFrameResult(scoreTypesResult()));
        }
    }

    private String scoreTypesResult() {

        String scoreTypesResult = this.scoreTypes.stream()
                .filter(ScoreType::isFrameEnd)
                .map(ScoreType::scoreResult)
                .collect(Collectors.joining(SCORE_ASSOCIATION_SYMBOL));

        if (!StringUtils.hasLength(scoreTypesResult)) {
            return scoreType.scoreResult();
        }

        if (StringUtils.hasLength(scoreTypesResult) && (!scoreType.isFrameEnd())) {
            return scoreTypesResult + SCORE_ASSOCIATION_SYMBOL + scoreType.scoreResult();
        }

        return scoreTypesResult;
    }

    @Override
    public BowlingBoard bowlingBoard() {
        BowlingBoard bowlingBoard = new BowlingBoard();
        addFrameResult(bowlingBoard);
        return bowlingBoard;
    }

    @Override
    public int frameNo() {
        return END_FRAME;
    }

    @Override
    public boolean bowlingGameEnd() {
        return maxBowlCount == currentBowlCount;
    }
}

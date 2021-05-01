package bowling.entity.frame;

import bowling.entity.BowlingBoard;
import bowling.entity.Pin;
import bowling.entity.score.None;
import bowling.entity.score.ScoreType;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static bowling.controller.BowlingController.END_FRAME;
import static bowling.entity.Pin.SCORE_ASSOCIATION_SYMBOL;

public class LastFrame implements Frame {
    private static final int EMPTY = 0;

    private final LastFrameInfo lastFrameInfo;
    private final List<ScoreType> scoreTypes;

    public LastFrame() {
        lastFrameInfo = new LastFrameInfo(new None());
        this.scoreTypes = new ArrayList<>();
    }

    @Override
    public Frame bowl(Pin fallenPin) {
        ScoreType scoreType = lastFrameInfo.bowl(fallenPin);
        scoreTypes.add(scoreType);
        return this;
    }

    @Override
    public void addFrameResult(BowlingBoard bowlingBoard) {
        normalFrameResult(bowlingBoard);
        lastFrameResult(bowlingBoard);
    }

    private void normalFrameResult(BowlingBoard bowlingBoard) {
        String scoreResult = lastFrameInfo.scoreResult();

        if (!(scoreResult.equals("")) && (frameNo() != END_FRAME)) {
            bowlingBoard.addResult(new NormalFrameResult(scoreResult));
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
            return lastFrameInfo.scoreResult();
        }

        if (StringUtils.hasLength(scoreTypesResult) && (!lastFrameInfo.isFrameEnd())) {
            return scoreTypesResult + SCORE_ASSOCIATION_SYMBOL + lastFrameInfo.scoreResult();
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

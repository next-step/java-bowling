package bowling.dto;

import bowling.domain.BowlingBoard;
import bowling.domain.ThrowsState;

import java.util.ArrayList;
import java.util.List;

public class BowlingBoardDto {
    private final List<ScoreDto> scoreDtoList;
    private final ThrowsState throwsState;
    private final List<Integer> framePoints;

    private BowlingBoardDto(List<ScoreDto> scoreDtoList, ThrowsState throwsState, List<Integer> framePoints) {
        this.scoreDtoList = scoreDtoList;
        this.throwsState = throwsState;
        this.framePoints = framePoints;
    }

    public static BowlingBoardDto of(BowlingBoard bowlingBoard) {
        return new BowlingBoardDto(bowlingBoard.toScoreDto(), bowlingBoard.state(), bowlingBoard.calculate());
    }

    public static BowlingBoardDto of() {
        return new BowlingBoardDto(new ArrayList<>(), ThrowsState.FIRST_THROWS, new ArrayList<>());
    }

    public List<ScoreDto> getScoreDtoList() {
        return scoreDtoList;
    }

    public ThrowsState getThrowsState() {
        return throwsState;
    }

    public List<Integer> getFramePoint() {
        return framePoints;
    }
}

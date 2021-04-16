package bowling.dto;

import bowling.domain.BowlingBoard;
import bowling.domain.ThrowsState;

import java.util.ArrayList;
import java.util.List;

public class BowlingBoardDto {

    private final List<ScoreDto> scoreDtoList;
    private final ThrowsState throwsState;

    private BowlingBoardDto(List<ScoreDto> scoreDtoList, ThrowsState throwsState) {
        this.scoreDtoList = scoreDtoList;
        this.throwsState = throwsState;
    }

    public static BowlingBoardDto of(BowlingBoard bowlingBoard) {
        return new BowlingBoardDto(bowlingBoard.toScoreDto(), bowlingBoard.state());
    }

    public static BowlingBoardDto of() {
        return new BowlingBoardDto(new ArrayList<>(), ThrowsState.FIRST_THROWS);
    }

    public List<ScoreDto> getScoreDtoList() {
        return scoreDtoList;
    }

    public ThrowsState getThrowsState() {
        return throwsState;
    }
}

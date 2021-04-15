package bowling.dto;

import bowling.domain.BowlingBoard;

import java.util.List;

public class BowlingBoardDto {

    private final List<ScoreDto> scoreDtoList;

    private BowlingBoardDto(List<ScoreDto> scoreDtoList) {
        this.scoreDtoList = scoreDtoList;
    }

    public static BowlingBoardDto of(BowlingBoard bowlingBoard) {
        return new BowlingBoardDto(bowlingBoard.toScoreDto());
    }

    public List<ScoreDto> getScoreDtoList() {
        return scoreDtoList;
    }
}

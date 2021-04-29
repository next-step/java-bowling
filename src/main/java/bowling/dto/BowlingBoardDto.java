package bowling.dto;

import bowling.domain.bowlingboard.BowlingBoard;
import bowling.domain.bowlingboard.ThrowCount;
import bowling.domain.score.FramePoint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingBoardDto {

    private final ScoresDto scoresDto;
    private final ThrowCount throwCount;
    private final List<Integer> framePoints;

    private BowlingBoardDto(List<ScoreDto> scoreDtoList, ThrowCount throwCount, List<FramePoint> framePoints) {
        this.scoresDto = ScoresDto.of(scoreDtoList);
        this.throwCount = throwCount;
        this.framePoints = framePoints.stream()
                .map(FramePoint::toInt)
                .collect(Collectors.toList());
    }


    public static BowlingBoardDto of(BowlingBoard bowlingBoard) {
        return new BowlingBoardDto(bowlingBoard.toScoreDto(), bowlingBoard.state(), bowlingBoard.framePoint());
    }

    public static BowlingBoardDto of() {
        return new BowlingBoardDto(new ArrayList<>(), ThrowCount.first(), new ArrayList<>());
    }

    public List<ScoreDto> getScoreDtoList() {
        return scoresDto.getScoreDtoList();
    }

    public ThrowCount getThrowCount() {
        return throwCount;
    }

    public List<Integer> getFramePoint() {
        return framePoints;
    }
}

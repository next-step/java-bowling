package bowling.dto;

import bowling.domain.BowlingBoard;
import bowling.domain.FramePoint;
import bowling.domain.ThrowCount;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingBoardDto {
    private final List<ScoreDto> scoreDtoList;
    private final ThrowCount throwCount;
    private final List<Integer> framePoints;

    private BowlingBoardDto(List<ScoreDto> scoreDtoList, ThrowCount throwCount, List<FramePoint> framePoints) {
        this.scoreDtoList = scoreDtoList;
        this.throwCount = throwCount;
        this.framePoints = framePoints.stream()
                .map(FramePoint::toInt)
                .collect(Collectors.toList());
    }

    public static BowlingBoardDto of(BowlingBoard bowlingBoard) {
        return new BowlingBoardDto(bowlingBoard.toScoreDto(), bowlingBoard.state(), bowlingBoard.framePoint());
    }

    public static BowlingBoardDto of() {
        return new BowlingBoardDto(new ArrayList<>(), ThrowCount.fisrt(), new ArrayList<>());
    }

    public List<ScoreDto> getScoreDtoList() {
        return scoreDtoList;
    }

    public ThrowCount getThrowCount() {
        return throwCount;
    }

    public List<Integer> getFramePoint() {
        return framePoints;
    }
}

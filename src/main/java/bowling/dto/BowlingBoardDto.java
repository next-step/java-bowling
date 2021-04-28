package bowling.dto;

import bowling.domain.bowlingboard.BowlingBoard;
import bowling.domain.bowlingboard.ThrowCount;
import bowling.domain.score.FramePoint;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingBoardDto {

    private static final int FINAL_ROUND = 10;

    private final List<ScoreDto> scoreDtoList;
    private final ThrowCount throwCount;
    private final List<Integer> framePoints;

    private BowlingBoardDto(List<ScoreDto> scoreDtoList, ThrowCount throwCount, List<FramePoint> framePoints) {
        this.scoreDtoList = createScoreDtoList(scoreDtoList);
        this.throwCount = throwCount;
        this.framePoints = framePoints.stream()
                .map(FramePoint::toInt)
                .collect(Collectors.toList());
    }

    private List<ScoreDto> createScoreDtoList(List<ScoreDto> scoreDtoList) {
        int emptyScoreCount = FINAL_ROUND - scoreDtoList.size();
        for (int i = 0; i < emptyScoreCount; i++) {
            scoreDtoList.add(ScoreDto.of(Score.initScore()));
        }
        return scoreDtoList;
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

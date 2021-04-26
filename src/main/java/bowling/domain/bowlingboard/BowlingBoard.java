package bowling.domain.bowlingboard;

import bowling.domain.ThrowsState;
import bowling.domain.frame.BowlingFrame;
import bowling.domain.frame.BowlingNormalFrame;
import bowling.domain.frame.round.Round;
import bowling.domain.score.FramePoint;
import bowling.domain.score.Point;
import bowling.dto.ScoreDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingBoard {

    private final List<BowlingFrame> bowlingFrameList;
    private final ThrowCount throwCount;

    private BowlingBoard(List<BowlingFrame> bowlingFrameList, ThrowCount throwCount) {
        this.bowlingFrameList = bowlingFrameList;
        this.throwCount = throwCount;
    }

    public static BowlingBoard of() {
        List<BowlingFrame> bowlingFrameList = new ArrayList<>();
        bowlingFrameList.add(BowlingNormalFrame.first(Round.first()));
        return new BowlingBoard(bowlingFrameList, ThrowCount.fisrt());
    }

    public static BowlingBoard of(List<BowlingFrame> bowlingFrameList, ThrowCount throwCount) {
        return new BowlingBoard(bowlingFrameList, throwCount);
    }

    public int round() {
        return bowlingFrameList.size();
    }

    public BowlingBoard pitching(Point point) {
        return ThrowsState.throwBall(point, bowlingFrameList, throwCount);
    }

    public List<ScoreDto> toScoreDto() {
        return bowlingFrameList.stream()
                .map(BowlingFrame::toDto)
                .collect(Collectors.toList());
    }

    public boolean isEnd() {
        return throwCount.equals(ThrowCount.of(3));
    }

    public List<FramePoint> framePoint() {
        return bowlingFrameList.stream()
                .map(BowlingFrame::calculateOfScore)
                .collect(Collectors.toList());
    }

    public ThrowCount state() {
        return throwCount;
    }
}

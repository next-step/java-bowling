package bowling.domain;

import bowling.dto.ScoreDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingBoard {

    private final List<BowlingFrame> bowlingFrameList;
    private final boolean isFirstThrow;

    public BowlingBoard(List<BowlingFrame> bowlingFrameList, boolean isFirstThrow) {
        this.bowlingFrameList = bowlingFrameList;
        this.isFirstThrow = isFirstThrow;
    }

    public static BowlingBoard of() {
        List<BowlingFrame> bowlingFrameList = new ArrayList<>();
        bowlingFrameList.add(BowlingNormalFrame.first(Round.first()));
        return new BowlingBoard(bowlingFrameList, true);
    }

    public static BowlingBoard of(List<BowlingFrame> bowlingFrameList, boolean isFirstThrow) {
        return new BowlingBoard(bowlingFrameList, isFirstThrow);
    }

    public int round() {
        return bowlingFrameList.size();
    }

    private BowlingFrame getFrame() {
        return bowlingFrameList.get(bowlingFrameList.size() - 1);
    }

    public List<BowlingFrame> firstPitching(Point point) {
        getFrame().firstPitching(point);
        return bowlingFrameList;
    }

    public List<BowlingFrame> secondPitching(Point point) {
        getFrame().secondPitching(point);
        return bowlingFrameList;
    }

    public boolean isType(BowlingRole type) {
        return getFrame().isType() == type;
    }

    public BowlingBoard pitching(Point point) {
        if (isFirstThrow) {
            bowlingFrameList.add(getFrame().nextFrame());
            return BowlingBoard.of(firstPitching(point), isType(BowlingRole.STRIKE));
        }
        return BowlingBoard.of(secondPitching(point), true);
    }

    public List<ScoreDto> toScoreDto() {
        return bowlingFrameList.stream()
                .map(BowlingFrame::toDto)
                .collect(Collectors.toList());
    }

    public boolean isEnd() {
        return getFrame() instanceof BowlingFinalFrame || isType(BowlingRole.MISS) || !isFirstThrow;
    }
}

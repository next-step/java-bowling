package bowling.domain;

import bowling.dto.ScoreDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingBoard {

    private final List<BowlingFrame> bowlingFrameList;
    private final ThrowsState throwsState;

    public BowlingBoard(List<BowlingFrame> bowlingFrameList, ThrowsState throwsState) {
        this.bowlingFrameList = bowlingFrameList;
        this.throwsState = throwsState;
    }

    public static BowlingBoard of() {
        List<BowlingFrame> bowlingFrameList = new ArrayList<>();
        bowlingFrameList.add(BowlingNormalFrame.first(Round.first()));
        return new BowlingBoard(bowlingFrameList, ThrowsState.FIRST_THROWS);
    }

    public static BowlingBoard of(List<BowlingFrame> bowlingFrameList, ThrowsState throwsState) {
        return new BowlingBoard(bowlingFrameList, throwsState);
    }

    public int round() {
        return bowlingFrameList.size();
    }

    private BowlingFrame getFrame() {
        return bowlingFrameList.get(bowlingFrameList.size() - 1);
    }

    public BowlingBoard firstPitching(Point point) {
        bowlingFrameList.set(round() - 1, getFrame().firstPitching(point));
        return BowlingBoard.of(bowlingFrameList, ThrowsState.SECOND_THROWS);
    }

    public BowlingBoard secondPitching(Point point) {
        if (round() == 10) {
            return BowlingBoard.of(bowlingFrameList, ThrowsState.FINISH_THROWS);
        }
        bowlingFrameList.set(round() - 1, getFrame().secondPitching(point));
        return BowlingBoard.of(bowlingFrameList, ThrowsState.FIRST_THROWS);
    }

    public BowlingBoard bonusPitching(Point point) {
        bowlingFrameList.set(round() - 1, getFrame().bonusPitching(point));
        return BowlingBoard.of(bowlingFrameList, ThrowsState.BONUS_THROWS);
    }

    public boolean isType(BowlingRole type) {
        return getFrame().isType() == type;
    }

    public BowlingBoard pitching(Point point) {
        if (throwsState == ThrowsState.SECOND_THROWS) {
            BowlingBoard bowlingBoard = secondPitching(point);
            createNextFrame();
            return bowlingBoard;
        }

        if (throwsState == ThrowsState.SECOND_THROWS && isType(BowlingRole.STRIKE)) {
            return bonusPitching(point);
        }

        if (throwsState == ThrowsState.FINISH_THROWS && isType(BowlingRole.SPARE)) {
            return bonusPitching(point);
        }

        return firstPitching(point);
    }

    private void createNextFrame() {
        if (bowlingFrameList.size() != 10) {
            bowlingFrameList.add(getFrame().nextFrame());
        }
    }

    public List<ScoreDto> toScoreDto() {
        return bowlingFrameList.stream()
                .map(BowlingFrame::toDto)
                .collect(Collectors.toList());
    }

    public boolean isEnd() {
        if (throwsState == ThrowsState.FINISH_THROWS && isType(BowlingRole.MISS)) {
            return true;
        }
        return throwsState == ThrowsState.BONUS_THROWS;
    }

    public boolean isSameState(ThrowsState throwsState) {
        return this.throwsState == throwsState;
    }

    public ThrowsState state() {
        return throwsState;
    }
}

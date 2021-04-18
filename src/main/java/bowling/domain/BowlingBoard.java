package bowling.domain;

import bowling.dto.ScoreDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingBoard {

    private static final int FINAL_ROUND = 10;

    private final List<BowlingFrame> bowlingFrameList;
    private final ThrowsState throwsState;

    private BowlingBoard(List<BowlingFrame> bowlingFrameList, ThrowsState throwsState) {
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
        BowlingFrame bowlingFrame = getFrame().firstPitching(point);
        bowlingFrameList.set(round() - 1, bowlingFrame);
        if (bowlingFrame.isType() == BowlingRole.STRIKE && round() == FINAL_ROUND) {
            return BowlingBoard.of(bowlingFrameList, ThrowsState.SECOND_THROWS);
        }
        if (bowlingFrame.isType() == BowlingRole.STRIKE) {
            createNextFrame();
            return BowlingBoard.of(bowlingFrameList, ThrowsState.FIRST_THROWS);
        }
        return BowlingBoard.of(bowlingFrameList, ThrowsState.SECOND_THROWS);
    }

    public BowlingBoard secondPitching(Point point) {
        bowlingFrameList.set(round() - 1, getFrame().secondPitching(point));
        if (round() == FINAL_ROUND) {
            return BowlingBoard.of(bowlingFrameList, ThrowsState.FINISH_THROWS);
        }
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
        if (throwsState == ThrowsState.SECOND_THROWS && isType(BowlingRole.STRIKE) && round() == 10) {
            return bonusPitching(point);
        }
        if (throwsState == ThrowsState.SECOND_THROWS) {
            BowlingBoard bowlingBoard = secondPitching(point);
            createNextFrame();
            return bowlingBoard;
        }

        if (throwsState == ThrowsState.FINISH_THROWS && isType(BowlingRole.SPARE)) {
            return bonusPitching(point);
        }

        return firstPitching(point);
    }

    private void createNextFrame() {
        if (bowlingFrameList.size() != FINAL_ROUND) {
            bowlingFrameList.add(getFrame().nextFrame());
        }
    }

    public List<ScoreDto> toScoreDto() {
        return bowlingFrameList.stream()
                .map(BowlingFrame::toDto)
                .collect(Collectors.toList());
    }

    public boolean isEnd() {
        System.out.println(round());
        if (round() > 2) {
            System.out.println(bowlingFrameList.get(round() - 1).isType());
        }

        if (throwsState == ThrowsState.FINISH_THROWS && getFrame().isType() == BowlingRole.MISS) {
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

    public List<Integer> calculate() {
        ScoreAccumulator scoreAccumulator = new ScoreAccumulator();
        return bowlingFrameList.stream()
                .map(frame -> scoreAccumulator.sum(frame.calculateOfScore()))
                .collect(Collectors.toList());
    }
}

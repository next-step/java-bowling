package bowling.domain;

import bowling.domain.bowlingboard.BowlingBoard;
import bowling.domain.bowlingboard.ThrowCount;
import bowling.domain.frame.BowlingFrame;
import bowling.domain.frame.round.FinalRound;
import bowling.domain.frame.round.Round;
import bowling.domain.score.Point;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public enum ThrowsState {

    FIRST_THROWS(ThrowCount.of(0), ThrowsState::firstPitching),
    SECOND_THROWS(ThrowCount.of(1), ThrowsState::secondPitching),
    BONUS_THROWS(ThrowCount.of(2), ThrowsState::bonusPitching),
    END_THROWS(ThrowCount.of(3), ThrowsState::endPitching);

    private final ThrowCount throwCount;
    private final BiFunction<Point, List<BowlingFrame>, BowlingBoard> throwBall;

    ThrowsState(ThrowCount throwCount, BiFunction<Point, List<BowlingFrame>, BowlingBoard> throwBall) {
        this.throwCount = throwCount;
        this.throwBall = throwBall;
    }

    public ThrowCount getThrowCount() {
        return throwCount;
    }

    public BiFunction<Point, List<BowlingFrame>, BowlingBoard> getThrowBall() {
        return throwBall;
    }

    public static BowlingBoard throwBall(Point point, List<BowlingFrame> frames, ThrowCount throwCount) {
        return Arrays.stream(values())
                .filter(state -> state.isSame(throwCount))
                .findAny()
                .map(ThrowsState::getThrowBall)
                .map(throwBall -> throwBall.apply(point, frames))
                .orElseThrow(() -> new IllegalArgumentException("던지는 기회는 최소 0번 최대 3번입니다."));
    }

    public static BowlingBoard firstPitching(Point point, List<BowlingFrame> frames) {
        BowlingFrame bowlingFrame = currentFrame(frames).firstPitching(point);
        if (bowlingFrame != currentFrame(frames)) {
            frames.add(bowlingFrame);
            return BowlingBoard.of(frames, ThrowCount.of(0));
        }
        if (bowlingFrame.round().equals(FinalRound.of()) && bowlingFrame.isType() == BowlingRole.STRIKE) {
            return BowlingBoard.of(frames, ThrowCount.of(2));
        }
        return BowlingBoard.of(frames, ThrowCount.of(1));
    }

    public static BowlingBoard secondPitching(Point point, List<BowlingFrame> frames) {
        BowlingFrame bowlingFrame = currentFrame(frames).secondPitching(point);
        if (bowlingFrame.round().equals(FinalRound.of()) && bowlingFrame.isType() == BowlingRole.SPARE) {
            return BowlingBoard.of(frames, ThrowCount.of(2));
        }
        if (bowlingFrame.round().equals(FinalRound.of()) && bowlingFrame.isType() != BowlingRole.EMPTY) {
            return BowlingBoard.of(frames, ThrowCount.of(3));
        }
        frames.add(bowlingFrame);
        return BowlingBoard.of(frames, ThrowCount.of(0));
    }

    public static BowlingBoard bonusPitching(Point point, List<BowlingFrame> frames) {
        if (!currentFrame(frames).round().equals(FinalRound.of())) {
            throw new IllegalArgumentException("마지막 라운드에서만 보너스 투구가 가능합니다.");
        }
        currentFrame(frames).bonusPitching(point);
        return BowlingBoard.of(frames, ThrowCount.of(3));
    }

    private static BowlingBoard endPitching(Point point, List<BowlingFrame> frames) {
        return BowlingBoard.of(frames, ThrowCount.of(3));
    }

    private static BowlingFrame currentFrame(List<BowlingFrame> frames) {
        return frames.get(frames.size() - 1);
    }

    private boolean isSame(ThrowCount throwCount) {
        return this.throwCount.equals(throwCount);
    }

    public ThrowsState next(Round round) {
        ThrowCount next = this.throwCount.next(round);
        return Arrays.stream(values()).
                filter(state -> state.isSame(next))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("던지는 기회는 최소 0번 최대 3번입니다."));
    }
}






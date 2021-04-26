package bowling.domain;

import bowling.domain.bowlingboard.BowlingBoard;
import bowling.domain.bowlingboard.ThrowCount;
import bowling.domain.frame.BowlingFinalFrame;
import bowling.domain.frame.BowlingFrame;
import bowling.domain.frame.BowlingNormalFrame;
import bowling.domain.frame.round.FinalRound;
import bowling.domain.frame.round.Round;
import bowling.domain.score.Point;
import bowling.domain.score.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ThrowsStateTest {

    @DisplayName("노말 프레임에서 첫번째 투구를 하면 스로우 카운트가 1 늘어난다.")
    @Test
    void case1() {
        Point point = Point.of(1);
        List<BowlingFrame> frameList = Arrays.asList(BowlingNormalFrame.first(Round.of(1)));
        ThrowCount throwCount = ThrowCount.of(0);

        BowlingBoard bowlingBoard = ThrowsState.throwBall(point, frameList, throwCount);
        assertThat(bowlingBoard.state()).isEqualTo(ThrowCount.of(1));
    }

    @DisplayName("노말 프레임에서 첫번째 투구를 스트라이크하면 스로우 카운트가 0이 되고 라운드가 늘어난다.")
    @Test
    void case2() {
        Point point = Point.of(10);
        List<BowlingFrame> frameList = new ArrayList<>();
        frameList.add(BowlingNormalFrame.first(Round.of(1)));
        ThrowCount throwCount = ThrowCount.of(0);

        BowlingBoard bowlingBoard = ThrowsState.throwBall(point, frameList, throwCount);
        assertThat(bowlingBoard.round()).isEqualTo(2);
        assertThat(bowlingBoard.state()).isEqualTo(ThrowCount.of(0));
    }

    @DisplayName("두번째 투구를 하면 스로우 카운트가 0으로 간다.")
    @Test
    void case3() {
        Point point = Point.of(4);
        List<BowlingFrame> frameList = new ArrayList<>();
        frameList.add(BowlingNormalFrame.of(Round.of(1), Score.of(Point.of(5), Point.of(0))));
        ThrowCount throwCount = ThrowCount.of(1);

        BowlingBoard bowlingBoard = ThrowsState.throwBall(point, frameList, throwCount);

        assertThat(bowlingBoard.round()).isEqualTo(2);
        assertThat(bowlingBoard.state()).isEqualTo(ThrowCount.of(0));
    }

    @DisplayName("final프레임에서 두번째 투구를 하면 스로우 카운트가 3으로 간다.")
    @Test
    void case5() {
        Point point = Point.of(4);
        List<BowlingFrame> frameList = new ArrayList<>();
        frameList.add(BowlingFinalFrame.of(FinalRound.of(), Score.of(Point.of(5), Point.of(0))));
        ThrowCount throwCount = ThrowCount.of(1);

        BowlingBoard bowlingBoard = ThrowsState.throwBall(point, frameList, throwCount);

        assertThat(bowlingBoard.state()).isEqualTo(ThrowCount.of(3));
    }

    @DisplayName("final프레임에서 두번째 투구를 하고 스페어나 스트라이크를 하면 스로우 카운트가 2으로 간다.")
    @Test
    void case6() {
        Point point = Point.of(5);
        List<BowlingFrame> frameList = new ArrayList<>();
        frameList.add(BowlingFinalFrame.of(FinalRound.of(), Score.of(Point.of(5), Point.of(0))));
        ThrowCount throwCount = ThrowCount.of(1);

        BowlingBoard bowlingBoard = ThrowsState.throwBall(point, frameList, throwCount);

        assertThat(bowlingBoard.state()).isEqualTo(ThrowCount.of(2));
    }

    @DisplayName("final프레임에서만 보너스 투구를 한다.")
    @Test
    void case7() {
        Point point = Point.of(5);
        List<BowlingFrame> frameList = new ArrayList<>();
        frameList.add(BowlingNormalFrame.of(Round.of(9), Score.of(Point.of(5), Point.of(0))));
        ThrowCount throwCount = ThrowCount.of(2);

        Assertions.assertThatThrownBy(() -> {
            ThrowsState.throwBall(point, frameList, throwCount);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
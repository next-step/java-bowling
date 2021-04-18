package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingBoardTest {

    @DisplayName("마지막 라운드이면서 미스면 게임을 끝낸다.")
    @Test
    void case1() {
        int round = 10;
        Point finalFirstPoint = Point.of(8);
        Point finalSecondPoint = Point.of(1);

        BowlingBoard bowlingBoard = BowlingBoard.of(createFrames(round, finalFirstPoint, finalSecondPoint), ThrowsState.FINISH_THROWS);

        assertThat(bowlingBoard.isEnd()).isTrue();
    }

    @DisplayName("마지막 라운드이면서 스페어면 게임 끝이 아니다")
    @Test
    void case2() {
        int round = 10;
        Point finalFirstPoint = Point.of(9);
        Point finalSecondPoint = Point.of(1);
        BowlingBoard bowlingBoard = BowlingBoard.of(createFrames(round, finalFirstPoint, finalSecondPoint), ThrowsState.FINISH_THROWS);

        assertThat(bowlingBoard.isEnd()).isFalse();
    }

    @DisplayName("마지막 라운드이면서 스트라이크면 게임 끝이 아니다")
    @Test
    void case3() {
        int round = 10;
        Point finalFirstPoint = Point.of(10);
        Point finalSecondPoint = Point.of(0);
        BowlingBoard bowlingBoard = BowlingBoard.of(createFrames(round, finalFirstPoint, finalSecondPoint), ThrowsState.FIRST_THROWS);

        assertThat(bowlingBoard.isEnd()).isFalse();
    }

    @DisplayName("처음 상태는 FIRST_THROWS 이다")
    @Test
    void case4() {
        BowlingBoard bowlingBoard = BowlingBoard.of();
        assertThat(bowlingBoard.isSameState(ThrowsState.FIRST_THROWS)).isTrue();
    }

    @DisplayName("첫번째 피칭 후 상태는 SECOND_THROWS 이다")
    @Test
    void case5() {
        BowlingBoard initBoard = BowlingBoard.of();
        BowlingBoard bowlingBoard = initBoard.pitching(Point.of(5));
        assertThat(bowlingBoard.isSameState(ThrowsState.SECOND_THROWS)).isTrue();
    }

    @DisplayName("두번째 피칭 후 상태는 다시 FIRST_THROWS 이다")
    @Test
    void case6() {
        BowlingBoard initBoard = BowlingBoard.of();
        BowlingBoard bowlingBoard = initBoard.pitching(Point.of(5));

        BowlingBoard lastBowlingBoard = bowlingBoard.pitching(Point.of(4));

        assertThat(lastBowlingBoard.isSameState(ThrowsState.FIRST_THROWS)).isTrue();
    }

    @DisplayName("마지막 라운드 두번째 피칭 후 상태는 FINISH_THROWS이다.")
    @Test
    void case7() {
        int round = 10;
        Point finalFirstPoint = Point.of(8);
        Point finalSecondPoint = Point.of(0);

        BowlingBoard bowlingBoard = BowlingBoard.of(createFrames(round, finalFirstPoint, finalSecondPoint), ThrowsState.FIRST_THROWS);
        BowlingBoard finalBoard = bowlingBoard.secondPitching(Point.of(1));

        assertThat(finalBoard.isSameState(ThrowsState.FINISH_THROWS)).isTrue();
    }

    @DisplayName("마지막 라운드 보너스 피칭 후 상태는 BONUS_THROWS이다.")
    @Test
    void case8() {
        int round = 10;
        Point finalFirstPoint = Point.of(8);
        Point finalSecondPoint = Point.of(2);

        BowlingBoard bowlingBoard = BowlingBoard.of(createFrames(round, finalFirstPoint, finalSecondPoint), ThrowsState.FINISH_THROWS);
        BowlingBoard finalBoard = bowlingBoard.bonusPitching(Point.of(5));
        assertThat(finalBoard.isSameState(ThrowsState.BONUS_THROWS)).isTrue();
    }

    @DisplayName("각 라운드 점수를 계산한다.")
    @Test
    void case9() {
        int round = 10;
        Point finalFirstPoint = Point.of(3);
        Point finalSecondPoint = Point.of(2);

        BowlingBoard bowlingBoard = BowlingBoard.of(createFrames(round, finalFirstPoint, finalSecondPoint), ThrowsState.FINISH_THROWS);
        List<Integer> list = bowlingBoard.calculate();
        assertThat(list).contains(5, 10, 15, 20, 25, 30, 35, 40, 45, 50);
    }
    

    private List<BowlingFrame> createFrames(int round, Point finalFirstPoint, Point finalSecondPoint) {
        List<BowlingFrame> frameList = IntStream.range(1, round)
                .mapToObj(i -> BowlingNormalFrame.of(i, (Point.of(5))))
                .collect(Collectors.toList());
        frameList.add(BowlingFinalFrame.of(FinalRound.of(), Score.of(finalFirstPoint, finalSecondPoint)));
        return frameList;
    }

}
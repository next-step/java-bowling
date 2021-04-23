package bowling.domain;

import bowling.dto.ScoreDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BowlingFinalFrameTest {

    @DisplayName("첫번째 공을 던진 후 쓰러트린 갯수 만큼 첫번째 point가 증가한다..")
    @Test
    void case1() {
        Point point = Point.of(5);
        BowlingFinalFrame initFrame = BowlingFinalFrame.first(FinalRound.of(), FramePoint.first());

        BowlingFrame bowlingFrame = initFrame.firstPitching(point);

        assertThat(bowlingFrame.toDto().getBowlingPoint()).contains(5, 0);
    }

    @DisplayName("두번째 공을 던진 후 쓰러트린 갯수 만큼 두번째 point가 증가한다..")
    @Test
    void case2() {
        Point firstPoint = Point.of(5);
        Point secondPoint = Point.of(4);
        BowlingFinalFrame initFrame = BowlingFinalFrame.first(FinalRound.of(), FramePoint.first());

        BowlingFrame firstFrame = initFrame.firstPitching(firstPoint);
        BowlingFrame secondFrame = firstFrame.secondPitching(secondPoint);
        ScoreDto scoreDto = secondFrame.toDto();

        assertThat(scoreDto.getBowlingPoint()).contains(5, 4);
    }

    @DisplayName("스페어 또는 스트라이크를 만족 못하면 보너스 투구를 할 수 없다.")
    @Test
    void case3() {
        BowlingFrame finalFrame = BowlingFinalFrame.of(FinalRound.of(), Score.of(Point.of(3), Point.of(6)));

        assertThatThrownBy(() -> {
            finalFrame.bonusPitching(Point.of(5));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 투구를 한다.")
    @Test
    void case4() {
        BowlingFrame bowlingFrame = BowlingFinalFrame.of(FinalRound.of(), Score.of(Point.of(4), Point.of(6)));
        BowlingFrame bonusFrame = bowlingFrame.bonusPitching(Point.of(5));

        ScoreDto scoreDto = bonusFrame.toDto();

        assertThat(scoreDto.getBowlingPoint()).contains(4, 6, 5);
    }

    @DisplayName("마지막 라운드가 10라운드가 아니면 예외가 발생한다.")
    @Test
    void case5() {
        assertThatThrownBy(() -> {
            BowlingFinalFrame.first(FinalRound.of(1), FramePoint.first());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Score Type이 MISS면 현재 스코어 합을 내보낸다.")
    @Test
    void case6() {
        BowlingFrame initFrame = BowlingFinalFrame.of(FinalRound.of(), Score.of(Point.of(1), Point.of(5)));
        FramePoint framePoint = initFrame.calculateOfScore();
        Assertions.assertThat(framePoint.toInt()).isEqualTo(6);
    }

    @DisplayName("Score Type이 SPARE나 STRIKE면 현재 스코어 합+ 보너스 포인트를 내보낸다.")
    @Test
    void case7() {
        //SPARE
        BowlingFrame initFrame = BowlingFinalFrame.of(FinalRound.of(), Score.of(Point.of(5), Point.of(5)));
        BowlingFrame bowlingFrame = initFrame.bonusPitching(Point.of(4));

        //STRIKE
        BowlingFrame initFrame2 = BowlingFinalFrame.of(FinalRound.of(), Score.of(Point.of(10), Point.of(0)));
        BowlingFrame bowlingFrame2 = initFrame2.bonusPitching(Point.of(4));

        FramePoint framePoint = bowlingFrame.calculateOfScore();
        FramePoint framePoint2 = bowlingFrame2.calculateOfScore();

        Assertions.assertThat(framePoint.toInt()).isEqualTo(14);
        Assertions.assertThat(framePoint2.toInt()).isEqualTo(14);
    }
}
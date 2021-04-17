package bowling.domain;

import bowling.dto.ScoreDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingNormalFrameTest {

    @DisplayName("첫번째 공을 던진 후 쓰러트린 갯수 만큼 첫번째 point가 증가한다..")
    @Test
    void case1() {
        Point point = Point.of(5);
        BowlingNormalFrame initFrame = BowlingNormalFrame.first(Round.first());

        BowlingFrame bowlingFrame = initFrame.firstPitching(point);

        assertThat(bowlingFrame.toDto().getBowlingPoint()).contains(5, 0);
    }

    @DisplayName("두번째 공을 던진 후 쓰러트린 갯수 만큼 두번째 point가 증가한다..")
    @Test
    void case2() {
        Point firstPoint = Point.of(5);
        Point secondPoint = Point.of(4);
        BowlingNormalFrame initFrame = BowlingNormalFrame.first(Round.first());

        BowlingFrame firstFrame = initFrame.firstPitching(firstPoint);
        BowlingFrame secondFrame = firstFrame.secondPitching(secondPoint);
        ScoreDto scoreDto = secondFrame.toDto();

        assertThat(scoreDto.getBowlingPoint()).contains(5, 4);
    }

    @DisplayName("첫번째에 10개를 쓰러트리면 스트라이크이다.")
    @Test
    void case3() {
        Point firstPoint = Point.of(10);
        BowlingNormalFrame initFrame = BowlingNormalFrame.first(Round.first());

        BowlingFrame firstFrame = initFrame.firstPitching(firstPoint);
        ScoreDto scoreDto = firstFrame.toDto();

        assertThat(scoreDto.getScoreType()).isEqualTo(BowlingRole.STRIKE);
    }

    @DisplayName("첫번째와 두번째 합쳐서 10개를 쓰러트리면 스페어이다.")
    @Test
    void case4() {
        Point firstPoint = Point.of(7);
        Point secondPoint = Point.of(3);
        BowlingNormalFrame initFrame = BowlingNormalFrame.first(Round.first());

        BowlingFrame firstFrame = initFrame.firstPitching(firstPoint);
        BowlingFrame secondFrame = firstFrame.secondPitching(secondPoint);
        ScoreDto scoreDto = secondFrame.toDto();

        assertThat(scoreDto.getScoreType()).isEqualTo(BowlingRole.SPARE);
    }

    @DisplayName("볼링핀을 모두 쓰러뜨리지 못하면 미스이다.")
    @Test
    void case5() {
        Point firstPoint = Point.of(7);
        Point secondPoint = Point.of(2);
        BowlingNormalFrame initFrame = BowlingNormalFrame.first(Round.first());

        BowlingFrame firstFrame = initFrame.firstPitching(firstPoint);
        BowlingFrame secondFrame = firstFrame.secondPitching(secondPoint);
        ScoreDto scoreDto = secondFrame.toDto();

        assertThat(scoreDto.getScoreType()).isEqualTo(BowlingRole.MISS);
    }
}
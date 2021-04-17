package bowling.domain;

import bowling.dto.ScoreDto;
import org.assertj.core.api.Assertions;
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

    @DisplayName("Score Type이 MISS면 현재 스코어 합을 내보낸다.")
    @Test
    void case6() {
        BowlingNormalFrame initFrame = BowlingNormalFrame.of(Round.first(), Score.of(Point.of(1), Point.of(5)));
        int score = initFrame.calculateOfScore();
        Assertions.assertThat(score).isEqualTo(6);
    }

    @DisplayName("Score Type이 SPARE면 현재 스코어 합+ 다음 프레임 첫번쨰 포인트를 내보낸다.")
    @Test
    void case7() {
        BowlingNormalFrame initFrame = BowlingNormalFrame.of(Round.first(), Score.of(Point.of(5), Point.of(5)));
        BowlingFrame bowlingFrame = initFrame.nextFrame();
        BowlingFrame bowlingFrame1 = bowlingFrame.firstPitching(Point.of(4));
        int score = initFrame.calculateOfScore();
        Assertions.assertThat(score).isEqualTo(14);
    }

    @DisplayName("Score Type이 STRIKE면 현재 스코어 합+ 다음 프레임 첫번쨰 포인트를 내보낸다.")
    @Test
    void case8() {
        BowlingNormalFrame initFrame = BowlingNormalFrame.of(Round.first(), Score.of(Point.of(10), Point.of(0)));
        BowlingFrame bowlingFrame = initFrame.nextFrame().firstPitching(Point.of(4)).secondPitching(Point.of(5));
        int score = initFrame.calculateOfScore();
        Assertions.assertThat(score).isEqualTo(19);
    }

}
package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-16 23:16
 */
public class FrameScoreTest {
    @DisplayName("정상적인 투구 - 스트라이크")
    @Test
    void 정상_스트라이크() {
        Point firstPoint = Point.of(10);

        FrameScore frameScore = new FrameScore();
        assertThat(frameScore.addPoint(firstPoint)).isTrue();
    }

    @DisplayName("정상적인 투구 - 스페어")
    @Test
    void 정상_스페어() {
        Point firstPoint = Point.of(5);
        Point secondPoint = Point.of(5);

        FrameScore frameScore = new FrameScore();
        frameScore.addPoint(firstPoint);
        assertThat(frameScore.addPoint(secondPoint)).isTrue();
    }

    @DisplayName("정상적인 투구 - 거터")
    @Test
    void 정상_거터() {
        Point firstPoint = Point.of(0);
        Point secondPoint = Point.of(0);

        FrameScore frameScore = new FrameScore();
        frameScore.addPoint(firstPoint);
        assertThat(frameScore.addPoint(secondPoint)).isTrue();
    }

    @DisplayName("정상적인 투구 - 미스")
    @Test
    void 정상_미스() {
        Point firstPoint = Point.of(3);
        Point secondPoint = Point.of(6);

        FrameScore frameScore = new FrameScore();
        frameScore.addPoint(firstPoint);
        assertThat(frameScore.addPoint(secondPoint)).isTrue();
    }

    @DisplayName("기본적인 프레임 스코어는 10점을 넘길 수 없다.")
    @Test
    void 프레임의_점수는_10점을_초과할수없다() {
        Point firstPoint = Point.of(9);
        Point secondPoint = Point.of(2);

        FrameScore frameScore = new FrameScore();
        frameScore.addPoint(firstPoint);
        assertThat(frameScore.addPoint(secondPoint)).isFalse();
    }

    @DisplayName("기본적인 프레임은 두번 이상 투구할 수 없다.")
    @Test
    void 프레임의_투구회수는_최대2번을_초과할수없다() {
        Point firstPoint = Point.of(5);
        Point secondPoint = Point.of(5);
        Point thirdPoint = Point.of(5);

        FrameScore frameScore = new FrameScore();
        frameScore.addPoint(firstPoint);
        frameScore.addPoint(secondPoint);
        assertThat(frameScore.addPoint(thirdPoint)).isFalse();

    }

    @DisplayName("스트라이크 유무 확인")
    @Test
    void 스트라이크() {
        Point firstPoint = Point.of(10);
        FrameScore frameScore = new FrameScore();

        frameScore.addPoint(firstPoint);
        assertThat(frameScore.isStrike()).isTrue();
    }

    @DisplayName("스페어 유무 확인")
    @Test
    void 스페어() {
        Point firstPoint = Point.of(5);
        Point secondPoint = Point.of(5);
        FrameScore frameScore = new FrameScore();

        frameScore.addPoint(firstPoint);
        frameScore.addPoint(secondPoint);
        assertThat(frameScore.isSpare()).isTrue();
    }
}

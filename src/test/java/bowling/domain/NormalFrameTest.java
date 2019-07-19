package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-17 00:48
 */
public class NormalFrameTest {
    private Frame frame;

    @BeforeEach
    void 초기화() {
        frame = new NormalFrame();
    }

    @DisplayName("NormalFrame 생성 시 초기화 작업")
    @Test
    void 일반_프레임_생성_초기화() {
        assertThat(frame.isGameOver()).isFalse();
    }

    @DisplayName("NormalFrame에서 게임종료 신호가 안될경우 기존 프레임을 계속사용")
    @Test
    void 기존_프레임_사용() {
        Frame origin = frame.bowl(5);
        Frame newFrame = origin.bowl(3);
        assertThat(origin).isEqualTo(newFrame);
    }

    @DisplayName("NormalFrame 종료 후 새로운 Frame 반환")
    @Test
    void 새로운_프레임_반환() {
        Frame origin = frame.bowl(10);
        Frame newFrame = origin.bowl(10);
        assertThat(origin).isNotSameAs(newFrame);
    }

    @DisplayName("NormalFrame 종료 확인 - FrameNumber가 9이면 종료이다.")
    @Test
    void 기본_프레임_종료() {
        frame = frame.bowl(10);
        frame = frame.bowl(10);
        frame = frame.bowl(10);
        frame = frame.bowl(10);
        frame = frame.bowl(10);
        frame = frame.bowl(10);
        frame = frame.bowl(10);
        frame = frame.bowl(10);
        frame = frame.bowl(10);
        assertThat(frame.isGameOver()).isTrue();
    }

    @DisplayName("Strike")
    @Test
    void 첫투구_STRIKE() {
        assertAll(
                () -> assertThat(frame.bowl(10).getState().printState()).isEqualTo("X"),
                () -> assertThat(frame.bowl(10).getState().getFirstBowl()).isEqualTo(Point.of(10))
        );
    }

    @DisplayName("HIT")
    @Test
    void 첫투구_HIT() {
        assertAll(
                () -> assertThat(frame.bowl(3).getState().printState()).isEqualTo("3"),
                () -> assertThat(frame.bowl(3).getState().getFirstBowl()).isEqualTo(Point.of(3))
        );
    }

    @DisplayName("GUTTER")
    @Test
    void 첫투구_GUTTER() {
        assertAll(
                () -> assertThat(frame.bowl(0).getState().printState()).isEqualTo("-"),
                () -> assertThat(frame.bowl(0).getState().getFirstBowl()).isEqualTo(Point.of(0))
        );
    }

    @DisplayName("SPARE")
    @ParameterizedTest
    @CsvSource({"0,10"})
    void 두번재_투구_SPARE_1(int firstBowl, int secondBowl) {
        frame = frame.bowl(firstBowl);
        frame = frame.bowl(secondBowl);
        assertAll(
                () -> assertThat(frame.isGameOver()).isFalse(),
                () -> assertThat(frame.getState().printState()).isEqualTo("-|/"),
                () -> assertThat(frame.getState().getFirstBowl()).isEqualTo(Point.of(0)),
                () -> assertThat(frame.getState().getSecondBowl()).isEqualTo(Point.of(10))
        );
    }

    @DisplayName("SPARE")
    @ParameterizedTest
    @CsvSource({"5,5"})
    void 두번재_투구_SPARE_2(int firstBowl, int secondBowl) {
        frame = frame.bowl(firstBowl);
        frame = frame.bowl(secondBowl);
        assertAll(
                () -> assertThat(frame.isGameOver()).isFalse(),
                () -> assertThat(frame.getState().printState()).isEqualTo("5|/"),
                () -> assertThat(frame.getState().getFirstBowl()).isEqualTo(Point.of(5)),
                () -> assertThat(frame.getState().getSecondBowl()).isEqualTo(Point.of(5))
        );
    }

    @DisplayName("MISS")
    @ParameterizedTest
    @CsvSource({"0,9"})
    void 두번재_투구_MISS_1(int firstBowl, int secondBowl) {
        frame = frame.bowl(firstBowl);
        frame = frame.bowl(secondBowl);
        assertAll(
                () -> assertThat(frame.isGameOver()).isFalse(),
                () -> assertThat(frame.getState().printState()).isEqualTo("-|9"),
                () -> assertThat(frame.getState().getFirstBowl()).isEqualTo(Point.of(0)),
                () -> assertThat(frame.getState().getSecondBowl()).isEqualTo(Point.of(9))
        );
    }

    @DisplayName("MISS")
    @ParameterizedTest
    @CsvSource({"1,8"})
    void 두번재_투구_MISS_2(int firstBowl, int secondBowl) {
        frame = frame.bowl(firstBowl);
        frame = frame.bowl(secondBowl);
        assertAll(
                () -> assertThat(frame.isGameOver()).isFalse(),
                () -> assertThat(frame.getState().printState()).isEqualTo("1|8"),
                () -> assertThat(frame.getState().getFirstBowl()).isEqualTo(Point.of(1)),
                () -> assertThat(frame.getState().getSecondBowl()).isEqualTo(Point.of(8))
        );
    }

    @DisplayName("DOUBLE GUTTER")
    @ParameterizedTest
    @CsvSource({"0,0"})
    void 두번재_투구_DOUBLEGUTTER(int firstBowl, int secondBowl) {
        frame = frame.bowl(firstBowl);
        frame = frame.bowl(secondBowl);
        assertAll(
                () -> assertThat(frame.isGameOver()).isFalse(),
                () -> assertThat(frame.getState().printState()).isEqualTo("-|-"),
                () -> assertThat(frame.getState().getFirstBowl()).isEqualTo(Point.of(0)),
                () -> assertThat(frame.getState().getSecondBowl()).isEqualTo(Point.of(0))
        );
    }
}

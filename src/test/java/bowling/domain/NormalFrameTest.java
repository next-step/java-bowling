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
    private NormalFrame normalFrame;

    @BeforeEach
    void 초기화() {
        normalFrame = new NormalFrame();
    }

    @DisplayName("NormalFrame 생성 시 초기화 작업")
    @Test
    void 일반_프레임_생성_초기화() {
        assertThat(normalFrame.isGameOver()).isFalse();
    }

    @DisplayName("NormalFrame에서 게임종료 신호가 안될경우 기존 프레임을 계속사용")
    @Test
    void 기존_프레임_사용() {
        NormalFrame origin = normalFrame.bowl(5);
        NormalFrame newFrame = origin.bowl(3);
        assertThat(origin).isEqualTo(newFrame);
    }

    @DisplayName("NormalFrame 종료 후 새로운 Frame 반환")
    @Test
    void 새로운_프레임_반환() {
        NormalFrame origin = normalFrame.bowl(10);
        NormalFrame newFrame = origin.bowl(10);
        assertThat(origin).isNotSameAs(newFrame);
    }

    @DisplayName("Strike")
    @Test
    void 첫투구_STRIKE() {
        assertAll(
                () -> assertThat(normalFrame.bowl(10).getState().printState()).isEqualTo(" X |"),
                () -> assertThat(normalFrame.bowl(10).getState().getFirstBowl()).isEqualTo(Point.of(10))
        );
    }

    @DisplayName("HIT")
    @Test
    void 첫투구_HIT() {
        assertAll(
                () -> assertThat(normalFrame.bowl(3).getState().printState()).isEqualTo("3"),
                () -> assertThat(normalFrame.bowl(3).getState().getFirstBowl()).isEqualTo(Point.of(3))
        );
    }

    @DisplayName("GUTTER")
    @Test
    void 첫투구_GUTTER() {
        assertAll(
                () -> assertThat(normalFrame.bowl(0).getState().printState()).isEqualTo("-"),
                () -> assertThat(normalFrame.bowl(0).getState().getFirstBowl()).isEqualTo(Point.of(0))
        );
    }

    @DisplayName("SPARE")
    @ParameterizedTest
    @CsvSource({"0,10"})
    void 두번재_투구_SPARE_1(int firstBowl, int secondBowl) {
        normalFrame = normalFrame.bowl(firstBowl);
        normalFrame = normalFrame.bowl(secondBowl);
        assertAll(
                () -> assertThat(normalFrame.isGameOver()).isFalse(),
                () -> assertThat(normalFrame.getState().printState()).isEqualTo("-|/ |"),
                () -> assertThat(normalFrame.getState().getFirstBowl()).isEqualTo(Point.of(0)),
                () -> assertThat(normalFrame.getState().getSecondBowl()).isEqualTo(Point.of(10))
        );
    }

    @DisplayName("SPARE")
    @ParameterizedTest
    @CsvSource({"5,5"})
    void 두번재_투구_SPARE_2(int firstBowl, int secondBowl) {
        normalFrame = normalFrame.bowl(firstBowl);
        normalFrame = normalFrame.bowl(secondBowl);
        assertAll(
                () -> assertThat(normalFrame.isGameOver()).isFalse(),
                () -> assertThat(normalFrame.getState().printState()).isEqualTo("5|/ |"),
                () -> assertThat(normalFrame.getState().getFirstBowl()).isEqualTo(Point.of(5)),
                () -> assertThat(normalFrame.getState().getSecondBowl()).isEqualTo(Point.of(5))
        );
    }

    @DisplayName("MISS")
    @ParameterizedTest
    @CsvSource({"0,9"})
    void 두번재_투구_MISS_1(int firstBowl, int secondBowl) {
        normalFrame = normalFrame.bowl(firstBowl);
        normalFrame = normalFrame.bowl(secondBowl);
        assertAll(
                () -> assertThat(normalFrame.isGameOver()).isFalse(),
                () -> assertThat(normalFrame.getState().printState()).isEqualTo("-|9 |"),
                () -> assertThat(normalFrame.getState().getFirstBowl()).isEqualTo(Point.of(0)),
                () -> assertThat(normalFrame.getState().getSecondBowl()).isEqualTo(Point.of(9))
        );
    }

    @DisplayName("MISS")
    @ParameterizedTest
    @CsvSource({"1,8"})
    void 두번재_투구_MISS_2(int firstBowl, int secondBowl) {
        normalFrame = normalFrame.bowl(firstBowl);
        normalFrame = normalFrame.bowl(secondBowl);
        assertAll(
                () -> assertThat(normalFrame.isGameOver()).isFalse(),
                () -> assertThat(normalFrame.getState().printState()).isEqualTo("1|8 |"),
                () -> assertThat(normalFrame.getState().getFirstBowl()).isEqualTo(Point.of(1)),
                () -> assertThat(normalFrame.getState().getSecondBowl()).isEqualTo(Point.of(8))
        );
    }

    @DisplayName("DOUBLE GUTTER")
    @ParameterizedTest
    @CsvSource({"0,0"})
    void 두번재_투구_DOUBLEGUTTER(int firstBowl, int secondBowl) {
        normalFrame = normalFrame.bowl(firstBowl);
        normalFrame = normalFrame.bowl(secondBowl);
        assertAll(
                () -> assertThat(normalFrame.isGameOver()).isFalse(),
                () -> assertThat(normalFrame.getState().printState()).isEqualTo("-|- |"),
                () -> assertThat(normalFrame.getState().getFirstBowl()).isEqualTo(Point.of(0)),
                () -> assertThat(normalFrame.getState().getSecondBowl()).isEqualTo(Point.of(0))
        );
    }
}

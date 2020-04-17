package bowling.score;

import bowling.domain.FinalFrame;
import bowling.domain.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ScoreTest {

    @Test
    @DisplayName("일반 프레임에서 첫 던지기 후 마크가져오기 테스트")
    void normalFrameFirstTryTest() {
        assertAll(
                () -> assertThat(new NormalFrame(1).throwBall(10).getScoreMark()).isEqualTo("X"),
                () -> assertThat(new NormalFrame(1).throwBall(0).getScoreMark()).isEqualTo("-"),
                () -> assertThat(new NormalFrame(1).throwBall(5).getScoreMark()).isEqualTo("5")
        );
    }

    @Test
    @DisplayName("일반 프레임에서 두번째 던지기 후 마크가져오기 테스트")
    void getNextFrameTest() {
        assertAll(
                () -> assertThat(new NormalFrame(1).throwBall(3).throwBall(0).getScoreMark()).isEqualTo("3|-"),
                () -> assertThat(new NormalFrame(1).throwBall(0).throwBall(5).getScoreMark()).isEqualTo("-|5"),
                () -> assertThat(new NormalFrame(1).throwBall(0).throwBall(0).getScoreMark()).isEqualTo("-|-"),
                () -> assertThat(new NormalFrame(1).throwBall(0).throwBall(10).getScoreMark()).isEqualTo("-|/"),
                () -> assertThat(new NormalFrame(1).throwBall(3).throwBall(6).getScoreMark()).isEqualTo("3|6"),
                () -> assertThat(new NormalFrame(1).throwBall(3).throwBall(7).getScoreMark()).isEqualTo("3|/")
        );
    }

    @Test
    @DisplayName("마지막 프레임에서 첫 던지기 후 마크가져오기 테스트")
    void finalFrameFirstTryTest() {
        assertAll(
                () -> assertThat(new FinalFrame(1).throwBall(10).getScoreMark()).isEqualTo("X"),
                () -> assertThat(new FinalFrame(1).throwBall(0).getScoreMark()).isEqualTo("-"),
                () -> assertThat(new FinalFrame(1).throwBall(5).getScoreMark()).isEqualTo("5")
        );
    }

    @Test
    @DisplayName("마지막 프레임에서 두번째 던지기 후 마크가져오기 테스트")
    void finalFrameSecondTryTest() {
        assertAll(
                () -> assertThat(new FinalFrame(1).throwBall(3).throwBall(0).getScoreMark()).isEqualTo("3|-"),
                () -> assertThat(new FinalFrame(1).throwBall(0).throwBall(5).getScoreMark()).isEqualTo("-|5"),
                () -> assertThat(new FinalFrame(1).throwBall(0).throwBall(0).getScoreMark()).isEqualTo("-|-"),
                () -> assertThat(new FinalFrame(1).throwBall(0).throwBall(10).getScoreMark()).isEqualTo("-|/"),
                () -> assertThat(new FinalFrame(1).throwBall(3).throwBall(6).getScoreMark()).isEqualTo("3|6"),
                () -> assertThat(new FinalFrame(1).throwBall(3).throwBall(7).getScoreMark()).isEqualTo("3|/")
        );
    }

    @Test
    @DisplayName("마지막 프레임에서 세번째 던지기 후 마크가져오기 테스트 (더블 스트라이크)")
    void finalFrameThirdTryWithDoubleStrikeTest() {
        assertAll(
                () -> assertThat(new FinalFrame(1).throwBall(10).throwBall(10).throwBall(10).getScoreMark()).isEqualTo("X|X|X"),
                () -> assertThat(new FinalFrame(1).throwBall(10).throwBall(10).throwBall(0).getScoreMark()).isEqualTo("X|X|-"),
                () -> assertThat(new FinalFrame(1).throwBall(10).throwBall(10).throwBall(5).getScoreMark()).isEqualTo("X|X|5")
        );
    }

    @Test
    @DisplayName("마지막 프레임에서 세번째 던지기 후 마크가져오기 테스트 (초구 스트라이크)")
    void finalFrameThirdTryWithFirstStrikeTest() {
        assertAll(
                () -> assertThat(new FinalFrame(1).throwBall(10).throwBall(5).throwBall(5).getScoreMark()).isEqualTo("X|5|/"),
                () -> assertThat(new FinalFrame(1).throwBall(10).throwBall(5).throwBall(3).getScoreMark()).isEqualTo("X|5|3"),
                () -> assertThat(new FinalFrame(1).throwBall(10).throwBall(5).throwBall(0).getScoreMark()).isEqualTo("X|5|-"),

                () -> assertThat(new FinalFrame(1).throwBall(10).throwBall(0).throwBall(10).getScoreMark()).isEqualTo("X|-|/"),
                () -> assertThat(new FinalFrame(1).throwBall(10).throwBall(0).throwBall(5).getScoreMark()).isEqualTo("X|-|5"),
                () -> assertThat(new FinalFrame(1).throwBall(10).throwBall(0).throwBall(0).getScoreMark()).isEqualTo("X|-|-")
        );
    }

    @Test
    @DisplayName("마지막 프레임에서 세번째 던지기 후 마크가져오기 테스트 (스페어 후)")
    void finalFrameThirdTryWithSpareTest() {
        assertAll(
                () -> assertThat(new FinalFrame(1).throwBall(0).throwBall(10).throwBall(10).getScoreMark()).isEqualTo("-|/|X"),
                () -> assertThat(new FinalFrame(1).throwBall(0).throwBall(10).throwBall(5).getScoreMark()).isEqualTo("-|/|5"),
                () -> assertThat(new FinalFrame(1).throwBall(0).throwBall(10).throwBall(0).getScoreMark()).isEqualTo("-|/|-"),

                () -> assertThat(new FinalFrame(1).throwBall(5).throwBall(5).throwBall(10).getScoreMark()).isEqualTo("5|/|X"),
                () -> assertThat(new FinalFrame(1).throwBall(5).throwBall(5).throwBall(5).getScoreMark()).isEqualTo("5|/|5"),
                () -> assertThat(new FinalFrame(1).throwBall(5).throwBall(5).throwBall(0).getScoreMark()).isEqualTo("5|/|-"),
                () -> assertThat(new FinalFrame(1).throwBall(3).throwBall(7).throwBall(10).getScoreMark()).isEqualTo("3|/|X"),
                () -> assertThat(new FinalFrame(1).throwBall(3).throwBall(7).throwBall(5).getScoreMark()).isEqualTo("3|/|5"),
                () -> assertThat(new FinalFrame(1).throwBall(3).throwBall(7).throwBall(0).getScoreMark()).isEqualTo("3|/|-")
        );
    }
}

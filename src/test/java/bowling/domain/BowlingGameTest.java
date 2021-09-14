package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class BowlingGameTest {

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame("asd");
    }

    @DisplayName("10개의 프레임이 있고 마지막 프레임이 종료가 되었으면 끝난 게임이다.")
    @Test
    void isEnd_strike() {
        for (int i = 0; i < 12; i++) {
            bowlingGame.pitch(10);
        }
        assertThat(bowlingGame.isEnd()).isTrue();
    }

    @DisplayName("마지막 프레임에 도달하지 않았으면 끝난 게임이 아니다.")
    @Test
    void isEnd_number_false() {
        for (int i = 0; i < 12; i++) {
            bowlingGame.pitch(1);
        }
        assertThat(bowlingGame.isEnd()).isFalse();
    }

    @DisplayName("10개의 프레임이 있고 마지막 프레임이 끝나지 않았으면 끝난 게임이 아니다.")
    @Test
    void isEnd_number_true() {
        for (int i = 0; i < 19; i++) {
            bowlingGame.pitch(1);
        }
        assertThat(bowlingGame.isEnd()).isFalse();
    }

    @DisplayName("처음 투구에서 10점을 받으면 스트라이크다. - strike")
    @Test
    void pitch_strike() {
        bowlingGame.pitch(10);
        assertThat(bowlingGame.frames().get(0).pitches().firstStrike()).isTrue();
    }

    @DisplayName("투구를 하면 투구한 상태의 프레임이 반환된다. - spare")
    @ParameterizedTest
    @CsvSource(value = {"2:8", "0:10", "3:7"}, delimiter = ':')
    void pitch_spare(int first, int second) {
        bowlingGame.pitch(first);
        bowlingGame.pitch(second);
        assertThat(bowlingGame.frames().get(0).pitches().statuses()).isEqualTo(Arrays.asList(Status.NUMBER, Status.SPARE));
    }

    @DisplayName("투구를 하면 투구한 상태의 프레임이 반환된다. - gutter")
    @ParameterizedTest
    @CsvSource(value = {"2:0", "1:0", "0:0"}, delimiter = ':')
    void pitch_gutter(int first, int second) {
        bowlingGame.pitch(first);
        bowlingGame.pitch(second);
        assertThat(bowlingGame.frames().get(0).pitches().statuses()).isEqualTo(Arrays.asList(Status.NUMBER, Status.GUTTER));
    }

    @DisplayName("현재 프레임을 반환한다")
    @Test
    void currentFrame() {
        bowlingGame.pitch(1);
        assertThat(bowlingGame.currentFrameNumber()).isEqualTo(1);
    }

}
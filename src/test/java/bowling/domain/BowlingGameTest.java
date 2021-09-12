package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame("asd");
    }

    @DisplayName("10개의 프레임이 있고 마지막 프레임이 종료가 되었으면 끝난 게임이다.")
    @Test
    void isEnd() {
        for (int i = 0; i < 12; i++) {
            bowlingGame.pitch(10);
        }
        assertThat(bowlingGame.isEnd()).isTrue();
    }

    @DisplayName("투구를 하면 투구한 상태의 프레임이 반환된다. - strike")
    @Test
    void pitch_strike() {
        bowlingGame.pitch(10);
        assertThat(bowlingGame.frames().get(0).result()).isEqualTo("X");
    }

    @DisplayName("투구를 하면 투구한 상태의 프레임이 반환된다. - spare, gutter")
    @ParameterizedTest
    @CsvSource(value = {"2 8 2|/", "0 0 0|-"}, delimiter = ' ')
    void pitch_gutter(int first, int second, String expected) {
        bowlingGame.pitch(first);
        bowlingGame.pitch(second);
        assertThat(bowlingGame.frames().get(0).result()).isEqualTo(expected);
    }

    @DisplayName("현재 프레임을 반환한다")
    @Test
    void currentFrame() {
        bowlingGame.pitch(1);
        assertThat(bowlingGame.currentFrameNumber()).isEqualTo(1);
    }

}
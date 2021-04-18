package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BowlingGameTest {

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame("mdy");
    }

    @ParameterizedTest
    @CsvSource(value = { "1,1,1,1,2", "10,10,10,10,4"})
    @DisplayName("투구했을 때의 프레임 개수 확인")
    void play(int first, int second, int third, int fourth, int expect) {
        bowlingGame.play(first);
        bowlingGame.play(second);
        bowlingGame.play(third);
        bowlingGame.play(fourth);

        assertThat(bowlingGame.getFrames()).hasSize(expect);
    }


    @ParameterizedTest
    @CsvSource(value = {"6,8", "1,-1", "10,12"})
    @DisplayName("볼링 개수 확인")
    void valid(int first, int second) {
        bowlingGame.play(first);

        assertThatThrownBy(() -> bowlingGame.play(second))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("점수 계산")
    void calculate() {
        bowlingGame.play(10);
        bowlingGame.play(4);
        bowlingGame.play(2);
        bowlingGame.play(3);
        bowlingGame.play(1);

        assertThat(bowlingGame.getScore()).containsExactly(16, 22, 26);
    }
}

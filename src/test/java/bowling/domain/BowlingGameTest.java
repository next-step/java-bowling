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
        bowlingGame = new BowlingGame("PJS");
    }

    @DisplayName("bowlingGame 생성")
    @Test
    void create() {
        assertThat(bowlingGame).isEqualTo(new BowlingGame("PJS"));
    }

    @DisplayName("bowlingGame 프레임 생성")
    @ParameterizedTest
    @CsvSource(value = {"10,5,5,10,3", "1,1,1,1,2", "10,10,10,10,4"})
    void pitch(int first, int second, int third, int fourth, int expect) {
        bowlingGame.pitch(first);
        bowlingGame.pitch(second);
        bowlingGame.pitch(third);
        bowlingGame.pitch(fourth);

        assertThat(bowlingGame.getFrames().size()).isEqualTo(expect);
    }

    @DisplayName("유효하지 않은 게임 진행")
    @ParameterizedTest
    @CsvSource(value = {"5,6", "1,-1", "10,12"})
    void pitch_invalid(int first, int second) {
        bowlingGame.pitch(first);

        assertThatThrownBy(() -> bowlingGame.pitch(second))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("점수 계산")
    @Test
    void getScore() {
        bowlingGame.pitch(10);
        bowlingGame.pitch(3);
        bowlingGame.pitch(2);
        bowlingGame.pitch(1);
        bowlingGame.pitch(1);

        assertThat(bowlingGame.getScore()).containsExactly(15, 20, 22);
    }
}

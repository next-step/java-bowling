package bowling_step3;

import bowling_step3.domain.BowlingGame;
import bowling_step3.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {
    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        Player player = Player.of("III");
        bowlingGame = new BowlingGame(player);
    }

    @ParameterizedTest
    @DisplayName("볼링_프레임_생성확인")
    @CsvSource(value = {"2,3,2,3,2", "10,10,10,10,4"})
    public void 볼링_프레임_생성확인(int first, int second, int third, int fourth, int expect) {
        bowlingGame.pitch(first);
        bowlingGame.pitch(second);
        bowlingGame.pitch(third);
        bowlingGame.pitch(fourth);

        assertThat(bowlingGame.getGameFrames().size()).isEqualTo(expect);
    }
}

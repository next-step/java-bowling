package bowling.acceptance;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGameMockFactory;
import bowling.view.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTest {
    @DisplayName("퍼펙트 게임")
    @Test
    void perfect_game() {
        // given
        final BowlingGame bowlingGame = BowlingGameMockFactory.create("HSM");

        // when
        for (int i = 0 ; i < 12; i ++) {
            bowlingGame.pitch(10);
            OutputView.printResult(bowlingGame.name(), bowlingGame.symbols());
        }

        // then
        assertThat(bowlingGame.isFinished()).isTrue();
    }
}

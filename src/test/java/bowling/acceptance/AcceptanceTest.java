package bowling.acceptance;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGameMockFactory;
import bowling.domain.Pins;
import bowling.view.output.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTest {
    @DisplayName("퍼펙트 게임")
    @Test
    void perfect_game() {
        // given
        final BowlingGame bowlingGame = BowlingGameMockFactory.create("HSM");

        // when
        Stream.generate(() -> bowlingGame.pitch(Pins.MAX))
                .limit(12)
                .forEach(OutputView::printResult);

        // then
        assertThat(bowlingGame.isFinished()).isTrue();
    }
}

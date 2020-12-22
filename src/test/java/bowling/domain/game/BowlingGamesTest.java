package bowling.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import bowling.domain.player.Player;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingGamesTest {

    private final int ALL_PIN_DOWN = 10;
    private final Player player1 = Player.create("AAA");
    private final Player player2 = Player.create("BBB");
    private BowlingGames bowlingGames;

    @BeforeEach
    void setup() {
        bowlingGames = new BowlingGames(Arrays.asList(player1, player2));
    }

    @DisplayName("모든 사용자의 게임이 끝나면 true 반환 테스트")
    @Test
    void isAllGameFinished() {
        int halfFrameNumber = 5;

        assertThat(bowlingGames.isAllGameFinished()).isFalse();

        rollingStrikeGivenCount(halfFrameNumber);
        assertThat(bowlingGames.isAllGameFinished()).isFalse();

        int halfFrameNumberPlusStrikeBonusNumber = halfFrameNumber + 2;
        rollingStrikeGivenCount(halfFrameNumberPlusStrikeBonusNumber);
        assertThat(bowlingGames.isAllGameFinished()).isTrue();
    }

    private void rollingStrikeGivenCount(int count) {
        for (int i = 0; i < count; i++) {
            bowlingGames.getGames()
                .forEach(game -> game.roll(ALL_PIN_DOWN));
        }
    }
}

package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultsTest {

    @Test
    void newGameResults() {
        assertThat(GameResults.newGameResults()).isNotNull();
    }

    @Test
    void add() {
        GameResults gameResults = GameResults.newGameResults();
        assertThat(gameResults.add(GameResult.ofStrike())).isTrue();
        assertThat(gameResults.size()).isEqualTo(1);
    }

    @Test
    void get() {
        GameResults gameResults = GameResults.newGameResults();
        gameResults.add(GameResult.ofStrike());
        gameResults.add(GameResult.ofSpare());
        assertThat(gameResults.get(0)).isEqualTo(GameResult.ofStrike());
        assertThat(gameResults.get(1)).isEqualTo(GameResult.ofSpare());
    }
}

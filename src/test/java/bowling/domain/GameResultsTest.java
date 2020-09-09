package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultsTest {

    @Test
    void newGameResults() {
        assertThat(GameResults.create()).isNotNull();
    }

    @Test
    void add() {
        GameResults gameResults = GameResults.create();
        assertThat(gameResults.record(GameResult.ofStrike())).isTrue();
        assertThat(gameResults.getHistory().size()).isEqualTo(1);
    }

    @Test
    void getAll() {
        GameResults gameResults = GameResults.create();
        gameResults.record(GameResult.ofStrike());
        gameResults.record(GameResult.ofSpare());
        assertThat(gameResults.getHistory()).hasSize(2);
        assertThat(gameResults.getHistory()).containsExactly(GameResult.ofStrike(), GameResult.ofSpare());
    }
}

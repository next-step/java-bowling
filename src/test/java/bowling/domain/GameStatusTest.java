package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameStatusTest {
    @Test
    void constructor() {
        assertThat(new GameResult(Status.STRIKE, GameResult.NO_COUNT)).isEqualTo(new GameResult(Status.STRIKE, GameResult.NO_COUNT));
    }

    @Test
    void of() {
        assertThat(GameResult.ofStrike()).isEqualTo(new GameResult(Status.STRIKE, GameResult.STRIKE_COUNT));
        assertThat(GameResult.ofSpare()).isEqualTo(new GameResult(Status.SPARE, GameResult.SPARE_COUNT));
        assertThat(GameResult.ofMiss()).isEqualTo(new GameResult(Status.MISS, GameResult.NO_COUNT));
        assertThat(GameResult.ofMiss(1)).isEqualTo(new GameResult(Status.MISS, 1));
        assertThat(GameResult.ofGutter()).isEqualTo(new GameResult(Status.GUTTER, GameResult.NO_COUNT));
    }
}

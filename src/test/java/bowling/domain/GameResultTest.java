package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    @Test
    void constructor() {
        assertThat(new GameResult(Result.STRIKE, GameResult.NO_COUNT)).isEqualTo(new GameResult(Result.STRIKE, GameResult.NO_COUNT));
    }

    @Test
    void of() {
        assertThat(GameResult.ofStrike()).isEqualTo(new GameResult(Result.STRIKE, GameResult.STRIKE_COUNT));
        assertThat(GameResult.ofSpare()).isEqualTo(new GameResult(Result.SPARE, GameResult.SPARE_COUNT));
        assertThat(GameResult.ofMiss()).isEqualTo(new GameResult(Result.MISS, GameResult.NO_COUNT));
        assertThat(GameResult.ofMiss(1)).isEqualTo(new GameResult(Result.MISS, 1));
        assertThat(GameResult.ofGutter()).isEqualTo(new GameResult(Result.GUTTER, GameResult.NO_COUNT));
    }
}

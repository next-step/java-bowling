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

    @Test
    void toResultForGutter() {
        assertThat(GameResult.toResult(1, 1, GameResult.NO_COUNT, false)).isEqualTo(GameResult.ofGutter());
    }

    @Test
    void toResultForStrike() {
        assertThat(GameResult.toResult(1, 1, GameResult.STRIKE_COUNT, true)).isEqualTo(GameResult.ofStrike());
    }

    @Test
    void toResultForSpare() {
        int count = GameResult.NO_COUNT;
        GameResult result = GameResult.toResult(1, 1, count, false);
        assertThat(GameResult.toResult(1, 2, GameResult.STRIKE_COUNT - result.getCount(), true)).isEqualTo(GameResult.ofSpare());
    }

    @Test
    void toResultForMiss() {
        int hitCount = 1;
        assertThat(GameResult.toResult(1, 1, hitCount, false)).isEqualTo(GameResult.ofMiss(hitCount));
    }

    @Test
    void getCount() {
        int hitCount = 1;
        GameResult result = GameResult.ofMiss(hitCount);
        assertThat(result.getCount()).isEqualTo(hitCount);
    }
}

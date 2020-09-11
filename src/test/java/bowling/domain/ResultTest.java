package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {
    @Test
    void constructor() {
        assertThat(new Result(Status.STRIKE, Result.NO_COUNT)).isEqualTo(new Result(Status.STRIKE, Result.NO_COUNT));
    }

    @Test
    void of() {
        assertThat(Result.ofStrike()).isEqualTo(new Result(Status.STRIKE, Result.STRIKE_COUNT));
        assertThat(Result.ofSpare()).isEqualTo(new Result(Status.SPARE, Result.SPARE_COUNT));
        assertThat(Result.ofMiss()).isEqualTo(new Result(Status.MISS, Result.NO_COUNT));
        assertThat(Result.ofMiss(1)).isEqualTo(new Result(Status.MISS, 1));
        assertThat(Result.ofGutter()).isEqualTo(new Result(Status.GUTTER, Result.NO_COUNT));
    }

    @Test
    void toResultForGutter() {
        assertThat(Result.of(1, 1, Result.NO_COUNT, false)).isEqualTo(Result.ofGutter());
    }

    @Test
    void toResultForStrike() {
        assertThat(Result.of(1, 1, Result.STRIKE_COUNT, true)).isEqualTo(Result.ofStrike());
    }

    @Test
    void toResultForSpare() {
        int count = Result.NO_COUNT;
        Result result = Result.of(1, 1, count, false);
        assertThat(Result.of(1, 2, Result.STRIKE_COUNT - result.getCount(), true)).isEqualTo(Result.ofSpare());
    }

    @Test
    void toResultForMiss() {
        int hitCount = 1;
        assertThat(Result.of(1, 1, hitCount, false)).isEqualTo(Result.ofMiss(hitCount));
    }

    @Test
    void getCount() {
        int hitCount = 1;
        Result result = Result.ofMiss(hitCount);
        assertThat(result.getCount()).isEqualTo(hitCount);
    }
}

package bowling.domain;

import bowling.domain.Turn;
import bowling.domain.score.TurnScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TurnTest {
    private static class FakeTurn extends Turn {
        protected FakeTurn(final TurnScore score) {
            super(score);
        }
    }

    @Test
    @DisplayName("점수가 0 일경우 Gutter")
    void isGutterTest() {
        assertThat(
                new FakeTurn(TurnScore.of(0)).isGutter()
        ).isTrue();
    }
}

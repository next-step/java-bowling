package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MissTest {
    public static final Miss MISS = Miss.of();
    public static final Miss MISS_5 = Miss.of(0, 5);

    @Test
    void 미스생성() {
        assertThat(MISS.isSpare()).isFalse();
        assertThat(MISS.isStrike()).isFalse();
        assertThat(MISS.hasNext()).isFalse();
        assertThat(MISS.canAccumulate()).isFalse();
        assertThat(MISS_5.eval()).isEqualTo(Score.of(5));
    }

    @Test
    void Miss는_점수총합이_10이될수없다() {
        assertThatThrownBy(() -> {
            Miss.of(5, 5);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

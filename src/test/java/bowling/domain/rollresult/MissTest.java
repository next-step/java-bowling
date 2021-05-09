package bowling.domain.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MissTest {
    public static final Miss MISS = Miss.of();
    @Test
    void 미스생성() {
        assertThat(MISS.isSpare()).isFalse();
        assertThat(MISS.isStrike()).isFalse();
        assertThat(MISS.eval().compareTo(0)).isEqualTo(0);
    }
}

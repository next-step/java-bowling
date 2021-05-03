package bowling.domain.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MissTest {
    public static final Miss MISS1 = Miss.of();
    @Test
    void 미스생성() {
        assertThat(MISS1.isSpare()).isFalse();
        assertThat(MISS1.isStrike()).isFalse();
        assertThat(MISS1.eval()).isEqualTo(0);
    }
}

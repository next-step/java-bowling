package bowling.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {
    public static final Strike ST1 = Strike.of();

    @Test
    void 스트라이크생성() {
        assertThat(ST1.isStrike()).isTrue();
        assertThat(ST1.isSpare()).isFalse();
        assertThat(ST1.eval()).isEqualTo(10);
    }
}

package bowling.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {
    public static final Spare SP1 = Spare.of(1);

    @Test
    void 스페어생성() {
        assertThat(SP1.isSpare()).isTrue();
        assertThat(SP1.isStrike()).isFalse();
        assertThat(SP1.eval()).isEqualTo(10);
    }
}

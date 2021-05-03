package bowling.domain.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GutterTest {
    public static final Gutter G1 = Gutter.of(3, 5);

    @Test
    void 거터생성() {
        assertThat(G1.isSpare()).isFalse();
        assertThat(G1.isStrike()).isFalse();
        assertThat(G1.eval()).isEqualTo(8);
    }
}

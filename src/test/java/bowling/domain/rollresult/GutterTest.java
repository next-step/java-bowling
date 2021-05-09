package bowling.domain.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GutterTest {
    public static final Gutter GUTTER = Gutter.of();

    @Test
    void 거터생성() {
        assertThat(GUTTER.isSpare()).isFalse();
        assertThat(GUTTER.isStrike()).isFalse();
        assertThat(GUTTER.eval().compareTo(0)).isEqualTo(0);
        assertThat(GUTTER).isEqualTo(Gutter.of());
    }
}

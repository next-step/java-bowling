package bowling.domain.rollresult;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GutterTest {
    public static final Gutter GUTTER = Gutter.of();

    @Test
    void 거터생성() {
        assertThat(GUTTER).isEqualTo(Gutter.of());
        assertThat(GUTTER.isSpare()).isFalse();
        assertThat(GUTTER.isStrike()).isFalse();
        assertThat(GUTTER.eval().isGutter()).isTrue();
        assertThat(GUTTER.hasNext()).isTrue();
        assertThat(GUTTER.canAccumulate()).isFalse();
    }

    @Test
    void 거터다음_10개치면_스페어() {
        RollResultType spare = GUTTER.next(10);
        assertThat(spare.isSpare()).isTrue();
        assertThat(spare.eval().isClear()).isTrue();
    }

    @Test
    void 거터다음_10개미만은_Miss() {
        RollResultType miss = GUTTER.next(5);
        assertThat(miss.isSpare()).isFalse();
        assertThat(miss.eval()).isEqualTo(Score.of(5));
    }
}

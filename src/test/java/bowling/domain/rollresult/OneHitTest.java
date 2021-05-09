package bowling.domain.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OneHitTest {
    public static final OneHit ONE_HIT_3 = OneHit.ofOne(3);

    @Test
    void 첫투구결과를생성() {
        RollResultType result = OneHit.of(3);
        assertThat(result).isInstanceOf(OneHit.class);
        assertThat(result.hasNext()).isTrue();
    }

    @Test
    void 첫투구가스트라이크() {
        RollResultType result = OneHit.of(10);
        assertThat(result.isStrike()).isTrue();
        assertThat(result.isSpare()).isFalse();
    }

    @Test
    void 두번째에스페어() {
        RollResultType result = OneHit.of(3).next(7);
        assertThat(result.isStrike()).isFalse();
        assertThat(result.isSpare()).isTrue();
    }

    @Test
    void 남은핀이있을떄Miss() {
        RollResultType result = OneHit.of(3).next(6);
        assertThat(result).isInstanceOf(Miss.class);
    }

    @Test
    void 첫투구아무것도못쳤을땐거터() {
        RollResultType result = OneHit.of(0);
        assertThat(result).isInstanceOf(Gutter.class);
    }
}

package bowling.domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OneHitTest {
    public static final OneHit ONE_HIT_3 = OneHit.ofOne(3);

    @Test
    void OneHit생성() {
        assertThat(ONE_HIT_3.isSpare()).isFalse();
        assertThat(ONE_HIT_3.isStrike()).isFalse();
        assertThat(ONE_HIT_3.hasNext()).isTrue();
        assertThat(ONE_HIT_3.canAccumulate()).isFalse();
    }

    @Test
    void 첫투구결과를생성() {
        State result = OneHit.of(3);
        assertThat(result).isEqualTo(OneHit.ofOne(3));
    }

    @Test
    void 첫투구가스트라이크() {
        State result = OneHit.of(10);
        assertThat(result.isStrike()).isTrue();
        assertThat(result.isSpare()).isFalse();
    }

    @Test
    void 두번째에스페어() {
        State result = OneHit.of(3).next(7);
        assertThat(result.isStrike()).isFalse();
        assertThat(result.isSpare()).isTrue();
    }

    @Test
    void 남은핀이있을떄Miss() {
        State result = OneHit.of(3).next(6);
        assertThat(result).isEqualTo(Miss.of(3,6));
    }

    @Test
    void 첫투구아무것도못쳤을땐거터() {
        State result = OneHit.of(0);
        assertThat(result).isEqualTo(Gutter.of());
    }
}

package bowling.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OneRollResultTest {
    @Test
    void 첫투구결과를생성() {
        RollResultType result = OneRollResult.of(3);
        assertThat(result).isInstanceOf(OneRollResult.class);
        assertThat(result.hasNext()).isTrue();
    }

    @Test
    void 첫투구가스트라이크() {
        RollResultType result = OneRollResult.of(10);
        assertThat(result.isStrike()).isTrue();
        assertThat(result.isSpare()).isFalse();
    }

    @Test
    void 두번째에스페어() {
        RollResultType result = OneRollResult.of(7).next(3);
        assertThat(result.isStrike()).isFalse();
        assertThat(result.isSpare()).isTrue();
    }

    @Test
    void 남은핀이있을떄거터() {
        RollResultType result = OneRollResult.of(3).next(3);
        assertThat(result).isInstanceOf(Gutter.class);
    }

    @Test
    void 아무것도못쳤을땐Miss() {
        RollResultType result = OneRollResult.of(0).next(0);
        assertThat(result).isInstanceOf(Miss.class);
    }
}

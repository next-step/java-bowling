package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinCountTest {

    @Test
    void 생성() {
        PinCount pinCount = PinCount.of(5);
        assertThat(pinCount).isEqualTo(PinCount.of(5));
    }

    @Test
    void 생성_실패() {
        assertThatIllegalArgumentException().isThrownBy(() -> PinCount.of(-1));
        assertThatIllegalArgumentException().isThrownBy(() -> PinCount.of(11));
    }

    @Test
    void 합() {
        int result = PinCount.of(5).sum(PinCount.of(5));
        assertThat(result).isEqualTo(10);
    }
}

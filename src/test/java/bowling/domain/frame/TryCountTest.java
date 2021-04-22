package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TryCountTest {

    @Test
    void 생성_테스트() {
        assertThat(TryCount.of(1)).isEqualTo(TryCount.of(1));
        assertThat(TryCount.first()).isEqualTo(TryCount.of(0));
    }

    @Test
    void 시도_횟수_유효성_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TryCount.of(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> TryCount.of(3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> TryCount.of(5));
    }

    @Test
    void 시도_횟수_최대_테스트() {
        assertThat(TryCount.of(2).isMaxHit()).isTrue();
        assertThat(TryCount.of(1).isMaxHit()).isFalse();
        assertThat(TryCount.of(0).isFirstHit()).isTrue();
        assertThat(TryCount.of(1).isFirstHit()).isFalse();
    }
}

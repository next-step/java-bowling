package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinCountsTest {
    @DisplayName("저장된 핀의 총 갯수를 구할 수 있다.")
    @Test
    void getTotal() {
        PinCounts pinCounts = new PinCounts();
        pinCounts.add(8);
        pinCounts.add(1);
        assertThat(pinCounts.getPinCountTotal()).isEqualTo(9);
    }

    @DisplayName("첫번째 핀 갯수를 구할 수 있다.")
    @Test
    void getFirst() {
        PinCounts pinCounts = new PinCounts();
        assertThat(pinCounts.getFirst().isPresent()).isFalse();
        pinCounts.add(1);
        assertThat(pinCounts.getFirst().isPresent()).isTrue();
    }
}

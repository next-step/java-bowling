package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinCountsTest {

    @DisplayName("지정한 갯수 까지만 저장할 수 있다")
    @Test
    void init() {
        PinCounts pinCounts = new PinCounts(1);
        pinCounts.add(PinCountTest.PIN_COUNT_1);
        assertThat(pinCounts.isFull()).isTrue();
    }

    @DisplayName("저장된 핀의 총 갯수를 구할 수 있다.")
    @Test
    void getTotal() {
        PinCounts pinCounts = new PinCounts(2);
        pinCounts.add(PinCountTest.PIN_COUNT_8);
        pinCounts.add(PinCountTest.PIN_COUNT_1);
        assertThat(pinCounts.getPintCountTotal()).isEqualTo(9);
    }

    @DisplayName("첫번째 핀 갯수를 구할 수 있다.")
    @Test
    void getFirst() {
        PinCounts pinCounts = new PinCounts(1);
        assertThat(pinCounts.getFirst().isPresent()).isFalse();
        pinCounts.add(PinCountTest.PIN_COUNT_1);
        assertThat(pinCounts.getFirst().isPresent()).isTrue();
    }
}

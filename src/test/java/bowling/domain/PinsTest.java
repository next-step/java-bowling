package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PinsTest {

    @DisplayName("투구로 쓰러트린 핀의 갯수를 얻는다")
    @Test
    void pinsCountTest() {
        List<Pin> pinList = Arrays.asList(new Pin(5), new Pin(5), new Pin(2));
        Pins pins = new Pins(pinList);
        assertThat(pins.totalCount()).isEqualTo(12);
    }

    @DisplayName("두번째 투구 까지의 핀의 갯수를 얻는다")
    @Test
    void pinsToSecondTest() {
        List<Pin> pinList = Arrays.asList(new Pin(5), new Pin(5), new Pin(2));
        Pins pins = new Pins(pinList);
        assertThat(pins.toSecondCount()).isEqualTo(10);
    }
}

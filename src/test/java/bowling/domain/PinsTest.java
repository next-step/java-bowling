package bowling.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PinsTest {

    private static List<Pin> pinList;
    private static Pins pins;

    @BeforeAll
    static void init() {
        pinList = Arrays.asList(new Pin(5), new Pin(5), new Pin(2));
        pins = new Pins(pinList);
    }

    @DisplayName("투구로 쓰러트린 핀의 갯수를 얻는다")
    @Test
    void pinsCountTest() {
        assertThat(pins.totalCount()).isEqualTo(12);
    }

    @DisplayName("두번째 투구 까지의 핀의 갯수를 얻는다")
    @Test
    void pinsToSecondTest() {
        assertThat(pins.toSecondCount()).isEqualTo(10);
    }

    @DisplayName("첫 시도의 갯수를 얻는다")
    @Test
    void pinsFirstTryTest() {
        assertThat(pins.firstTryCount()).isEqualTo(5);
    }

    @DisplayName("두번째 시도의 갯수를 얻는다")
    @Test
    void pinsSecondTryTest() {
        assertThat(pins.secondTryCount()).isEqualTo(5);
    }

    @DisplayName("보너스 시도의 갯수를 얻는다")
    @Test
    void pinsBonusTryTest() {
        assertThat(pins.bonusTryCount()).isEqualTo(2);
    }
}

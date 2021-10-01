package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PinTypeTest {

    @Test
    @DisplayName("스트라이크인 경우 X가 반환되어야 한다.")
    void strikeTest() {

        // given
        List<Pin> pins = Arrays.asList(Pin.of(10));
        int index = 0;

        String expected = PinType.STRIKE.status();

        // when
        String result = PinType.pinToString(pins, index);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("스페어인 경우 /가 반환되어야 한다.")
    void spareTest() {

        // given
        List<Pin> pins = Arrays.asList(Pin.of(0), Pin.of(10));
        int index = 1;

        String expected = PinType.SPARE.status();

        // when
        String result = PinType.pinToString(pins, index);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("거터인 경우 -가 반환되어야 한다.")
    void gutterTest() {

        // given
        List<Pin> pins = Arrays.asList(Pin.of(0));
        int index = 0;

        String expected = PinType.GUTTER.status();

        // when
        String result = PinType.pinToString(pins, index);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("아무 상태가 아닌 경우 숫자를 반환해야 한다.")
    void remainTest() {

        // given
        List<Pin> pins = Arrays.asList(Pin.of(1));
        int index = 0;

        String expected = "1";

        // when
        String result = PinType.pinToString(pins, index);

        // then
        assertThat(result).isEqualTo(expected);
    }

}
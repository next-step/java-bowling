package domain.state.open;

import domain.Pins;
import domain.state.State;
import domain.state.closed.Miss;
import domain.state.closed.Spare;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class HitTest {

    @ParameterizedTest
    @CsvSource({"0, 10", "1, 9", "9, 1"})
    void 첫번째_투구결과와_두번째_투구결과의_합이_10이면_SPARE_상태를_생성한다(int firstPins, int secondPins) {
        //given
        Hit hit = new Hit(Pins.from(firstPins));

        //when
        State state = hit.update(Pins.from(secondPins));

        //then
        assertThat(state instanceof Spare).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"0, 9", "1, 8", "9, 0"})
    void 첫번째_투구결과와_두번째_투구결과의_합이_10보다_작으면_MISS_상태를_생성한다(int firstPins, int secondPins) {
        //given
        Hit hit = new Hit(Pins.from(firstPins));

        //when
        State state = hit.update(Pins.from(secondPins));

        //then
        assertThat(state instanceof Miss).isTrue();
    }
}

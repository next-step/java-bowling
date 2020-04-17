package bowling.domain.frame.state;

import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @DisplayName("첫투구에 핀이 다 쓰러지면 Strike를 반환한다.")
    @Test
    void strike() {
        BowlCount bowlCount = new BowlCount(10);
        Pins pins = Pins.of();
        final Pins remains = pins.knockOver(bowlCount);

        State actual = new Ready().roll(remains);

        assertThat(actual).isInstanceOf(Strike.class);
    }

    @DisplayName("첫투구에 strike가 아니라면 진행상태인 FirstBowl을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void running(int count) {
        BowlCount bowlCount = new BowlCount(count);
        Pins pins = Pins.of();
        final Pins remains = pins.knockOver(bowlCount);

        State actual = new Ready().roll(remains);

        assertThat(actual).isInstanceOf(FirstBowl.class);
    }
}
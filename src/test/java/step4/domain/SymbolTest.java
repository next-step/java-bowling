package step4.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step4.domain.state.Miss;
import step4.domain.state.Spare;
import step4.domain.state.State;
import step4.domain.state.Strike;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

class SymbolTest {

    @Test
    @DisplayName("스트라이크일 경우, 스트라이크 표식을 반환한다.")
    public void of_strike() throws Exception {
        String result = Symbol.of(new Strike(), new PinCount(10), true);
        assertThat(result).isEqualTo(Symbol.STRIKE.mark());
    }

    @Test
    @DisplayName("스페어일 경우, 스페어 표식을 반환한다.")
    public void of_spare() throws Exception {
        //given
        PinCount firstPinCount = new PinCount(0);
        PinCount secondPinCount = new PinCount(10);
        State spare = new Spare(firstPinCount, secondPinCount);

        //when
        String result = Symbol.of(spare, secondPinCount, false);

        then(result).isEqualTo(Symbol.SPARE.mark());
    }

    @Test
    @DisplayName("거터일 경우, 거터 표식을 반환한다.")
    public void of_gutter() throws Exception {
        State miss = new Miss(new PinCount(0), new PinCount(1));
        String result = Symbol.of(miss, new PinCount(0), true);
        assertThat(result).isEqualTo(Symbol.GUTTER.mark());
    }

    @Test
    @DisplayName("미스일 경우, 값 그대로를 반환한다.")
    public void of_miss() throws Exception {
        //given
        PinCount firstPinCount = new PinCount(1);
        PinCount secondPinCount = new PinCount(2);
        State miss = new Miss(firstPinCount, secondPinCount);

        //when
        String result = Symbol.of(miss, firstPinCount, true);

        then(result).isEqualTo(String.valueOf(firstPinCount.value()));
    }
}
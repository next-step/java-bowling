package bowling.domain.state;

import bowling.domain.value.Pins;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

class ReadyTest {
    @Test
    @DisplayName("Reday 상태에서 첫 투구가 스트라이크 검증")
    void ready_to_strike() {
        State ready = Ready.of();
        State strike = ready.pitch(Pins.from(10));

        MatcherAssert.assertThat(strike, instanceOf(Strike.class));
    }

    @Test
    @DisplayName("Reday 상태에서 첫 투구가 거터 검증")
    void ready_to_gutter() {
        State ready = Ready.of();
        State gutter = ready.pitch(Pins.from(0));

        MatcherAssert.assertThat(gutter, instanceOf(FirstGutter.class));
    }

    @Test
    @DisplayName("Reday 상태에서 첫 투구가 핀을 몇개 넘어트린 경우")
    void ready_to_firstBowl() {
        State ready = Ready.of();
        State firstBowl = ready.pitch(Pins.from(5));

        MatcherAssert.assertThat(firstBowl, instanceOf(FirstBowl.class));
    }

    @Test
    @DisplayName("Reday 상태의 기록 검증")
    void ready_mark() {
        State ready = Ready.of();

        assertThat(ready.mark()).isEmpty();
    }
}

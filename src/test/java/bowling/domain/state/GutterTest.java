package bowling.domain.state;

import bowling.domain.value.Pins;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

class GutterTest {
    @Test
    @DisplayName("거터 상태에서 두번째 투구가 스페어 검증")
    void gutter_to_spare() {
        State gutter = Gutter.of(Pins.from(0));
        State spare = gutter.pitch(Pins.from(10));

        MatcherAssert.assertThat(spare, instanceOf(Spare.class));
    }

    @Test
    @DisplayName("거터 상태에서 두번째 투구가 미스 검증")
    void gutter_to_miss() {
        State gutter = Gutter.of(Pins.from(0));
        State miss = gutter.pitch(Pins.from(5));

        MatcherAssert.assertThat(miss, instanceOf(Miss.class));
    }


    @Test
    @DisplayName("거터 상태의 기록 검증")
    void gutter_mark() {
        State gutter = Gutter.of(Pins.from(0));

        assertThat(gutter.mark()).isEqualTo("-");
    }

    @Test
    @DisplayName("거터 상태의 기록 검증")
    void gutter_mark2() {
        State gutter = Gutter.of(Pins.from(5), Pins.from(0));

        assertThat(gutter.mark()).isEqualTo("5|-");
    }

    @Test
    @DisplayName("거터 상태의 기록 검증")
    void gutter_mark3() {
        State gutter = Gutter.of(Pins.from(0), Pins.from(5));

        assertThat(gutter.mark()).isEqualTo("-|5");
    }

    @Test
    @DisplayName("거터 상태의 기록 검증")
    void gutter_mark5() {
        State gutter = Gutter.of(Pins.from(0), Pins.from(0));

        assertThat(gutter.mark()).isEqualTo("-|-");
    }
}

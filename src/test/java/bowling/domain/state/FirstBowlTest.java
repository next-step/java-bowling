package bowling.domain.state;

import bowling.domain.value.Pins;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

class FirstBowlTest {
    @Test
    @DisplayName("firstBowl 상태에서 두번째 투구가 스페어 검증")
    void firstBowl_to_spare() {
        State firstBowl = FirstBowl.of(Pins.from(5));
        State spare = firstBowl.pitch(Pins.from(5));

        MatcherAssert.assertThat(spare, instanceOf(Spare.class));
    }

    @Test
    @DisplayName("firstBowl 상태에서 두번째 투구가 거터 검증")
    void firstBowl_to_gutter() {
        State firstBowl = FirstBowl.of(Pins.from(5));
        State spare = firstBowl.pitch(Pins.from(0));

        MatcherAssert.assertThat(spare, instanceOf(SecondGutter.class));
    }

    @Test
    @DisplayName("firstBowl 상태에서 스페어를 못한 경우(미스) 검증")
    void firstBowl_to_miss() {
        State firstBowl = FirstBowl.of(Pins.from(5));
        State miss = firstBowl.pitch(Pins.from(3));

        MatcherAssert.assertThat(miss, instanceOf(Miss.class));
    }

    @Test
    @DisplayName("firstBowl 상태의 기록 검증")
    void firstBowl_mark() {
        State firstBowl = FirstBowl.of(Pins.from(5));

        assertThat(firstBowl.getMark()).isEqualTo("5");
    }
}

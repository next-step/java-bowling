package bowling.domain.state;

import bowling.domain.value.Pins;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

class FirstBowlTest {

    @ParameterizedTest
    @CsvSource(value = {
            "   5|    5",
            "  10|    0"
    }, delimiter = '|')
    @DisplayName("firstBowl 상태에서 두번째 투구가 스페어 검증")
    void firstBowl_to_spare(int firstPins, int secondPins) {
        State firstBowl = FirstBowl.of(Pins.from(firstPins));
        State spare = firstBowl.pitch(Pins.from(secondPins));

        MatcherAssert.assertThat(spare, instanceOf(Spare.class));
    }

    @Test
    @DisplayName("firstBowl 상태에서 두번째 투구가 거터 검증")
    void firstBowl_to_gutter() {
        State firstBowl = FirstBowl.of(Pins.from(5));
        State spare = firstBowl.pitch(Pins.from(0));

        MatcherAssert.assertThat(spare, instanceOf(Gutter.class));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "   5|    3",
            "   0|    5"
    }, delimiter = '|')
    @DisplayName("firstBowl 상태에서 스페어를 못한 경우(미스) 검증")
    void firstBowl_to_miss(int firstPins, int secondPins) {
        State firstBowl = FirstBowl.of(Pins.from(firstPins));
        State miss = firstBowl.pitch(Pins.from(secondPins));

        MatcherAssert.assertThat(miss, instanceOf(Miss.class));
    }

    @Test
    @DisplayName("firstBowl 상태의 기록 검증")
    void firstBowl_mark() {
        State firstBowl = FirstBowl.of(Pins.from(5));

        assertThat(firstBowl.getMark()).isEqualTo("5");
    }


    @Test
    @DisplayName("첫번째 거터 상태에서 두번째 투구가 거터 검증")
    void gutter_to_gutter() {
        State firstGutter = FirstBowl.of(Pins.from(0));
        State secondGutter = firstGutter.pitch(Pins.from(0));

        MatcherAssert.assertThat(secondGutter, instanceOf(Gutter.class));
    }

    @Test
    @DisplayName("거터 상태의 기록 검증")
    void gutter_mark() {
        State gutter = FirstBowl.of(Pins.from(0));

        assertThat(gutter.getMark()).isEqualTo("-");
    }
}

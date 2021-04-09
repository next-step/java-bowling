package bowling.domain;

import bowling.domain.State.StateType;
import bowling.domain.frame.PinCount;
import bowling.domain.frame.PinCounts;
import bowling.dto.PinCountsResult;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class PinCountsTest {

    @Test
    void create_from_total_pin_counts_more_than_10_throw_exception() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new PinCounts(Arrays.asList(new PinCount(5), new PinCount(11))));
    }

    @Test
    void create_from_pin_count_list_size_more_than_2_throw_exception() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new PinCounts(Arrays.asList(new PinCount(5), new PinCount(5), new PinCount(5))));
    }

    @Test
    void add_pin_count() {
        int pinCount = 6;
        PinCounts pinCounts = new PinCounts();
        pinCounts.add(pinCount);

        PinCountsResult result = pinCounts.result();
        assertThat(result.stateType()).isEqualTo(StateType.NONE);
        assertThat(result.pinCounts()).containsExactly(pinCount);
    }

    @Test
    void add_pin_count_after_done_throw_exception() {
        PinCounts pinCounts = new PinCounts();
        pinCounts.add(6);
        pinCounts.add(4);

        assertThat(pinCounts.isDone()).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                pinCounts.add(2));
    }

    @Test
    void add_pin_count_over_max_total_pin_counts_throw_exception() {
        PinCounts pinCounts = new PinCounts();
        pinCounts.add(5);

        assertThatIllegalArgumentException().isThrownBy(() ->
                pinCounts.add(9));
    }

    @Test
    void result_when_strike() {
        PinCounts pinCounts = new PinCounts();
        int strikePinCounts = 10;
        pinCounts.add(strikePinCounts);

        PinCountsResult result = pinCounts.result();
        assertThat(pinCounts.isDone()).isTrue();
        assertThat(pinCounts.isMatchCurrentState(StateType.STRIKE)).isTrue();
        assertThat(result.pinCounts()).containsExactly(strikePinCounts);
    }


    @Test
    void result_when_spare() {
        PinCounts pinCounts = new PinCounts();
        int firstPinCount = 2;
        int secondPinCount = 8;
        pinCounts.add(firstPinCount);
        pinCounts.add(secondPinCount);

        PinCountsResult result = pinCounts.result();
        assertThat(pinCounts.isDone()).isTrue();
        assertThat(pinCounts.isMatchCurrentState(StateType.SPARE)).isTrue();
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(firstPinCount, secondPinCount);
    }

    @Test
    void result_when_miss() {
        PinCounts pinCounts = new PinCounts();
        int firstPinCount = 2;
        int secondPinCount = 6;
        pinCounts.add(firstPinCount);
        pinCounts.add(secondPinCount);

        PinCountsResult result = pinCounts.result();
        assertThat(pinCounts.isDone()).isTrue();
        assertThat(pinCounts.isMatchCurrentState(StateType.MISS)).isTrue();
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(firstPinCount, secondPinCount);
    }

    @Test
    void result_when_gutter() {
        PinCounts pinCounts = new PinCounts();
        int firstPinCount = 0;
        int secondPinCount = 0;
        pinCounts.add(firstPinCount);
        pinCounts.add(secondPinCount);

        PinCountsResult result = pinCounts.result();
        assertThat(pinCounts.isDone()).isTrue();
        assertThat(pinCounts.isMatchCurrentState(StateType.GUTTER)).isTrue();
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(firstPinCount,secondPinCount);
    }

    @Test
    void result_when_none() {
        PinCounts pinCounts = new PinCounts();
        int firstPinCount = 2;
        pinCounts.add(firstPinCount);

        PinCountsResult result = pinCounts.result();
        assertThat(pinCounts.isDone()).isFalse();
        assertThat(pinCounts.isMatchCurrentState(StateType.NONE)).isTrue();
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(firstPinCount);
    }

    @Test
    void hit_count() {
        PinCounts pinCounts = new PinCounts();
        int firstPinCount = 2;
        pinCounts.add(firstPinCount);

        assertThat(pinCounts.hitCount()).isEqualTo(1);
    }

}

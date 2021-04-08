package bowling.domain;

import bowling.domain.frame.PinCount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PinCountTest {

    @Test
    void create() {
        int pintCountInInt = 1;
        PinCount actual = new PinCount(pintCountInInt);

        assertThat(actual).isEqualTo(new PinCount(pintCountInInt));
    }

    @Test
    void when_pin_count_is_smaller_than_0_throws_exception() {
        int pintCountInInt = -1;

        assertThatIllegalArgumentException().isThrownBy(() ->
                new PinCount(pintCountInInt));
    }

    @Test
    void when_frame_number_is_bigger_than_10_throws_exception() {
        int pintCountInInt = 11;

        assertThatIllegalArgumentException().isThrownBy(() ->
                new PinCount(pintCountInInt));
    }

}

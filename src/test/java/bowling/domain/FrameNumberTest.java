package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameNumberTest {

    @Test
    void create() {
        int frameNumberInInt = 1;
        FrameNumber actual = new FrameNumber(frameNumberInInt);

        assertThat(actual).isEqualTo(new FrameNumber(frameNumberInInt));
    }

    @Test
    void when_frame_number_is_smaller_than_1_throws_exception() {
        int frameNumberInInt = 0;

        assertThatIllegalArgumentException().isThrownBy(() ->
                new FrameNumber(frameNumberInInt));
    }

    @Test
    void when_frame_number_is_bigger_than_10_throws_exception() {
        int frameNumberInInt = 11;

        assertThatIllegalArgumentException().isThrownBy(() ->
                new FrameNumber(frameNumberInInt));
    }

}

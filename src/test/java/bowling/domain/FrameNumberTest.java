package bowling.domain;

import bowling.domain.frame.FrameNumber;
import org.junit.jupiter.api.DisplayName;
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
    void first() {
        FrameNumber first = FrameNumber.first();

        assertThat(first.number()).isEqualTo(1);
    }

    @Test
    void next() {
        FrameNumber nextThree = FrameNumber.first().next().next();

        assertThat(nextThree.number()).isEqualTo(3);
    }


    @Test
    @DisplayName("프레임 번호가 0보다 작은면 예외 발생")
    void when_frame_number_is_smaller_than_1_throws_exception() {
        int frameNumberInInt = 0;

        assertThatIllegalArgumentException().isThrownBy(() ->
                new FrameNumber(frameNumberInInt));
    }

}

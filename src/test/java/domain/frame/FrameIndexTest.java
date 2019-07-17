package domain.frame;

import domain.frame.FrameIndex;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static domain.frame.FrameIndex.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FrameIndexTest {

    @ParameterizedTest
    @ValueSource(ints = {MINIMUM_FRAME_INDEX, MAXIMUM_FRAME_INDEX})
    void 프레임_번호를_생성한다(int frameNumber) {
        //when
        FrameIndex frameIndex = FrameIndex.from(frameNumber);

        //then
        assertThat(frameIndex).isEqualTo(FrameIndex.from(frameNumber));
    }

    @ParameterizedTest
    @ValueSource(ints = {MINIMUM_FRAME_INDEX - 1, MAXIMUM_FRAME_INDEX + 1})
    void 프레임번호가_1보다_작거나_10보다_큰_경우_예외가_발생한다(int frameNumber) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    FrameIndex.from(frameNumber);
                }).withMessage(ALERT_INVALID_FRAME_NUMBER);
    }
}

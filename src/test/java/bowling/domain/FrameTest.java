package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created By mand2 on 2020-12-18.
 */
public class FrameTest {

    @ParameterizedTest
    @DisplayName("1~9프레임 생성자")
    @ValueSource(ints = {1, 2, 8, 9})
    void createNormalFrame(int frameIndex) {
        Frame frame = NormalFrame.of(frameIndex);
        assertThat(frame).isEqualTo(NormalFrame.of(frameIndex));
    }

    @Test
    @DisplayName("1~9프레임 생성시 입력가능한 프레임 번호를 벗어나면 예외처리")
    void createNormalFrameRangeException() {
        assertThatThrownBy(() -> NormalFrame.of(10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NormalFrame.ERROR_MESSAGE_FRAME_INDEX_RANGE);
    }

    @Test
    @DisplayName("10프레임 생성자")
    void createFinalFrame() {
        int frameIndex = 10;
        Frame frame = FinalFrame.of(frameIndex);
        assertThat(frame).isEqualTo(FinalFrame.of(frameIndex));
    }

    @ParameterizedTest
    @DisplayName("10프레임 생성시 입력가능한 프레임 번호를 벗어나면 예외처리")
    @ValueSource(ints = {1, 2, 8, 9})
    void createFinalFrameRangeException(int frameIndex) {
        assertThatThrownBy(() -> FinalFrame.of(frameIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(FinalFrame.ERROR_MESSAGE_FINAL_FRAME_INDEX);
    }


}

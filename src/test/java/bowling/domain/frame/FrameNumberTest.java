package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameNumberTest {
    @Test
    @DisplayName("일반 Frame의 수는 10회 이상이 될 수 없다.")
    void assertFrameNo() {
        int frameNumber = 10;
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new FrameNumber(frameNumber);
        }).withMessage(FrameNumber.OVER_NORMAL_FRAME_NO_ERROR);
    }

    @Test
    @DisplayName("NormalFrame의 개수만큼의 FrameNumber가 채워졌을 경우 true를 반환한다.")
    void isMaxNormalFrameCount() {
        int frameNumber = 9;
        assertThat(new FrameNumber(frameNumber).isMaxNormalFrameCount()).isTrue();
    }

    @Test
    @DisplayName("현재 FrameNumber의 다음 숫자륿 반환한다.")
    void getNext() {
        int frameNumber = 1;
        assertThat(new FrameNumber(frameNumber).getNext().getValue()).isEqualTo(2);
    }
}

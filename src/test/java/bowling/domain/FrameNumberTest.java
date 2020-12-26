package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameNumberTest {

    @Test
    @DisplayName("init() 숫자가 1인지 확인")
    public void frameNumber_firstNumber_isEqualTo() {
        assertThat(FrameNumber.init()).isEqualTo(FrameNumber.from(FrameNumber.FRAME_START_NUMBER));
    }

    @Test
    @DisplayName("last() 숫자가 10인지 확인")
    public void frameNumber_lastNumber_isEqualTo() {
        assertThat(FrameNumber.last()).isEqualTo(FrameNumber.from(FrameNumber.FRAME_LAST_NUMBER));
    }

    @Test
    @DisplayName("add() 후 숫자 비교")
    public void frameNumber_addNumber_isEqualTo() {
        //given
        FrameNumber frameNumber = FrameNumber.init();

        //when
        frameNumber = frameNumber.add();
        frameNumber = frameNumber.add();
        frameNumber = frameNumber.add();

        //then
        assertThat(frameNumber).isEqualTo(FrameNumber.from(4));
    }

    @Test
    @DisplayName("number 가 1미만 일 경우  IllegalArgumentException 발생")
    public void frameNumber_minimumSizeNumber_throwException() {
        assertThatThrownBy(() -> FrameNumber.from(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(FrameNumber.MIN_SIZE_ERROR);
    }

    @Test
    @DisplayName("number 가 10을 넘길경우  IllegalArgumentException 발생")
    public void frameNumber_overSizeNumber_throwException() {
        assertThatThrownBy(() -> FrameNumber.from(11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(FrameNumber.OVER_SIZE_ERROR);
    }
}

package bowling.frame.domain;

import bowling.global.exception.OutOfFrameRangeException;
import bowling.pin.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class NormalFrameTest {

    private Frame nomalFrame;

    @Test
    @DisplayName("게임 단위인 Frame 생성")
    void create() {
        nomalFrame = NormalFrame.newFrame(2, Pins.playPitch("5", 1));
        assertThat(nomalFrame.getNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("허용(1 ~ 9)되지 않은 프레임 번호가 들어오면 Exception 발생")
    void invalidNomalFrameNumberException() {
        assertThatExceptionOfType(OutOfFrameRangeException.class)
                .isThrownBy(() -> {
                    nomalFrame = NormalFrame.newFrame(11, Pins.playPitch("5", 1));
                });
    }
}

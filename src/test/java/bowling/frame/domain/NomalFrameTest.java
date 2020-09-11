package bowling.frame.domain;

import bowling.global.exception.OutOfFrameRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class NomalFrameTest {

    private Frame nomalFrame;

    @Test
    @DisplayName("게임 단위인 Frame 생성")
    void create() {
        nomalFrame = NomalFrame.newFrame(2);
        assertThat(nomalFrame.getNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("허용(1 ~ 9)되지 않은 프레임 번호가 들어오면 Exception 발생")
    void invalidNomalFrameNumberException() {
        assertThatExceptionOfType(OutOfFrameRangeException.class)
                .isThrownBy(() -> {
                    nomalFrame = NomalFrame.newFrame(11);
                });
    }
}

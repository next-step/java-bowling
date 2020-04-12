package bowling.domain;

import bowling.domain.exception.OutOfRangeArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NormalFrameTest {
    @DisplayName("프레임의 쓰러트린 핀 갯수를 저장할 수 있다.")
    @Test
    void init() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addPinCount(8);
        normalFrame.addPinCount(1);

        assertThat(normalFrame.getScore()).isEqualTo(9);
    }

    @DisplayName("한 프레임의 핀 갯수는 10개를 넘을 수 없다")
    @Test
    void error() {
        assertThatExceptionOfType(OutOfRangeArgumentException.class)
                .isThrownBy(() -> {
                    NormalFrame normalFrame = new NormalFrame();
                    normalFrame.addPinCount(8);
                    normalFrame.addPinCount(3);
                });
    }

    @DisplayName("2회 시도하면 한 프레임은 끝이 난다.")
    @Test
    void done() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addPinCount(8);
        assertThat(normalFrame.isDone()).isFalse();
        normalFrame.addPinCount(1);
        assertThat(normalFrame.isDone()).isTrue();
    }

    @DisplayName("스트라이크 치면 프레임은 끝난다.")
    @Test
    void strikeDone() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addPinCount(10);
        assertThat(normalFrame.isDone()).isTrue();
    }
}

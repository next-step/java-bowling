package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-09 12:41
 */
class FrameTest {
    @DisplayName("첫 번째 투구 후 남은 개수 - 10개 중 3개 쓰러트림")
    @Test
    void firstThrow() {
        Pin pin = Pin.of(3);
        List<Pin> firstRollPerFrame = new ArrayList<>(Arrays.asList(pin));
        Frame frame = new Frame(firstRollPerFrame);

        assertThat(frame.checkRemainPin()).isEqualTo(7);
    }

    @DisplayName("두 번째 투구 후 남은 개수 - 10개 중 3개, 4개 쓰러트림")
    @Test
    void secondThrow() {
        Pin pin1 = Pin.of(3);
        Pin pin2 = Pin.of(4);
        List<Pin> firstRollPerFrame = new ArrayList<>(Arrays.asList(pin1, pin2));

        Frame frame = new Frame(firstRollPerFrame);
        assertThat(frame.checkRemainPin()).isEqualTo(3);
    }

    @DisplayName("현재 Frame에 투구할 수 있는지 확인")
    @Test
    void isFinishFrame() {
        Pin pin1 = Pin.of(3);
        Pin pin2 = Pin.of(4);
        List<Pin> firstRollPerFrame = new ArrayList<>(Arrays.asList(pin1, pin2));

        Frame frame = new Frame(firstRollPerFrame);
        assertThat(frame.isFinishFrame()).isTrue();
    }
}
package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FrameTest {

    @Test
    @DisplayName("스트라이크일 때, 프레임 생성")
    void strike() {
        Frame frame = Frame.init();

        Pitch pitch = Pitch.from(10);
        frame.add(pitch);
        Assertions.assertThat(frame.getPitchSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("스트라이크가 아닐 때, 스페어 처리하는 프레임 생성")
    void spare() {
        Frame frame = Frame.init();

        Pitch pitch1 = Pitch.from(9);
        Pitch pitch2 = Pitch.from(1);
        frame.add(pitch1);
        frame.add(pitch2);
        Assertions.assertThat(frame.getPitchSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("스페어 처리시, 총 점수가 10점을 넘을 때 예외 처리")
    void spareException() {
        Frame frame = Frame.init();

        Pitch pitch1 = Pitch.from(9);
        Pitch pitch2 = Pitch.from(2);
        frame.add(pitch1);
        assertThrows(IllegalArgumentException.class,
                () -> frame.add(pitch2));
    }

}
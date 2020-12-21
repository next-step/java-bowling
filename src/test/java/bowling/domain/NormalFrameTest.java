package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NormalFrameTest {

    @Test
    @DisplayName("스트라이크일 때, 프레임 생성")
    void strike() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch = Pitch.from(10);
        normalFrame.add(pitch);
        Assertions.assertThat(normalFrame.getPitchSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("스트라이크가 아닐 때, 스페어 처리하는 프레임 생성")
    void spare() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch1 = Pitch.from(9);
        Pitch pitch2 = Pitch.from(1);
        normalFrame.add(pitch1);
        normalFrame.add(pitch2);
        Assertions.assertThat(normalFrame.getPitchSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("스페어 처리시, 총 점수가 10점을 넘을 때 예외 처리")
    void spareException() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch1 = Pitch.from(9);
        Pitch pitch2 = Pitch.from(2);
        normalFrame.add(pitch1);
        assertThrows(IllegalArgumentException.class,
                () -> normalFrame.add(pitch2));
    }

}
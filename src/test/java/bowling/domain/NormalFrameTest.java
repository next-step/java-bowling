package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("노말 프레임에서 2구까지 던질 수 있다.")
    @Test
    void roll_2() {
        // Given
        Frame givenFrame = NormalFrame.of(0, 0, Pins.create());
        Frame round1 = givenFrame.roll(5);
        round1.roll(5);

        // When && Then
        assertThat(givenFrame.numberOfDownedPins()).isEqualTo(5);
        assertThat(round1.numberOfDownedPins()).isEqualTo(5);
    }

    @DisplayName("1프레임에 1구에 스트라이크시 2프레임으로 진행된다.")
    @Test
    void nextFrame() {
        // Given
        Frame givenFrame = NormalFrame.of(0, 0, Pins.create());
        Frame nextFrame = givenFrame.roll(10);
        nextFrame.roll(5);

        // When && Then
        assertThat(givenFrame.currentFrame()).isEqualTo(0);
        assertThat(givenFrame.numberOfDownedPins()).isEqualTo(10);

        assertThat(nextFrame.currentFrame()).isEqualTo(1);
        assertThat(nextFrame.numberOfDownedPins()).isEqualTo(5);
    }

    @DisplayName("Normal Frame은 9프레임까지 10프레임은 final Frame이다")
    @Test
    void NormalFrame_Number() {
        // Given
        List<Frame> frames = new ArrayList<>();

        Frame normalFrame = NormalFrame.of(0, 0, Pins.create());
        frames.add(normalFrame);

        for (int i = 0; i < 9; i++) {
            normalFrame = normalFrame.roll(10);
            frames.add(normalFrame);
        }

        // When && Then
        assertThat(frames.size()).isEqualTo(10);
        assertThat(frames.get(0)).isInstanceOf(NormalFrame.class);
        assertThat(frames.get(1)).isInstanceOf(NormalFrame.class);
        assertThat(frames.get(9)).isInstanceOf(FinalFrame.class);
    }

}
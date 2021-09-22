package bowling.domain.frame;

import bowling.domain.frame.info.NormalFrameInfo;
import bowling.domain.score.Score;
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
        Frame givenFrame = NormalFrame.create();
        givenFrame.roll(5);
        Frame round1 = givenFrame.nextRound().orElseThrow(IllegalArgumentException::new);
        round1.roll(5);

        // When && Then
        assertThat(givenFrame.numberOfDownedPins()).isEqualTo(Score.from(5));
        assertThat(round1.numberOfDownedPins()).isEqualTo(Score.from(5));
    }

    @DisplayName("1프레임에 1구에 스트라이크시 2프레임으로 진행된다.")
    @Test
    void nextFrame() {
        // Given
        Frame givenFrame = NormalFrame.create();
        givenFrame.roll(10);
        Frame nextFrame = givenFrame.nextRound().orElseThrow(IllegalArgumentException::new);
        nextFrame.roll(5);

        // When && Then
        assertThat(givenFrame.frameInfo()).isEqualTo(NormalFrameInfo.of(0, 0));
        assertThat(givenFrame.numberOfDownedPins()).isEqualTo(Score.from(10));

        assertThat(nextFrame.frameInfo()).isEqualTo(NormalFrameInfo.of(1, 0));
        assertThat(nextFrame.numberOfDownedPins()).isEqualTo(Score.from(5));
    }

    @DisplayName("Normal Frame은 9프레임까지 10프레임은 final Frame이다")
    @Test
    void NormalFrame() {
        // Given
        List<Frame> frames = new ArrayList<>();

        Frame normalFrame = NormalFrame.create();

        for (int i = 0; i < 8; i++) {
            frames.add(normalFrame);
            normalFrame.roll(10);
            normalFrame = normalFrame.nextRound().orElseThrow(IllegalArgumentException::new);
        }

        normalFrame.roll(5);
        Frame lastNormalFrame = normalFrame.nextRound().orElseThrow(IllegalArgumentException::new);
        frames.add(lastNormalFrame);
        lastNormalFrame.roll(5);
        Frame lastNormalFrameRound = lastNormalFrame.nextRound().orElseThrow(IllegalArgumentException::new);
        frames.add(lastNormalFrameRound);

        // When && Then
        assertThat(frames.size()).isEqualTo(10);
        assertThat(frames.get(0)).isInstanceOf(NormalFrame.class);
        assertThat(frames.get(1)).isInstanceOf(NormalFrame.class);
        assertThat(frames.get(frames.size() - 1)).isInstanceOf(FinalFrame.class);
    }

}
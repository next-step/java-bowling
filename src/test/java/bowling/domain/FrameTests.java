package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTests {
    @DisplayName("ThrowResults를 전달받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        int firstNumberOfHitPin = 3;
        int secondNumberOfHitPin = 3;

        Frame frame = new Frame(ThrowResults.of(firstNumberOfHitPin, secondNumberOfHitPin));
        assertThat(frame)
                .isEqualTo(new Frame(ThrowResults.of(firstNumberOfHitPin, secondNumberOfHitPin), null));
    }

    @DisplayName("현재 프레임에서 다음 프레임을 생성할 수 있다.")
    @Test
    void createNextTest() {
        int firstFrameFirstHit = 3;
        int firstFrameSecondHit = 3;
        int secondFrameFirstHit = 4;
        int secondFrameSecondHit = 4;

        Frame firstFrame = new Frame(ThrowResults.of(firstFrameFirstHit, firstFrameSecondHit));
        Frame secondFrame = firstFrame.next(secondFrameFirstHit, secondFrameSecondHit);
        assertThat(secondFrame)
                .isEqualTo(new Frame(ThrowResults.of(secondFrameFirstHit, secondFrameSecondHit), null));
        assertThat(firstFrame)
                .isEqualTo(new Frame(ThrowResults.of(firstFrameFirstHit, firstFrameSecondHit), secondFrame));
    }
}

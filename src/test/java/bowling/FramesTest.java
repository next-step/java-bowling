package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;

import static org.assertj.core.api.BDDAssertions.then;

class FramesTest {

    @ParameterizedTest
    @CsvSource(value = {"2", "3", "4"})
    @DisplayName("경기 종료 여부 검증")
    void isFinished(int totalFrames) {
        Frames frames = Frames.of(totalFrames);

        IntStream.range(0, totalFrames + 1).forEach(index -> {
            then(frames.isFinished()).isFalse();
            frames.bowl(Pin.ofMax());
            if (frames.hasNextFrameAndIsCurrentFrameFinished()) {
                frames.shiftCurrentFrame();
            }
        });

        then(frames.isFinished()).isTrue();
    }
}

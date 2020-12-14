package bowling.domain.frame;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import bowling.domain.FallingPinCount;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FramesTest {

    private Frames frames;

    @BeforeEach
    void setup() {
        frames = Frames.of(IntStream.of(1)
            .mapToObj(Frame::new)
            .collect(Collectors.toCollection(LinkedList::new)));
    }

    @DisplayName("게임상태 저장 테스트")
    @Test
    void pitchTheBall() {
        frames.saveSate(FallingPinCount.fromFallingCount(8));
        assertThat(frames.getCurrentFrameSequence()).isEqualTo(1);

        frames.saveSate(FallingPinCount.fromFallingCount(2));
        assertThat(frames.getCurrentFrameSequence()).isEqualTo(2);
    }
}
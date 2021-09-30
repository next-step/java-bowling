package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FramesTest {

    @Test
    @DisplayName("final frame까지 저장된 frame으로 Frames객체를 생성할 수 있다.")
    void createFramesByFirstFrameTest() {

        // given
        List<Frame> frames = new ArrayList<>();
        Frame frame = NormalFrame.createFirstFrame();
        Frame first = frame;
        frames.add(frame);
        for (int i=2; i<=10; i++){
            frame = frame.createNextFrame();
            frames.add(frame);
        }
        Frames expected = Frames.of(frames);

        // when
        Frames result = Frames.createFramesByFirstFrame(first);

        // then
        assertThat(result).isEqualTo(expected);
    }

}
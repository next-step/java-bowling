package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FramesTest {

    @Test
    @DisplayName("첫 Frame으로 연결된 Frames 생성 기능 구현")
    void createByFirstFrameTest() {

        // given
        Frame first = NormalFrame.createFirstFrame();
        Frame second = first.createNextFrame();
        Frames expected = Frames.of(Arrays.asList(first, second));

        // when
        Frames result = Frames.creatByFirstFrame(first);

        // then
        assertThat(result).isEqualTo(expected);
    }

}
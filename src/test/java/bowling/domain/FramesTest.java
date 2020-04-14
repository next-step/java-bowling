package bowling.domain;

import bowling.domain.exception.OutOfRangeArgumentException;
import bowling.domain.frame.Frame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = new Frames(2);
    }

    @DisplayName("지정된 사이즈만큼 생성한다.")
    @Test
    void init() {
        assertThat(frames.size()).isEqualTo(2);
    }

    @DisplayName("프레임 갯수는 최소 2 이상이여야 한다")
    @Test
    void minError() {
        assertThatExceptionOfType(OutOfRangeArgumentException.class)
                .isThrownBy(() -> new Frames(1));
    }

    @DisplayName("현재 프레임의 인덱스를 얻어온다.")
    @Test
    void index() {
        Frame first = frames.getFirstFrame();
        assertThat(frames.getCurrentFrameIndex(first.getNext())).isEqualTo(1);
    }
}

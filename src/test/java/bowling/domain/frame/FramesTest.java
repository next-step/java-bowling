package bowling.domain.frame;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FramesTest {
    public static final Frames INITIAL_FRAMES = Frames.initialize();

    @ParameterizedTest
    @NullAndEmptySource
    void Frames는_frames없이_생성_될_경우_예외를_발생_시킨다(List<Frame> frames) {
        assertThatThrownBy(() -> {
            new Frames(frames);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

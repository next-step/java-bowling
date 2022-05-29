package bowling.domain.frame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static bowling.domain.frame.FrameTest.LAST_DONE_FRAME;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FramesTest {
    public static final Frames INITIAL_FRAMES = Frames.initialize();

    @ParameterizedTest
    @NullAndEmptySource
    void Frames는_frames없이_생성_될_경우_예외를_발생_시킨다(List<Frame> frames) {
        assertThatThrownBy(() -> {
            new Frames(frames);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isDone은_게임_종료_여부를_반환한다() {
        assertAll(
                () -> assertFalse(INITIAL_FRAMES.isDone()),
                () -> assertTrue(new Frames(List.of(LAST_DONE_FRAME)).isDone())
        );
    }
}

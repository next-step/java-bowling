package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bowling.domain.frame.exception.FramesSizeException;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("프레임들")
class FramesTest {

    private static final Random RANDOM = new Random();

    private static final Supplier<Frame> FRAME_GENERATOR =
        () -> Frame.of(PitchResult.of(RANDOM.nextInt(10)));

    @DisplayName("[성공] 프레임 추가")
    @Test
    public void add() {
        // given
        final Frames frames = Frames.of();

        // when
        frames.add(FRAME_GENERATOR.get());

        // then
        assertThat(frames.size()).isEqualTo(1);
    }

    @DisplayName("[실패] 프레임 추가 - 유효하지 않은 프레임 사이즈")
    @Test
    public void add_invalidSize() {
        // given
        final int size = 10;
        final Frames frames = Frames.of();
        IntStream.range(0, size).forEach(i -> frames.add(FRAME_GENERATOR.get()));

        // when
        assertThrows(FramesSizeException.class, () -> frames.add(Frame.of(PitchResult.of(1))));

        // then
    }
}

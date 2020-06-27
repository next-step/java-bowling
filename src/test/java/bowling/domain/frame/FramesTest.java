package bowling.domain.frame;

import bowling.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {

    private Frames createFrame(String playerName) {
        return new Frames(new Player(playerName));
    }

    @ParameterizedTest
    @MethodSource("provideFrameSize")
    @DisplayName("frame 추가")
    void addFrame(int frameSize) {
        Frames frames = createFrame("WIJ");
        IntStream.range(0, frameSize)
                .forEach(index -> frames.createNextFrame());

        assertThat(frames.getFrameSize()).isEqualTo(frameSize);
    }

    private static Stream<Arguments> provideFrameSize() {
        return Stream.of(
                Arguments.of(5),
                Arguments.of(10),
                Arguments.of(9)
        );
    }

    @Test
    @DisplayName("마지막 frame에서 새로운 frame 추가 시 Exception")
    void addLastFrameException() {
        Frames frames = createFrame("WIJ");
        IntStream.range(0, 10)
                .forEach(index -> frames.createNextFrame());

        assertThatThrownBy(() -> frames.createNextFrame())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
package bowling.domain.frame;

import bowling.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {

    private Frames createFrame(String playerName) {
        return new Frames(new Player(playerName));
    }

    @ParameterizedTest
    @MethodSource("providePoint")
    @DisplayName("frame 추가")
    void addFrame(List<Integer> points, int frameSize) {
        Frames frames = createFrame("WIJ");
        for (Integer point : points) {
            frames.addPoint(point);
        }

        assertThat(frames.getFrameSize()).isEqualTo(frameSize);
    }

    private static Stream<Arguments> providePoint() {
        return Stream.of(
                Arguments.of(Arrays.asList(10,10,10,10,10), 5),
                Arguments.of(Arrays.asList(10,10,10,10,10,10,10,10,10,10), 10),
                Arguments.of(Arrays.asList(1, 8, 2, 6, 3, 7, 4, 5, 5, 5, 1, 3, 4, 6, 7, 3, 1, 2), 9)
        );
    }

    @Test
    @DisplayName("마지막 frame에서 새로운 frame 추가 시 Exception")
    void addLastFrameException() {
        Frames frames = createFrame("WIJ");
        for (int i = 0; i < 10; i++) {
            frames.addPoint(10);
        }

        assertThatThrownBy(() -> frames.addPoint(1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
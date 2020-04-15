package bowling.domain.frame;

import bowling.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = frames.of(new Player("LDC"));
    }

    @DisplayName("프레임 리스트 생성")
    @Test
    void create() {
        assertThatCode(() -> Frames.of(new Player("LDC")));
    }

    @DisplayName("프레임 추가")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void add(int size) {
        for (int i = 0; i < size; i++) {
            frames.add(DefaultFrame.first());
        }

        assertThat(frames.size()).isEqualTo(size);
    }


    @DisplayName("10 프레임일 경우 추가 불가능")
    @Test
    void addFailByFrameCount() {
        for (int i = 0; i < 10; i++) {
            frames.add(DefaultFrame.first());
        }

        assertThat(frames.isOver()).isTrue();
    }
}
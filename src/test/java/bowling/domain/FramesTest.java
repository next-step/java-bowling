package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.count.Gutter;
import bowling.domain.count.Miss;
import bowling.domain.count.Remain;
import bowling.domain.count.Spare;
import bowling.domain.count.Strike;

class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = new Frames(new User("PJS"), List.of(
                new Strike(1),
                new Remain(2,9), new Spare(2, 1),
                new Strike(3),
                new Strike(4),
                new Strike(5),
                new Gutter(6),
                new Remain(7, 7), new Miss(7, 2),
                new Strike(8),
                new Strike(9),
                new Strike(10), new Remain(10, 9), new Spare(10, 1)
        ));
    }

    @DisplayName("프레임 번호는 1부터 10까지 이다.")
    @Test
    void getFrameNumbers() {
        assertThat(new Frames(new User("PJS"), Collections.emptyList()).getFrameNumbers()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @DisplayName("첫 프레임부터 파라미터로 넘어온 프레임까지 목록을 리턴한다.")
    @ParameterizedTest
    @MethodSource("getPitches")
    void getPitches(Pitch lastFrame, List<Pitch> expected) {
        assertThat(frames.getPitches(lastFrame)).containsExactlyElementsOf(expected);
    }
    
    private static Stream getPitches() {
        return Stream.of(
                Arguments.arguments(new Strike(1), List.of(new Strike(1))),
                Arguments.arguments(new Remain(7, 7), List.of(
                        new Strike(1),
                        new Remain(2, 9), new Spare(2, 1),
                        new Strike(3),
                        new Strike(4),
                        new Strike(5),
                        new Gutter(6),
                        new Remain(7, 7)
                )),
                Arguments.arguments(new Spare(10, 1), List.of(
                        new Strike(1),
                        new Remain(2, 9), new Spare(2, 1),
                        new Strike(3),
                        new Strike(4),
                        new Strike(5),
                        new Gutter(6),
                        new Remain(7, 7), new Miss(7, 2),
                        new Strike(8),
                        new Strike(9),
                        new Strike(10), new Remain(10, 9), new Spare(10, 1)
                ))
        );
    }
}

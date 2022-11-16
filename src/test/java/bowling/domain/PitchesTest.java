package bowling.domain;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.count.Gutter;
import bowling.domain.count.Miss;
import bowling.domain.count.Remain;
import bowling.domain.count.Spare;
import bowling.domain.count.Strike;

class PitchesTest {
    @DisplayName("첫 투구부터 마지막 투구까지 목록을 리턴한다.")
    @ParameterizedTest
    @MethodSource("getPitches")
    void getPitches(List<Pitch> pitches, Pitch lastPitch, List<Pitch> expected) {
        assertThat(new Pitches(pitches).getPitches(lastPitch)).hasSameElementsAs(expected);
    }

    @DisplayName("투구 목록을 추가하면 기존 투구 목록 뒤에 추가한다.")
    @ParameterizedTest
    @MethodSource("add")
    void add(List<Pitch> pitches, List<Pitch> add) {
        List<Pitch> expected = Stream.concat(pitches.stream(), add.stream()).collect(toList());
        assertThat(new Pitches(pitches).add(add).getPitches()).hasSameElementsAs(expected);
    }
    
    private static Stream add() {
        return Stream.of(
                Arguments.arguments(
                        list(new Strike(1), new Remain(2, 5), new Spare(2, 5)),
                        list(new Gutter(3), new Miss(3, 5))
                )
        );
    }

    private static Stream getPitches() {
        return Stream.of(
                Arguments.arguments(
                        list(new Strike(1), new Remain(2, 5), new Spare(2, 5)),
                        new Remain(2, 5),
                        list(new Strike(1), new Remain(2, 5))
                ),
                Arguments.arguments(
                        list(new Gutter(1), new Miss(1, 1), new Remain(2, 7), new Spare(2, 3)),
                        new Gutter(1),
                        list(new Gutter(1))
                )
        );
    }
    
    private static List<Pitch> list(Pitch... pitches) {
        return Arrays.stream(pitches).collect(toList());
    }
}

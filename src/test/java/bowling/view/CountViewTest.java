package bowling.view;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.Pitch;
import bowling.domain.count.Gutter;
import bowling.domain.count.Remain;
import bowling.domain.count.Spare;
import bowling.domain.count.Strike;

class CountViewTest {
    @DisplayName("스트라이크이면 X, 거터이면 -, 스페어이면 / 를 리턴한다.")
    @ParameterizedTest
    @MethodSource("getCount")
    void getCount(Pitch frame, String expected) {
        assertThat(CountView.getCount(frame)).isEqualTo(expected);
    }
    
    private static Stream getCount() {
        return Stream.of(
                Arguments.arguments(new Strike(1), "X"),
                Arguments.arguments(new Gutter(2), "-"),
                Arguments.arguments(new Spare(5, 3), "/"),
                Arguments.arguments(new Remain(1, 4), "4")
        );
    }
}

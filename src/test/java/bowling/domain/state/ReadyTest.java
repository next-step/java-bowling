package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ReadyTest {
    private ThrowingState ready;

    private static Stream<Arguments> providePinsAndResultState() {
        List<Arguments> bowlCases = firstBowlCases();
        bowlCases.add(arguments(Pins.create(10), Strike.class));
        return bowlCases.stream();
    }

    private static List<Arguments> firstBowlCases() {
        return IntStream.rangeClosed(0, 9)
                .mapToObj(v -> arguments(Pins.create(v), FirstBowl.class))
                .collect(Collectors.toList());
    }

    @BeforeEach
    void beforeEach() {
        ready = Ready.create();
    }

    @DisplayName("한 프레임에서 투구 이전 상태 확인")
    @ParameterizedTest
    @MethodSource("providePinsAndResultState")
    void bowl(Pins pins, Class<ThrowingState> expectedChangedState) {
        // when & then
        assertThat(ready.bowl(pins)).isInstanceOf(expectedChangedState);
    }

    @DisplayName("한 프레임에서 투구 이전 종료 여부 확인")
    @Test
    void isEnd() {
        // when & then
        assertThat(ready.isEnd()).isFalse();
    }

    @DisplayName("한 프레임에서 투구 이전 상태 symbol 확인")
    @Test
    void symbol() {
        // when & then
        assertThat(ready.symbol()).isBlank();
    }

    @DisplayName("현재 Score 반환 확인")
    @Test
    void score() {
        // when & then
        assertThat(ready.score()).isEqualTo(Score.withReady());
    }

    @DisplayName("Ready 상태에서 Score 계산 반환 확인")
    @Test
    void scoreAfter() {
        // when & then
        assertThatIllegalStateException()
                .isThrownBy(() -> ready.scoreAfter(null));
    }
}

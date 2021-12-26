package bowling.domain;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ShotResultTest {
    public static Stream<Arguments> parseCreate() {
        return Stream.of(
                Arguments.of(0, ShotResult.GUTTER),
                Arguments.of(1, ShotResult.ONE),
                Arguments.of(10, ShotResult.STRIKE)
        );
    }

    @ParameterizedTest
    @MethodSource("parseCreate")
    public void create(int score, ShotResult expected) {
        assertThat(ShotResult.of(score)).isInstanceOf(ShotResult.class);
        assertThat(ShotResult.of(score)).isEqualTo(expected);
        assertThat(ShotResult.of(score).toInt()).isEqualTo(score);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    public void createFailed(int score) {
        assertThatIllegalArgumentException().isThrownBy(() -> ShotResult.of(score));
    }

    public static Stream<Arguments> parseDisplay() {
        return Stream.of(
                Arguments.of(0, "-"),
                Arguments.of(1, "1"),
                Arguments.of(10, "X")
        );
    }

    @ParameterizedTest
    @MethodSource("parseDisplay")
    public void display(int score, String expected) {
        assertThat(ShotResult.of(score).toString()).isEqualTo(expected);
    }
}

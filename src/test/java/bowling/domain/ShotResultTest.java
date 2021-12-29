package bowling.domain;

import java.util.stream.Stream;

import bowling.engine.Shot;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.domain.ShotResult.EIGHT;
import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResult.NINE;
import static bowling.domain.ShotResult.ONE;
import static bowling.domain.ShotResult.STRIKE;
import static bowling.domain.SpareShotResult.SPARE_NINE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ShotResultTest {
    public static Stream<Arguments> parseCreate() {
        return Stream.of(
                Arguments.of(0, GUTTER),
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

    public static Stream<Arguments> parseSpare() {
        return Stream.of(
                Arguments.of(GUTTER, STRIKE),
                Arguments.of(ONE, NINE)
        );
    }

    @ParameterizedTest
    @MethodSource("parseSpare")
    public void spare(Shot first, Shot secondShot) {
        assertThat(SpareShotResult.of(first, secondShot).toInt()).isEqualTo(secondShot.toInt());
        assertThat(SpareShotResult.of(first, secondShot).toString()).isEqualTo("/");
        assertThat(SpareShotResult.of(first, secondShot).isSpare()).isTrue();
        assertThat(SpareShotResult.of(first, secondShot)).isInstanceOf(SpareShotResult.class);
    }

    public static Stream<Arguments> parseNotSpare() {
        return Stream.of(
                Arguments.of(GUTTER, NINE),
                Arguments.of(ONE, EIGHT)
        );
    }

    @ParameterizedTest
    @MethodSource("parseNotSpare")
    public void notSpare(Shot first, Shot secondShot) {
        assertThat(SpareShotResult.of(first, secondShot).toInt()).isEqualTo(secondShot.toInt());
        assertThat(SpareShotResult.of(first, secondShot).isSpare()).isFalse();
        assertThat(SpareShotResult.of(first, secondShot)).isInstanceOf(ShotResult.class);
    }

    @Test
    public void notEquals() {
        assertThat(STRIKE.notEquals(GUTTER)).isTrue();
        assertThat(STRIKE.notEquals(STRIKE)).isFalse();
        assertThat(NINE.notEquals(SPARE_NINE)).isTrue();
    }
}

package bowling.domain;

import java.util.stream.Stream;

import bowling.engine.Shot;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ShotResultTest {
    public static Stream<Arguments> parseCreate() {
        return Stream.of(
                Arguments.of(0, GUTTER),
                Arguments.of(1, ONE),
                Arguments.of(10, STRIKE)
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

    public static Stream<Arguments> parseSpare() {
        return Stream.of(
                Arguments.of(GUTTER, STRIKE),
                Arguments.of(ONE, NINE)
        );
    }

    @ParameterizedTest
    @MethodSource("parseSpare")
    public void spare(Shot first, Shot secondShot) {
        assertThat(ShotResult.of(first, secondShot).toInt()).isEqualTo(secondShot.toInt());
        assertThat(ShotResult.of(first, secondShot).isSpare()).isTrue();
        assertThat(ShotResult.of(first, secondShot)).isInstanceOf(ShotResult.class);
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
        assertThat(ShotResult.of(first, secondShot).toInt()).isEqualTo(secondShot.toInt());
        assertThat(ShotResult.of(first, secondShot).isSpare()).isFalse();
        assertThat(ShotResult.of(first, secondShot)).isInstanceOf(ShotResult.class);
    }

    @Test
    public void notEquals() {
        assertThat(STRIKE.notEquals(GUTTER)).isTrue();
        assertThat(STRIKE.notEquals(STRIKE)).isFalse();
        assertThat(NINE.notEquals(SPARE_NINE)).isTrue();
    }

    public static final Shot ONE = ShotResult.of(1);
    @SuppressWarnings("unused")
    public static final Shot TWO = ShotResult.of(2);
    public static final Shot THREE = ShotResult.of(3);
    public static final Shot FOUR = ShotResult.of(4);
    public static final Shot FIVE = ShotResult.of(5);
    public static final Shot SIX = ShotResult.of(6);
    public static final Shot SEVEN = ShotResult.of(7);
    public static final Shot EIGHT = ShotResult.of(8);
    public static final Shot NINE = ShotResult.of(9);

    public static final Shot SPARE_NINE = ShotResult.of(ONE, NINE);
}

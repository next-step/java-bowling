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

    @ParameterizedTest(name = "check spare: {arguments}")
    @MethodSource("parseSpare")
    public void spare(Shot first, Shot second) {
        assertThat(ShotResult.of(first, second).toInt()).isEqualTo(second.toInt());
        assertThat(ShotResult.of(first, second).isSpare()).isTrue();
        assertThat(ShotResult.of(first, second)).isInstanceOf(ShotResult.class);

        assertThat(second.isSpareWith(first)).isTrue();
    }

    public static Stream<Arguments> parseNotSpare() {
        return Stream.of(
                Arguments.of(GUTTER, NINE),
                Arguments.of(ONE, EIGHT)
        );
    }

    @ParameterizedTest(name = "check not spare: {arguments}")
    @MethodSource("parseNotSpare")
    public void notSpare(Shot first, Shot second) {
        assertThat(ShotResult.of(first, second).toInt()).isEqualTo(second.toInt());
        assertThat(ShotResult.of(first, second).isSpare()).isFalse();
        assertThat(ShotResult.of(first, second)).isInstanceOf(ShotResult.class);

        assertThat(second.isSpareWith(first)).isFalse();
    }


    @Test
    public void isClear() {
        assertThat(STRIKE.isClear()).isTrue();
        assertThat(ShotResult.of(SEVEN, THREE).isClear()).isTrue();

        assertThat(ShotResult.of(SEVEN, TWO).isClear()).isFalse();
        assertThat(SEVEN.isClear()).isFalse();
    }


    @Test
    public void notEquals() {
        assertThat(STRIKE.notEquals(GUTTER)).isTrue();
        assertThat(STRIKE.notEquals(STRIKE)).isFalse();
        assertThat(NINE.notEquals(SPARE_NINE)).isTrue();
    }

    public static Stream<Arguments> parseAdd() {
        return Stream.of(
                Arguments.of(GUTTER, ONE, ONE),
                Arguments.of(ONE, ONE, TWO),
                Arguments.of(NINE, ONE, STRIKE)
        );
    }

    @ParameterizedTest(name = "add: {arguments}")
    @MethodSource("parseAdd")
    public void add(Shot first, Shot second, Shot expected) {
        assertThat(first.add(second)).isEqualTo(expected);
    }

    public static Stream<Arguments> parseAddFailed() {
        return Stream.of(
                Arguments.of(STRIKE, ONE),
                Arguments.of(NINE, TWO),
                Arguments.of(SEVEN, SIX)
        );
    }

    @ParameterizedTest(name = "add failed {arguments}")
    @MethodSource("parseAddFailed")
    public void addFailed(Shot first, Shot second) {
        assertThatIllegalArgumentException().isThrownBy(() -> first.add(second));
    }

    @Test
    public void score() {
        assertThat(GUTTER.score()).isEqualTo(BowlingScore.of(0));
        assertThat(STRIKE.score()).isEqualTo(BowlingScore.of(10));
    }

    @Test
    public void bonusScore() {
        assertThat(STRIKE.bonusScore().remain()).isTrue();
        assertThat(ShotResult.of(SEVEN, THREE).bonusScore().remain()).isTrue();

        assertThat(ShotResult.of(SEVEN, TWO).bonusScore().remain()).isFalse();
        assertThat(SEVEN.bonusScore().remain()).isFalse();
    }

    public static final Shot ONE = ShotResult.of(1);
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

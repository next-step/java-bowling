package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import bowling.engine.Shot;
import bowling.engine.Shots;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResultTest.EIGHT;
import static bowling.domain.ShotResultTest.ONE;
import static bowling.domain.ShotResultTest.THREE;
import static bowling.domain.ShotResultTest.FOUR;
import static bowling.domain.ShotResultTest.FIVE;
import static bowling.domain.ShotResultTest.SIX;
import static bowling.domain.ShotResultTest.SEVEN;
import static bowling.domain.ShotResultTest.NINE;
import static bowling.domain.ShotResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FrameShotsTest {
    public static Stream<Arguments> parseCreate() {
        return Stream.of(
                Arguments.of(List.of(STRIKE)),
                Arguments.of(List.of(GUTTER, STRIKE)),
                Arguments.of(List.of(NINE, ONE)),
                Arguments.of(List.of(ONE, SEVEN))
        );
    }

    @ParameterizedTest(name = "create: {arguments}")
    @MethodSource("parseCreate")
    public void create(List<Shot> shots) {
        assertThat(FrameShots.of(shots)).isInstanceOf(FrameShots.class);
    }

    public static Stream<Arguments> parseCreateFinal() {
        return Stream.of(
                Arguments.of(List.of(STRIKE, STRIKE, STRIKE)),
                Arguments.of(List.of(STRIKE, GUTTER, GUTTER)),
                Arguments.of(List.of(GUTTER, STRIKE, SIX))
        );
    }

    @ParameterizedTest(name = "create final: {arguments}")
    @MethodSource("parseCreateFinal")
    public void createFinal(List<Shot> shots) {
        assertThat(FrameShots.ofFinal(shots)).isInstanceOf(FrameShots.class);
    }

    public static Stream<Arguments> parseCreateFailed() {
        return Stream.of(
                Arguments.of(List.of(SIX, SEVEN)),
                Arguments.of(List.of(GUTTER, GUTTER, GUTTER)),
                Arguments.of(List.of(GUTTER, GUTTER, GUTTER, GUTTER))
        );
    }

    @ParameterizedTest(name = "create failed: {arguments}")
    @NullSource
    @MethodSource("parseCreateFailed")
    public void createFailed(List<Shot> shots) {
        assertThatIllegalArgumentException().isThrownBy(() -> FrameShots.of(shots));
    }

    public static Stream<Arguments> parseCreateFailedFinal() {
        return Stream.of(
                Arguments.of(List.of(SIX, SEVEN)),
                Arguments.of(List.of(STRIKE, SIX, SEVEN)),
                Arguments.of(List.of(GUTTER, GUTTER, GUTTER)),
                Arguments.of(List.of(GUTTER, GUTTER, GUTTER, GUTTER))
        );
    }

    @ParameterizedTest(name = "create failed final: {arguments}")
    @NullSource
    @MethodSource("parseCreateFailedFinal")
    public void createFailedFinal(List<Shot> shots) {
        assertThatIllegalArgumentException().isThrownBy(() -> FrameShots.ofFinal(shots));
    }

    @Test
    public void nextShot() {
        assertThat(ss(THREE).nextShot(FOUR).stream()).containsExactly(THREE, FOUR);
    }

    public static Stream<Arguments> parseNextFinal() {
        return Stream.of(
                Arguments.of(NINE, ONE, SEVEN)
        );
    }
    @ParameterizedTest(name = "next shot final: {arguments}")
    @MethodSource("parseNextFinal")
    public void nextShoFinal(Shot first, Shot second, Shot third) {
        assertThat(fss(first).nextShot(second).nextShot(third).stream().map(Shot::toInt))
                .containsExactly(first.toInt(), second.toInt(), third.toInt());
    }

    @ParameterizedTest(name = "next shot failed: {arguments}")
    @NullSource
    public void nextShotFailed(Shot shot) {
        assertThatIllegalArgumentException().isThrownBy(() -> ss().nextShot(shot));
    }

    static Stream<Arguments> parseClear() {
        return Stream.of(
                Arguments.of(List.of(GUTTER, STRIKE)),
                Arguments.of(List.of(ONE, NINE)),
                Arguments.of(List.of(SIX, FOUR))
        );
    }

    @ParameterizedTest(name = "clear: {arguments}")
    @MethodSource("parseClear")
    public void isClear(List<Shot> shots) {
        assertThat(ss(shots).isClear()).isTrue();
    }

    static Stream<Arguments> parseNotClear() {
        return Stream.of(
                Arguments.of(List.of(GUTTER, GUTTER)),
                Arguments.of(List.of(GUTTER, NINE)),
                Arguments.of(List.of(FIVE, FOUR))
        );
    }

    @ParameterizedTest(name = "not clear: {arguments}")
    @MethodSource("parseNotClear")
    public void isNotClear(List<Shot> shots) {
        assertThat(ss(shots).isClear()).isFalse();
    }

    @Test
    public void isClearByStrike() {
        assertThat(ss(STRIKE).isClear()).isTrue();
    }

    static Stream<Arguments> parseScore() {
        return Stream.of(
                Arguments.of(List.of(GUTTER, GUTTER), 0),
                Arguments.of(List.of(GUTTER, NINE), 9),
                Arguments.of(List.of(FIVE, FOUR), 9),
                Arguments.of(List.of(SIX, FOUR), 10),
                Arguments.of(List.of(STRIKE), 10)
        );
    }

    @ParameterizedTest(name = "score: {arguments}")
    @MethodSource("parseScore")
    public void score(List<Shot> shots, int expected) {
        assertThat(ss(shots).score()).isEqualTo(FrameScore.of(expected));
    }

    static Stream<Arguments> parseClearBonus() {
        return Stream.of(
                Arguments.of(ONE, NINE),
                Arguments.of(GUTTER, STRIKE),
                Arguments.of(SIX, FOUR)
        );
    }

    @ParameterizedTest(name = "clear bonus: {arguments}")
    @MethodSource("parseClearBonus")
    public void clearBonus(Shot first, Shot second) {
        assertThat(ss(first).nextShot(second).clearBonus().remain()).isTrue();
    }

    @Test
    public void clearBonusByStrike() {
        assertThat(ss(STRIKE).clearBonus().remain()).isTrue();
    }

    static Stream<Arguments> parseNoBonus() {
        return Stream.of(
                Arguments.of(ONE, EIGHT),
                Arguments.of(GUTTER, NINE),
                Arguments.of(SIX, THREE)
        );
    }

    @ParameterizedTest(name = "no bonus: {arguments}")
    @MethodSource("parseNoBonus")
    public void noBonus(Shot first, Shot second) {
        assertThat(ss(first).nextShot(second).clearBonus().remain()).isFalse();
    }

    public static Shots ss(Shot ... shots) {
        return FrameShots.of(Arrays.asList(shots));
    }

    public static Shots fss(Shot ... shots) {
        return FrameShots.ofFinal(Arrays.asList(shots));
    }

    public static Shots ss(List<Shot> shots) {
        return FrameShots.of(shots);
    }
}
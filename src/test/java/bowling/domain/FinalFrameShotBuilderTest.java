package bowling.domain;

import java.util.List;
import java.util.stream.Stream;

import bowling.engine.Shot;
import bowling.engine.ShotsBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResult.STRIKE;
import static bowling.domain.ShotResultTest.SEVEN;
import static bowling.domain.ShotResultTest.SIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FinalFrameShotBuilderTest {
    public static Stream<Arguments> parseValidShots() {
        return Stream.of(
                Arguments.of(List.of(STRIKE, STRIKE, STRIKE)),
                Arguments.of(List.of(STRIKE, GUTTER, GUTTER)),
                Arguments.of(List.of(GUTTER, STRIKE, SIX))
        );
    }

    public static Stream<Arguments> parseInvalidShots() {
        return Stream.of(
                Arguments.of(List.of(SIX, SEVEN)),
                Arguments.of(List.of(STRIKE, SIX, SEVEN)),
                Arguments.of(List.of(GUTTER, GUTTER, GUTTER)),
                Arguments.of(List.of(GUTTER, GUTTER, GUTTER, GUTTER))
        );
    }

    @ParameterizedTest(name = "create: {arguments}")
    @MethodSource("parseValidShots")
    public void create(List<Shot> shots) {
        assertThat(FinalFrameShotsBuilder.of(shots)).isInstanceOf(FinalFrameShotsBuilder.class);
    }

    @ParameterizedTest(name = "create failed: {arguments}")
    @NullSource
    public void createFailed(List<Shot> shots) {
        assertThatIllegalArgumentException().isThrownBy(() -> FinalFrameShotsBuilder.of(shots));
    }

    @ParameterizedTest(name = "build: {arguments}")
    @MethodSource("parseValidShots")
    public void build(List<Shot> shots) {
        final ShotsBuilder builder = FinalFrameShotsBuilder.of(shots);
        assertThat(builder.build()).isInstanceOf(FrameShots.class);
    }

    @ParameterizedTest(name = "build failed: {arguments}")
    @MethodSource("parseInvalidShots")
    public void buildFailed(List<Shot> shots) {
        final ShotsBuilder builder = FinalFrameShotsBuilder.of(shots);
        assertThatIllegalArgumentException().isThrownBy(builder::build);
    }

    @ParameterizedTest(name = "build: {arguments}")
    @MethodSource("parseValidShots")
    public void validate(List<Shot> shots) {
        final ShotsBuilder builder = FinalFrameShotsBuilder.of(shots);
        assertThat(builder.validate()).isTrue();
    }

    @ParameterizedTest(name = "build failed: {arguments}")
    @MethodSource("parseInvalidShots")
    public void invalidate(List<Shot> shots) {
        final ShotsBuilder builder = FinalFrameShotsBuilder.of(shots);
        assertThatIllegalArgumentException().isThrownBy(builder::validate);
    }

}

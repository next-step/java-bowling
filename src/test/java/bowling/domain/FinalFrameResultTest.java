package bowling.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import bowling.engine.Result;
import bowling.engine.Shot;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResultTest.FIVE;
import static bowling.domain.ShotResultTest.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameResultTest {


    public static Stream<Arguments> parseNotForThird() {
        return Stream.of(
                Arguments.of(rf(GUTTER, GUTTER)),
                Arguments.of(rf(FOUR, FIVE))
        );
    }

    @ParameterizedTest(name = "has not third chance: {arguments}")
    @MethodSource("parseNotForThird")
    public void hasNotThirdChance(Result afterSecond) {
        assertThat(afterSecond.hasThirdChance()).isFalse();
    }

    public static Result rf(Shot... shots) {
        return FinalFrameResult.ofFinal(Arrays.asList(shots), Collections.emptyList());
    }

}

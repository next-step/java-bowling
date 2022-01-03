package bowling.domain.result;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import bowling.engine.Result;
import bowling.engine.Shot;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static bowling.domain.shot.ShotResult.GUTTER;
import static bowling.domain.shot.ShotResultTest.FIVE;
import static bowling.domain.shot.ShotResultTest.FOUR;
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
    public void hasNotThirdChance(FinalFrameResult afterSecond) {
        assertThat(afterSecond.hasThirdChance()).isFalse();
    }

    public static Result rf(Shot... shots) {
        return FinalFrameResult.ofFinal(Arrays.asList(shots), Collections.emptyList());
    }

}

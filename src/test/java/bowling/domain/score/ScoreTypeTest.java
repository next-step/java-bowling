package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static bowling.domain.score.ScoreType.GUTTER;
import static bowling.domain.score.ScoreType.SPARE;
import static bowling.domain.score.ScoreType.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("스코어 타입 테스트")
public class ScoreTypeTest {
    private static Stream<Arguments> getTypes() {
        return Stream.of(Arguments.arguments(STRIKE, "X"),
                Arguments.arguments(SPARE, "/"),
                Arguments.arguments(GUTTER, "-"));
    }

    @DisplayName("표현 테스트")
    @ParameterizedTest
    @MethodSource("getTypes")
    public void getMessage(ScoreType type, String expression) {
        assertThat(type.getExpression()).isEqualTo(expression);
    }

}
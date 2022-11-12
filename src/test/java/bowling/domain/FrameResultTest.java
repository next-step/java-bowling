package bowling.domain;

import bowling.domain.FrameResult;
import bowling.domain.Scores;
import bowling.exception.BowlingGameException;
import bowling.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameResultTest {
    public static Stream<Arguments> provideScoresAndResult() {
        return Stream.of(
                Arguments.of(new Scores(10), FrameResult.STRIKE),
                Arguments.of(new Scores(9, 1), FrameResult.SPARE),
                Arguments.of(new Scores(5, 2), FrameResult.MISS),
                Arguments.of(new Scores(0, 0), FrameResult.GUTTER)
        );
    }

    @ParameterizedTest
    @MethodSource("provideScoresAndResult")
    void 프레임_정상_결과(Scores scores, FrameResult result) {
        assertThat(FrameResult.match(scores)).isEqualTo(result);
    }

    @Test
    void 프레임_잘못된_결과() {
        assertThatThrownBy(() -> FrameResult.match(new Scores(1, 2, 3)))
                .isInstanceOf(BowlingGameException.class)
                .hasMessage(ErrorMessage.FRAME_RESULT_NOT_EXIST.getMessage());
    }
}

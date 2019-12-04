package dto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import score.ScoreInfo;
import score.Status;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FrontConverterTest {

    private static final Stream<Arguments> getScoreInfo() {
        return Stream.of(
                Arguments.of(new ScoreInfo(10, Status.STRIKE), "|X"),
                Arguments.of(new ScoreInfo(5, Status.SPARE), "|/"),
                Arguments.of(new ScoreInfo(3, Status.MISS), "|3"),
                Arguments.of(new ScoreInfo(0, Status.GUTTER), "|-"),
                Arguments.of(null, "")
        );
    }

    @ParameterizedTest
    @MethodSource("getScoreInfo")
    void convert(ScoreInfo scoreInfo, String answer) {
        String convert = FrontConverter.convert(scoreInfo);

        assertThat(convert).isEqualTo(answer);
    }
}
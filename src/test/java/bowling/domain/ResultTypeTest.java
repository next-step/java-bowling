package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ResultType 로직 테스트")
class ResultTypeTest {

    @Test
    public void test_stlike() {
        ResultType resultType = ResultType.of(true, 10, 0);
        assertThat(resultType).isEqualTo(ResultType.STRIKE);
    }

    @Test
    public void test_spare() {
        ResultType resultType = ResultType.of(false, 5, 0);
        assertThat(resultType).isEqualTo(ResultType.SPARE);
    }

    @ParameterizedTest
    @MethodSource("source_test_remain")
    public void test_remain(int remain, ResultType expected) {
        ResultType resultType = ResultType.of(false, remain, 1);
        assertThat(resultType).isEqualTo(expected);
    }

    public static Stream<Arguments> source_test_remain() {
        return Stream.of(
                Arguments.of(0, ResultType.GUTTER),
                Arguments.of(1, ResultType.ONE),
                Arguments.of(2, ResultType.TWO),
                Arguments.of(3, ResultType.THREE),
                Arguments.of(4, ResultType.FOUR),
                Arguments.of(5, ResultType.FIVE),
                Arguments.of(6, ResultType.SIX),
                Arguments.of(8, ResultType.EIGHT),
                Arguments.of(9, ResultType.NINE)
        );
    }
}

package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ResultType 로직 테스트")
class ShotTest {

    @Test
    public void 첫번째_투구로_10점_획득은_스트라이크() {
        Shot shot = Shot.of(true, 10, 0);
        assertThat(shot).isEqualTo(Shot.STRIKE);
    }

    @Test
    public void 두번째_투구로_10점_획득은_스페어() {
        Shot shot = Shot.of(false, 5, 0);
        assertThat(shot).isEqualTo(Shot.SPARE);
    }

    @ParameterizedTest
    @MethodSource("source_miss")
    public void 두번째_투구로_10점미만_획득은_미스(int remain, Shot expected) {
        Shot shot = Shot.of(false, remain, 1);
        assertThat(shot).isEqualTo(expected);
    }

    public static Stream<Arguments> source_miss() {
        return Stream.of(
                Arguments.of(0, Shot.GUTTER),
                Arguments.of(1, Shot.ONE),
                Arguments.of(2, Shot.TWO),
                Arguments.of(3, Shot.THREE),
                Arguments.of(4, Shot.FOUR),
                Arguments.of(5, Shot.FIVE),
                Arguments.of(6, Shot.SIX),
                Arguments.of(8, Shot.EIGHT),
                Arguments.of(9, Shot.NINE)
        );
    }
}

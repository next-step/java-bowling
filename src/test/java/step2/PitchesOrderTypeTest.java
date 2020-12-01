package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step2.domain.BowlingPoints;
import step2.type.PitchesOrderType;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static step2.type.PitchesOrderType.*;

public class PitchesOrderTypeTest {

    @DisplayName("getType 테스트")
    @ParameterizedTest
    @MethodSource("providePitchesOrderTypeInfo")
    void getType(int input, PitchesOrderType type) {
        assertThat(PitchesOrderType.getType(input)).isEqualTo(type);
    }

    private static Stream<Arguments> providePitchesOrderTypeInfo() {
        return Stream.of(
                Arguments.of(0, NONE),
                Arguments.of(1, FIRST),
                Arguments.of(2, SECOND),
                Arguments.of(3, THIRD)
        );
    }

    @DisplayName("currentType테스트")
    @Test
    void currentType() {
        BowlingPoints points = BowlingPoints.of(3);

        assertThat(PitchesOrderType.currentType(points)).isEqualTo(NONE);

        points.push(5);
        assertThat(PitchesOrderType.currentType(points)).isEqualTo(FIRST);

        points.push(5);
        assertThat(PitchesOrderType.currentType(points)).isEqualTo(SECOND);

        points.push(5);
        assertThat(PitchesOrderType.currentType(points)).isEqualTo(THIRD);
    }

    @DisplayName("nextType 테스트")
    @Test
    void nextType() {
        BowlingPoints points = BowlingPoints.of(3);

        assertThat(PitchesOrderType.nextType(points)).isEqualTo(FIRST);

        points.push(5);
        assertThat(PitchesOrderType.nextType(points)).isEqualTo(SECOND);

        points.push(5);
        assertThat(PitchesOrderType.nextType(points)).isEqualTo(THIRD);

        points.push(5);
        assertThatIllegalArgumentException()
                .isThrownBy(()-> PitchesOrderType.nextType(points))
                .withMessage(PitchesOrderType.ERROR_NO_SUCH_MATCH_TYPE);
    }

}

package bowling.view.state;

import bowling.domain.state.*;
import bowling.dto.StateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static bowling.domain.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상태에 따라 특정 포멧으로 출력하기 위한 enum 테스트")
class StateFormatTest {

    @DisplayName("상태에 따라 그에 맞는 문자열로 변환한다")
    @MethodSource
    @ParameterizedTest
    void convert(State state, String expectedString) {
        assertThat(StateFormat.convert(StateDto.from(state))).isEqualTo(expectedString);
    }

    private static Stream<Arguments> convert() {
        return Stream.of(
                Arguments.of(Preparation.instance(), ""),
                Arguments.of(InProgress.from(DOWNED_PINS_5), "5"),
                Arguments.of(InProgress.from(DOWNED_PINS_0), "-"),
                Arguments.of(Miss.of(DOWNED_PINS_5, DOWNED_PINS_2), "5|2"),
                Arguments.of(Miss.of(DOWNED_PINS_5, DOWNED_PINS_0), "5|-"),
                Arguments.of(Spare.from(DOWNED_PINS_5), "5|/"),
                Arguments.of(Strike.instance(), "X")
        );
    }


}

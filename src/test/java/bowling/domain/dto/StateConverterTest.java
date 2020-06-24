package bowling.domain.dto;

import bowling.fixture.StateDtoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StateConverterTest {

    @DisplayName("특정 상태값을 알맞는 출력값으로 변환")
    @ParameterizedTest
    @MethodSource
    void getDesc(final StateDto stateDto, final String expected) {
        assertThat(StateConverter.convertToDesc(stateDto))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> getDesc() {
        return Stream.of(
                Arguments.of(StateDtoFixture.getReadyState(), ""),
                Arguments.of(StateDtoFixture.getFirstHitState(), "9"),
                Arguments.of(StateDtoFixture.getStrikeState(), "X"),
                Arguments.of(StateDtoFixture.getSpareState(), "9|/"),
                Arguments.of(StateDtoFixture.getMissState(), "8|1")
        );
    }
}

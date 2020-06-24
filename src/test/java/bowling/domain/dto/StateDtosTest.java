package bowling.domain.dto;

import bowling.domain.state.State;
import bowling.domain.state.StateExpression;
import bowling.fixture.StateDtosFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class StateDtosTest {

    @DisplayName("StateDtos 생성 실패: null")
    @ParameterizedTest
    @NullSource
    void createFailure(final List<State> states) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> StateDtos.of(states));
    }

    @DisplayName("각 프레임의 모든 상태값을 출력값으로 변환하여 반환")
    @ParameterizedTest
    @MethodSource
    void getDesc(final StateDtos stateDtos, final String expected) {
        assertThat(stateDtos.getSymbol())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> getDesc() {
        return Stream.of(
                Arguments.of(StateDtosFixture.getReadyState(), StateExpression.READY),
                Arguments.of(StateDtosFixture.getFirstHitState(), "9"),
                Arguments.of(StateDtosFixture.getStrikeState(), StateExpression.STRIKE),
                Arguments.of(StateDtosFixture.getSpareState(), "9|/"),
                Arguments.of(StateDtosFixture.getMissState(), "8|1"),
                Arguments.of(StateDtosFixture.getThreeStrikeFinalFrame(), "X|X|X"),
                Arguments.of(StateDtosFixture.getStrikeMissFinalFrame(), "X|8|1"),
                Arguments.of(StateDtosFixture.getSpareStrikeFinalFrame(), "9|/|X"),
                Arguments.of(StateDtosFixture.getSpareHitFinalFrame(), "9|/|9")
        );
    }
}

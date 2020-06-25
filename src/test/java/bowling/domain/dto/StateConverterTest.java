package bowling.domain.dto;

import bowling.domain.pin.Pins;
import bowling.domain.state.StateExpression;
import bowling.domain.state.finish.Finished;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.domain.state.running.FirstHit;
import bowling.fixture.StateDtoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class StateConverterTest {

    @DisplayName("convert 실패: null 값")
    @ParameterizedTest
    @NullSource
    void failureConvertToSymbolByNull(final StateDto stateDto) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> StateConverter.convertToSymbol(stateDto));
    }

    @DisplayName("convert 실패: 존재하지 않는 상태값")
    @Test
    void failureConvertToSymbolByNotExistState() {
        StateDto stateDto = StateDto.of(new TestState());
        assertThatIllegalArgumentException()
                .isThrownBy(() -> StateConverter.convertToSymbol(stateDto));
    }

    private static class TestState extends Finished {
        TestState() {
        }

        @Override
        public Pins getFirstPins() {
            return null;
        }
    }

    @DisplayName("특정 상태값을 알맞는 출력값으로 변환")
    @ParameterizedTest
    @MethodSource
    void getSymbol(final StateDto stateDto, final String expected) {
        assertThat(StateConverter.convertToSymbol(stateDto))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> getSymbol() {
        return Stream.of(
                Arguments.of(StateDtoFixture.getReadyState(), StateExpression.READY),
                Arguments.of(StateDtoFixture.getFirstHitState(), "9"),
                Arguments.of(StateDtoFixture.getStrikeState(), StateExpression.STRIKE),
                Arguments.of(StateDtoFixture.getSpareState(), "9|/"),
                Arguments.of(StateDtoFixture.getMissState(), "8|1"),

                Arguments.of(StateDto.of(Miss.of(Pins.of(9), Pins.of(0))), "9|-"),
                Arguments.of(StateDto.of(Miss.of(Pins.of(0), Pins.of(0))), "-|-"),
                Arguments.of(StateDto.of(Miss.of(Pins.of(2), Pins.of(3))), "2|3"),

                Arguments.of(StateDto.of(Spare.of(Pins.of(9), Pins.of(1))), "9|/"),
                Arguments.of(StateDto.of(Spare.of(Pins.of(2), Pins.of(8))), "2|/"),
                Arguments.of(StateDto.of(Spare.of(Pins.of(0), Pins.of(10))), "-|/"),

                Arguments.of(StateDto.of(FirstHit.of(Pins.of(0))), StateExpression.GUTTER),
                Arguments.of(StateDto.of(FirstHit.of(Pins.of(2))), "2")
        );
    }
}

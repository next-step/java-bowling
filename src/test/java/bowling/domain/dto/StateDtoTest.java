package bowling.domain.dto;

import bowling.domain.state.State;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.running.FirstHit;
import bowling.domain.state.running.Ready;
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

public class StateDtoTest {

    @DisplayName("StateDto 생성 실패: null")
    @ParameterizedTest
    @NullSource
    void createFailure(final State state) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> StateDto.of(state));
    }

    @DisplayName("상태값의 클래스 타입을 반환")
    @ParameterizedTest
    @MethodSource
    void getStateClassType(final StateDto stateDto, final Class<? extends State> expected) {
        assertThat(stateDto.getStateClassType())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> getStateClassType() {
        return Stream.of(
                Arguments.of(StateDtoFixture.getReadyState(), Ready.class),
                Arguments.of(StateDtoFixture.getFirstHitState(), FirstHit.class),
                Arguments.of(StateDtoFixture.getStrikeState(), Strike.class),
                Arguments.of(StateDtoFixture.getSpareState(), Spare.class),
                Arguments.of(StateDtoFixture.getMissState(), Miss.class)
        );
    }

    @DisplayName("상태값의 타입이 Ready 인지 여부를 확인")
    @Test
    void isReady() {
        assertThat(StateDto.of(Ready.getInstance()).isReady())
                .isTrue();
    }

    @DisplayName("첫 번째 볼링핀의 출력값을 반환")
    @ParameterizedTest
    @MethodSource
    void getFirstPins(final StateDto stateDto, final int expected) {
        assertThat(stateDto.getFirstPinCount())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> getFirstPins() {
        return Stream.of(
                Arguments.of(StateDtoFixture.getFirstHitState(), 9),
                Arguments.of(StateDtoFixture.getSpareState(), 9),
                Arguments.of(StateDtoFixture.getMissState(), 8)
        );
    }

    @DisplayName("두 번째 볼링핀의 출력값을 반환")
    @ParameterizedTest
    @MethodSource
    void getSecondPins(final StateDto stateDto, final int expected) {
        assertThat(stateDto.getSecondPinCount())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> getSecondPins() {
        return Stream.of(
                Arguments.of(StateDtoFixture.getSpareState(), 1),
                Arguments.of(StateDtoFixture.getMissState(), 1)
        );
    }

    @DisplayName("첫 번째 볼링핀이 없는 경우 예외 반환")
    @Test
    void exceptionGetFirstPins() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> StateDtoFixture.getReadyState().getFirstPinCount());
    }

    @DisplayName("두 번째 볼링핀이 없는 경우 예외 반환")
    @ParameterizedTest
    @MethodSource
    void exceptionGetSecondPins(final StateDto stateDto) {
        assertThatIllegalArgumentException()
                .isThrownBy(stateDto::getSecondPinCount);
    }

    private static Stream<Arguments> exceptionGetSecondPins() {
        return Stream.of(
                Arguments.of(StateDtoFixture.getReadyState()),
                Arguments.of(StateDtoFixture.getStrikeState()),
                Arguments.of(StateDtoFixture.getFirstHitState())
        );
    }
}

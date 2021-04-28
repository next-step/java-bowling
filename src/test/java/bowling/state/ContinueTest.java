package bowling.state;

import bowling.domain.Pins;
import bowling.domain.exception.PinsCountException;
import bowling.domain.state.Continue;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ContinueTest {
    private State continueState;

    @BeforeEach
    void setUp() {
        Pins pins = Pins.ofFirstPitch(7);
        continueState = Continue.of(pins);
    }

    @Test
    @DisplayName("턴이 끝났는지 확인 테스트")
    void isFinishedTest() {
        assertThat(continueState.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Miss 상태로 변경 확인 테스트")
    void bowlToMissTest() {
        Pins firstPins = Pins.ofFirstPitch(7);
        Pins secondPins = Pins.ofFirstPitch(2);
        assertThat(continueState.stateAfterBowling(2)).isEqualTo(Miss.of(firstPins, secondPins));
    }

    @Test
    @DisplayName("Spare 상태로 변경 확인 테스트")
    void bowlToSpareTest() {
        Pins firstPins = Pins.ofFirstPitch(7);
        Pins secondPins = Pins.ofFirstPitch(3);
        assertThat(continueState.stateAfterBowling(3)).isEqualTo(Spare.of(firstPins, secondPins));
    }

    @ParameterizedTest(name = "넘어뜨린 핀 개수 예외 테스트")
    @ValueSource(ints = {5,7,-1,11})
    void pinsCountExceptionTest(int secondPitch) {
        assertThatThrownBy(() -> continueState.stateAfterBowling(secondPitch))
                .isInstanceOf(PinsCountException.class)
                .hasMessage("넘어뜨린 핀수는 0에서 10 사이의 정수여야 합니다.");
    }
}

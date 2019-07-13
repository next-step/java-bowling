package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalStateTest {

    private FinalState finalState;

    @BeforeEach
    void setUp() {
        finalState = (FinalState) FinalState.valueOf();
    }

    @DisplayName("첫번째 볼이 스트라이크일 시 게임을 계속 진행한다")
    @Test
    void bowl_pinsFirstTen_strike() {
        // given
        Pins first = Pins.DOWN_ALL;

        // when
        finalState.bowl(first);

        // then
        assertThat(finalState.isFinished()).isFalse();
        assertThat(finalState.getStates()).hasOnlyElementsOfType(Strike.class);
    }

    @DisplayName("첫번째, 두번째 볼이 스트라이크일 시 게임을 계속 진행한다")
    @Test
    void bowl_pinsFirstTenSecondTen_twoStrike() {
        // given
        Pins first = Pins.DOWN_ALL;
        Pins second = Pins.DOWN_ALL;

        // when
        finalState.bowl(first);
        finalState.bowl(second);

        // then
        assertThat(finalState.isFinished()).isFalse();
        assertThat(finalState.getStates()).hasOnlyElementsOfType(Strike.class);
    }

    @DisplayName("스트라이크, 스트라이크, 스트라이크 일 시 게임을 종료한다")
    @Test
    void bowl_pinsTenAndTenAndTen_thanGameOver() {
        // given
        Pins first = Pins.DOWN_ALL;
        Pins second = Pins.DOWN_ALL;
        Pins third = Pins.DOWN_ALL;

        // when
        finalState.bowl(first);
        finalState.bowl(second);
        finalState.bowl(third);

        // then
        assertThat(finalState.isFinished()).isTrue();
        assertThat(finalState.getStates()).hasOnlyElementsOfType(Strike.class);
    }

    @DisplayName("스페어, 스트라이크 일 시 게임을 종료한다")
    @Test
    void bowl_pinsZeroAndTenAndTen_thanGameOver() {
        // given
        Pins first = Pins.DOWN_ZERO;
        Pins second = Pins.DOWN_ALL;
        Pins third = Pins.DOWN_ALL;

        // when
        finalState.bowl(first);
        finalState.bowl(second);
        finalState.bowl(third);

        // then
        assertThat(finalState.isFinished()).isTrue();
        assertThat(finalState.getStates()).hasOnlyElementsOfTypes(Spare.class, Strike.class);
    }

    @DisplayName("스트라이크, 스페어 일 시 게임을 종료한다")
    @Test
    void bowl_pinsTenAndZeroAndTen_thanGameOver() {
        // given
        Pins first = Pins.DOWN_ALL;
        Pins second = Pins.DOWN_ZERO;
        Pins third = Pins.DOWN_ALL;

        // when
        finalState.bowl(first);
        finalState.bowl(second);
        finalState.bowl(third);

        // then
        assertThat(finalState.isFinished()).isTrue();
        assertThat(finalState.getStates()).hasOnlyElementsOfTypes(Spare.class, Strike.class);
    }
}
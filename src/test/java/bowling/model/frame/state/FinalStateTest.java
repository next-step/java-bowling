package bowling.model.frame.state;

import bowling.model.DownPin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static bowling.model.DownPin.*;
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
        DownPin first = DOWN_ALL;

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
        DownPin strike = DOWN_ALL;

        // when
        finalState.bowl(strike);
        finalState.bowl(strike);

        // then
        assertThat(finalState.isFinished()).isFalse();
        assertThat(finalState.getScore()).extracting(Score::getScore).isEqualTo(20);
        assertThat(finalState.getStates()).hasOnlyElementsOfType(Strike.class);
    }

    @DisplayName("스트라이크, 스트라이크, 스트라이크 일 시 게임을 종료한다")
    @Test
    void bowl_pinsTenAndTenAndTen_thanGameOver() {
        // given
        DownPin strike = DOWN_ALL;

        // when
        finalState.bowl(strike);
        finalState.bowl(strike);
        finalState.bowl(strike);

        // then
        assertThat(finalState.isFinished()).isTrue();
        assertThat(finalState.getScore()).extracting(Score::getScore).isEqualTo(30);
        assertThat(finalState.getStates()).hasOnlyElementsOfType(Strike.class);
    }

    @DisplayName("스페어, 스트라이크 일 시 게임을 종료한다")
    @ParameterizedTest
    @CsvSource(value = {
             MAX + "," + MIN,
             MIN + "," + MAX
    })
    void bowl_pinsZeroAndTenAndTen_thanGameOver(int first, int second) {
        // when
        finalState.bowl(DownPin.valueOf(first));
        finalState.bowl(DownPin.valueOf(second));
        finalState.bowl(DOWN_ALL);

        // then
        assertThat(finalState.isFinished()).isTrue();
        assertThat(finalState.getScore()).extracting(Score::getScore).isEqualTo(20);
        assertThat(finalState.getStates()).hasOnlyElementsOfTypes(Spare.class, Strike.class);
    }
}
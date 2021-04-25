package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RoundTest {

    @DisplayName("첫 라운드 생성")
    @Test
    void initFirstRoundTest() {
        Round round = Round.from(1);
        assertThat(Round.firstRound()).isEqualTo(round);
    }

    @DisplayName("마지막 라운드 생성")
    @Test
    void initFinalRoundTest() {
        Round round = Round.from(10);
        assertThat(Round.finalRound()).isEqualTo(round);
    }

    @DisplayName("10 라운드 이상은 예외를 발생시킨다")
    @Test
    void initRoundExceptionTest() {
        assertThatThrownBy(() -> Round.from(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("다음 라운드를 생성한다")
    @Test
    void nextRoundTest() {
        Round round = Round.from(1);
        assertThat(Round.from(2)).isEqualTo(round.nextRound());
    }

    @DisplayName("마지막 라운드인지 판별한다")
    @Test
    void isFinalRoundTest() {
        Round round = Round.finalRound();
        assertThat(round.isFinalRound()).isTrue();
    }
}

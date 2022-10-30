package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BowlingRoundTest {

    @ParameterizedTest
    @CsvSource({"1,1,true", "2,2,true", "1,2,false"})
    void shouldReturnTrueIfSameRound(int startRoundNumber, int targetRoundNumber, boolean expectedResult) {
        BowlingRound round = new BowlingRound(startRoundNumber);

        assertThat(round.isSameRound(new BowlingRound(targetRoundNumber))).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({"0,-1,11"})
    void shouldValidateBallingRound(int roundNumber) {
        assertThatThrownBy(() -> new BowlingRound(roundNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({"1,2,true", "2,3,true", "2,2,false"})
    void shouldMoveRound(int startRoundNumber, int targetRoundNumber, boolean expectedResult) {
        BowlingRound round = new BowlingRound(startRoundNumber);

        assertThat(round.next().isSameRound(new BowlingRound(targetRoundNumber))).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({"1,10,true", "2,10,true", "2,5,false"})
    void shouldAddPinsAndReturnWhetherNextRoundOrNot(int startRoundNumber, int pinNumber, boolean expectedResult) {
        BowlingRound round = new BowlingRound(startRoundNumber);
        round.addKnockDownPins(pinNumber);

        assertThat(round.isNextRound()).isEqualTo(expectedResult);
    }

    @Test
    void shouldReturnIsFinishOrNot() {
        BowlingRound round = new BowlingRound(BowlingRound.LAST_ROUND_NUM);

        round.addKnockDownPins(10);
        round.addKnockDownPins(10);
        assertThat(round.isFinish()).isFalse();
        round.addKnockDownPins(10);
        assertThat(round.isFinish()).isTrue();
    }


}

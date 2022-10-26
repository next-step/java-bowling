package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BallingRoundTest {

    @Test
    void shouldValidateBallingRound() {
        assertThatThrownBy(() -> new BallingRound(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new BallingRound(-1))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void shouldReturnTrueIfSameRound() {
        BallingRound round = new BallingRound(1);

        assertThat(round.isSameRound(new BallingRound(1))).isTrue();
        assertThat(round.isSameRound(new BallingRound(2))).isFalse();
    }

    @Test
    void shouldMoveRound() {
        BallingRound round = new BallingRound(1);

        assertThat(round.next().isSameRound(new BallingRound(2))).isTrue();
        assertThat(round.next().next().isSameRound(new BallingRound(3))).isTrue();

    }

    @Test
    void shouldAddPinsAndReturnWhetherNextRoundOrNot_whenNonStrike() {
        BallingRound round = new BallingRound(1);

        boolean isNextRoundA = round.addKnockDownPins(5);
        assertThat(isNextRoundA).isFalse();

        boolean isNextRoundB = round.addKnockDownPins(5);
        assertThat(isNextRoundB).isTrue();
        assertThat(round.getScores().getScores()).containsExactly(new Score(5), new Score(5));
    }

    @Test
    void shouldAddPinsAndReturnWhetherNextRoundOrNot_whenStrike() {
        BallingRound round = new BallingRound(1);

        boolean isNextRoundA = round.addKnockDownPins(10);
        assertThat(isNextRoundA).isTrue();
        assertThat(round.getScores().getScores()).containsExactly(new Score(10));
    }

    @Test
    void shouldReturnIsFinishOrNot() {
        BallingRound round = new BallingRound(10);

        round.addKnockDownPins(10);
        round.addKnockDownPins(10);
        assertThat(round.isFinish()).isFalse();
        round.addKnockDownPins(10);
        assertThat(round.isFinish()).isTrue();
    }


}

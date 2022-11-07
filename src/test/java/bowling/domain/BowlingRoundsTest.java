package bowling.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingRoundsTest {

    @Test
    void shouldFindRoundByPosition() {
        BowlingRounds rounds = new BowlingRounds();

        Optional<BowlingRound> resultA = rounds.findRoundByPosition(new Position(1));
        Optional<BowlingRound> resultB = rounds.findRoundByPosition(new Position(2));

        assertThat(resultA.isPresent()).isTrue();
        assertThat(resultB.isPresent()).isFalse();
    }

    @Test
    void shouldAddKnockDownPins() {
        BowlingRounds rounds = new BowlingRounds();

        rounds.addKnockDownPins(10);

        rounds.findRoundByPosition(new Position(1))
                .ifPresentOrElse((round) -> {
                            assertThat(round.getScores().containsAll(List.of(new Score(10)))).isTrue();
                            assertThat(rounds.currentRound().isSameRound(new BowlingRound(2))).isTrue();
                        },
                        Assertions::fail
                );
    }
}

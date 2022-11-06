package bowling.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingRoundsTest {

    @ParameterizedTest
    @CsvSource({"1,1,true", "2,1,false"})
    void shouldFindRoundByPosition(int position, int searchPosition, boolean isExist) {
        BowlingRounds rounds = new BowlingRounds(position);

        Optional<BowlingRound> result = rounds.findRoundByPosition(new Position(searchPosition));

        assertThat(result.isPresent()).isEqualTo(isExist);
    }

    @Test
    void shouldAddKnockDownPins() {
        BowlingRounds rounds = new BowlingRounds(2);

        rounds.addKnockDownPins(10);

        rounds.findRoundByPosition(new Position(2))
                .ifPresentOrElse((round) -> {
                            assertThat(round.getScores().containsAll(List.of(new Score(10)))).isTrue();
                            assertThat(rounds.currentRound().isSameRound(new BowlingRound(3))).isTrue();
                        },
                        Assertions::fail
                );
    }
}

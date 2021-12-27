package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.exception.MaximumRoundException;
import bowling.exception.MinimumRoundException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RoundTest {

    @ParameterizedTest
    @ValueSource(ints = 10)
    void finalRound(int round) {
        assertThat(new Round(round).isFinalRound()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void notFinalRound(int round) {
        assertThat(new Round(round).isFinalRound()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void create(int round) {
        assertThat(new Round(round)).isInstanceOf(Round.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void minimumRoundException(int round) {
        assertThatExceptionOfType(MinimumRoundException.class)
            .isThrownBy(() -> new Round(round));
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 12})
    void maximumRoundException(int round) {
        assertThatExceptionOfType(MaximumRoundException.class)
            .isThrownBy(() -> new Round(round));
    }

}

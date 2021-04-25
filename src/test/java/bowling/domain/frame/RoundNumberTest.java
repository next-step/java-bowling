package bowling.domain.frame;

import bowling.exception.InvalidRoundNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RoundNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("라운드 번호를 생성할 수 있다.")
    void create(final int roundNumberSource) {
        // given
        // when
        final RoundNumber roundNumber = new RoundNumber(roundNumberSource);

        // then
        assertThat(roundNumber).isEqualTo(new RoundNumber(roundNumberSource));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    @DisplayName("라운드 번호는 0보다 크고 11보다 작아야 한다.")
    void invalidRoundNumber(final int invalidRoundNumberSource) {
        assertThatThrownBy(() -> new RoundNumber(invalidRoundNumberSource))
                .isInstanceOf(InvalidRoundNumberException.class)
                .hasMessage(InvalidRoundNumberException.INVALID_ROUND_NUMBER + invalidRoundNumberSource);
    }

    @Test
    @DisplayName("첫번째 라운드 번호를 생성한다.")
    void firstRoundNumber() {
        // given
        final int firstRoundNumber = 1;

        // when
        final RoundNumber roundNumber = RoundNumber.firstRoundNumber();

        // then
        assertThat(roundNumber).isEqualTo(new RoundNumber(firstRoundNumber));
    }

    @Test
    @DisplayName("다음 라운드 번호를 생성한다.")
    void nextRoundNumber() {
        // given
        final RoundNumber firstRoundNumber = RoundNumber.firstRoundNumber();

        // when
        final RoundNumber nextRoundNumber = firstRoundNumber.nextRoundNumber();

        // then
        assertThat(nextRoundNumber).isEqualTo(new RoundNumber(2));
    }
}

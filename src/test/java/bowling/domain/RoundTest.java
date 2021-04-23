package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RoundTest {

    @DisplayName("범위를 초과하는 라운드를 생성하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    void valid_round(int round) {
        assertThatThrownBy(() -> {
            Round.from(round);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("next()메서드를 호출하면 다음 라운드가 생성된다.")
    @Test
    void next() {
        // given
        int roundNum = 1;

        // when
        Round nextRound = Round.from(roundNum).next();

        // then
        assertThat(nextRound).isEqualTo(Round.from(roundNum + 1));
    }
}
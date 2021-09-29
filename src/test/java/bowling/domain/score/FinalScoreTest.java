package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalScoreTest {

    @Test
    @DisplayName("FinalScore equals, hashCode 재정의 테스트")
    void finalScoreEqualsHashCodeTest() {

        // given
        Pin pin = Pin.of(5);

        Score expected = FinalScore.of(pin, pin, pin);

        // when
        Score result = FinalScore.of(pin, pin, pin);

        // then
        assertThat(result)
            .isEqualTo(expected)
            .hasSameHashCodeAs(expected);
    }

}
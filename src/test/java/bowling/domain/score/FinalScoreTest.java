package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalScoreTest {

    @Test
    @DisplayName("빈 FinalScore를 생성할 수 있다.")
    void createEmptyScoreTest() {

        // given
        Score expected = FinalScore.of(null, null, null);

        // when
        Score result = FinalScore.emptyScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("첫번째 pin을 저장할 수 있다.")
    void saveFirstPinTest() {

        // given
        Pin first = Pin.of(3);
        Score input = FinalScore.emptyScore();

        Score expected = FinalScore.of(first, null, null);

        // when
        Score result = input.saveNextPin(first);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("두번째 pin을 저장할 수 있다.")
    void saveSecondPinTest() {

        // given
        Pin first = Pin.of(3);
        Pin second = Pin.of(4);
        Score input = FinalScore.of(first, null, null);

        Score expected = FinalScore.of(first, second, null);

        // when
        Score result = input.saveNextPin(second);

        // then
        assertThat(result).isEqualTo(expected);
    }

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
package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.exception.score.BonusPinValueException;
import bowling.exception.score.PinSaveExcessException;
import bowling.exception.score.SecondPinValueException;
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
    @DisplayName("첫번째 핀이 Strike가 아니면 남는 핀만 second pin으로 들어올 수 있다.")
    void saveFailSecondPinByRemainTest() {

        // given
        Pin first = Pin.of(5);
        Pin second = Pin.of(6);
        Score input = FinalScore.of(first, null, null);

        // when & then
        assertThatExceptionOfType(SecondPinValueException.class)
            .isThrownBy(() -> input.saveNextPin(second))
            .withMessageMatching("두번째 핀은 남은 핀만 저장할 수 있습니다.");
    }

    @Test
    @DisplayName("첫번째 핀이 Strike면 두번째 핀은 자유롭게 들어올 수 있다.")
    void saveSecondPinByStrikeTest() {

        // given
        Pin first = Pin.of(10);
        Pin second = Pin.of(7);
        Score input = FinalScore.of(first, null, null);

        Score expected = FinalScore.of(first, second, null);

        // when
        Score result = input.saveNextPin(second);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("두번째 pin이 spare면 bonus pin을 저장할 수 있다.")
    void saveBonusPinTest() {

        // given
        Pin pin = Pin.of(5);
        Score input = FinalScore.of(pin, pin, null);

        Score expected = FinalScore.of(pin, pin, pin);

        // when
        Score result = input.saveNextPin(pin);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("두번째 pin이 strike면 bonus pin을 저장할 수 있다.")
    void saveBonusPinByStrikeTest() {

        // given
        Pin pin = Pin.of(10);
        Score input = FinalScore.of(pin, pin, null);

        Score expected = FinalScore.of(pin, pin, pin);

        // when
        Score result = input.saveNextPin(pin);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("두번째 핀이 spare나 strike가 아니면 bonus pin을 저장할 수 없다.")
    void saveFailBonusPinTest() {

        // given
        Pin pin = Pin.of(3);
        Score input = FinalScore.of(pin, pin, null);

        // when & then
        assertThatExceptionOfType(BonusPinValueException.class)
            .isThrownBy(() -> input.saveNextPin(pin))
            .withMessageMatching("보너스 핀은 스페어나 스트라이크 후 칠 수 있습니다.");
    }

    @Test
    @DisplayName("pin이 다찼을 때 더이상 저장할 수 없다.")
    void saveFailByFullScoreTest() {

        // given
        Pin pin = Pin.of(10);
        Score input = FinalScore.of(pin, pin, pin);

        // when & then
        assertThatExceptionOfType(PinSaveExcessException.class)
            .isThrownBy(() -> input.saveNextPin(pin))
            .withMessageMatching("현재 Score가 다 차있어 저장할 수 없습니다.");
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
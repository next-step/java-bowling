package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.exception.score.SecondPinValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalScoreTest {

    @Test
    @DisplayName("빈 socre를 생성할 수 있다.")
    void createEmptyScoreTest() {

        // given
        Score expected = NormalScore.emptyScore();

        // when
        Score result = NormalScore.emptyScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("첫번째 pin이 비어있다면 저장할 수 있다.")
    void saveFirstPinTest() {

        // given
        Pin first = Pin.of(3);
        Score input = NormalScore.emptyScore();

        Score expected = NormalScore.of(first, null);

        // when
        Score result = input.saveNextPin(first);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("두번째 pin이 비어있다면 저장할 수 있다.")
    void saveSecondPinTest() {

        // given
        Pin first = Pin.of(3);
        Pin second = Pin.of(4);
        Score input = NormalScore.of(first, null);

        Score expected = NormalScore.of(first, second);

        // when
        Score result = input.saveNextPin(second);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("첫번째 pin이 스트라이크라면 두번째 핀을 저장할 수 없다.")
    void saveSecondPinFailByStrikeTest() {

        // given
        Pin first = Pin.of(10);
        Pin second = Pin.of(3);
        Score input = NormalScore.of(first, null);

        // when & then
        assertThatExceptionOfType(SecondPinValueException.class)
            .isThrownBy(() -> input.saveNextPin(second))
            .withMessageMatching("두번째 핀은 남은 핀만 저장할 수 있습니다.");
    }

    @Test
    @DisplayName("첫번째 pin을 치고 남은 핀 이상으로 두번째 핀을 저장할 수 없다.")
    void saveSecondPinFailByRemainTest() {

        // given
        Pin first = Pin.of(5);
        Pin second = Pin.of(6);
        Score input = NormalScore.of(first, null);

        // when & then
        assertThatExceptionOfType(SecondPinValueException.class)
            .isThrownBy(() -> input.saveNextPin(second))
            .withMessageMatching("두번째 핀은 남은 핀만 저장할 수 있습니다.");
    }

}
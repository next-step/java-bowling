package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.exception.Pin.PinSecondValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalScoreTest {

    @Test
    @DisplayName("빈 NormalScore 보드를 생성할 수 있다.")
    void createEmptyNormalScoreTest() {

        // when
        NormalScore result = NormalScore.empty();

        // then
        assertThat(result).isInstanceOf(NormalScore.class);
    }

    @Test
    @DisplayName("첫번째 핀을 생성할 수 있다.")
    void createFirstPinTest() {

        // given
        Pin first = Pin.of(5);

        // when
        NormalScore result = NormalScore.ofFirst(first);

        // then
        assertThat(result).isInstanceOf(NormalScore.class);
    }

    @Test
    @DisplayName("두번째 핀을 생성할 수 있다.")
    void createSecondPinTest() {

        // given
        Pin first = Pin.of(5);
        NormalScore firstScore = NormalScore.ofFirst(first);
        Pin second = Pin.of(3);

        // when
        NormalScore result = firstScore.createSecondPin(second);

        // then
        assertThat(result).isInstanceOf(NormalScore.class);
    }

    @Test
    @DisplayName("첫번째 핀이 스트라이크인 경우 두번째 핀을 생성할 수 없다.")
    void createSecondExceptionTest() {

        // given
        Pin first = Pin.of(10);
        NormalScore firstScore = NormalScore.ofFirst(first);
        Pin second = Pin.of(3);

        // when & then
        assertThatExceptionOfType(PinSecondValueException.class)
            .isThrownBy(() -> firstScore.createSecondPin(second))
            .withMessageMatching("첫번째 핀이 쓰러뜨리고 남은 핀 개수만 저장할 수 있다.");
    }

}
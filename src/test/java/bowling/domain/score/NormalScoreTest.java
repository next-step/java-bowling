package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bowling.exception.score.PinSaveExcessException;
import bowling.exception.score.PinSecondValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalScoreTest {

    @Test
    @DisplayName("빈 NormalScore 보드를 생성할 수 있다.")
    void createEmptyNormalScoreTest() {

        // when
        Score result = NormalScore.empty();

        // then
        assertThat(result).isInstanceOf(NormalScore.class);
    }

    @Test
    @DisplayName("첫번째 핀을 생성할 수 있다.")
    void createFirstPinTest() {

        // given
        Score start = NormalScore.empty();
        Pin first = Pin.of(5);

        // when
        Score result = start.nextPin(first);

        // then
        assertThat(result).isInstanceOf(NormalScore.class);
    }

    @Test
    @DisplayName("두번째 핀을 생성할 수 있다.")
    void createSecondPinTest() {

        // given
        Score start = NormalScore.empty();
        Pin first = Pin.of(5);
        Score firstScore = start.nextPin(first);
        Pin second = Pin.of(3);

        // when
        Score result = firstScore.nextPin(second);

        // then
        assertThat(result).isInstanceOf(NormalScore.class);
    }

    @Test
    @DisplayName("첫번째 핀이 스트라이크인 경우 두번째 핀을 생성할 수 없다.")
    void createSecondExceptionTest() {

        // given
        Score start = NormalScore.empty();
        Pin first = Pin.of(10);
        Score firstScore = start.nextPin(first);
        Pin second = Pin.of(3);

        // when & then
        assertThatExceptionOfType(PinSecondValueException.class)
            .isThrownBy(() -> firstScore.nextPin(second))
            .withMessageMatching("첫번째 핀이 쓰러뜨리고 남은 핀 개수만 저장할 수 있다.");
    }

    @Test
    @DisplayName("첫번째 핀이 쓰러뜨리고 남은 핀보다 많은 핀이 두번째 들어올 수 없다.")
    void createSecondExceptionRangeTest() {

        // given
        Score start = NormalScore.empty();
        Pin first = Pin.of(9);
        Score firstScore = start.nextPin(first);
        Pin second = Pin.of(3);

        // when & then
        assertThatExceptionOfType(PinSecondValueException.class)
            .isThrownBy(() -> firstScore.nextPin(second))
            .withMessageMatching("첫번째 핀이 쓰러뜨리고 남은 핀 개수만 저장할 수 있다.");
    }


    @Test
    @DisplayName("첫번째 핀이 비어있다면 isNext는 true여야 한다.")
    void isNextFirstNullTest() {

        // given
        Score start = NormalScore.empty();

        // when
        boolean result = start.isNext();

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("첫번째핀이 비어있지 않지만 strike가 아니라면 isNext는 true여야 한다.")
    void isNextNotStrikeTest() {

        // given
        Score start = NormalScore.empty();
        Pin startPin = Pin.of(3);
        Score next = start.nextPin(startPin);

        // when
        boolean result = next.isNext();

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("현재 스코어에 pin이 전부 저장되어있을때 pin정보가 더 들어오면 Exception이 발생해야 한다.")
    void savePinExceptionTest() {

        // given
        Score score = NormalScore.empty();
        score = score.nextPin(Pin.of(5));
        score = score.nextPin(Pin.of(5));

        // when & then
        Score result = score;
        assertThatExceptionOfType(PinSaveExcessException.class)
            .isThrownBy(() -> result.nextPin(Pin.of(5)))
            .withMessageMatching("현재 Score가 다 차있을 때 더이상 Pin을 저장할 수 없다.");
    }

}
package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bowling.exception.score.PinBonusException;
import bowling.exception.score.PinSaveExcessException;
import bowling.exception.score.PinSecondValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalScoreTest {

    @Test
    @DisplayName("빈 Score 생성 테스트")
    void createEmptyScoreTest() {

        // when
        Score result = FinalScore.empty();

        // then
        assertThat(result).isInstanceOf(FinalScore.class);
    }

    @Test
    @DisplayName("첫번째 핀을 생성할 수 있다.")
    void createFirstPinTest() {

        // given
        Score start = FinalScore.empty();
        Pin first = Pin.of(5);

        // when
        Score result = start.nextPin(first);

        // then
        assertThat(result).isInstanceOf(FinalScore.class);
    }

    @Test
    @DisplayName("두번째 핀을 생성할 수 있다.")
    void createSecondPinTest() {

        // given
        Score start = FinalScore.empty();
        Pin first = Pin.of(5);
        Score firstScore = start.nextPin(first);
        Pin second = Pin.of(3);

        // when
        Score result = firstScore.nextPin(second);

        // then
        assertThat(result).isInstanceOf(FinalScore.class);
    }

    @Test
    @DisplayName("첫번째 핀이 쓰러뜨리고 남을 경우 남은 핀보다 많은 핀이 두번째 들어올 수 없다.")
    void createSecondExceptionRangeTest() {

        // given
        Score start = FinalScore.empty();
        Pin first = Pin.of(9);
        Score firstScore = start.nextPin(first);
        Pin second = Pin.of(3);

        // when & then
        assertThatExceptionOfType(PinSecondValueException.class)
            .isThrownBy(() -> firstScore.nextPin(second))
            .withMessageMatching("첫번째 핀이 쓰러뜨리고 남은 핀 개수만 저장할 수 있다.");
    }

    @Test
    @DisplayName("첫번째 핀이 스트라이크인 경우 두번째 핀은 10개 온전히 다 칠 수 있다.")
    void crerteSecondTenTest() {

        // given
        Score start = FinalScore.empty();
        Pin first = Pin.of(10);
        Score firstScore = start.nextPin(first);
        Pin second = Pin.of(10);

        // when
        Score result = firstScore.nextPin(second);

        // then
        assertThat(result).isInstanceOf(FinalScore.class);
    }

    @Test
    @DisplayName("보너스 핀을 생성할 수 있다.")
    void createBonusPinTest() {

        // given
        Score start = FinalScore.empty();
        Pin first = Pin.of(5);
        Score firstScore = start.nextPin(first);
        Pin second = Pin.of(5);
        Score secondScore = firstScore.nextPin(second);
        Pin bonus = Pin.of(3);

        // when
        Score result = secondScore.nextPin(bonus);

        // then
        assertThat(result).isInstanceOf(FinalScore.class);
    }

    @Test
    @DisplayName("두번째 핀이 스페어나 스트라이크가 아닌 경우 Exception이 발생해야 한다.")
    void createBonusPinExceptionByNotSpare() {

        // given
        Score start = FinalScore.empty();
        Pin first = Pin.of(5);
        Score firstScore = start.nextPin(first);
        Pin second = Pin.of(4);
        Score secondScore = firstScore.nextPin(second);
        Pin bonus = Pin.of(3);

        // when & then
        assertThatExceptionOfType(PinBonusException.class)
            .isThrownBy(() -> secondScore.nextPin(bonus))
            .withMessageMatching("보너스 핀은 스페어나 스트라이크 후 칠 수 있다.");
    }

    @Test
    @DisplayName("첫번째 핀이 비어있으면 isNext는 true여야 한다.")
    void isNextFirstNullTest() {

        // given
        Score start = FinalScore.empty();

        // when
        boolean result = start.isNext();

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("두번째 핀이 비어있으면 isNext는 true여야 한다.")
    void isNextSecondNullTest() {

        // given
        Score start = FinalScore.empty();
        Pin first = Pin.of(5);
        Score firstScore = start.nextPin(first);

        // when
        boolean result = firstScore.isNext();

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("첫번째 핀이 스트라이크고 두번째핀이 스트라이크일 때 isNext는 true여야 한다.")
    void isNextFirstAndSecondAllStrikeTest() {

        // given
        Score start = FinalScore.empty();
        Pin first = Pin.of(10);
        Score firstScore = start.nextPin(first);
        Pin second = Pin.of(10);
        Score secondScore = firstScore.nextPin(second);

        // when
        boolean result= secondScore.isNext();

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("두번째 핀이 스페어일 때 isNext는 true여야 한다.")
    void isNextSpareTest() {

        // given
        Score start = FinalScore.empty();
        Pin first = Pin.of(3);
        Score firstScore = start.nextPin(first);
        Pin second = Pin.of(7);
        Score secondScore = firstScore.nextPin(second);

        // when
        boolean result= secondScore.isNext();

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("현재 스코어에 pin이 전부 저장되어있을때 pin정보가 더 들어오면 Exception이 발생해야 한다.")
    void savePinExceptionTest() {

        // given
        Score score = FinalScore.empty();
        score = score.nextPin(Pin.of(5));
        score = score.nextPin(Pin.of(5));
        score = score.nextPin(Pin.of(5));

        // when & then
        Score result = score;
        assertThatExceptionOfType(PinSaveExcessException.class)
            .isThrownBy(() -> result.nextPin(Pin.of(5)))
            .withMessageMatching("현재 Score가 다 차있을 때 더이상 Pin을 저장할 수 없다.");
    }

}
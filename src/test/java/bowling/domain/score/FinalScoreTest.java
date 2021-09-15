package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.exception.Pin.PinBonusException;
import bowling.exception.Pin.PinSecondValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalScoreTest {

    @Test
    @DisplayName("빈 Score 생성 테스트")
    void createEmptyScoreTest() {

        // when
        FinalScore result = FinalScore.empty();

        // then
        assertThat(result).isInstanceOf(FinalScore.class);
    }

    @Test
    @DisplayName("첫번째 핀을 생성할 수 있다.")
    void createFirstPinTest() {

        // given
        FinalScore start = FinalScore.empty();
        Pin first = Pin.of(5);

        // when
        FinalScore result = start.createFirstPin(first);

        // then
        assertThat(result).isInstanceOf(FinalScore.class);
    }

    @Test
    @DisplayName("두번째 핀을 생성할 수 있다.")
    void createSecondPinTest() {

        // given
        FinalScore start = FinalScore.empty();
        Pin first = Pin.of(5);
        FinalScore firstScore = start.createFirstPin(first);
        Pin second = Pin.of(3);

        // when
        FinalScore result = firstScore.createNextPin(second);

        // then
        assertThat(result).isInstanceOf(FinalScore.class);
    }

    @Test
    @DisplayName("첫번째 핀이 쓰러뜨리고 남을 경우 남은 핀보다 많은 핀이 두번째 들어올 수 없다.")
    void createSecondExceptionRangeTest() {

        // given
        FinalScore start = FinalScore.empty();
        Pin first = Pin.of(9);
        FinalScore firstScore = start.createFirstPin(first);
        Pin second = Pin.of(3);

        // when & then
        assertThatExceptionOfType(PinSecondValueException.class)
            .isThrownBy(() -> firstScore.createNextPin(second))
            .withMessageMatching("첫번째 핀이 쓰러뜨리고 남은 핀 개수만 저장할 수 있다.");
    }

    @Test
    @DisplayName("첫번째 핀이 스트라이크인 경우 두번째 핀은 10개 온전히 다 칠 수 있다.")
    void crerteSecondTenTest() {

        // given
        FinalScore start = FinalScore.empty();
        Pin first = Pin.of(10);
        FinalScore firstScore = start.createFirstPin(first);
        Pin second = Pin.of(10);

        // when
        FinalScore result = firstScore.createNextPin(second);

        // then
        assertThat(result).isInstanceOf(FinalScore.class);
    }

    @Test
    @DisplayName("보너스 핀을 생성할 수 있다.")
    void createBonusPinTest() {

        // given
        FinalScore start = FinalScore.empty();
        Pin first = Pin.of(5);
        FinalScore firstScore = start.createFirstPin(first);
        Pin second = Pin.of(5);
        FinalScore secondScore = firstScore.createNextPin(second);
        Pin bonus = Pin.of(3);

        // when
        FinalScore result = secondScore.createNextPin(bonus);

        // then
        assertThat(result).isInstanceOf(FinalScore.class);
    }

    @Test
    @DisplayName("두번째 핀이 스페어나 스트라이크가 아닌 경우 Exception이 발생해야 한다.")
    void createBonusPinExceptionByNotSpare() {

        // given
        FinalScore start = FinalScore.empty();
        Pin first = Pin.of(5);
        FinalScore firstScore = start.createFirstPin(first);
        Pin second = Pin.of(4);
        FinalScore secondScore = firstScore.createNextPin(second);
        Pin bonus = Pin.of(3);

        // when & then
        assertThatExceptionOfType(PinBonusException.class)
            .isThrownBy(() -> secondScore.createNextPin(bonus))
            .withMessageMatching("보너스 핀은 스페어나 스트라이크 후 칠 수 있다.");
    }

}
package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
        FinalScore result = firstScore.createSecondPin(second);

        // then
        assertThat(result).isInstanceOf(FinalScore.class);
    }

    @Test
    @DisplayName("첫번째 핀이 쓰러뜨리고 남을 경우 남은 핀보다 많은 핀이 두번째 들어올 수 없다.")
    void createSecondExceptionRangeTest() {

        // given
        NormalScore start = NormalScore.empty();
        Pin first = Pin.of(9);
        NormalScore firstScore = start.createFirstPin(first);
        Pin second = Pin.of(3);

        // when & then
        assertThatExceptionOfType(PinSecondValueException.class)
            .isThrownBy(() -> firstScore.createSecondPin(second))
            .withMessageMatching("첫번째 핀이 쓰러뜨리고 남은 핀 개수만 저장할 수 있다.");
    }


}
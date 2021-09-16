package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PitchTest {

    @DisplayName("0-10 사이의 숫자로 생성하지 않으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void validate(int countOfPins) {
        assertThatThrownBy(() -> new Pitch(countOfPins, Status.NUMBER)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("처음 투구할 때 10개를 쓰러트리면 Strike Pitch 타입이 된다.")
    @Test
    void firstPitch_strike() {
        assertThat(Pitch.firstPitch(10).isStatus(Status.STRIKE)).isTrue();
    }

    @DisplayName("이전 투구가 10이고 현재 투구가 10이면 스트라이크다.")
    @Test
    void second_strike() {
        assertThat(Pitch.firstPitch(10).pitch(10).isStatus(Status.STRIKE)).isTrue();
    }

    @DisplayName("이전투구가 0-10의 spare 일때 현재 투구가 10이면 스트라이크다.")
    @Test
    void third_strike() {
        assertThat(Pitch.firstPitch(0).pitch(10).pitch(10).isStatus(Status.STRIKE)).isTrue();
    }

    @DisplayName("처음 투구할 때 10개 이하를 쓰러트리면 Pitch 타입이 된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void firstPitch(int first) {
        assertThat(Pitch.firstPitch(first).isStatus(Status.NUMBER)).isTrue();
    }

    @DisplayName("스트라이크 투구의 값은 X 이다.")
    @Test
    void value_strike() {
        assertThat(Pitch.firstPitch(10).value()).isEqualTo("X");
    }

    @DisplayName("스페어 투구의 값은 /이다.")
    @Test
    void value_spare() {
        assertThat(new Pitch(2, Status.NUMBER).pitch(8).value()).isEqualTo("/");
    }

    @DisplayName("아무것도 아닌 투구의 값은 숫자(문자)이다.")
    @Test
    void value() {
        assertThat(new Pitch(1, Status.NUMBER).pitch(2).value()).isEqualTo("2");
    }

}
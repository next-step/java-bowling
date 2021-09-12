package bowling.domain.pitch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PitchTest {

    @DisplayName("0-10 사이의 숫자로 생성하지 않으면 에러가 발생한다.")
    @Test
    void validate() {
        assertThatThrownBy(() -> new Pitch(-1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Pitch(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("처음 투구할 때 10개를 쓰러트리면 Strike Pitch 타입이 된다.")
    @Test
    void firstPitch_strike() {
        assertThat(Pitch.firstPitch(10)).isExactlyInstanceOf(Strike.class);
    }

    @DisplayName("이전투구가 10이고 현재투구가 10이면 스트라이크다.")
    @Test
    void second_strike(){
        assertThat(new Pitch(10).pitch(10)).isExactlyInstanceOf(Strike.class);
    }

    @DisplayName("처음 투구할 때 10개 이하를 쓰러트리면 Pitch 타입이 된다.")
    @Test
    void firstPitch() {
        assertThat(Pitch.firstPitch(2)).isExactlyInstanceOf(Pitch.class);
        assertThat(Pitch.firstPitch(2)).isNotExactlyInstanceOf(Strike.class);
    }

    @DisplayName("스트라이크 투구의 값은 X 이다.")
    @Test
    void value_strike() {
        assertThat(Pitch.firstPitch(10).value()).isEqualTo("X");
    }

    @DisplayName("스페어 투구의 값은 /이다.")
    @Test
    void value_spare() {
        assertThat(new Pitch(2).pitch(8).value()).isEqualTo("/");
    }

    @DisplayName("아무것도 아닌 투구의 값은 숫자(문자)이다.")
    @Test
    void value() {
        assertThat(new Pitch(1).pitch(2).value()).isEqualTo("2");
    }

}
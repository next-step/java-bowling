package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PinsTest {

    @Test
    @DisplayName("핀 객체 생성")
    void createPin() {
        assertThat(Pins.from(3)).isInstanceOf(Pins.class);
    }

    @Test
    @DisplayName("떨어진 핀이 0개 밑일 경우 예외 처리")
    void exceptZero() {
        assertThrows(IllegalArgumentException.class,
                () -> Pins.from(-1));
    }

    @Test
    @DisplayName("핀이 10개 초과일 경우 예외 처리")
    void exceptTen() {
        assertThrows(IllegalArgumentException.class,
                () -> Pins.from(11));
    }

    @Test
    @DisplayName("스트라이크인지 확인")
    void isStrike() {
        Pins pins = Pins.from(10);
        assertThat(pins.isStrike()).isTrue();
    }

    @Test
    @DisplayName("스트라이크가 아님을 확인")
    void isNotStrike() {
        Pins pins = Pins.from(9);
        assertThat(pins.isStrike()).isFalse();
    }

    @Test
    @DisplayName("스페어인지 확인")
    void isSpare() {
        Pins pins1 = Pins.from(8);
        Pins pins2 = Pins.from(2);

        assertThat(pins1.isSpare(pins2)).isTrue();
    }

    @Test
    @DisplayName("스페어가 아님을 확인")
    void isNotSpare() {
        Pins pins1 = Pins.from(8);
        Pins pins2 = Pins.from(1);

        assertThat(pins1.isSpare(pins2)).isFalse();
    }

    @Test
    @DisplayName("점수 더하기 기능")
    void sumScore() {
        Score score = Score.of(5, 1);

        Pins pins = Pins.from(8);

        assertThat(pins.sumScore(score).getScore()).isEqualTo(13);
    }

}
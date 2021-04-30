package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PitchTest {
    @DisplayName("0개 이하의 핀을 쓰러트리는 경우 예외를 반환한다.")
    @Test
    void underZero() {
        assertThatThrownBy(() -> new Pitch(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("0개 이하의 핀을 쓰러트리는 경우 예외를 반환한다.")
    @Test
    void upTen() {
        assertThatThrownBy(() -> new Pitch(11)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스트라이크 투구")
    @Test
    void strike() {
        Pitch initPitch = new Pitch(0);
        Pitch firstPitch = initPitch.pitch(10, 0);

        assertThat(firstPitch.isStrike()).isTrue();
    }

    @DisplayName("스페어 투구")
    @Test
    void spare() {
        Pitch initPitch = new Pitch(0);
        Pitch firstPitch = initPitch.pitch(8, 0);
        Pitch secondPitch = firstPitch.pitch(2, 1);

        assertThat(secondPitch.isSpare()).isTrue();
    }

    @DisplayName("오픈 투구")
    @Test
    void open() {
        Pitch initPitch = new Pitch(0);
        Pitch firstPitch = initPitch.pitch(0, 0);
        Pitch secondPitch = firstPitch.pitch(0, 1);

        assertThat(secondPitch.isOpen()).isTrue();
    }
}

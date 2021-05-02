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
        Pitch initPitch = new Pitch(10);

        assertThat(initPitch.isStrike()).isTrue();
    }

    @DisplayName("스페어 투구")
    @Test
    void spare() {
        Pitch initPitch = new Pitch(8);

        assertThat(initPitch.isSpare(2)).isTrue();
    }
}

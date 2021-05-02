package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PitchesTest {
    @DisplayName("스트라이크의 종료 여부를 반환한다.")
    @Test
    void isEnd_strike() {
        Pitches pitches = new Pitches();
        pitches.pitch(10);
        assertThat(pitches.isEnd()).isTrue();
    }

    @DisplayName("스페어의 종료 여부를 반환한다.")
    @Test
    void isEnd_spare() {
        Pitches pitches = new Pitches();
        pitches.pitch(6);
        pitches.pitch(4);
        assertThat(pitches.isEnd()).isTrue();
    }

    @DisplayName("오픈프레임의 종료 여부를 반환한다.")
    @Test
    void isEnd_open() {
        Pitches pitches = new Pitches();
        pitches.pitch(4);
        pitches.pitch(3);
        assertThat(pitches.isEnd()).isTrue();
    }

    @DisplayName("스트라이크의 보너스 피치 여부를 반환한다.")
    @Test
    void hasBonusPitch_strike() {
        Pitches pitches = new Pitches();
        pitches.pitch(10);
        assertThat(pitches.hasBonusPitch()).isTrue();
    }

    @DisplayName("스페어의 보너스 피치 여부를 반환한다.")
    @Test
    void hasBonusPitch_spare() {
        Pitches pitches = new Pitches();
        pitches.pitch(8);
        pitches.pitch(2);
        assertThat(pitches.hasBonusPitch()).isTrue();
    }
}

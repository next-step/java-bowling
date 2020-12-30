package bowling.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PitchTest {

    @Test
    @DisplayName("프레임 점수 입력")
    void createFrame() {
        assertThat(Pitch.from(10)).isInstanceOf(Pitch.class);
    }

    @Test
    @DisplayName("점수가 10점을 초과할 경우 예외 처리")
    void exceptScoreLimit() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Pitch.from(11));
    }

    @Test
    @DisplayName("점수가 0점 아래인 경우 예외 처리")
    void exceptScoreBelowZero() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Pitch.from(-1));
    }

    @Test
    @DisplayName("프레임 투구 준비")
    void readyFrame() {
        Pitch pitch = Pitch.of(0, 2);

        Pitch newPitch = pitch.bowl(8);

        assertThat(newPitch).isEqualTo(Pitch.of(8, 1));
    }

}
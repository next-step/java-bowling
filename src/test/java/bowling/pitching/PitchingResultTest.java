package bowling.pitching;

import bowling.global.exception.CanNotPitchingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PitchingResultTest {

    private Pitching pitching;
    private PitchingResult pitchResult;

    @Test
    @DisplayName("첫번째 투구시 남은 Pin이 0개라면 Strike")
    void pitchingResultIsStrike() {
        pitching = Pitching.pitch("10", 0);
        pitchResult = PitchingResult.from(pitching, 10, 2);
        assertThat(pitchResult.getPitchResultState()).isEqualTo("X");
    }

    @Test
    @DisplayName("두번째 투구시 남은 Pin이 0개라면 Spare")
    void pitchingResultIsSpare() {
        pitching = Pitching.pitch("10", 1);
        pitchResult = PitchingResult.from(pitching, 10, 2);
        assertThat(pitchResult.getPitchResultState()).isEqualTo("/");
    }

    @Test
    @DisplayName("첫번째 투구시 쓰러트린 Pin이 없으면 Gutter")
    void pitchingResultIsGutter1() {
        pitching = Pitching.pitch("0", 0);
        pitchResult = PitchingResult.from(pitching, 10, 2);
        assertThat(pitchResult.getPitchResultState()).isEqualTo("-");
    }

    @Test
    @DisplayName("두번째 투구시 쓰러트린 Pin이 없으면 Gutter")
    void pitchingResultIsGutter2() {
        pitching = Pitching.pitch("0", 1);
        pitchResult = PitchingResult.from(pitching, 10, 2);
        assertThat(pitchResult.getPitchResultState()).isEqualTo("-");
    }

    @Test
    @DisplayName("첫번째 투구시 쓰러트린 Pin의 값 확인")
    void pitchingResult1() {
        pitching = Pitching.pitch("5", 0);
        pitchResult = PitchingResult.from(pitching, 10, 2);
        assertThat(pitchResult.getPitchResultState()).isEqualTo("5");
    }

    @Test
    @DisplayName("두번째 투구시 쓰러트린 Pin의 값 확인")
    void pitchingResult2() {
        pitching = Pitching.pitch("5", 1);
        pitchResult = PitchingResult.from(pitching, 10, 2);
        assertThat(pitchResult.getPitchResultState()).isEqualTo("5");
    }

    @Test
    @DisplayName("투구 횟수가 지정된 수를 넘을 경우 Exception 발생")
    void pitchingCountException() {
        assertThatExceptionOfType(CanNotPitchingException.class)
                .isThrownBy(() -> {
                    pitching = Pitching.pitch("5", 2);
                    pitchResult = PitchingResult.from(pitching, 10, 2);
                });
    }

}

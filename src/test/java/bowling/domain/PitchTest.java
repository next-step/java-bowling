package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PitchTest {

    @Test
    @DisplayName("한 피치의 점수가 10을 넘으면 IllegalArgumentException이 발생한다.")
    void pitchException() {
        int score = 11;
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Pitch(score))
                .withMessage("한 핏치의 점수가 10을 넘을 수 없습니다. 점수 : " + score);
    }
}
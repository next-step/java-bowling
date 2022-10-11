package bowling.step2.domain.state;

import bowling.step2.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class StrikeTest {
    public static final Strike STRIKE = new Strike();
    
    @Test
    @DisplayName("투구 시 예외")
    void bowl_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> STRIKE.bowl(1))
                .withMessage("더이상 투구할 수 없습니다.");
    }
    
    @Test
    @DisplayName("프레임 종료")
    void frame_finish() {
        assertThat(STRIKE.isFinished()).isTrue();
    }
    
    @Test
    @DisplayName("점수 확인")
    void display() {
        assertThat(STRIKE.getScores()).isEqualTo(List.of(new Score(10)));
    }
}
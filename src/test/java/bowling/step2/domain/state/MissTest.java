package bowling.step2.domain.state;

import bowling.step2.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MissTest {
    public Miss miss;
    
    @BeforeEach
    void setUp() {
        miss = new Miss(new Score(2), new Score(3));
    }
    
    @Test
    @DisplayName("투구 시 예외")
    void bowl_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> miss.bowl(1))
                .withMessage("더이상 투구할 수 없습니다.");
    }
    
    @Test
    @DisplayName("프레임 종료")
    void frame_finish() {
        assertThat(miss.isFinished()).isTrue();
    }
    
    @Test
    @DisplayName("display 반환 값 확인")
    void display() {
        assertThat(miss.display()).isEqualTo("2|3");
    }
}